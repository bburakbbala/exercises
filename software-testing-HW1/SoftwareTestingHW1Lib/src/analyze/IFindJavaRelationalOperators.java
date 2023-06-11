package analyze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFindJavaRelationalOperators extends IAnalyze {
	
	public long getNumberOfOperators();
	
	public long getNumberOfOperands();
	
	default long findNumberOfEqualityOperators(String content) {
		Pattern decrementPattern = Pattern.compile("==");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfNotEqualOperators(String content) {
		Pattern decrementPattern = Pattern.compile("!=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count(); 
	}
	
	default long findNumberOfLessThanOperators(String content) {
		// remove angle brackets <> parts and <=
		content = content.replaceAll("<>|<\\s*\\w*\\s*>", "")
				.replaceAll("<=", "");
		Pattern decrementPattern = Pattern.compile("<");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfLessThanOrEqualToOperators(String content) {
		Pattern decrementPattern = Pattern.compile("<=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfGreaterThanOperators(String content) {
		// remove angle brackets <> parts
		content = content.replaceAll("<>|<\\s*\\w*\\s*>", "")
				.replaceAll(">=", "");;
		Pattern decrementPattern = Pattern.compile(">");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfGreaterThanOrEqualToOperators(String content) {
		Pattern decrementPattern = Pattern.compile(">=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
}
