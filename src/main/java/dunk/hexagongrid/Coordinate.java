package dunk.hexagongrid;

/**
 * Represents the location of a {@code Hexagon} in a {@code Grid}.
 * This implementation of a coordinate is described as an axial/cube-coordinate in http://www.redblobgames.com/grids/hexagons/#coordinates.
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
	 * Creates a new {@code Coordinate} from x, y and an implicit z-value.
	 * 
	 * @param x  x-value
	 * @param y  y-value
	 * @return a new {@code Coordinate}
	 * @throws IllegalArgumentException	if x + y + (-x-y) != 0
	 */
	public static Coordinate from(final int x, final int y) {
		return from(x, y, -x-y);
	}
	
	/**
	 * Creates a new {@code Coordinate} from x, y and an explicit z-value.
	 * 
	 * @param x  x-value
	 * @param y  y-value
	 * @param z  z-value
	 * @return a new {@code Coordinate}
	 * @throws IllegalArgumentException	if x + y + z != 0
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
	
	/**
	 * Compares the specified {@code object} with this {@code Coordinate} for equality.
	 * Returns true if and only if the specified {@code object} is also a {@code Coordinate} and their {@code x-} and {@code y-coordinates} are equal.
	 * 
	 * @param obj  the {@code object} to be compared for equality with this {@code Coordinate}
	 * @return true if the specified {@code object} is equal to this {@code Coordinate}
	 */
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
	
	/**
	 * Returns the hash code value for this {@code Coordinate}.
	 * 
	 * @return the hash code value for this {@code Coordinate}
	 */
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
