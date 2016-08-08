package dunk.hexagongrid;

public final class GridLayout {

	public final Point origin;
	public final double sizeX;
	public final double sizeY;
	
	public GridLayout(final Point origin, final double sizeX, final double sizeY) {
		if (origin == null)
			throw new NullPointerException();
		
		this.origin = origin;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
}
