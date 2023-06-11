package analyze;

public class FindJavaBinaryOperators implements IFindJavaBinaryOperators {
	private long numberOfOperators;
	private long numberOfOperands;
	
	public long getNumberOfOperators() {
		return numberOfOperators;
	}
	
	public long getNumberOfOperands() {
		return numberOfOperands;
	}
	
	@Override
	public void analyze(String content) {
		FindJavaLogicalOperators findJavaLogicalOperators = new FindJavaLogicalOperators();
	    FindJavaRelationalOperators findJavaRelationalOperators = new FindJavaRelationalOperators();
	    FindJavaArithmeticOperators findJavaArithmeticOperators = new FindJavaArithmeticOperators();
	    
		System.out.println("\n------------------------------Find Java Binary Operators------------------------------");
		long no = 0;
		numberOfOperands = 0;
		numberOfOperators = 0;

		// Logical operators
		no = findJavaLogicalOperators.findNumberOfLogicalAndOperators(content);
		numberOfOperands += no*2;
		numberOfOperators += no;
		System.out.println("Number of Logical And Operators: " + no);
		
		no = findJavaLogicalOperators.findNumberOfLogicalOrOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Logical Or Operators: " + no);
		
		// Relational operators
		no = findJavaRelationalOperators.findNumberOfLessThanOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Less Than Operators: " + no);
		
		no = findJavaRelationalOperators.findNumberOfLessThanOrEqualToOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Less Than or Equal To Operators: " + no);
		
		no = findJavaRelationalOperators.findNumberOfGreaterThanOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Greater Than Operators: " + no);
		
		no = findJavaRelationalOperators.findNumberOfGreaterThanOrEqualToOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Greater Than or Equal To Operators: " + no);
		
		no = findJavaRelationalOperators.findNumberOfEqualityOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Equality Operators: " + no);
		
		no = findJavaRelationalOperators.findNumberOfNotEqualOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Not Equal To Operators: " + no);
		
		// Concatenation
		no = findNumberOfConcatenationOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Concatenation Operators: " + no);
		
		// Arithmetic operators
		no = findJavaArithmeticOperators.findNumberOfAdditionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Addition Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfSubtractionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Subtraction Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfMultiplicationOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Multiplication Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfDivisionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Division Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfModuloOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Modulo Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfBitwiseAndOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Bitwise And Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfBitwiseOrOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Bitwise Or Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfBitwiseXOROperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Bitwise XOR Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfAssignmentOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Assignment Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberShorthandAdditionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Addition Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfShorthandSubtractionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Subtraction Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfShorthandDivisionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Division Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfShorthandMultiplicationOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Multiplication Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfShorthandModuloOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Modulo Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfShorthandBitwiseAndOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Bitwise And Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfShorthandBitwiseOrOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Bitwise Or Operators: " + no);
		
		no = findJavaArithmeticOperators.findNumberOfShorthandBitwiseXOROperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Bitwise XOR Operators: " + no);
	}
	
}
