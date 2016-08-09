package dunk.hexagongrid.internal;

import org.junit.Test;

import dunk.hexagongrid.Coordinate;

import static org.junit.Assert.assertEquals;

public class CoordinateCalculatorTest {

	@Test
	public void addCoordinateTest() {
		Coordinate c1 = Coordinate.from(3, 0);
		Coordinate c2 = Coordinate.from(-2, 2);
		Coordinate expectedResult = Coordinate.from(1, 2);
		assertEquals(expectedResult, CoordinateCalculator.add(c1, c2));
	}
	
	@Test
	public void subtractCoordinateTest() {
		Coordinate c1 = Coordinate.from(3, 0);
		Coordinate c2 = Coordinate.from(-2, 2);
		Coordinate expectedResult = Coordinate.from(5, -2);
		assertEquals(expectedResult, CoordinateCalculator.subtract(c1, c2));
	}
	
	@Test
	public void multiplyCoordinateTest() {
		Coordinate c1 = Coordinate.from(3, 1);
		int k = 3;
		Coordinate expectedResult = Coordinate.from(3*k, 1*k);
		assertEquals(expectedResult, CoordinateCalculator.multiply(c1, k));
	}
	
	@Test
	public void roundCoordinateTest() {
		FractionalCoordinate f1 = FractionalCoordinate.from(-1.910683602522959, 2.6666666665);
		Coordinate c1 = Coordinate.from(-2, 3);
		
		FractionalCoordinate f2 = FractionalCoordinate.from(-1.821367205045918, 1.3333333);
		Coordinate c2 = Coordinate.from(-2, 1);
		
		assertEquals(c1, CoordinateCalculator.round(f1));
		assertEquals(c2, CoordinateCalculator.round(f2));
	}
}
