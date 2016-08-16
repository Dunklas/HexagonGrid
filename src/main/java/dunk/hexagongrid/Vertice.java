package dunk.hexagongrid;

/**
 * Represents a {@code Vertice} of a {@code Hexagon}.
 * For each {@link Hexagon}, two {@code Vertice}s are created.
 * <p>
 * A reference to a {@code Vertice} is acquired by invoking the {@link Hexagon#getVertices() getVertices} or {@link Hexagon#getVertice(Vertice.Direction) getVertice} method.
 * <p>
 * A {@code Vertice} may be used to relate data, such as an object in a game to a {@code Vertice}.
 */
public interface Vertice {

	/**
	 * Represents the direction of a {@code Vertice}.
	 * <p>
	 * For a pointy {@link Hexagon}, {@code Vertice}s of direction {@code NORTH} and {@code SOUTH} are created.
	 * <p>
	 * For a flat {@code Hexagon}, {@code Vertice}s of direction {@code WEST}, and {@code EAST} are created.
	 */
	public enum Direction {
		NORTH,
		SOUTH,
		WEST,
		EAST,
	}
	
	
	/**
	 * Returns a {@code Point} representing this {@code Vertice}.
	 * 
	 * @param layout  represents the size and position of a {@link Grid}, not null
	 * @return the {@link Point} representing this {@code Vertice}
	 */
	Point 			getPoint(GridLayout layout);
	
	
	/**
	 * Returns the parent {@code Hexagon} of this {@code Vertice}.
	 * 
	 * @return the parent {@link Hexagon}
	 */
	Hexagon 		getParent();
	
	
	/**
	 * Returns the {@code Vertice.Direction} of this {@code Vertice}.
	 *
	 * @return the {@link Vertice.Direction} of this {@code Vertice}.
	 */
	Vertice.Direction 	getType();
	
	
	/**
	 * Compares the specified {@code object} with this {@code Vertice} for equality.
	 * Returns true if and only if the specified {@code object} is of the same implementation class and their parent {@link Hexagon} and {@link Vertice.Direction} are equal.
	 * 
	 * @param obj  the {@code object} to be compared for equality with this {@code Vertice}
	 * @return true if the specified {@code object} is equal to this {@code Vertice}
	 */
	@Override
	boolean				equals(Object obj);
	
	
	/**
	 * Returns the hash code value for this {@code Vertice}.
	 * 
	 * @return the hash code value for this {@code Vertice}
	 */
	@Override
	int					hashCode();
}
