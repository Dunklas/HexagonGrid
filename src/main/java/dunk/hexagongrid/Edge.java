package dunk.hexagongrid;

/**
 * Represents an edge of a {@link Hexagon}.
 * For each {@link Hexagon} in a {@link Grid}, three Edge instances are created.
 */
public interface Edge {

	/**
	 * Represents the direction of an Edge.
	 * For a pointy {@link Hexagon}, Edge instances of type NORTH_WEST, WEST and SOUTH_WEST are created.
	 * For a flat {@link Hexagon}, Edge instances of type NORTH_WEST, NORTH and NORTH_EAST are created.
	 */
	public enum Type {
		NORTH,
		WEST,
		NORTH_WEST,
		NORTH_EAST,
		SOUTH_WEST
	}
	
	/**
	 * @param layout	an instance of {@link GridLayout} representing size and position of {@link Grid}
	 * @return			the top/left {@link Point} of this Edge
	 */
	Point 		getFirstPoint(GridLayout layout);
	
	/**
	 * @param layout	an instance of {@link GridLayout} representing size and position of {@link Grid}
	 * @return			the lower/right {@link Point} of this Edge.
	 */
	Point 		getSecondPoint(GridLayout layout);
	
	/**
	 * @param layout	an instance of {@link GridLayout} representing size and position of {@link Grid}
	 * @return			the centre {@link Point} of this Edge.
	 */
	Point 		getCenterPoint(GridLayout layout);
	
	/**
	 * @return			the parent {@link Hexagon}
	 */
	Hexagon 	getParent();
	
	/**
	 * @return			the {@link Edge.Type} of this Edge.
	 */
	Edge.Type 	getType();
}
