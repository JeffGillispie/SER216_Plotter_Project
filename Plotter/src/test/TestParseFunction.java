package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maths.ParseFunction;

public class TestParseFunction {

	private ParseFunction pf;
	
	@Before
	public void setUp() throws Exception {
		pf = new ParseFunction();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParseFunction() {
		pf.function = "100.0";
		assertEquals(100.0, pf.parseFunction(), 0.0);
		
		pf.function = "10 + 10";
		assertEquals(20, pf.parseFunction(), 0.0);
		
		pf.function = "";
		assertEquals(1.0, pf.parseFunction(), 0.0);
		
		NumberFormatException varFuncEx = null;
		pf.function = "x";
		try {
			pf.parseFunction();
		} catch (NumberFormatException ex) {
			varFuncEx = ex;
		}
		assertTrue(varFuncEx != null && varFuncEx instanceof NumberFormatException);
		
		NumberFormatException noMidOperandEx = null;
		pf.function = "10 + * 100";
		try {
			pf.parseFunction();
		} catch (NumberFormatException ex) {
			noMidOperandEx = ex;
		}
		assertTrue(noMidOperandEx != null && noMidOperandEx instanceof NumberFormatException);
		
		//StackOverflowError beginParensOnlyErr = null;
		NumberFormatException beginParensOnlyErr = null;
		pf.function = "(";
		try {
			pf.parseFunction();
		} catch (NumberFormatException err) {
			beginParensOnlyErr = err;
		}
		assertTrue(beginParensOnlyErr != null && beginParensOnlyErr instanceof NumberFormatException);
		
		
		StringIndexOutOfBoundsException endParensEx = null;
		pf.function = ")";
		try {
			pf.parseFunction();
		} catch (StringIndexOutOfBoundsException ex) {
			endParensEx = ex;
		}
		assertTrue(endParensEx != null && endParensEx instanceof StringIndexOutOfBoundsException);
		
		pf.function = "()";
		assertEquals(0.0, pf.parseFunction(), 0.0);
				
		pf.function = "(1)";
		assertEquals(1.0, pf.parseFunction(),0.0);
				
		pf.function = "(1 + 1)";
		assertEquals(2.0, pf.parseFunction(), 0.0);
		
		pf.function = "sin()";
		assertEquals(1.0, pf.parseFunction(), 0.0);
		
	}

	@Test
	public void testFormatVal() {
		assertEquals("0", ParseFunction.formatVal(0.0000001));
		assertEquals("0.000001", ParseFunction.formatVal(0.0000009));
		assertEquals("97", ParseFunction.formatVal('a'));
		assertEquals("179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", ParseFunction.formatVal(Double.MAX_VALUE));
		assertEquals("0", ParseFunction.formatVal(Double.MIN_VALUE));
		assertEquals("179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", ParseFunction.formatVal(Double.MAX_VALUE + 1));
		assertEquals("-1", ParseFunction.formatVal(Double.MIN_VALUE - 1));
		assertEquals("123", ParseFunction.formatVal(123));
		assertEquals("123", ParseFunction.formatVal(123L));
		assertFalse("123.1".equals(ParseFunction.formatVal(123.1f)));
		assertEquals("123.123123", ParseFunction.formatVal(123.1231231f));
	}

	@Test
	public void testGetFunction() {
		pf.setFunction("x");
		assertEquals("x", pf.getFunction());
		
		pf.setFunction("");
		assertEquals("", pf.getFunction());
		
		pf.setFunction(null);
		assertEquals(null, pf.getFunction());
		
		pf.setFunction("\t\t\t");
		assertEquals("\t\t\t", pf.getFunction());
		
		pf.setFunction("" + 123);
		assertEquals("123", pf.getFunction());
	}

	@Test
	public void testSetFunction() {
		pf.setFunction("x");
		assertEquals("x", pf.function);
		
		pf.setFunction("");
		assertEquals("", pf.function);
		
		pf.setFunction(null);
		assertEquals(null, pf.function);
		
		pf.setFunction("\t\t\t");
		assertEquals("\t\t\t", pf.function);
		
		pf.setFunction("" + 123);
		assertEquals("123", pf.function);
	}

}
