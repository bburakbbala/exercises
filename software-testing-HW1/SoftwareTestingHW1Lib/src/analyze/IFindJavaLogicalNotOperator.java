package analyze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFindJavaLogicalNotOperator extends IAnalyze {
		
	default long findNumberOfLogicalNotOperators(String content) {
		Pattern logicalNotPattern = Pattern.compile("(![^=]\\s*([a-zA-Z][a-zA-Z0-9])*)");
		Matcher logicalNotMatcher = logicalNotPattern.matcher(content);
		return logicalNotMatcher.results().count();
	}
	
}
