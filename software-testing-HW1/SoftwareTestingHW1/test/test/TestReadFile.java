package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.RepeatedTest;

import analyze.ReadFile;

class TestReadFile {

	@RepeatedTest(3)
	void testFile() throws IOException {
		String content = ReadFile.readFile("test-files/Binary.java");
		assertEquals(true, !content.equals(""));
	}
	
	@RepeatedTest(3)
	void testFile2() throws IOException {
		String content = ReadFile.readFile("test-files/Binary2.java");
		assertEquals(true, !content.equals(""));
	}
	
	@RepeatedTest(3)
	void testFile3() throws IOException {
		String content = ReadFile.readFile("test-files/Unary.java");
		assertEquals(true, !content.equals(""));
	}
}
