package dunk.hexagongrid.internal.strategies;

import java.util.Collection;
import java.util.HashSet;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.GridBuilder;

public final class HexagonalGridStrategy extends GridStrategy {

	@Override
	public Collection<Coordinate> getCoordinates(GridBuilder builder) {
		Collection<Coordinate> grid = new HashSet<>();
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
}
