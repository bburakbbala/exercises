package main;

import java.io.IOException;
import analyze.*;

public class App {

	public static void main(String[] args) throws IOException {
		AnalyzeJavaFile analyzeJavaFile = new AnalyzeJavaFile("Main.java");
		analyzeJavaFile.analyze();
	}

}
