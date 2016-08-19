package dunk.hexagongrid;

import java.util.List;
import java.util.Set;

/**
 * Represents a {@code Hexagon} in a {@code Grid}.
 * <p>
 * A reference to a {@code Hexagon} is acquired by invoking for example the {@link Grid#getHexagons() getHexagons} or {@link Grid#getHexagon(Coordinate)} method.
 * <p>
 * A {@code Hexagon} may be used to relate data, such as an object in a game to a {@code Hexagon}.
 */
public interface Hexagon {
	
	/**
	 * Represents the orientation of a Hexagon.
	 */
	public enum Orientation {
		FLAT,
		POINTY
	}
	
	/**
	 * Returns the {@code Coordinate} of this {@code Hexagon}.
	 * 
	 * @return the coordinate of this {@code Hexagon}
	 */
	Coordinate 			getCoordinate();
	
	
	/**
	 * Returns a {@code Set} of {@code Point}s which represents the corners of this {@code Hexagon}. 
	 * The {@link Point}s may be used to draw the {@code Hexagon}.
	 * 
	 * @param layout  represents the size and position of a {@link Grid}, not null
	 * @return a {@code Set} of {@code Point}s which represents the corners of this {@code Hexagon}
	 */
	List<Point> 	getPoints(GridLayout layout);
	
	
	/**
	 * Returns a {@code Point} representing the centre of this {@code Hexagon}.
	 * 
	 * @param layout  represents the size and position of a {@link Grid}, not null
	 * @return a {@link Point} representing the centre of this {@code Hexagon}
	 */
	Point				getCentrePoint(GridLayout layout);
	
	
	/**
	 * Returns a {@code Vertice} in the specified {@code direction}.
	 * 
	 * @param direction  the direction
	 * @return a {@link Vertice} in the specified {@code direction}
	 * @throws IllegalArgumentException if {@code direction} is not present for this {@code Hexagon}
	 * @see Vertice.Direction
	 */
	Vertice 			getVertice(Vertice.Direction direction);
	
	
	/**
	 * Returns a {@code Set} of all {@code Vertice}s for this {@code Hexagon}.
	 *  
	 * @return a copy of the internal {@code Set} of {@link Vertice}s
	 * @see Vertice.Direction
	 */
	Set<Vertice> getVertices();
	
	
	/**
	 * Returns an {@code Edge} in the specified {@code direction}.
	 * 
	 * @param direction  the direction
	 * @return an {@link Edge} in the specified {@code direction}
	 * @throws IllegalArgumentException if {@code direction} is not present for this {@code Hexagon}
	 * @see Edge.Direction
	 */
	Edge 				getEdge(Edge.Direction direction);
	
	
	/**
	 * Returns a {@code Set} of all {@code Edge}s for this {@code Hexagon}.
	 * 
	 * @return a copy of the internal {@code Set} of {@link Edge}s
	 * @see Edge.Direction
	 */
	Set<Edge>	getEdges();
	
	
	/**
	 * Compares the specified {@code object} with this {@code Hexagon} for equality.
	 * Returns true if and only if the specified {@code object} implements Hexagon and their {@code coordinate} are equal.
	 * 
	 * @param obj  the {@code object} to be compared for equality with this {@code Hexagon}
	 * @return true if the specified {@code object} is equal to this {@code Hexagon}
	 */
	@Override
	boolean				equals(Object obj);
	
	
	/**
	 * Returns the hash code value for this {@code Hexagon}.
	 * 
	 * @return the hash code value for this {@code Hexagon}
	 */
	@Override
	int					hashCode();
}
