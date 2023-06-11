package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import analyze.FindJavaRelationalOperators;
import analyze.IFindJavaRelationalOperators;

@DisplayName("Test for Java Relational Operators")
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestFindJavaRelationalOperators {
	private IFindJavaRelationalOperators relational;

	@BeforeEach
	void setUp() throws Exception {
		relational = new FindJavaRelationalOperators();
	}

	@Test
	@DisplayName("Find Less Than Operators")
	void testFindNumberOfLessThanOperators() {
		String content = "int var1 = 10, var2 = 20, var3 = 5;\n"
				+ "System.out.println(\"Var1 = \" + var1);\n"
				+ "System.out.println(\"Var2 = \" + var2);\n"
				+ "System.out.println(\"Var3 = \" + var3);\n"
				+ "System.out.println((var1 < var2));\n"
				+ "System.out.println((var2 < var3));";
		
		assertEquals(2, relational.findNumberOfLessThanOperators(content));
	}
	
	@Test
	@DisplayName("Find Less Than or Equal to Operators")
	void testFindNumberOfLessThanOrEqualOperators() {
		String content = "int var1 = 10, var2 = 10, var3 = 9;\n"
				+ "System.out.println(\"Var1 = \" + var1);\n"
				+ "System.out.println(\"Var2 = \" + var2);\n"
				+ "System.out.println(\"Var3 = \" + var3);System.out.println(var1 <= var2);\n"
				+ "System.out.println(var2 <= var3);";
		
		assertEquals(2, relational.findNumberOfLessThanOrEqualToOperators(content));
	}
	
	@Test
	@DisplayName("Find Greater Than Operators")
	void testFindNumberOfGreaterThanOperators() {
		String content = "System.out.println(var1 > var2);";
		
		assertEquals(1, relational.findNumberOfGreaterThanOperators(content));
	}
	
	@Test
	@DisplayName("Find Greater Than or Equal to Operators")
	void testFindNumberOfGreaterThanOrEqualOperators() {
		String content = "System.out.println(var1 >= var2);\n"
				+ "System.out.println(var2 >= var3);";
		
		assertEquals(2, relational.findNumberOfGreaterThanOrEqualToOperators(content));
	}
	
	@Test
	@DisplayName("Find Equality Operators")
	void testFindNumberOfEqualityOperators() {
		String content = "System.out.println(var1 == var2);\n"
				+ "System.out.println(var1 == var3);";
		
		assertEquals(2, relational.findNumberOfEqualityOperators(content));
	}
	
	@Test
	@DisplayName("Find Not Equal Operators")
	void testFindNumberOfNotEqualOperators() {
		String content = "System.out.println(var1 != var2);\n"
				+ "System.out.println(var1 != var3);";
		
		assertEquals(2, relational.findNumberOfNotEqualOperators(content));
	}

}
