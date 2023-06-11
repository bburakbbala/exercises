package analyze;

public class RemoveUnnecessaryPartsOfString {
	public static String Remove(String content) {
		content = content.replaceAll("[^\\w\\\"]//.*", "") // delete line comments
    			.replaceAll("/\\*(\\*(?!/)|[^*])*\\\\*\\*/", " ") // delete block comments
    			.replaceAll("\\s{1,}", " ") // delete any whitespace equivalent to [\r\n\t\f\v ]
    			.replaceAll("(package|import)[^;]*;\\s", "") // delete import and package declarations
    			.replaceAll("\"[^\"]*\"", "\"\""); // remove all string parts
		return content;
	}
}
