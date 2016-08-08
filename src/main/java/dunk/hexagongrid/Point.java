package dunk.hexagongrid;

public final class Point {
	
	private final double x;
	private final double y;
	
	public Point(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	public double distanceTo(final Point p2) {
		double distance = Math.sqrt( (p2.getX() - this.x) * (p2.getX() - this.x)
									+(p2.getY() - this.y) * (p2.getY() - this.y));
		return distance;
	}
	
	public static Point centerOf(final Point p1, final Point p2) {
		double x = (p1.getX()+p2.getX())/2; 
		double y = (p1.getY()+p2.getY())/2;
		return new Point(x, y);
	}
	
	public long getX() { 
		return Math.round(x);
	}
	public long getY() { 
		return Math.round(y);
	}
}
