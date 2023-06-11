package analyze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFindJavaBinaryOperators extends IAnalyze {
	
	public long getNumberOfOperators();
	
	public long getNumberOfOperands();
	
	default long findNumberOfConcatenationOperators(String content) {
		Pattern concatenationPattern = Pattern.compile("(\\\"\\s*\\+[^\\+(;]*|\\+\\s*\\\")");
		Matcher concatenationMatcher = concatenationPattern.matcher(content);
		long no = 0;
		
		while (concatenationMatcher.find()) {
			concatenationMatcher = concatenationPattern.matcher(content);
			no += concatenationMatcher.results().count();
			content = content.replaceAll(concatenationPattern.toString(), "\\\"");
			concatenationMatcher.reset();
		}
		return no;
	}
	
}
