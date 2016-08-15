package dunk.hexagongrid;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PointTest {

	@Test
	public void creationOfPoint() {
		final double x = 8;
		final double y = 16;
		Point p = new Point(8, 16);
		assertEquals(x, p.getX(), 0.001);
		assertEquals(y, p.getY(), 0.001);
	}
	
	@Test
	public void centerOfTwoPoints() {
		Point p1 = new Point(-1, 2);
		Point p2 = new Point(3, -6);
		Point center = new Point(1, -2);
		Point calculatedCenter = Point.centerOf(p1, p2);
		assertEquals(center.getX(), calculatedCenter.getX(), 0.001);
		assertEquals(center.getY(), calculatedCenter.getY(), 0.001);
	}
	
	@Test
	public void distanceBetweenTwoPoints() {
		Point p1 = new Point(-1, 2);
		Point p2 = new Point(3, -6);
		
		assertEquals("distance between p1 and p2 should be equal to distance between p2 and p1",
				p1.distanceTo(p2),
				p2.distanceTo(p1),
				0.001);
	}
}
