package test;

import static org.junit.Assert.*;

import java.util.Vector;

import com.maths.MathTree;
import com.maths.TNode;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMathTree {

	MathTree tree;

	@Before
	public void setUp() throws Exception {
		tree = new MathTree();
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMathTreeString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testEvaluateDoubleDouble() {
		
		
	}

	@Test
	public void testEvaluateTNodeDoubleDouble() {
		TNode node = new TNode();
		assertEquals(0.0, tree.evaluate(node, 0.0, 0.0), 0.0);
		
		node.setValue("42");
		assertEquals(42, tree.evaluate(node, 0.0, 0.0), 0.0);
		assertEquals(42, tree.evaluate(node, 10.0, 0.0), 0.0);
		
		node.setLabel("-");
		assertEquals(42, tree.evaluate(node, 0.0, 0.0), 0.0);
		
		node.appendChild(new TNode("23"));
		node.appendChild(new TNode("43"));
		assertEquals(-20, tree.evaluate(node, 0.0, 0.0), 0.0);
		
		node = new TNode("10");
		node.setLabel("+");
		TNode child1 = new TNode("20");
		child1.setLabel("-");
		node.appendChild(child1);
		TNode child2 = new TNode("15");
		node.appendChild(child2);
		TNode child1a = new TNode("3");
		TNode child1b = new TNode("6");
		child1.appendChild(child1a);
		child1.appendChild(child1b);
		assertEquals(12, tree.evaluate(node, 0.0, 0.0),0.0);
		
	}


	@Test
	public void testBuildStringTreeString() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuildStringTreeTNode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEvaluateFunction() {
		assertEquals(null, tree.evaluateFunction(""));
		assertEquals(null, tree.evaluateFunction("1"));
		assertEquals("", tree.evaluateFunction("()"));
		assertEquals("SQRT", tree.evaluateFunction("SQRT()"));
		assertEquals("COS", tree.evaluateFunction("COS(SQRT())"));
		assertEquals("TAN", tree.evaluateFunction("TAN(COS(SQRT))"));
		assertEquals("COS", tree.evaluateFunction("COS(dflkjsdlfkjsdlfpookjsdlkfjsldkfjlsjd)"));
		assertEquals(null, tree.evaluateFunction("COS("));
		assertEquals(null, tree.evaluateFunction("CO()"));
	}

	@Test
	public void testAppendChildren() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCanDecomposeBySymbol() {	
		assertTrue(tree.canDecomposeBySymbol("1+1", "+"));
		assertTrue(tree.canDecomposeBySymbol("1+2/3", "+"));
		assertTrue(tree.canDecomposeBySymbol("1+/2", "+"));
		assertFalse(tree.canDecomposeBySymbol("1*2", "+"));
		assertTrue(tree.canDecomposeBySymbol("1+", "+"));
		assertTrue(tree.canDecomposeBySymbol("+", "+"));
		assertFalse(tree.canDecomposeBySymbol("(1+2)*3*4", "+"));
		assertFalse(tree.canDecomposeBySymbol("a", "+"));
		assertTrue(tree.canDecomposeBySymbol("a+b", "+"));
		assertFalse(tree.canDecomposeBySymbol("1/(-2)", "-"));
		assertTrue(tree.canDecomposeBySymbol("+*-", "*"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDecomposeBySymbol() {
		Vector v = new Vector();
		assertEquals(v, tree.decomposeBySymbol("",""));
		
		v.add("1");
		assertEquals(v, tree.decomposeBySymbol("1", "+"));
		
		v = new Vector();
		v.add("1 * 10");
		assertEquals(v, tree.decomposeBySymbol("1 * 10", "+"));
		
		v = new Vector();
		v.add("1");
		v.add("10");
		assertEquals(v, tree.decomposeBySymbol("1*10", "*"));
		
		v = new Vector();
		v.add("");
		v.add("");
		assertEquals(v, tree.decomposeBySymbol("+", "+"));
		
		v = new Vector();
		v.add("(10*10)");
		v.add("10");
		assertEquals(v, tree.decomposeBySymbol("(10*10)+10", "+"));
		
		v = new Vector();
		v.add("()");
		v.add("()");
		assertEquals(v, tree.decomposeBySymbol("()+()", "+"));
		
		v = new Vector();
		v.add("(1/2)");
		v.add("(3/4)");
		assertEquals(v, tree.decomposeBySymbol("(1/2)/(3/4)", "/"));
	}

	@Test
	public void testPrintBranchesWithLabel() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFormatVal() {
		assertEquals("0", MathTree.formatVal(0.0000001));
		assertEquals("0.000001", MathTree.formatVal(0.0000009));
		assertEquals("97", MathTree.formatVal('a'));
		assertEquals("179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", MathTree.formatVal(Double.MAX_VALUE));
		assertEquals("0", MathTree.formatVal(Double.MIN_VALUE));
		assertEquals("179769313486231570000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", MathTree.formatVal(Double.MAX_VALUE + 1));
		assertEquals("-1", MathTree.formatVal(Double.MIN_VALUE - 1));
		assertEquals("123", MathTree.formatVal(123));
		assertEquals("123", MathTree.formatVal(123L));
		assertFalse("123.1".equals(MathTree.formatVal(123.1f)));
		assertEquals("123.123123", MathTree.formatVal(123.1231231f));
	}

}
