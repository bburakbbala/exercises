package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import analyze.FindJavaLogicalOperators;
import analyze.IFindJavaLogicalOperators;

class TestFindJavaLogicalOperators {
	private IFindJavaLogicalOperators logical;
	
	@BeforeEach
	void setUp() throws Exception {
		logical = new FindJavaLogicalOperators();
	}

	@Test
	@DisplayName("Find Logical Not Operators")
	void testFindNumberOfLogicalNotOperators() {
		String content = "System.out.println(!(a < b));\n"
				+ "System.out.println(!(a > b));";
		
		assertEquals(2, logical.findNumberOfLogicalNotOperators(content));
	}
	
	@Test
	@DisplayName("Find Logical And Operators")
	void testFindNumberOfLogicalAndOperators() {
		String content = "if ((a < b) && (b == c)) {\n"
				+ "    d = a + b + c;\n"
				+ "    System.out.println(\"The sum is: \" + d);\n"
				+ "}\n"
				+ "else\n"
				+ "    System.out.println(\"False conditions\");";
		
		assertEquals(1, logical.findNumberOfLogicalAndOperators(content));
	}
	
	@Test
	@DisplayName("Find Logical Or Operators")
	void testFindNumberOfLogicalOrOperators() {
		String content = "if (a < b || b == c) {\n"
				+ "    d = a + b + c;\n"
				+ "    System.out.println(\"The sum is: \" + d);\n"
				+ "}\n"
				+ "else\n"
				+ "    System.out.println(\"False conditions\");";
		
		assertEquals(1, logical.findNumberOfLogicalOrOperators(content));
	}

}
