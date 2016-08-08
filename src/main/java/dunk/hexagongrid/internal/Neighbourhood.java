package dunk.hexagongrid.internal;

import dunk.hexagongrid.Coordinate;

final class Neighbourhood {
	
	enum Regular {
		N1 (1, 0), // Pointy EAST, Flat SOUTH_EAST
		N2 (1, -1), // Pointy NORTH_EAST, Flat NORTH_EAST
		N3 (0, -1), // Pointy NORTH_WEST, Flat NORTH
		N4 (-1, 0), // Pointy WEST, Flat NORTH_WEST
		N5 (-1, 1), // Pointy SOUTH_WEST, Flat SOUTH_WEST
		N6 (0, 1); // Pointy SOUTH_EAST, Flat SOUTH
		
		final Coordinate coordinate;
		
		private Regular(int x, int y) {
			this.coordinate = Coordinate.from(x, y);
		}
	}
	
	enum Diagonal {
		D1 (-1, 2), // Pointy SOUTH, Flat SOUTH_WEST
		D2 (-2, 1), // Pointy SOUTH_WEST, Flat WEST
		D3 (-1, -1), // Pointy NORTH_WEST, Flat NORTH_WEST
		D4 (1, -2), // Pointy NORTH, Flat NORTH_EAST
		D5 (2, -1), // Pointy NORTH_EAST, Flat EAST
		D6 (1, 1); // Pointy SOUTH_EAST, Flat SOUTH_EAST
		
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
