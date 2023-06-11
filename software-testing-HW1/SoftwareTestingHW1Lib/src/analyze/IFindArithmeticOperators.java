package analyze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IFindArithmeticOperators extends IAnalyze, IFindJavaIncrementAndDecrementOperators {
	
	public long getNumberOfOperators();
	
	public long getNumberOfOperands();
	
	default long findNumberOfAdditionOperators(String content) {
		// remove concatenation operators
		Pattern concatenationPattern = Pattern.compile("(\\\"\\s*\\+[^\\+(;]*|\\+\\s*\\\")");
		Matcher concatenationMatcher = concatenationPattern.matcher(content);
		while (concatenationMatcher.find()) {
			concatenationMatcher = concatenationPattern.matcher(content);
			content = content.replaceAll(concatenationPattern.toString(), "\\\"");
			concatenationMatcher.reset();
		}
		
		// remove increment then remove positive sign
		content = content.replaceAll("\\+\\+", "")
				.replaceAll("((=\\s*\\+\\s*)|(\\s*\\+\\s+\\+))(\\d*|((a-zA-Z])*\\w*))", "");
		Pattern additionPattern = Pattern.compile("\\+");
		Matcher additionMatcher = additionPattern.matcher(content);
		return additionMatcher.results().count();
	}
	
	default long findNumberOfSubtractionOperators(String content) {
		// first remove decrements then negative sign
		content = content.replaceAll("--", "")
				.replaceAll("((=\\s*\\-\\s*)|(\\s*\\+\\s+\\-))(\\d*|((a-zA-Z])*\\w*))", "");
		
		Pattern subtractionPattern = Pattern.compile("-");
		Matcher subtractionMatcher = subtractionPattern.matcher(content);
		return subtractionMatcher.results().count();
	}

	default long findNumberOfMultiplicationOperators(String content) {
		Pattern logicalNotPattern = Pattern.compile("\\*");
		Matcher logicalNotMatcher = logicalNotPattern.matcher(content);
		return logicalNotMatcher.results().count();
	}
	
	default long findNumberOfDivisionOperators(String content) {
		Pattern incrementPattern = Pattern.compile("/");
		Matcher incrementMatcher = incrementPattern.matcher(content);
		return incrementMatcher.results().count();
	}
	
	default long findNumberOfModuloOperators(String content) {
		Pattern decrementPattern = Pattern.compile("%");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfBitwiseAndOperators(String content) {
		// remove && operators
		content = content.replaceAll("&&", "");
		
		Pattern decrementPattern = Pattern.compile("&");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfBitwiseOrOperators(String content) {
		// remove || operators
		content = content.replaceAll("[\\|]{2}", "");
		
		Pattern decrementPattern = Pattern.compile("\\|");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfBitwiseXOROperators(String content) {
		Pattern decrementPattern = Pattern.compile("\\^");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfAssignmentOperators(String content) {
		// remove == != += -= /= *= %= &= |= ^= <= >=
		content = content.replaceAll("==|!=|\\+=|-=|/=|\\*=|%=|&=|\\|=|\\^=|<=|>=", "");
		
		Pattern decrementPattern = Pattern.compile("=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberShorthandAdditionOperators(String content) {
		Pattern decrementPattern = Pattern.compile("\\+=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfShorthandSubtractionOperators(String content) {
		Pattern decrementPattern = Pattern.compile("-=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfShorthandDivisionOperators(String content) {
		Pattern decrementPattern = Pattern.compile("/=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfShorthandMultiplicationOperators(String content) {
		Pattern decrementPattern = Pattern.compile("\\*=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfShorthandModuloOperators(String content) {
		Pattern decrementPattern = Pattern.compile("%=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfShorthandBitwiseAndOperators(String content) {
		Pattern decrementPattern = Pattern.compile("&=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfShorthandBitwiseOrOperators(String content) {
		Pattern decrementPattern = Pattern.compile("\\|=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
	default long findNumberOfShorthandBitwiseXOROperators(String content) {
		Pattern decrementPattern = Pattern.compile("\\^=");
		Matcher decrementMatcher = decrementPattern.matcher(content);
		return decrementMatcher.results().count();
	}
	
}
