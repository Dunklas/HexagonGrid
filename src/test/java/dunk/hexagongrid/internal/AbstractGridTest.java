package dunk.hexagongrid.internal;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Grid;
import dunk.hexagongrid.GridBuilder;
import dunk.hexagongrid.Hexagon;
import dunk.hexagongrid.HexagonOutOfBoundsException;

public final class AbstractGridTest {

	Grid pointyGrid;
	GridBuilder pointyBuilder;
	Grid flatGrid;
	GridBuilder flatBuilder;
	
	@Before
	public void setUp() {
		pointyBuilder  	= new GridBuilder()
				.setOrientation(Hexagon.Orientation.POINTY)
				.setRadius(3);
		flatBuilder		= new GridBuilder()
				.setOrientation(Hexagon.Orientation.FLAT)
				.setRadius(3);
		pointyGrid 	= pointyBuilder.build();
		flatGrid 	= flatBuilder.build();
		
	}
	
	@Test
	public void getHexagonsShouldReturnProperly() {
		Set<Hexagon> pointyHexagons = new HashSet<>();
		for (Coordinate c : pointyBuilder.getFormation().getStrategy().getCoordinates(pointyBuilder)) {
			pointyHexagons.add(new PointyHexagon(c));
		}
		assertEquals(pointyHexagons, pointyGrid.getHexagons());
		
		Set<Hexagon> flatHexagons = new HashSet<>();
		for (Coordinate c : flatBuilder.getFormation().getStrategy().getCoordinates(flatBuilder)) {
			flatHexagons.add(new FlatHexagon(c));
		}
		assertEquals(flatHexagons, flatGrid.getHexagons());
	}
	
	@Test
	public void getHexagonShouldReturnProperHexagon() {
		Hexagon pointyHexagon = new PointyHexagon(Coordinate.from(3, 0));
		assertEquals(pointyGrid.getHexagon(Coordinate.from(3, 0)), pointyHexagon);
		
		Hexagon flatHexagon	= new FlatHexagon(Coordinate.from(2, -1));
		assertEquals(flatGrid.getHexagon(Coordinate.from(2, -1)), flatHexagon);
	}
	
	@Test(expected=HexagonOutOfBoundsException.class)
	public void getHexagonPointyShouldThrowExceptionForInvalidCoordinate() {
		pointyGrid.getHexagon(Coordinate.from(5, -1));
	}
	
	@Test(expected=HexagonOutOfBoundsException.class)
	public void getHexagonFlatShouldThrowExceptionForInvalidCoordinate() {
		flatGrid.getHexagon(Coordinate.from(5, -1));
	}
}
