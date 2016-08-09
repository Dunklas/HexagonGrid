package dunk.hexagongrid;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PointTest {

	@Test
	public void creationOfPoint() {
		final long x = 8;
		final long y = 16;
		Point p = new Point(8, 16);
		assertEquals("X should equal X", x, p.getX());
		assertEquals("Y should equal Y", y, p.getY());
	}
	
	@Test
	public void centerOfTwoPoints() {
		Point p1 = new Point(-1, 2);
		Point p2 = new Point(3, -6);
		Point center = new Point(1, -2);
		Point calculatedCenter = Point.centerOf(p1, p2);
		assertEquals("calculatedCenter should be equal to center", center.getX(), calculatedCenter.getX());
		assertEquals("calculatedCenter should be equal to center", center.getY(), calculatedCenter.getY());
	}
	
	@Test
	public void distanceBetweenTwoPoints() {
		Point p1 = new Point(-1, 2);
		Point p2 = new Point(3, -6);
		
		assertEquals("distance between p1 and p2 should be equal to distance between p2 and p1",
				p1.distanceTo(p2),
				p2.distanceTo(p1),
				0);
	}
}
