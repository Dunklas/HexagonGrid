package dunk.hexagongrid.internal.strategies;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.GridBuilder;
import dunk.hexagongrid.GridFormation;
import dunk.hexagongrid.Hexagon;

public class HexagonalGridStrategyTest {

	@Test
	public void shouldCreateProperCoordinates() {
		Set<Coordinate> expectedValues = new HashSet<>();
		expectedValues.add(Coordinate.from(0, 0));
		expectedValues.add(Coordinate.from(1, -1));
		expectedValues.add(Coordinate.from(1, 0));
		expectedValues.add(Coordinate.from(0, 1));
		expectedValues.add(Coordinate.from(-1, 1));
		expectedValues.add(Coordinate.from(-1, 0));
		expectedValues.add(Coordinate.from(0, -1));
		
		Set<Coordinate> comparedValues = new HashSet<>();
		GridBuilder builder = new GridBuilder()
				.setRadius(1)
				.setFormation(GridFormation.HEXAGONAL)
				.setOrientation(Hexagon.Orientation.POINTY);
		comparedValues.addAll(new HexagonalGridStrategy().getCoordinates(builder));
		
		assertEquals(expectedValues, comparedValues);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionWhenBuildingWithTooHighRadius() {
		new GridBuilder()
		.setFormation(GridFormation.HEXAGONAL)
		.setRadius(501)
		.build();
	}
}
