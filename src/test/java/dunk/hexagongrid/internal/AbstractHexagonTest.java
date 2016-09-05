package dunk.hexagongrid.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

public final class AbstractHexagonTest {

	private static final Hexagon.Orientation orientation = Hexagon.Orientation.POINTY;
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
	public void getCentrePointShouldReturnProperPoint() {
		for (int i = 30; i <= 90; i+=30) {
			GridLayout layout = new GridLayout(1024/2, 768/2, i);
			Point p = grid.getHexagon(Coordinate.from(0, 0)).getCentrePoint(layout);
			assertEquals(p.getX(), 1024/2, 0.001);
			assertEquals(p.getY(), 768/2, 0.001);
		}
	}
	
	@Test
	public void getVerticesShouldReturnProperAmountOfVertices() {
		Hexagon hex = grid.getHexagon(Coordinate.from(0, 0));
		Set<Vertice> vertices = hex.getVertices();
		assertEquals(vertices.size(), 2);
	}
	
	@Test
	public void getEdgesShouldReturnProperAmountOfEdges() {
		Hexagon hex = grid.getHexagon(Coordinate.from(0, 0));
		Set<Edge> edges = hex.getEdges();
		assertEquals(edges.size(), 3);
	}
	
	@Test
	public void hexagonEqualityTest() {
		Coordinate c1 = Coordinate.from(0, 1);
		
		Hexagon ph1 = new PointyHexagon(c1);
		Hexagon fh1 = new FlatHexagon(c1);
		assertNotEquals(ph1, null);
		assertNotEquals(fh1, null);
		assertEquals(ph1, ph1);
		assertEquals(fh1, fh1);
		assertEquals(ph1, fh1);
		
		Coordinate c2 = Coordinate.from(2, 0);
		Hexagon ph2 = new PointyHexagon(c1);
		Hexagon ph3 = new PointyHexagon(c2);
		assertNotEquals(ph2, ph3);
		
		Hexagon fh2 = new FlatHexagon(c1);
		Hexagon fh3 = new FlatHexagon(c2);
		assertNotEquals(fh2, fh3);
		
		assertNotEquals(ph2, fh3);
	}
}
