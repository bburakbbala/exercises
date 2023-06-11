package analyze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFindJavaIncrementAndDecrementOperators extends IAnalyze {
	
	default long findNumberOfIncrementOperators(String content) {
		Pattern incrementPattern = Pattern.compile("\\+\\+");
		Matcher incrementMatcher = incrementPattern.matcher(content);
		long no = incrementMatcher.results().count();
		return no;
	}
	
	default long findNumberOfDecrementOperators(String content) {
		Pattern decrementPattern = Pattern.compile("--");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		long no = decrementMatcher.results().count();
		return no;
	}
	
}
