package analyze;

public class FindJavaLogicalOperators implements IFindJavaLogicalOperators {
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
		System.out.println("\n------------------------------Find Java Logical Operators------------------------------");
		long no = 0;
		numberOfOperands = 0;
		numberOfOperators = 0;
		
		no = findNumberOfLogicalNotOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Logical Not Operators: " + no);
		
		no = findNumberOfLogicalAndOperators(content);
		numberOfOperands += no*2;
		numberOfOperators += no;
		System.out.println("Number of Logical And Operators: " + no);
		
		no = findNumberOfLogicalOrOperators(content);
		numberOfOperators += no;
		numberOfOperands += no*2;
		System.out.println("Number of Logical Or Operators: " + no);
	}
	
}
