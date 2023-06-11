public class LogicalOperators {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
 
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("a && b: " + (a && b));
        System.out.println("a || b: " + (a || b));
        System.out.println("!a: " + !a);
        System.out.println("!b: " + !b);
    }
}

//Java code to illustrate
//logical NOT operator
import java.io.*;

class Logical {
 public static void main(String[] args)
 {
     // initializing variables
     int a = 10, b = 1;

     // Displaying a, b, c
     System.out.println("Var1 = " + a);
     System.out.println("Var2 = " + b);

     // Using logical NOT operator
     System.out.println("!(a < b) = " + !(a < b));
     System.out.println("!(a > b) = " + !(a > b));
 }
}

//Java code to illustrate
//logical OR operator

import java.io.*;

class Logical {
 public static void main(String[] args)
 {
     // initializing variables
     int a = 10, b = 1, c = 10, d = 30;

     // Displaying a, b, c
     System.out.println("Var1 = " + a);
     System.out.println("Var2 = " + b);
     System.out.println("Var3 = " + c);
     System.out.println("Var4 = " + d);

     // using logical OR to verify
     // two constraints
     if (a > b || c == d)
         System.out.println(
             "One or both + the conditions are true");
     else
         System.out.println(
             "Both the + conditions are false");
 }
}