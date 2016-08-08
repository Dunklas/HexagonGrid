package dunk.hexagongrid.internal;

import dunk.hexagongrid.Coordinate;

final class Neighbourhood {
	
	enum Regular {
		N1 (1, 0), // Pointy EAST, Flat SOUTH
		N2 (1, -1), // Pointy NORTH_EAST, Flat SOUTH_WEST
		N3 (0, -1), // Pointy NORTH_WEST, Flat NORTH_EAST
		N4 (-1, 0), // Pointy WEST, Flat NORTH
		N5 (-1, 1), // Pointy SOUTH_WEST, Flat NORTH_WEST
		N6 (0, 1); // Pointy SOUTH_EAST, Flat SOUTH_WEST
		
		final Coordinate coordinate;
		
		private Regular(int x, int y) {
			this.coordinate = Coordinate.from(x, y);
		}
	}
	
	enum Diagonal {
		D1 (-1, 2), // Pointy SOUTH, Flat WEST
		D2 (-2, 1), // Pointy SOUTH_WEST, Flat NORTH_WEST
		D3 (-1, -1), // Pointy NORTH_WEST, Flat NORTH_EAST
		D4 (1, -2), // Pointy NORTH, Flat EAST
		D5 (2, -1), // Pointy NORTH_EAST, Flat SOUTH_EAST
		D6 (1, 1); // Pointy SOUTH_EAST, Flat SOUTH_WEST
		
		final Coordinate coordinate;
		
		private Diagonal(int x, int y) {
			this.coordinate = Coordinate.from(x, y);
		}
	}

	private Neighbourhood(){}
	
	static Coordinate adjacentTo(Coordinate coord, Neighbourhood.Regular direction) {
		Coordinate regularNeighbour = CoordinateCalculator.add(
				coord,
				direction.coordinate);
		return regularNeighbour; 
	}
	
	static Coordinate diagonalTo(Coordinate coord, Neighbourhood.Diagonal direction) {
		Coordinate diagonalNeighbour = CoordinateCalculator.add(
				coord,
				direction.coordinate);
		return diagonalNeighbour;
	}
}
