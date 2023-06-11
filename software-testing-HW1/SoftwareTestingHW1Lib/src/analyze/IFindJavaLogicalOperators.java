package analyze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFindJavaLogicalOperators extends IAnalyze, IFindJavaLogicalNotOperator {
	
	public long getNumberOfOperators();
	
	public long getNumberOfOperands();
	
	default long findNumberOfLogicalAndOperators(String content) {
		Pattern decrementPattern = Pattern.compile("&&");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfLogicalOrOperators(String content) {
		Pattern decrementPattern = Pattern.compile("[\\|]{2}");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
}
