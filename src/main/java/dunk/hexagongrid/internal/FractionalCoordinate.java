package dunk.hexagongrid.internal;

class FractionalCoordinate {

	private final double x; // X = R
	private final double y; // Y = Q
						 	// Z = S
	
	private FractionalCoordinate(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	static FractionalCoordinate from(final double x, final double y) {
		return from(x, y, -x-y);
	}
	
	static FractionalCoordinate from(final double x, final double y, final double z) {
		return new FractionalCoordinate(x, y);
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	public double getZ() { return -x - y; }
}
