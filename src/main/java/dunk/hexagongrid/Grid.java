package dunk.hexagongrid;

import java.util.Collection;

/**
 * Represents a {@code Grid} of {@code Hexagon}s.
 */
public interface Grid {

	/**
	 * Returns a {@code Collection} of all {@code Hexagon}s present in this {@code Grid}.
	 * 
	 * @return a copy of the internal {@code Collection} of {@link Hexagon}s
	 */
	Collection<Hexagon> getHexagons();
	
	
	/**
	 * Returns a {@code Collection} of {@code Hexagon}s within a range.
	 * 
	 * @param center  the center of the range, not null
	 * @param radius  the radius of the range
	 * @return a {@code Collection} of the {@link Hexagon}s which are less than or equal to {@code radius} distance from {@code center}
	 * @throws IllegalArgumentException if {@code radius} is less than 0 or more than the {@code radius} of this {@code Grid}
	 * @throws IllegalArgumentException if {@code center} is not present in this {@code Grid}
	 */
	Collection<Hexagon> getRange(Hexagon center, int radius);
	
	
	/**
	 * Returns a {@code Collection} of {@code Hexagon}s in a ring.
	 * 
	 * @param center  the center of the ring, not null
	 * @param radius  the radius of the ring
	 * @return a {@code Collection} of the {@link Hexagon}s which are {@code radius} distance from {@code center}
	 * @throws IllegalArgumentException if {@code radius} is less than 1 or more than the {@code radius} of this {@code Grid}
	 * @throws IllegalArgumentException if {@code center} is not present in this {@code Grid}
	 */
	Collection<Hexagon> getRing(Hexagon center, int radius);
	
	
	/**
	 * Returns a {@code Hexagon} by its {@code coordinate}.
	 * 
	 * @param coordinate  the {@code coordinate}, not null
	 * @return the {@code Hexagon} of {@code coordinate}
	 * @throws HexagonOutOfBoundsException if {@link Hexagon} of {@code coordinate} is not present in this {@code Grid}
	 */
	Hexagon				getHexagon(Coordinate coordinate);
	
	
	/**
	 * Returns a {@code Hexagon} from a pixel coordinate.
	 * 
	 * @param x  the x-coordinate of a pixel
	 * @param y  the y-coordinate of a pixel
	 * @param layout  represents the size and position of a {@link Grid}, not null
	 * @return the {@link Hexagon} of the pixel coordinate
	 * @throws HexagonOutOfBoundsException if pixel coordinate represents a position outside this {@code Grid}
	 */
	Hexagon				getHexagon(double x, double y, GridLayout layout);
	
	
	/**
	 * Returns a {@code Vertice} from a pixel coordinate.
	 * 
	 * @param x  the x-coordinate of a pixel
	 * @param y  the y-coordinate of a pixel
	 * @param layout  represents the size and position of a {@link Grid}, not null
	 * @return the {@link Vertice} of the pixel coordinate
	 * @throws HexagonOutOfBoundsException if pixel coordinate represents a position outside this {@code Grid}
	 */
	Vertice				getVertice(double x, double y, GridLayout layout);
	
	
	/**
	 * Returns an {@code Edge} from a pixel coordinate.
	 * 
	 * @param x  the x-coordinate of a pixel
	 * @param y  the y-coordinate of a pixel
	 * @param layout  represents the size and position of a {@link Grid}, not null
	 * @return the {@link Edge} of the pixel coordinate
	 * @throws HexagonOutOfBoundsException if pixel coordinate represents a position outside this {@code Grid}
	 */
	Edge				getEdge(double x, double y, GridLayout layout);
	
	
	/**
	 * Returns a {@code Collection} of {@code Hexagon}s neighbouring {@code hexagon}.
	 * 
	 * @param hexagon  the target hexagon, not null
	 * @return a {@code Collection} of {@code Hexagon}s which are adjacent to {@code hexagon}
	 * @throws IllegalArgumentException if {@code hexagon} is not present in this {@code Grid}
	 */
	Collection<Hexagon> hexagonsNear(Hexagon hexagon);
	
	
	/**
	 * Returns a {@code Collection} of {@code Hexagon}s neighbouring {@code vertice}.
	 * 
	 * @param vertice  the target vertice, not null
	 * @return a {@code Collection} of {@link Hexagon}s which are adjacent to {@code vertice}
	 */
	Collection<Hexagon> hexagonsNear(Vertice vertice);
	
	
	/**
	 * Returns a {@code Collection} of {@code Hexagon}s neighbouring {@code edge}.
	 * 
	 * @param edge  the target edge, not null
	 * @return a {@code Collection} of {@link Hexagon}s which are adjacent to {@code edge}
	 */
	Collection<Hexagon> hexagonsNear(Edge edge);
	
	
	/**
	 * Returns a {@code Collection} of {@code Vertice}s neighbouring {@code hexagon}.
	 * 
	 * @param hexagon  the target hexagon, not null
	 * @return a {@code Collection} of {@link Vertice}s which are adjacent to {@code hexagon}
	 */
	Collection<Vertice> verticesNear(Hexagon hexagon);
	
	
	/**
	 * Returns a {@code Collection} of {@code Vertice}s neighbouring {@code vertice}.
	 * 
	 * @param vertice  the target vertice, not null
	 * @return a {@code Collection} of {@link Vertice}s which are adjacent to {@code vertice}
	 */
	Collection<Vertice> verticesNear(Vertice vertice);
	
	
	/**
	 * Returns a {@code Collection} of {@code Vertice}s neighbouring {@code edge}.
	 * 
	 * @param edge  the target edge, not null
	 * @return a {@code Collection} of {@link Vertice}s which are adjacent to {@code edge}
	 */
	Collection<Vertice> verticesNear(Edge edge);
	
	
	/**
	 * Returns a {@code Collection} of {@code Edge}s neighbouring {@code hexagon}.
	 * 
	 * @param hexagon  the target hexagon, not null
	 * @return a {@code Collection} of {@link Edge}s which are adjacent to {@code hexagon}
	 */
	Collection<Edge>	edgesNear(Hexagon hexagon);
	
	
	/**
	 * Returns a {@code Collection} of {@code Edge}s neighbouring {@code vertice}.
	 * 
	 * @param vertice  the target vertice, not null
	 * @return a {@code Collection} of {@link Edge}s which are adjacent to {@code vertice}
	 */
	Collection<Edge>	edgesNear(Vertice vertice);
	
	
	/**
	 * Returns a {@code Collection} of {@code Edge}s neighbouring {@code edge}.
	 * 
	 * @param edge  the target edge, not null
	 * @return a {@code Collection} of {@link Edge}s which are adjacent to {@code edge}
	 */
	Collection<Edge> 	edgesNear(Edge edge);
	
	
	/**
	 * Indicates whether {@code coordinate} is present in this {@code Grid}.
	 * 
	 * @param coordinate  the target coordinate, not null
	 * @return true if {@code coordinate} is present, false otherwise
	 */
	boolean				contains(Coordinate coordinate);
}
