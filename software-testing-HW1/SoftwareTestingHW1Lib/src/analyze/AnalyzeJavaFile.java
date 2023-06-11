/**
*
* @author Burak Bala burak.bala@ogr.sakarya.edu.tr
* @since 06.04.2021
* <p>
* AnalyzeJavaFile analyses Java file by its operators, operands and functions
* </p>
*/

package analyze;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AnalyzeJavaFile {
    private String fileContent;
    private long numberOfOperands;
    
    private IFindJavaUnaryOperators unaryOperators;
    private IFindJavaLogicalOperators logicalOperators;
    private IFindJavaRelationalOperators relationalOperators;
    private IFindArithmeticOperators arithmeticOperators;
    private IFindJavaBinaryOperators binaryOperators;
    
    public AnalyzeJavaFile(String filePath) throws IOException {
        fileContent = ReadFile.readFile(filePath);
        fileContent = RemoveUnnecessaryPartsOfString.Remove(fileContent);
        
        unaryOperators = new FindJavaUnaryOperators();
        logicalOperators = new FindJavaLogicalOperators();
        relationalOperators = new FindJavaRelationalOperators();
        arithmeticOperators = new FindJavaArithmeticOperators();
        binaryOperators = new FindJavaBinaryOperators();
    }
    
	public void analyze() {
		findJavaUnaryOperators();
		
		findNumberOfBinaryOperators();
		
		findNumberOfArithmeticOperators();
		
		findNumberOfRelationalOperators();
		
		findNumberOfLogicalOperators();
		
		numberOfOperands = 0;
		
		numberOfOperands += unaryOperators.getNumberOfOperands();
		
		numberOfOperands += binaryOperators.getNumberOfOperands();
		
		System.out.println("\n------------------------------Number Of Operands------------------------------");
		System.out.println("Number Of Operands: " + findNumberOfOperands());
		
		System.out.println("\n------------------------------Number Of Functions------------------------------");
		System.out.println("Number Of Functions: " + findNumberOfFunctions());
	} 
    
    // find number of unary operators in Java file
    public long findJavaUnaryOperators() {
		unaryOperators.analyze(fileContent);
		return unaryOperators.getNumberOfOperators();
    }
    
    // find number of logical operators in Java file
    public long findNumberOfLogicalOperators() {
    	logicalOperators.analyze(fileContent);
    	return logicalOperators.getNumberOfOperators();
    }
    
    // find number of relational operators in Java file
    public long findNumberOfRelationalOperators() {
    	relationalOperators.analyze(fileContent);
    	return relationalOperators.getNumberOfOperators();
    }
    
    // find number of arithmetic operators in Java file
    public long findNumberOfArithmeticOperators() {
    	arithmeticOperators.analyze(fileContent);
    	return arithmeticOperators.getNumberOfOperators();
    }
  
    // find number of binary operators in Java file
    public long findNumberOfBinaryOperators() {
    	binaryOperators.analyze(fileContent);
		return binaryOperators.getNumberOfOperators();
    }
    
    // find number of operands in Java file
    public long findNumberOfOperands() {
    	return numberOfOperands;
    }
    
    // find number of functions in Java file
    public long findNumberOfFunctions() {
    	Pattern functionPattern = Pattern.compile("(?:(?:public|private|protected|static|final|native|synchronized|abstract|transient)+\\s+)+[$_\\w<>\\[\\]\\s]*\\s+[\\$_\\w]+\\([^\\)]*\\)");
    	Matcher functionMatcher = functionPattern.matcher(fileContent);
    	
    	return functionMatcher.results().count();
    }
    
}
