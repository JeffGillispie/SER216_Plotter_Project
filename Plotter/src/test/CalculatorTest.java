package test;

import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.main.Visualizer;
import com.maths.Calculator;

public class CalculatorTest {
	private Calculator calc;
	private final double EPSILON = 0.001;

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
	public void testGetFunction() {
		//

		double[][] testResult = calc.getFunction();
		
		for (double[] coordinates : testResult) {
			assertEquals(Math.sin(coordinates[0]), coordinates[1], 0.00000001);
		}
	}
	
	@Test
	public void testGet3DFunction() {
		calc.draw3D(new BufferedImage(Visualizer.WIDTH,Visualizer.HEIGHT,BufferedImage.TYPE_INT_RGB));
		calc.DISPLAYED_FUNCTION = "sin(x+y)";
		double[][][] testResult = calc.getFunction3D();
		List<String> errors = new ArrayList<String>();
		
		for(double[][] values : testResult) {
			for (double[] coordinates : values) {
				double expected = Math.sin(coordinates[0] + coordinates[1]);
				double actual = coordinates[2];
				if (expected != actual)
				{
					errors.add(String.format("x=%1$.5f, y=%2$.5f, z=%3$.5f, exp=%4$.5f, act=%5$.5f", coordinates[0], coordinates[1], coordinates[2], expected, actual));
				}
				assertEquals(expected, actual, EPSILON);
			}
		}
		
		String s = "blah";
	}

}
