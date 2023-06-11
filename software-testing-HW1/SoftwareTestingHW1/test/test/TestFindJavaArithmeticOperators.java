package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import analyze.FindJavaArithmeticOperators;
import analyze.IFindArithmeticOperators;

@DisplayName("Test for Java Arithmetic Operators")
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestFindJavaArithmeticOperators {
	private IFindArithmeticOperators arithmetic;
	
	@BeforeEach
	void setUp() throws Exception {
		arithmetic = new FindJavaArithmeticOperators();
	}

	@Test
	@DisplayName("Find Number of Addition Operators")
	void testFindNumberOfAdditionOperators() {
		String content = "System.out.println(\"\" + num1);\n"
				+ "System.out.println(\"\" + num2);\n"
				+ "sum = num1 + num2;\n"
				+ "System.out.println(\"\" + sum);\n"
				+ "System.out.println(2 + 0 + 1 + 6 + \"\");\n"
				+ "System.out.println(\"\" + 2 + 0 + 1 + 6);\n"
				+ "System.out.println(2 + 0 + 1 + 5 + \"\" + 2 + 0 + 1 + 6);\n"
				+ "System.out.println(2 + 0 + 1 + 5 + \"\" + (2 + 0 + 1 + 6));\n"
				+ "int a = 10, b = 4, res;\n"
				+ "System.out.println(\"\" + a + \"\" + b);\n"
				+ "res = a + b;";
		
		assertEquals(14, arithmetic.findNumberOfAdditionOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Increment Operators")
	void testFindNumberOfIncrementOperators() {
		String content = "System.out.println(\"\" + num1);\n"
				+ "System.out.println(\"\" + num2);\n"
				+ "sum = num1 + num2;\n"
				+ "System.out.println(\"\" + sum);\n"
				+ "System.out.println(2 + 0 + 1 + 6 + \"\");\n"
				+ "System.out.println(\"\" + 2 + 0 + 1 + 6);\n"
				+ "System.out.println(2 + 0 + 1 + 5 + \"\" + 2 + 0 + 1 + 6);\n"
				+ "System.out.println(2 + 0 + 1 + 5 + \"\" + (2 + 0 + 1 + 6));\n"
				+ "int a = 10, b = 4, res;\n"
				+ "System.out.println(\"\" + a + \"\" + b);\n"
				+ "res = a + b;"
				+ "int n1 = +20;\n"
				+ "System.out.println(\"Number = \" + n1);\n"
				+ "n1 = +n1;\n"
				+ "n1 = + n1;\n"
				+ "System.out.println(\"Result = \" + n1);\n"
				+ "int a = +10, b = 4++, res;\n"
				+ "System.out.println(\"a\" + +5 + +5);\n"
				+ "int a = 5;\n"
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
		
		assertEquals(8, arithmetic.findNumberOfIncrementOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Subtraction Operators")
	void testFindNumberOfSubtractionOperators() {
		String content = "System.out.println(\"num1 = \" + num1);\n"
				+ "System.out.println(\"num2 = \" + num2);\n"
				+ "sub = num1 - num2;\n"
				+ "System.out.println(\"Subtraction = \" + sub);\n"
				+ "int n1 = -20;\n"
				+ "System.out.println(\"Number = \" + n1);\n"
				+ "n1 = -n1;\n"
				+ "int num = num--;\n"
				+ "System.out.println(\"Result = \" + -n1);\n"
				+ "System.out.println(\"a \" + -5);\n"
				+ "System.out.println(\"Result = \" + n1);";
		
		assertEquals(1, arithmetic.findNumberOfSubtractionOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Decrement Operators")
	void testFindNumberOfDecrementOperators() {
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
				+ "b = (a--)--;"
				+ "int n1 = -20;\n"
				+ "System.out.println(\"Number = \" + n1);\n"
				+ "n1 = -n1;\n"
				+ "int num = num--;\n"
				+ "System.out.println(\"Result = \" + -n1);\n"
				+ "System.out.println(\"a \" + -5);\n"
				+ "System.out.println(\"Result = \" + n1);\n";
		
		assertEquals(9, arithmetic.findNumberOfDecrementOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Multiplication Operators")
	void testFindNumberOfMultiplicationOperators() {
		String content = "System.out.println(\"num1 = \" + num1);\n"
				+ "System.out.println(\"num2 = \" + num2);\n"
				+ "mult = num1 * num2;";
		
		assertEquals(1, arithmetic.findNumberOfMultiplicationOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Division Operators")
	void testFindNumberOfDivisionOperators() {
		String content = "System.out.println(\"num1 = \" + num1);\n"
				+ "System.out.println(\"num2 = \" + num2);\n"
				+ "div = num1 / num2;";
		
		assertEquals(1, arithmetic.findNumberOfDivisionOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Modulo Operators")
	void testFindNumberOfModuloOperators() {
		String content = "System.out.println(\"num1 = \" + num1);\n"
				+ "System.out.println(\"num2 = \" + num2);\n"
				+ "modulo = num1 % num2;";
		
		assertEquals(1, arithmetic.findNumberOfModuloOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Bitwise And Operators")
	void testFindNumberOfBitwiseAndOperators() {
		String content = "int a = 5;\n"
				+ "int b = 7;\n"
				+ "\n"
				+ "System.out.println(a & b);\n"
				+ "System.out.println(a | b);\n"
				+ "System.out.println(a ^ b);";
		
		assertEquals(1, arithmetic.findNumberOfBitwiseAndOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Bitwise Or Operators")
	void testFindNumberOfBitwiseOrOperators() {
		String content = "int a = 5;\n"
				+ "int b = 7;\n"
				+ "\n"
				+ "System.out.println(a & b);\n"
				+ "System.out.println(a | b);\n"
				+ "System.out.println(a ^ b);";
		
		assertEquals(1, arithmetic.findNumberOfBitwiseOrOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Bitwise XOR Operators")
	void testFindNumberOfBitwiseXOROperators() {
		String content = "int a = 5;\n"
				+ "int b = 7;\n"
				+ "\n"
				+ "System.out.println(a & b);\n"
				+ "System.out.println(a | b);\n"
				+ "System.out.println(a ^ b);";
		
		assertEquals(1, arithmetic.findNumberOfBitwiseXOROperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Assignment Operators")
	void testFindNumberOfAssignmentOperators() {
		String content = "int num;\n"
				+ "String name;\n"
				+ "num = 10;\n"
				+ "name = \"GeeksforGeeks\";\n"
				+ "System.out.println(\"num is assigned: \" + num);\n"
				+ "System.out.println(\"name is assigned: \" + name);\n"
				+ "int num1 = 10, num2 = 20;\n"
				+ "num1 += num2;";
		
		assertEquals(4, arithmetic.findNumberOfAssignmentOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Shorthand Addition Operators")
	void testFindNumberShorthandAdditionOperators() {
		String content = "byte b = 120;\n"
				+ "b += 10;\n"
				+ "byte b1 = 120;\n"
				+ "b1 *= 10;\n"
				+ "short s = 330;\n"
				+ "s -= 30;\n"
				+ "byte b2 = 127;\n"
				+ "b2 %= 7;\n"
				+ "byte b3 = 120;\n"
				+ "b3 &= 40;\n"
				+ "short s1 = 300;\n"
				+ "s1 ^= 100;";
		
		assertEquals(1, arithmetic.findNumberShorthandAdditionOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Shorthand Subtraction Operators")
	void testFindNumberOfShorthandSubtractionOperators() {
		String content = "byte b = 120;\n"
				+ "b += 10;\n"
				+ "byte b1 = 120;\n"
				+ "b1 *= 10;\n"
				+ "short s = 330;\n"
				+ "s -= 30;\n"
				+ "byte b2 = 127;\n"
				+ "b2 %= 7;\n"
				+ "byte b3 = 120;\n"
				+ "b3 &= 40;\n"
				+ "short s1 = 300;\n"
				+ "s1 ^= 100;";
		
		assertEquals(1, arithmetic.findNumberOfShorthandSubtractionOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Shorthand Division Operators")
	void testFindNumberOfShorthandDivisionOperators() {
		String content = "byte b = 120;\n"
				+ "b += 10;\n"
				+ "byte b1 = 120;\n"
				+ "b1 *= 10;\n"
				+ "short s = 330;\n"
				+ "s -= 30;\n"
				+ "byte b2 = 127;\n"
				+ "b2 %= 7;\n"
				+ "b /= 6"
				+ "byte b3 = 120;\n"
				+ "b3 &= 40;\n"
				+ "short s1 = 300;\n"
				+ "s1 ^= 100;";
		
		assertEquals(1, arithmetic.findNumberOfShorthandDivisionOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Shorthand Multiplicaiton Operators")
	void testFindNumberOfShorthandMultiplicationOperators() {
		String content = "byte b = 120;\n"
				+ "b += 10;\n"
				+ "byte b1 = 120;\n"
				+ "b1 *= 10;\n"
				+ "short s = 330;\n"
				+ "s -= 30;\n"
				+ "byte b2 = 127;\n"
				+ "b2 %= 7;\n"
				+ "byte b3 = 120;\n"
				+ "b3 &= 40;\n"
				+ "short s1 = 300;\n"
				+ "s1 ^= 100;";
		
		assertEquals(1, arithmetic.findNumberOfShorthandMultiplicationOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Shorthand Modulo Operators")
	void testFindNumberOfShorthandModuloOperators() {
		String content = "byte b = 120;\n"
				+ "b += 10;\n"
				+ "byte b1 = 120;\n"
				+ "b1 *= 10;\n"
				+ "short s = 330;\n"
				+ "s -= 30;\n"
				+ "byte b2 = 127;\n"
				+ "b2 %= 7;\n"
				+ "byte b3 = 120;\n"
				+ "b3 &= 40;\n"
				+ "short s1 = 300;\n"
				+ "s1 ^= 100;";
		
		assertEquals(1, arithmetic.findNumberOfShorthandModuloOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Shorthand Bitwise And Operators")
	void testFindNumberOfShorthandBitwiseAndOperators() {
		String content = "byte b = 120;\n"
				+ "b += 10;\n"
				+ "byte b1 = 120;\n"
				+ "b1 *= 10;\n"
				+ "short s = 330;\n"
				+ "s -= 30;\n"
				+ "byte b2 = 127;\n"
				+ "b2 %= 7;\n"
				+ "byte b3 = 120;\n"
				+ "b3 &= 40;\n"
				+ "short s1 = 300;\n"
				+ "s1 ^= 100;";
		
		assertEquals(1, arithmetic.findNumberOfShorthandBitwiseAndOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Shorthand Bitwise Or Operators")
	void testFindNumberOfShorthandBitwiseOrOperators() {
		String content = "byte b = 120;\n"
				+ "b += 10;\n"
				+ "byte b1 = 120;\n"
				+ "b1 *= 10;\n"
				+ "short s = 330;\n"
				+ "s -= 30;\n"
				+ "byte b2 = 127;\n"
				+ "b2 %= 7;\n"
				+ "byte b3 = 120;\n"
				+ "b3 &= 40;\n"
				+ "short s1 = 300;\n"
				+ "s1 |= 10"
				+ "s1 ^= 100;";
		
		assertEquals(1, arithmetic.findNumberOfShorthandBitwiseOrOperators(content));
	}
	
	@Test
	@DisplayName("Find Number of Shorthand Bitwise XOR Operators")
	void testFindNumberOfShorthandBitwiseXOROperators() {
		String content = "byte b = 120;\n"
				+ "b += 10;\n"
				+ "byte b1 = 120;\n"
				+ "b1 *= 10;\n"
				+ "short s = 330;\n"
				+ "s -= 30;\n"
				+ "byte b2 = 127;\n"
				+ "b2 %= 7;\n"
				+ "byte b3 = 120;\n"
				+ "b3 &= 40;\n"
				+ "short s1 = 300;\n"
				+ "s1 ^= 100;";
		
		assertEquals(1, arithmetic.findNumberOfShorthandBitwiseXOROperators(content));
	}
}
