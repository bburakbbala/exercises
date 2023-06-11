package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import analyze.FindJavaRelationalOperators;
import analyze.IFindJavaRelationalOperators;
import analyze.ReadFile;
import analyze.RemoveUnnecessaryPartsOfString;

class IntegrationTestFindJavaRelationalOperators {

	private String content;
	private IFindJavaRelationalOperators relational;

	@BeforeEach
	void setUp() throws Exception {
		content = ReadFile.readFile("test-files/Relational.java");
		content = RemoveUnnecessaryPartsOfString.Remove(content);
		relational = new FindJavaRelationalOperators();
	}

	@Test
	void testFindJavaRelationOperators() {
		relational.analyze(content);
		assertEquals(6, relational.getNumberOfOperators());
	}
	
	@ParameterizedTest
	@CsvSource({"test-files/Relational.java, 6", "test-files/Relational2.java, 2"})
	void testFindJavaRelationalOperators2(String filePath, long result) throws IOException {
		content = ReadFile.readFile(filePath);
		content = RemoveUnnecessaryPartsOfString.Remove(content);
		relational.analyze(content);
		assertEquals(result, relational.getNumberOfOperators());
	}

}
