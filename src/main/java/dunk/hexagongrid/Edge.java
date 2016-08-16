package dunk.hexagongrid;

/**
 * Represents an {@code Edge} of a {@code Hexagon}.
 * For each {@link Hexagon}, three {@code Edge}s are created.
 * <p>
 * A reference to an {@code Edge} is acquired by invoking the {@link Hexagon#getEdges() getEdges} or {@link Hexagon#getEdge(Edge.Direction) getEdge} method.
 * <p>
 * An {@code Edge} may be used to relate data, such as an object in a game to an {@code Edge}.
 */
public interface Edge {

	/**
	 * Represents the direction of an {@code Edge}.
	 * <p>
	 * For a pointy {@link Hexagon}, {@code Edge}s of direction {@code NORTH_WEST}, {@code WEST} and {@code SOUTH_WEST} are created.
	 * <p>
	 * For a flat {@code Hexagon}, {@code Edge}s of direction {@code NORTH_WEST}, {@code NORTH} and {@code NORTH_EAST} are created.
	 */
	public enum Direction {
		NORTH,
		WEST,
		NORTH_WEST,
		NORTH_EAST,
		SOUTH_WEST
	}
	
	/**
	 * Returns a {@code Point} representing one end of this {@code Edge}.
	 * 
	 * @param layout  represents the size and position of a {@link Grid}, not null
	 * @return the top/left {@link Point} of this {@code Edge}
	 */
	Point 		getFirstPoint(GridLayout layout);
	
	/**
	 * Returns a {@code Point} representing one end of this {@code Edge}.
	 * 
	 * @param layout  represents the size and position of a {@link Grid}, not null
	 * @return the lower/right {@link Point} of this {@code Edge}
	 */
	Point 		getSecondPoint(GridLayout layout);
	
	/**
	 * Returns a {@code Point} representing the centre of this {@code Edge}.
	 * 
	 * @param layout  represents the size and position of a {@link Grid}, not null
	 * @return the centre {@link Point} of this {@code Edge}
	 */
	Point 		getCenterPoint(GridLayout layout);
	
	/**
	 * Returns the parent {@code Hexagon} of this {@code Edge}.
	 * 
	 * @return the parent {@link Hexagon}
	 */
	Hexagon 	getParent();
	
	/**
	 * Returns the {@code Edge.Direction} of this {@code Edge}.
	 *
	 * @return the {@link Edge.Direction} of this {@code Edge}.
	 */
	Edge.Direction 	getType();

	
	/**
	 * Compares the specified {@code object} with this {@code Edge} for equality.
	 * Returns true if and only if the specified {@code object} is of the same implementation class and their parent {@link Hexagon} and {@link Edge.Direction} are equal.
	 * 
	 * @param obj  the {@code object} to be compared for equality with this {@code Edge}
	 * @return true if the specified {@code object} is equal to this {@code Edge}
	 */
	@Override
	boolean				equals(Object obj);
	
	
	/**
	 * Returns the hash code value for this {@code Edge}.
	 * 
	 * @return the hash code value for this {@code Edge}
	 */
	@Override
	int					hashCode();
}
