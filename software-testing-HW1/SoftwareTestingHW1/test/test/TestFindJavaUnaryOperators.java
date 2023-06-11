package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import com.github.javafaker.*;

import analyze.FindJavaUnaryOperators;
import analyze.IFindJavaUnaryOperators;

@DisplayName("Test for Java Unary Operators")
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestFindJavaUnaryOperators {
	private IFindJavaUnaryOperators unary;

	@BeforeEach
	void setUp() throws Exception {
		unary = new FindJavaUnaryOperators();
	}

	@Test
	@DisplayName("Find Postive Sign Operators")
	void testFindPositiveSignOperators() {
		String content = "int n1 = +20;\n"
				+ "System.out.println(\"Number = \" + n1);\n"
				+ "n1 = +n1;\n"
				+ "n1 = + n1;\n"
				+ "System.out.println(\"Result = \" + n1);\n"
				+ "int a = +10, b = 4++, res;\n"
				+ "System.out.println(\"a\" + +5 + +5);\n";
		
		assertEquals(6, unary.findNumberOfPositiveSignOperators(content));
	}
	
	@Test
	@DisplayName("Find Postive Sign Operators Faker")
	void testFindPositiveSignOperators2() {
	    Faker faker = new Faker();
	    Pattern positiveSignPattern = Pattern.compile("((=\\s*\\+\\s*)|(\\s*\\+\\s+\\+))(\\d*|((a-zA-Z])*\\w*))");
	    String positiveSign = faker.regexify(positiveSignPattern.toString());
		Matcher positiveSignMathcer = Pattern.compile(positiveSignPattern.toString()).matcher(positiveSign);
		
		assertTrue(positiveSignMathcer.find());
	}
	
	@Test
	@DisplayName("Find Negative Sign Operators")
	void testFindNegativeSignOperators() {
		String content = "int n1 = -20;\n"
				+ "System.out.println(\"Number = \" + n1);\n"
				+ "n1 = -n1;\n"
				+ "int num = num--;\n"
				+ "System.out.println(\"Result = \" + -n1);\n"
				+ "System.out.println(\"a \" + -5);\n"
				+ "System.out.println(\"Result = \" + n1);\n";
		
		assertEquals(4, unary.findNumberOfNegativeSignOperators(content));
	}
	
	@Test
	@DisplayName("Find Negative Sign Operators Faker")
	void testFindNegativeSignOperators2() {
	    Faker faker = new Faker();
		
    	Pattern negativeSignPattern = Pattern.compile("((=\\s*\\-\\s*)|(\\s*\\+\\s+\\-))(\\d*|((a-zA-Z])*\\w*))");
	    String negativeSign = faker.regexify(negativeSignPattern.toString());
		Matcher negativeSignMathcer = Pattern.compile(negativeSignPattern.toString()).matcher(negativeSign);
		
		assertTrue(negativeSignMathcer.find());
	}
	
	@Test
	@DisplayName("Find Increment Operators")
	void testFindIncrementOperators() {
		String content = "int a = 5;\n"
				+ "int b = 7;\n"
				+ "int b = ++a;\n"
				+ "int c = a++ + b;"
				+ "a = 10;"
				+ "b = ++(++a);"
				+ "System.out.println(\"Post- Increment \\n c = \"+ c);\n"
				+ "int A = 5;\n"
				+ "int B = 7;\n"
				+ "int C = ++A + B;"
				+ "System.out.println(\"Pre- Increment \\n C = \"+ C);\n"
				+ "int m = 1, n = 2;\n"
				+ "int o = m++ + n + ++m;"
				+ "System.out.println(\"Example \\n o = \"+ o);\n";
		
		assertEquals(7, unary.findNumberOfIncrementOperators(content));
	}
	
	@Test
	@DisplayName("Find Increment Operators Faker")
	void testFindIncrementOperators2() {
	    Faker faker = new Faker();
    	Pattern incrementPattern = Pattern.compile("\\+\\+");
	    String increment = faker.regexify(incrementPattern.toString());
		Matcher incrementMatcher = Pattern.compile(incrementPattern.toString()).matcher(increment);
		
		assertTrue(incrementMatcher.find());
	}
	
	@Test
	@DisplayName("Find Decrement Operators")
	void testFindDecrementOperators() {
		String content = "int a = 5;\n"
				+ "int b = 7;\n"
				+ "int c = a-- + b;\n"
				+ "System.out.println(\"Post- Decrement \\n c = \"+ c);\n"
				+ "int A = 5;\n"
				+ "int B = 7;\n"
				+ "int C = --A + B;\n"
				+ "System.out.println(\"Pre- Decrement \\n C = \"+ C);\n"
				+ "int m = 3, n = 2;\n"
				+ "int o = m-- + n + --m;\n"
				+ "System.out.println(\"Example \\n o = \"+ o);\n"
				+ "a = 10;\n"
				+ "b = --(--a);\n"
				+ "b = (a--)--;";
		
		assertEquals(8, unary.findNumberOfDecrementOperators(content));
	}
	
	@Test
	@DisplayName("Find Decrement Operators Faker")
	void testFindDecrementOperators2() {
	    Faker faker = new Faker();
	    Pattern decrementPattern = Pattern.compile("--");
	    String decrement = faker.regexify(decrementPattern.toString());
		Matcher decrementMatcher = Pattern.compile(decrementPattern.toString()).matcher(decrement);
		
		assertTrue(decrementMatcher.find());
	}
	
	@Test
	@DisplayName("Find Logical Not Operators")
	void testFindLogicalNotOperators() {
		String content = "boolean cond = true;\n"
				+ "int a = 10, b = 1;\n"
				+ "System.out.println(\"Cond is: \" + cond);\n"
				+ "System.out.println(\"Var1 = \" + a);\n"
				+ "System.out.println(\"Var2 = \" + b);\n"
				+ "System.out.println(\"Now cond is: \" + !cond);\n"
				+ "System.out.println(!(a < b));\n"
				+ "System.out.println(!(a > b));";
		
		assertEquals(3, unary.findNumberOfLogicalNotOperators(content));
	}
	
	@Test
	@DisplayName("Find Logical Not Operators Faker")
	void testFindLogicalNotOperators2() {
	    Faker faker = new Faker();
		Pattern logicalNotPattern = Pattern.compile("(![^=]\\s*([a-zA-Z][a-zA-Z0-9])*)");
	    String logicalNot = faker.regexify(logicalNotPattern.toString());
		Matcher logicalNotMatcher = Pattern.compile(logicalNotPattern.toString()).matcher(logicalNot);
		
		assertTrue(logicalNotMatcher.find());
	}
}
