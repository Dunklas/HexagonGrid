package dunk.hexagongrid.internal;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Grid;
import dunk.hexagongrid.GridBuilder;
import dunk.hexagongrid.GridFormation;
import dunk.hexagongrid.GridLayout;
import dunk.hexagongrid.Hexagon;
import dunk.hexagongrid.HexagonOutOfBoundsException;

public final class AbstractGridTest {

	private static final Hexagon.Orientation orientation = Hexagon.Orientation.POINTY;
	private static final GridFormation formation = GridFormation.HEXAGONAL;
	private static final int radius = 3;
	
	private Grid grid;
	private GridBuilder builder;
	
	@Before
	public void setUp() {
		builder = new GridBuilder()
				.setOrientation(orientation)
				.setFormation(formation)
				.setRadius(3);
		
		grid = builder.build();
	}
	
	@Test
	public void getHexagonsShouldReturnProperly() {
		Set<Hexagon> hexagons = new HashSet<>();
		for (Coordinate c : builder.getFormation().getStrategy().getCoordinates(builder)) {
			hexagons.add(new PointyHexagon(c));
		}
		assertEquals(hexagons, grid.getHexagons());
	}
	
	@Test
	public void getHexagonByCoordShouldReturnProperHexagon() {
		Hexagon hexagon = new PointyHexagon(Coordinate.from(radius, 0));
		assertEquals(grid.getHexagon(Coordinate.from(radius, 0)), hexagon);
	}
	
	@Test(expected=HexagonOutOfBoundsException.class)
	public void getHexagonByCoordShouldThrowExceptionForInvalidCoordinate() {
		grid.getHexagon(Coordinate.from(radius+1, -1));
	}
	
	
	// These tests assume the pixel coordinates are in a 1024x768 window, with the grids origin at 1024/2, 768/2
	// I've used pixels which should return same Hexagon, both for pointy and flat grids
	// If ever implementing more formations, this needs to be changed
	@Test
	public void getHexagonByPixelShouldReturnProperHexagon() {
		GridLayout layout = new GridLayout(1024/2, 768/2, 60);
		int[] xcoord = {556, 499, 510};
		int[] ycoord = {339, 323, 385};
		Coordinate expectedCoord[] = {Coordinate.from(1, -1), Coordinate.from(0, -1), Coordinate.from(0, 0)};
		
		for (int i = 0; i < xcoord.length; i++) {
			Hexagon tempHex = grid.getHexagon(xcoord[i], ycoord[i], layout);
			assertEquals(tempHex.getCoordinate(), expectedCoord[i]);
		}
	}
	
	@Test(expected=HexagonOutOfBoundsException.class)
	public void getHexagonByPixelShouldThrowExceptionForOutsideGrid() {
		GridLayout layout = new GridLayout(1024/2, 768/2, 60);
		double x = 272;
		double y = 156;
		grid.getHexagon(x, y, layout);
	}
	
	@Test
	public void getRangeShouldReturnProperHexagons() {
		Set<Hexagon> expectedHexagons = new HashSet<>();
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-1, -1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(0, -1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(0, 0)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-1, 1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-2, 1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-2, 0)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-1, 0)));
		
		Hexagon targetHex = grid.getHexagon(Coordinate.from(-1, 0));
		assertEquals(grid.getRange(targetHex, 1), expectedHexagons);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getRangeShouldThrowExceptionForInvalidRange() {
		Hexagon testHex = new PointyHexagon(Coordinate.from(radius, 0));
		grid.getRange(testHex, radius+1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getRangeShouldThrowExceptionForInvalidHexagon() {
		Hexagon testHex = new PointyHexagon(Coordinate.from(radius+1, 0));
		grid.getRange(testHex, radius);
	}
	
	@Test
	public void getRingShouldReturnProperHexagons() {
		Set<Hexagon> expectedHexagons = new HashSet<>();
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-1, -1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(0, -1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(0, 0)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-1, 1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-2, 1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-2, 0)));
		
		Hexagon targetHex = grid.getHexagon(Coordinate.from(-1, 0));
		assertEquals(grid.getRing(targetHex, 1), expectedHexagons);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getRingShouldThrowExceptionForInvalidRange() {
		Hexagon testHex = new PointyHexagon(Coordinate.from(radius, 0));
		grid.getRing(testHex, radius+1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getRingShouldThrowExceptionForInvalidHexagon() {
		Hexagon testHex = new PointyHexagon(Coordinate.from(radius+1, 0));
		grid.getRing(testHex, radius);
	}
	
	@Test
	public void hexagonsNearShouldReturnProperNeighbours() {
		Set<Hexagon> expectedHexagons = new HashSet<>();
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-1, -1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(0, -1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(0, 0)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-1, 1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-2, 1)));
		expectedHexagons.add(new PointyHexagon(Coordinate.from(-2, 0)));
		
		Hexagon targetHex = grid.getHexagon(Coordinate.from(-1, 0));
		assertEquals(grid.hexagonsNear(targetHex), expectedHexagons);
	}
	
}
