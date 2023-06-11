package analyze;

public class FindJavaRelationalOperators implements IFindJavaRelationalOperators {
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
		System.out.println("\n------------------------------Find Java Relational Operators------------------------------");
		long no = 0;
		numberOfOperands = 0;
		numberOfOperators = 0;
		
		no = findNumberOfLessThanOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Less Than Operators: " + no);
		
		no = findNumberOfLessThanOrEqualToOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Less Than or Equal To Operators: " + no);
		
		no = findNumberOfGreaterThanOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Greater Than Operators: " + no);
		
		no = findNumberOfGreaterThanOrEqualToOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Greater Than or Equal To Operators: " + no);
		
		no = findNumberOfEqualityOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Equality Operators: " + no);
		
		no = findNumberOfNotEqualOperators(content);
		numberOfOperators += no;
		System.out.println("Number of Not Equal To Operators: " + no);
		
		numberOfOperands = numberOfOperators * 2;
	}
	
}
