package analyze;

public class FindJavaUnaryOperators implements IFindJavaUnaryOperators {
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
		System.out.println("\n------------------------------Find Java Unary Operators------------------------------");
		long no = 0;
		numberOfOperands = 0;
		numberOfOperators = 0;
		
		no = findNumberOfPositiveSignOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Positive Sign Operators: " + no);
		
		no = findNumberOfNegativeSignOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Negitive Sign Operators: " + no);
		
		no = findNumberOfIncrementOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Increment Operators: " + no);
		
		no = findNumberOfDecrementOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Decrement Operators: " + no);
		
		no = findNumberOfLogicalNotOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Logical Not Operators: " + no);
	}
	
}
