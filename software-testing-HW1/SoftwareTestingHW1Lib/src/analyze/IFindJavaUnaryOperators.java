package analyze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFindJavaUnaryOperators extends IAnalyze, IFindJavaLogicalNotOperator, IFindJavaIncrementAndDecrementOperators {
	
	public long getNumberOfOperators();
	
	public long getNumberOfOperands();
	
	default long findNumberOfPositiveSignOperators(String content) {
		// remove increment signs
		content = content.replaceAll("\\+\\+", "");
		
		Pattern positiveSignPattern = Pattern.compile("((=\\s*\\+\\s*)|(\\s*\\+\\s+\\+))(\\d*|((a-zA-Z])*\\w*))");
		Matcher positiveSignMathcer = positiveSignPattern.matcher(content);
		return positiveSignMathcer.results().count();
	}
		
	default long findNumberOfNegativeSignOperators(String content) {
		// remove negative sings
		content = content.replaceAll("--", "");
				
		Pattern negativeSignPattern = Pattern.compile("((=\\s*\\-\\s*)|(\\s*\\+\\s+\\-))(\\d*|((a-zA-Z])*\\w*))");
		Matcher negativeSignMathcer = negativeSignPattern.matcher(content);
		return negativeSignMathcer.results().count();
	}
	
}
