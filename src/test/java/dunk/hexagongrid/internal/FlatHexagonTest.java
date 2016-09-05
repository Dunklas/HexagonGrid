package dunk.hexagongrid.internal;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Edge;
import dunk.hexagongrid.Grid;
import dunk.hexagongrid.GridBuilder;
import dunk.hexagongrid.GridFormation;
import dunk.hexagongrid.GridLayout;
import dunk.hexagongrid.Hexagon;
import dunk.hexagongrid.Point;
import dunk.hexagongrid.Vertice;

public final class FlatHexagonTest {

	private static final Hexagon.Orientation orientation = Hexagon.Orientation.FLAT;
	private static final GridFormation formation = GridFormation.HEXAGONAL;
	private static final int radius = 3;
	
	private Grid grid;
	
	@Before
	public void setUp() {
		grid = new GridBuilder()
				.setOrientation(orientation)
				.setFormation(formation)
				.setRadius(3)
				.build();
	}
	
	@Test
	public void getPointsShouldReturnProperPoints() {
		GridLayout layout = new GridLayout(1024/2, 768/2, 60);
		List<Point> expectedPoints = new ArrayList<>();
		expectedPoints.add(new Point(572.0, 384.0));
		expectedPoints.add(new Point(542.0, 332.0384757729337));
		expectedPoints.add(new Point(482.0, 332.0384757729337));
		expectedPoints.add(new Point(452.0, 384.0));
		expectedPoints.add(new Point(482.0, 435.9615242270663));
		expectedPoints.add(new Point(542.0, 435.9615242270663));
		
		List<Point> actualPoints = grid.getHexagon(Coordinate.from(0, 0)).getPoints(layout);
		
		for (int i = 0; i < expectedPoints.size(); i++) {
			Point p1 = expectedPoints.get(i);
			Point p2 = actualPoints.get(i);
			assertEquals(p1.getX(), p2.getX(), 0.001);
			assertEquals(p1.getY(), p2.getY(), 0.001);
		}
	}
	
	@Test
	public void getVerticeShouldReturnProperVertice() {
		Hexagon hexagon = grid.getHexagon(Coordinate.from(0, 0));
		Vertice northVertice = hexagon.getVertice(Vertice.Direction.WEST);
		
		assertEquals(northVertice.getType(), Vertice.Direction.WEST);
		assertEquals(northVertice.getParent(), hexagon);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getVerticeShouldThrowExceptionForInvalidDirection() {
		Hexagon hexagon = grid.getHexagon(Coordinate.from(0, 0));
		hexagon.getVertice(Vertice.Direction.NORTH);
	}
	
	@Test
	public void getEdgeShouldReturnProperEdge() {
		Hexagon hexagon = grid.getHexagon(Coordinate.from(0, 0));
		Edge westEdge = hexagon.getEdge(Edge.Direction.NORTH);
		
		assertEquals(westEdge.getType(), Edge.Direction.NORTH);
		assertEquals(westEdge.getParent(), hexagon);
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void getEdgeShouldThrowExceptionForInvalidDirection() {
		Hexagon hexagon = grid.getHexagon(Coordinate.from(0, 0));
		hexagon.getEdge(Edge.Direction.WEST);
	}
}
