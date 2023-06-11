package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import analyze.FindJavaBinaryOperators;
import analyze.IFindJavaBinaryOperators;
import analyze.ReadFile;
import analyze.RemoveUnnecessaryPartsOfString;

class IntegrationTestFindJavaBinaryOperators {
	private String content;
	private IFindJavaBinaryOperators binary;

	@BeforeEach
	void setUp() throws Exception {
		content = ReadFile.readFile("test-files/Binary.java");
		content = RemoveUnnecessaryPartsOfString.Remove(content);
		binary = new FindJavaBinaryOperators();
	}

	@Test
	void testFindJavaBinaryOperators() {
		binary.analyze(content);
		assertEquals(15, binary.getNumberOfOperators());
	}
	
	@ParameterizedTest
	@CsvSource({"test-files/Binary.java, 15", "test-files/Binary2.java, 12"})
	void testFindJavaBinaryOperators2(String filePath, long result) throws IOException {
		content = ReadFile.readFile(filePath);
		content = RemoveUnnecessaryPartsOfString.Remove(content);
		binary.analyze(content);
		assertEquals(result, binary.getNumberOfOperators());
	}
}
