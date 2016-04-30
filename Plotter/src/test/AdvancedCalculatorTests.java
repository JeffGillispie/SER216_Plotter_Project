package test;

import com.main.Visualizer;
import com.maths.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdvancedCalculatorTests {
	
	private final double EPSILON = 0.001;
	private Calculator calc; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		calc = new Calculator(Visualizer.WIDTH, Visualizer.HEIGHT);
		calc.draw(null, -10,  10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDerivativeFunction() {
		double testResult = AdvancedCalculator.df(0, calc);
		assertEquals(1, testResult, EPSILON);
		testResult = AdvancedCalculator.df(Math.PI/2, calc);
		assertEquals(0, testResult, EPSILON);
		testResult = AdvancedCalculator.df(-Math.PI/2, calc);
		assertEquals(0, testResult, EPSILON);
		testResult = AdvancedCalculator.df(-Math.PI, calc);
		assertEquals(-1, testResult, EPSILON);
		testResult = AdvancedCalculator.df(Math.PI, calc);
		assertEquals(-1, testResult, EPSILON);
		
	}
	
	@Test
	public void testSimpsonIntegral() {
		calc.a = 0;
		calc.b = 2;
		double testResult = AdvancedCalculator.SimpsonIntegral(calc);
		assertEquals(1.416, testResult, EPSILON);
	}
	
	@Test
	public void testTrapeziumIntegral() {
		calc.a = 0;
		calc.b = 2;
		double testResult = AdvancedCalculator.trapeziumIntegral(calc);
		assertEquals(1.416, testResult, EPSILON);
	}
	
	@Test
	public void testGaussIntegral() {
		calc.a = 0;
		calc.b = 2;
		double testResult = AdvancedCalculator.gaussIntegral(calc);
		assertEquals(1.416, testResult, EPSILON);
	}

}
