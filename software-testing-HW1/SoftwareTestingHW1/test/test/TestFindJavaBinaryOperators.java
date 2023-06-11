package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import analyze.FindJavaBinaryOperators;
import analyze.IFindJavaBinaryOperators;

@DisplayName("Test for Java Binary Operators")
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestFindJavaBinaryOperators {
	private IFindJavaBinaryOperators binary;
	
	@BeforeEach
	void setUp() throws Exception {
		binary = new FindJavaBinaryOperators();
	}

	@Test
	@DisplayName("Find Number of Concatenation Operators")
	void testFindNumberOfConcatenationOperators() {
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
		
		assertEquals(18, binary.findNumberOfConcatenationOperators(content));
	}

}
