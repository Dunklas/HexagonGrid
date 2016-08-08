package dunk.hexagongrid;

public final class Coordinate {
	
	private final int x; // Q = X
	private final int y; // R = Y
						 // S = Z
	
	private Coordinate(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	public static Coordinate from(final int x, final int y) {
		return from(x, y, -x-y);
	}
	
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
