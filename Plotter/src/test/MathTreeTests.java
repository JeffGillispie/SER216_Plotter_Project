package test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.maths.MathTree;
import com.maths.TNode;

public class MathTreeTests {
	private MathTree tree;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		tree = new MathTree("sin(x+y)");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEvaluateFor3dFunction() {
		tree = new MathTree("sin(x+y)");
		double x = 3.68724;
		double y = 3.90414;
		
		double e = 0.96572;
		
		double result = tree.evaluate(x, y);
		
		assertEquals(result, e, 0.00001);
	}

	@Test
	public void testAppendChildren() {
		Vector vector = new Vector();
		vector.add("one");
		vector.add("two");
		vector.add("three");
		TNode actualNode = new TNode("root");
		tree.appendChildren(actualNode, vector);
		TNode expectedNode = new TNode("root");
		expectedNode.appendChild(new TNode("one"));
		expectedNode.appendChild(new TNode("two"));
		expectedNode.appendChild(new TNode("three"));
		assertEquals(expectedNode.getValue(), actualNode.getValue());
		
		for (int i = 0; i < 3; i++) {
			assertEquals(expectedNode.getChildAt(i).getValue(), actualNode.getChildAt(i).getValue());
		}
	}
	
	@Test
	public void testCanDecomposeBySymbol() {
		// test 1
		String value = "2*2";
		String symbol = "*";
		boolean result = tree.canDecomposeBySymbol(value, symbol); 
		System.out.println(String.format("MathTree.canDecomposeBySymbol(\"%s\", \"%s\") = %s", value, symbol, result));
		assertTrue(result);
		// test 2
		value = "2+*2";
		symbol = "*";
		result = tree.canDecomposeBySymbol(value, symbol);
		System.out.println(String.format("MathTree.canDecomposeBySymbol(\"%s\", \"%s\") = %s", value, symbol, result));
		assertFalse(result);
		// test 3
		value = "2-*2";
		symbol = "*";
		result = tree.canDecomposeBySymbol(value, symbol);
		System.out.println(String.format("MathTree.canDecomposeBySymbol(\"%s\", \"%s\") = %s", value, symbol, result));
		assertFalse(result);
		// test 4
		value = "2+-2";
		symbol = "-";
		result = tree.canDecomposeBySymbol(value, symbol);
		System.out.println(String.format("MathTree.canDecomposeBySymbol(\"%s\", \"%s\") = %s", value, symbol, result));
		assertFalse(result);
		// test 5
		value = "2-+2";
		symbol = "+";
		result = tree.canDecomposeBySymbol(value, symbol);
		System.out.println(String.format("MathTree.canDecomposeBySymbol(\"%s\", \"%s\") = %s", value, symbol, result));
		assertFalse(result);
		// test 6
		value = "(2+2)*3/(2-1)";
		symbol = "/";
		result = tree.canDecomposeBySymbol(value, symbol); 
		System.out.println(String.format("MathTree.canDecomposeBySymbol(\"%s\", \"%s\") = %s", value, symbol, result));
		assertTrue(result);
		// test 7
		value = "2+/2";
		symbol = "/";
		result = tree.canDecomposeBySymbol(value, symbol);
		System.out.println(String.format("MathTree.canDecomposeBySymbol(\"%s\", \"%s\") = %s", value, symbol, result));
		assertFalse(result);
		// test 8
		value = "2-/2";
		symbol = "/";
		result = tree.canDecomposeBySymbol(value, symbol);
		System.out.println(String.format("MathTree.canDecomposeBySymbol(\"%s\", \"%s\") = %s", value, symbol, result));
		assertFalse(result);
		// test 9
		value = "2++2";
		symbol = "+";
		result = tree.canDecomposeBySymbol(value, symbol);
		System.out.println(String.format("MathTree.canDecomposeBySymbol(\"%s\", \"%s\") = %s", value, symbol, result));
		assertTrue(result); // should this be false?
		// test 10
		value = "2**2";
		symbol = "*";
		result = tree.canDecomposeBySymbol(value, symbol);
		System.out.println(String.format("MathTree.canDecomposeBySymbol(\"%s\", \"%s\") = %s", value, symbol, result));
		assertTrue(result); // should this be false?
	}

}
