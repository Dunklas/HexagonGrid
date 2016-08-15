package dunk.hexagongrid;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CoordinateTest {

	@Test
	public void equalityOfCoordinates() {
		Coordinate c1 = Coordinate.from(2, 0);
		Coordinate c2 = Coordinate.from(2, 0, -2);
		assertEquals(c1, c2);
		
		Coordinate c3 = Coordinate.from(2, 0);
		Coordinate c4 = Coordinate.from(0, 2);
		assertNotEquals(c3, c4);
		
		Grid g1 = GridFactory.createGrid(1, Grid.Formation.HEXAGON, Hexagon.Orientation.POINTY);
		Coordinate c5 = Coordinate.from(1, 0);
		Hexagon h1 = g1.getHexagon(c5);
		assertNotEquals(c5, h1);
		
		Grid g2 = GridFactory.createGrid(1, Grid.Formation.HEXAGON, Hexagon.Orientation.FLAT);
		Coordinate c6 = Coordinate.from(-1, 0);
		Hexagon h2 = g2.getHexagon(c6);
		assertNotEquals(c6, h2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwExceptionForIllegalCoordinates() {
		int x = 3;
		int y = 5;
		int z = 11;
		Coordinate c1 = Coordinate.from(x, y, z);
	}
	
	@Test
	public void attributesOfCoordinates() {
		int x = 2;
		int y = -3;
		int z = -x-y;
		
		Coordinate c1 = Coordinate.from(x, y);
		assertEquals(x, c1.getX());
		assertEquals(y, c1.getY());
		assertEquals(z, c1.getZ());
		
		Coordinate c2 = Coordinate.from(x, y, z);
		assertEquals(x, c2.getX());
		assertEquals(y, c2.getY());
		assertEquals(z, c2.getZ());
	}
}
