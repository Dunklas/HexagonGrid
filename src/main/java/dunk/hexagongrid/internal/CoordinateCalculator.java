package dunk.hexagongrid.internal;

import dunk.hexagongrid.Coordinate;

final class CoordinateCalculator {
	
	private CoordinateCalculator() {}
	
	static Coordinate add(Coordinate c1, Coordinate c2) {
		return Coordinate.from(
				c1.getX() + c2.getX(),
				c1.getY() + c2.getY(),
				c1.getZ() + c2.getZ());
	}
	
	static Coordinate subtract(Coordinate c1, Coordinate c2) {
		return Coordinate.from(
				c1.getX() - c2.getX(),
				c1.getY() - c2.getY(),
				c1.getZ() - c2.getZ());
	}
	
	static Coordinate multiply(Coordinate c, int k) {
		return Coordinate.from(
				c.getX() * k,
				c.getY() * k,
				c.getZ() * k);
	}
	
	static Coordinate round(FractionalCoordinate coord) {
		int x = (int) Math.round(coord.getX());
		int y = (int) Math.round(coord.getY());
		int z = (int) Math.round(coord.getZ());
		double xDiff = Math.abs(x - coord.getX());
		double yDiff = Math.abs(y - coord.getY());
		double zDiff = Math.abs(z - coord.getZ());
		
		if (xDiff > yDiff && xDiff > zDiff)
			x = -y - z;
		else if (yDiff > zDiff)
			y = -x - z;
		else
			z = -x - y;
		
		return Coordinate.from(x, y, z);
	}

}
