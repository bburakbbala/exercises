/**
*
* @author Burak Bala burak.bala@ogr.sakarya.edu.tr
* @since 06.04.2021
* <p>
* AnalyzeCppFile analyze cpp class and public methods of class
* </p>
*/

package g181210058;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeCppFile {
    private final List<CppClass> cppClasses;
    private final CppBaseClassMap cppBaseClassMap;
    private String fileContent;

    public AnalyzeCppFile(String filePath) throws IOException {
        readFile(filePath);
        cppClasses = new ArrayList<>();
        cppBaseClassMap = new CppBaseClassMap();
        analyze();
        print();
    }
    private void readFile(String filePath) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            String line;

            while ((line = buffer.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            // delete the last new line separator
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            fileContent = stringBuilder.toString()
                    .replaceAll("\\s{2,}", " ")
                    .replaceAll("//.*?(\\r?\\n|$)", "") // delete line comments
                    .replaceAll("/\\*(\\*(?!/)|[^*])*\\*/", " ") // delete block comments
                    .replaceAll("const|static|inline", "")
                    .replaceAll("using\\s*enum\\s*\\w*\\s*;", "") // removes using enum \\w;
                    .replaceAll("^typedef\\s*struct\\w*\\s*\\w*;$", "")
                    .replaceAll("[\n\t\r]", " ") // delete line feeds
                    .replaceAll("(enum\\s+class|enum|struct|typedef\\s*struct)[^{)]*\\{(.*?)}\\s*;", " ") // remove structs and enums (enum and enum class)
                    .replaceAll("(if|switch|while|for|else)\\s*\\(([^;]*);", " ")
                    .replaceAll("int\s*main[^{]", " ")
                    .replaceAll("\\s{2,}", " "); // delete spaces that more than one " +", " " -> also valid
        }
    }

    private void analyze() {
        Pattern classPattern = Pattern.compile("class\\b\\s\\b([A-Za-z_][A-Za-z_0-9]*)\\b\\s*($)?(|:\\s*($)?"
                + "(public|private|protected)\\s*($)?\\b[^{]*\\s*)\\s*($)?\\{(.*?)}\\s*;");
        Pattern publicPart = Pattern.compile("public\\s*:\\s*(.*?)((?=(private|protected))|}\\s*;)");
        Pattern methodPattern = Pattern.compile("((?:[\\w*~_&<>]*?\\s*(\\*|&|\\[|\\])*))?" +
                "(operator\\s?[\\+\\-\\*\\/\\%\\^\\&\\|\\~\\!\\=\\<\\>\\^\\|\\?\\,\\(\\)\\[\\]]*\\s*" +
                "[\\+\\-\\*\\/\\%\\^\\&\\|\\~\\!\\=\\<\\>\\^\\|\\?\\,\\(\\)\\[\\]]*|" +
                "[a-zA-Z_~][\\w~_]+\\s*)\\s*\\(([^);]*)\\)[^{;]*(?:^[^\\r\\n{]*;?[\\s]+)*\\{");
        Pattern arrayParameterPattern = Pattern.compile("\\*|\\&");
        Matcher classMatcher = classPattern.matcher(fileContent);
        Matcher publicPartMatcher;
        Matcher methodMatcher;
        Matcher arrayParameterMatcher;

        String temp;
        String[] temp2;
        String[] base;
        String parameters;
        String returnType;
        int numberOfClasses = 0;
        while (classMatcher.find()) {
            cppClasses.add(new CppClass(classMatcher.group(1))); // class name

            // base class part
            if (classMatcher.group(3) != null) {
                temp = classMatcher.group(3).replaceAll("(public|protected|private)", "").replaceAll("[:,\\s]", ",");
                base = temp.split(",");
                for (String item : base)
                    cppBaseClassMap.add(item);
            }

            // class body part
            if (!classMatcher.group(8).isEmpty()) {
                publicPartMatcher = publicPart.matcher(classMatcher.group(0)); // find class public part
                while (publicPartMatcher.find()) {
                    methodMatcher = methodPattern.matcher(publicPartMatcher.group(1)
                            .replaceAll("([a-zA-Z_][\\w]*(\\.|->))?[a-zA-Z_][\\w]*\\.[\\w]*\\(.*?\\)", " ") // remove \w*() parts
                            .replaceAll("\\w*\\(\\)(?=,)", "")); // remove .() ->() parts
                    while (methodMatcher.find()) {
                        int param = 0;
                        // group 1 return type
                        // group 3 method name
                        // group 4 parameters
                        if (methodMatcher.group(4).contains(",")) {
                            parameters = methodMatcher.group(4)
                                    .replaceAll("([^&*\\s]*[^&*\\s,]*),|([^&*,\\[\\]\\s]*\\s?\\=\\s?\\w*\\s?,)", ",") // delete parameter names excepts last one
                                    .replaceAll("([^\\*\\&]*\\=\\s?)?([\\w]*)$", "").trim() // delete last parameter name
                                    .replaceAll("\\s+", "") // delete whitespaces
                                    .replaceAll(",", ", ");
                            param = parameters.split(",").length;
                        } else if (!methodMatcher.group(4).contains(",") && !methodMatcher.group(4).isEmpty()) { // one parameter
                            parameters = methodMatcher.group(4)
                                    .replaceAll("[^*&\\[\\]\\s]*$", ""); // delete parameter names
                            if (parameters.contains("[")) {
                                temp2 = parameters.split(" ");
                                temp2[1] = temp2[1].replaceAll("(\\[\\d*\\]|\\[ *\\])", "*"); // delete [] -> inside of brackets
                                StringBuilder arrayParameter = new StringBuilder();
                                arrayParameterMatcher = arrayParameterPattern.matcher(temp2[1]);
                                while (arrayParameterMatcher.find()) {
                                    arrayParameter.append(arrayParameterMatcher.group(0));
                                }
                                parameters = (temp2[0].replaceAll("\\s*", "") + arrayParameter.toString())
                                        .replaceAll("(?=\\*&\\[\\]\\s)*\\s", "");
                            } else {
                                parameters = parameters.replaceAll("(?=\\*&\\[\\]\\s)*\\s", ""); // delete whitespaces
                            }
                            param = 1;
                        } else { // no parameter
                            parameters = "";
                        }
                        
                        // return type
                        if (methodMatcher.group(3).contains("~"))
                            returnType = "void";
                        else if (!methodMatcher.group(1).replaceAll("\\s", "").isEmpty() && methodMatcher.group(1) != null)
                            returnType = methodMatcher.group(1).replaceAll("\\s", "");
                        else
                            returnType = "Object Address";

                        cppClasses.get(numberOfClasses)
                                .addMethod(new CppMethod(param, methodMatcher.group(3).replaceAll("\\s", ""), parameters, returnType));
                    }
                }
                publicPartMatcher.reset();
            }
            numberOfClasses++;
        }
    }

    public final void print() {
        for (CppClass cppClass : cppClasses) {
            if (cppClass != null) cppClass.print();
        }
        if (!cppBaseClassMap.isEmpty()) cppBaseClassMap.print();
    }
}

// Java Program to Illustrate Unary - Operator
 
// Importing required classes
import java.io.*;
 
// Main class
class GFG {
 
    // Main driver method
    public static void main(String[] args)
    {
        // Declaring a custom variable
        int n1 = +20;
 
        // Printing the above variable
        System.out.println("Number = " + n1);
 
        // Performing unary operation
        n1 = +n1;
        n1 = + n1;
 
        // Printing the above result number
        // after unary operation
        System.out.println("Result = " + n1);
    }
}

// Java Program to Illustrate Unary - Operator

// Importing required classes
import java.io.*;

// Main class
class GFG {

	// Main driver method
	public static void main(String[] args)
	{
		// Declaring a custom variable
		int n1 = 20;

		// Printing the above variable
		System.out.println("Number = " + n1);

		// Performing unary operation
		n1 = -n1;
		n1 = - n1;

		// Printing the above result number
		// after unary operation
		System.out.println("Result = " + n1);
	}
}

// Java Program to Illustrate Unary NOT Operator
 
// Importing required classes
import java.io.*;
 
// Main class
class GFG {
 
    // Main driver method
    public static void main(String[] args)
    {
        // Initializing variables
        boolean cond = true;
        int a = 10, b = 1;
 
        // Displaying values stored in above variables
        System.out.println("Cond is: " + cond);
        System.out.println("Var1 = " + a);
        System.out.println("Var2 = " + b);
 
        // Displaying values stored in above variables
        // after applying unary NOT operator
        System.out.println("Now cond is: " + !cond);
        System.out.println("!(a < b) = " + !(a < b));
        System.out.println("!(a > b) = " + !(a > b));
    }
}


num = 5
++num = 6

num = 5
num++ = 6

num = 5
num-- = 5 // Value will be decremented before execution of next statement.

num = 5
--num = 5 //output is 5, value is decremented before execution of next statement


// Java program to Illustrate Unary
// Bitwise Complement Operator

// Importing required classes
import java.io.*;

// Main class
class GFG {

	// Main driver method
	public static void main(String[] args)
	{
		// Declaring a variable
		int n1 = 6, n2 = -2;

		// Printing numbers on console
		System.out.println("First Number = " + n1);
		System.out.println("Second Number = " + n2);

		// Printing numbers on console after
		// performing bitwise complement
		System.out.println(
			n1 + "'s bitwise complement = " + ~n1);
		System.out.println(
			n2 + "'s bitwise complement = " + ~n2);
	}
}


import java.util.Scanner;

public class UnaryOperators {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		// fro user inputs here is the code.

		// System.out.print("Enter a number: ");
		// int num = sc.nextInt();
		int num = 10;

		int result = +num;
		System.out.println(
			"The value of result after unary plus is: "
			+ result);

		result = -num;
		System.out.println(
			"The value of result after unary minus is: "
			+ result);

		result = ++num;
		System.out.println(
			"The value of result after pre-increment is: "
			+ result);

		result = num++;
		System.out.println(
			"The value of result after post-increment is: "
			+ result);

		result = --num;
		System.out.println(
			"The value of result after pre-decrement is: "
			+ result);

		result = num--;
		System.out.println(
			"The value of result after post-decrement is: "
			+ result);
	}
}

// Java Program to Illustrate Arithmetic Operators
 
import java.util.*;
 
// Class 1
class A {
 
    // Main driver method
    public static void main(String args[])
    {
 
        int a = 10, b = 4, res;
 
        // printing a and b
        System.out.println("a is " + a + " and b is " + b);
 
        res = a + b; // addition
        System.out.println("a+b is " + res);
 
        res = a - b; // subtraction
        System.out.println("a-b is " + res);
 
        res = a * b; // multiplication
        System.out.println("a*b is " + res);
 
        res = a / b; // division
        System.out.println("a/b is " + res);
 
        res = a % b; // modulus
        System.out.println("a%b is " + res);
    }
}

public class BitwiseOperatorDemo { 
public static void main(String[] args) 
    { 
        //Initial values 
        int x = 5; 
        int y = 6;
        int z = 2;
 
        // binary value of x=5 is 0101
        // binary value of y=6 is 0110
        // binary value of z = 2 0010
 
        // bitwise and  0101& 0110=0100 = 4 
        System.out.println("x&y = " + (x & y)); 
 
        // bitwise or 0101 | 0110 = 0111 = 7 
        System.out.println("x|y = " + (x | y)); 
 
        // bitwise xor  0101 ^ 0110 = 0011 = 3 
        System.out.println("x^y = " + (x ^ y));
 
    } 
}
