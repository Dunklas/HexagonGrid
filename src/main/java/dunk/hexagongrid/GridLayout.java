package dunk.hexagongrid;

/**
 * Represents the size of {@code Hexagon}s and the position of a {@code Grid}.
 * <p>
 * A {@code GridLayout} object is used in for example {@link Hexagon#getPoints(GridLayout) getPoints}, in order to calculate size and position of a {@code Hexagon}.
 */
public final class GridLayout {

	public final Point origin;
	public final double sizeX;
	public final double sizeY;
	
	/**
	 * Creates a {@code GridLayout}.
	 * 
	 * @param posX  the specified x-coordinate of a {@link Grid}s centre
	 * @param posY  the specified y-coordinate of a {@code Grid}s centre
	 * @param width  the width of a {@link Hexagon}
	 * @param height  the height of a {@code Hexagon}
	 * @throws IllegalArgumentException if {@code width} or {@code height} is less than 1
	 */
	public GridLayout(final double posX, final double posY, final double width, final double height) {
		if (width < 1 || height < 1) throw new IllegalArgumentException();
		
		this.origin = new Point(posX, posY);
		this.sizeX = width;
		this.sizeY = height;
	}
}
