package dunk.hexagongrid;

/**
 * Represents a location in a two-dimensional space, specified in double precision.
 */
public final class Point {
	
	private final double x;
	private final double y;
	
	/**
	 * Creates a {@code Point} of the specified ({@code x}, {@code y}) location.
	 * 
	 * @param x  the x-coordinate
	 * @param y  the y-coordinate
	 */
	public Point(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the distance between this {@code Point} and {@code point}, in double precision.
	 * 
	 * @param point  the point to calculate the distance to, not null
	 * @return the distance between this {@code Point} and {@code point}
	 */
	public double distanceTo(final Point point) {
		if (point == null) throw new NullPointerException();
		
		double distance = Math.sqrt( (point.getX() - this.x) * (point.getX() - this.x)
									+(point.getY() - this.y) * (point.getY() - this.y));
		return distance;
	}
	
	/**
	 * Returns a new {@code Point} located at the centre of {@code p1} and {@code p2}.
	 * 
	 * @param p1  the first point, not null
	 * @param p2  the second point, not null
	 * @return a new {@code Point} with ({@code x}, {@code y}) location at the centre of {@code p1} and {@code p2}
	 */
	public static Point centerOf(final Point p1, final Point p2) {
		if (p1 == null || p2 == null) throw new NullPointerException();
		
		double x = (p1.getX()+p2.getX())/2; 
		double y = (p1.getY()+p2.getY())/2;
		return new Point(x, y);
	}
	
	/**
	 * Returns the x-coordinate of this {@code Point}.
	 * 
	 * @return the x-coordinate of this {@code Point} in double precision
	 */
	public double getX() { 
		return x;
	}
	
	/**
	 * Returns the y-coordinate of this {@code Point}.
	 * 
	 * @return the y-coordinate of this {@code Point} in double precision
	 */
	public double getY() { 
		return y;
	}
	
	public String toString() {
		return String.format("X: %f Y: %f", x, y);
	}
}
