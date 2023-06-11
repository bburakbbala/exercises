package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import analyze.FindJavaUnaryOperators;
import analyze.IFindJavaUnaryOperators;
import analyze.ReadFile;

class IntegrationTestFindJavaUnaryOperators {
	private String content;
	private IFindJavaUnaryOperators unary;
	
	@BeforeEach
	void setUp() throws Exception {
		content = ReadFile.readFile("test-files/Unary.java");
		unary = new FindJavaUnaryOperators();
	}

	@Test
	void testFindJavaUnaryOperators() {
		unary.analyze(content);
		assertEquals(6, unary.getNumberOfOperators());
	}
	
	@ParameterizedTest
	@CsvSource({"test-files/Unary2.java, 5", "test-files/Unary.java, 6"})
	void testFindJavaUnaryOperators2(String filePath, long result) throws IOException {
		content = ReadFile.readFile(filePath);
		unary.analyze(content);
		assertEquals(result, unary.getNumberOfOperators());
	}

}
