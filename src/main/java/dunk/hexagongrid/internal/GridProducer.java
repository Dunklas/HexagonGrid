package dunk.hexagongrid.internal;

import java.util.Collection;
import java.util.HashSet;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Grid;

final class GridProducer {
	
	private GridProducer() {}

	static Collection<Coordinate> getCoordinates(Grid.Formation formation, int radius) {
		switch (formation) {
			case HEXAGON: 
				return createHexagonGrid(radius);
			default: 
				return createHexagonGrid(radius);
		}
	}
	
	private static Collection<Coordinate> createHexagonGrid(int radius) {
		Collection<Coordinate> grid = new HashSet<>();
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
