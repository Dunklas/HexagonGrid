package dunk.hexagongrid;

/**
 * Represents the coordinate of a {@link Hexagon} in a {@link Grid}
 * This implementation of a coordinate is described as a axial/cube-coordinate in http://www.redblobgames.com/grids/hexagons/#coordinates.
 */
public final class Coordinate {
	
	private final int x; // Q = X
	private final int y; // R = Y
						 // S = Z
	
	private Coordinate(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates an instance of Coordinate from x, y and an implicit z-value
	 * @param x x value
	 * @param y y value
	 * @throws IllegalArgumentException	If x + y + (-x-y) != 0
	 * @return an instance of Coordinate
	 */
	public static Coordinate from(final int x, final int y) {
		return from(x, y, -x-y);
	}
	
	/**
	 * Creates an instance of Coordinate from x, y and z
	 * @param x x value
	 * @param y y value
	 * @param z z value
	 * @throws IllegalArgumentException	If x + y + z != 0
	 * @return an instance of Coordinate
	 */
	public static Coordinate from(final int x, final int y, final int z) {
		if (x + z + y != 0) {
			throw new IllegalArgumentException("Coordinate values including implicit Z-coordinate must equal 0");
		} else {
			return new Coordinate(x, y);
		}
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getZ() { return -x - y; }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("X: %d Y: %d", x, y);
	}
}
