package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import analyze.IFindArithmeticOperators;
import analyze.FindJavaArithmeticOperators;
import analyze.ReadFile;
import analyze.RemoveUnnecessaryPartsOfString;

class IntegrationTestFindJavaArithmeticOperators {

	private String content;
	private IFindArithmeticOperators arithmetic;

	@BeforeEach
	void setUp() throws Exception {
		content = ReadFile.readFile("test-files/Arithmetic.java");
		content = RemoveUnnecessaryPartsOfString.Remove(content);
		arithmetic = new FindJavaArithmeticOperators();
	}

	@Test
	void testFindJavaArithmeticOperators() {
		arithmetic.analyze(content);
		assertEquals(10, arithmetic.getNumberOfOperators());
	}
	
	@ParameterizedTest
	@CsvSource({"test-files/Arithmetic.java, 8", "test-files/Arithmetic2.java, 21"})
	void testFindJavaArithmeticOperators2(String filePath, long result) throws IOException {
		content = ReadFile.readFile(filePath);
		content = RemoveUnnecessaryPartsOfString.Remove(content);
		arithmetic.analyze(content);
		assertEquals(result, arithmetic.getNumberOfOperators());
	}

}
