package analyze;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {
		AnalyzeJavaFile analyzeJavaFile = new AnalyzeJavaFile("Main.java");
		analyzeJavaFile.analyze();
	}

}
