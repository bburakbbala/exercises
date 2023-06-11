package analyze;

public class FindJavaArithmeticOperators implements IFindArithmeticOperators {
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
		System.out.println("\n------------------------------Find Java Arithmetic Operators------------------------------");
		long no = 0;
		numberOfOperands = 0;
		numberOfOperators = 0;
		
		no = findNumberOfAdditionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Addition Operators: " + no);
		
		no = findNumberOfIncrementOperators(content);
		numberOfOperators += no;
		numberOfOperands += no;
		System.out.println("Number of Increment Operators: " + no);
		
		no = findNumberOfSubtractionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Subtraction Operators: " + no);
		
		no = findNumberOfDecrementOperators(content);
		numberOfOperators += no;
		numberOfOperands += no;
		System.out.println("Number of Decrement Operators: " + no);
		
		no = findNumberOfMultiplicationOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Multiplication Operators: " + no);
		
		no = findNumberOfDivisionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Division Operators: " + no);
		
		no = findNumberOfModuloOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Modulo Operators: " + no);
		
		no = findNumberOfBitwiseAndOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Bitwise And Operators: " + no);
		
		no = findNumberOfBitwiseOrOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Bitwise Or Operators: " + no);
		
		no = findNumberOfBitwiseXOROperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Bitwise XOR Operators: " + no);
		
		no = findNumberOfAssignmentOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Assignment Operators: " + no);
		
		no = findNumberShorthandAdditionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Addition Operators: " + no);
		
		no = findNumberOfShorthandSubtractionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Subtraction Operators: " + no);
		
		no = findNumberOfShorthandDivisionOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Division Operators: " + no);
		
		no = findNumberOfShorthandMultiplicationOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Multiplication Operators: " + no);
		
		no = findNumberOfShorthandModuloOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Modulo Operators: " + no);
		
		no = findNumberOfShorthandBitwiseAndOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Bitwise And Operators: " + no);
		
		no = findNumberOfShorthandBitwiseOrOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Bitwise Or Operators: " + no);
		
		no = findNumberOfShorthandBitwiseXOROperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Shorthand Assignment Bitwise XOR Operators: " + no);
	}
	
}
