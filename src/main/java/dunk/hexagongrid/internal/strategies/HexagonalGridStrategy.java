package dunk.hexagongrid.internal.strategies;

import java.util.HashSet;
import java.util.Set;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.GridBuilder;

public final class HexagonalGridStrategy extends GridStrategy {

	@Override
	public Set<Coordinate> getCoordinates(final GridBuilder builder) {
		Set<Coordinate> grid = new HashSet<>();
		int radius = builder.getRadius();
		for (int x = -radius; x <= radius; x++) {
			int y1 = Math.max(-radius, -x -radius);
			int y2 = Math.min(radius, -x + radius);
			for (int y = y1; y <= y2; y++) {
				Coordinate coord = Coordinate.from(x, y);
				grid.add(coord);
				}
			}
		return grid;
	}

	@Override
	public void verifyBuilder(final GridBuilder builder) {
		if (builder.getRadius() > 500)
			throw new IllegalArgumentException("Illegal radius: " + builder.getRadius() + ". Must be less than 0 for formation: " + builder.getFormation());
	}
}
