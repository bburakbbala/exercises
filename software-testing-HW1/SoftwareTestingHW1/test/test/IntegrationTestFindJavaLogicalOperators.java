package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import analyze.FindJavaLogicalOperators;
import analyze.IFindJavaLogicalOperators;
import analyze.ReadFile;
import analyze.RemoveUnnecessaryPartsOfString;

class IntegrationTestFindJavaLogicalOperators {

	private String content;
	private IFindJavaLogicalOperators logical;

	@BeforeEach
	void setUp() throws Exception {
		content = ReadFile.readFile("test-files/Logical.java");
		content = RemoveUnnecessaryPartsOfString.Remove(content);
		logical = new FindJavaLogicalOperators();
	}

	@Test
	void testFindJavaLogicalOperators() {
		logical.analyze(content);
		assertEquals(4, logical.getNumberOfOperators());
	}
	
	@ParameterizedTest
	@CsvSource({"test-files/Logical.java, 4", "test-files/Logical2.java, 7"})
	void testFindJavaLogicalOperators2(String filePath, long result) throws IOException {
		content = ReadFile.readFile(filePath);
		content = RemoveUnnecessaryPartsOfString.Remove(content);
		logical.analyze(content);
		assertEquals(result, logical.getNumberOfOperators());
	}

}
