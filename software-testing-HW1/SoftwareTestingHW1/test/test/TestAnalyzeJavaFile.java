package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import analyze.AnalyzeJavaFile;
import analyze.FindJavaArithmeticOperators;
import analyze.FindJavaBinaryOperators;
import analyze.FindJavaLogicalOperators;
import analyze.FindJavaRelationalOperators;
import analyze.FindJavaUnaryOperators;
import analyze.IFindArithmeticOperators;
import analyze.IFindJavaBinaryOperators;
import analyze.IFindJavaLogicalOperators;
import analyze.IFindJavaRelationalOperators;
import analyze.IFindJavaUnaryOperators;

@ExtendWith(MockitoExtension.class)
class TestAnalyzeJavaFile {
	@Mock
	private IFindArithmeticOperators arithmeticMock;
	
	@Mock
	private IFindJavaUnaryOperators unaryMock;
	
	@Mock
	private IFindJavaRelationalOperators relationalMock;
	
	@Mock
	private IFindJavaLogicalOperators logicalMock;
	
	@Mock
	private IFindJavaBinaryOperators binaryMock;
	
	@InjectMocks
	private AnalyzeJavaFile analyzeMock;
	
	private String filePath;

	@BeforeEach
	void setUp() throws Exception {
		filePath = "test-files/Main.java";
        analyzeMock = new AnalyzeJavaFile(filePath);
	}

	@Test
	void testAnalyzeUnary() {
		Mockito.when(unaryMock.findNumberOfDecrementOperators(Mockito.anyString()));
		analyzeMock.findJavaUnaryOperators();
		assertEquals(Long.class, analyzeMock.findJavaUnaryOperators());
	}

}
