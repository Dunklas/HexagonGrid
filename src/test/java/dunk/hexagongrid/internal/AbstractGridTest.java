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
	@Test
	public void getHexagonByPixelShouldReturnProperHexagon() {
		GridLayout layout = new GridLayout(1024/2, 768/2, 60, 60);
		double[] pixelX = {511, 252, 205};
		double[] pixelY = {386, 378, 477};
		Coordinate[] expectedCoord = {Coordinate.from(0, 0), Coordinate.from(-3, 0), Coordinate.from(-3, 1)};
		
		for (int i = 0; i < pixelX.length; i++) {
			Coordinate tempCoord = grid.getHexagon(pixelX[i], pixelY[i], layout).getCoordinate();
			assertEquals(tempCoord, expectedCoord[i]);
		}
	}
	
	@Test(expected=HexagonOutOfBoundsException.class)
	public void getHexagonByPixelShouldThrowExceptionForOutsideGrid() {
		GridLayout layout = new GridLayout(1024/2, 768/2, 60, 60);
		double x = 146;
		double y = 385;
		grid.getHexagon(x, y, layout);
	}
}
