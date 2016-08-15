package dunk.hexagongrid;

import java.util.Collection;

/**
 * Represents a grid of hexagons
 */
public interface Grid {

	/**
	 * Represents the formation of a Grid
	 * E.g. Rectangle, Hexagon, Triangle
	 */
	public enum Formation {
		HEXAGON,
	}
	
	/**
	 * Returns all {@link Hexagon} instances in this Grid
	 * @return a {@link Collection} of all {@link Hexagon} instances in this Grid
	 */
	Collection<Hexagon> getHexagons(); // Returns COPY (defensive protection)
	
	/**
	 * Returns {@link Hexagon} instances within a range. See http://www.redblobgames.com/grids/hexagons/#range for more information.
	 * @param center the {@link Hexagon} from where the range should originate
	 * @param radius the radius of the range
	 * @throws NullPointerException if center is null
	 * @throws IllegalArgumentException if radius is less than 0, if radius is more than the radius of this Grid, or if center is not present in this Grid
	 * @return a {@link Collection} of the {@link Hexagon} instances in specified range
	 */
	Collection<Hexagon> getRange(Hexagon center, int radius);
	
	/**
	 * Returns a ring of {@link Hexagon} instances. See http://www.redblobgames.com/grids/hexagons/#rings for more information.
	 * @param center the {@link Hexagon} from where the ring should originate
	 * @param radius the radius from where the ring should be retrieved.
	 * @throws NullPointerException if center is null
	 * @throws IllegalArgumentException if radius is less than 1, if radius is more than the radius of this Grid, or if center is not present in this Grid
	 * @return a {@link Collection} of the {@link Hexagon} instances in the specified ring
	 */
	Collection<Hexagon> getRing(Hexagon center, int radius);
	
	/**
	 * Returns a {@link Hexagon} of the specified coordinate
	 * @param coordinate coordinate of the Hexagon to retrieve
	 * @throws HexagonOutOfBoundsException if {@link Hexagon} of coordinate is not present in this Grid
	 * @return the {@link Hexagon} of the specified coordinate
	 */
	Hexagon				getHexagon(Coordinate coordinate);
	
	/**
	 * Returns a {@link Hexagon} from the provided pixel
	 * @param pixel a pixel
	 * @param layout an instance of {@link GridLayout} representing size and position of {@link Grid}
	 * @return the {@link Hexagon} corresponding to provided {@link Point} pixel
	 * @throws AlienHexagonException if {@link Point} pixel represents a position outside this Grid
	 */
	Hexagon				getHexagon(Point pixel, GridLayout layout) throws AlienHexagonException;
	
	/**
	 * Returns a {@link Vertice} from the provided pixel
	 * @param pixel a pixel
	 * @param layout an instance of {@link GridLayout} representing size and position of {@link Grid}
	 * @return the {@link Vertice} corresponding to provided {@link Point} pixel
	 * @throws AlienHexagonException if {@link Point} pixel represents a position outside this Grid
	 */
	Vertice				getVertice(Point pixel, GridLayout layout) throws AlienHexagonException;
	
	/**
	 * Returns an {@link Edge} from the provided pixel
	 * @param pixel a pixel
	 * @param layout an instance of {@link GridLayout} representing size and position of {@link Grid}
	 * @return the {@link Edge} corresponding to provided {@link Point} pixel
	 * @throws AlienHexagonException if {@link Point} pixel represents a position outside this Grid
	 */
	Edge				getEdge(Point pixel, GridLayout layout) throws AlienHexagonException;
	
	Collection<Hexagon> hexagonsNear(Hexagon hexagon);
	Collection<Hexagon> hexagonsNear(Vertice vertice);
	Collection<Hexagon> hexagonsNear(Edge edge);
	Collection<Vertice> verticesNear(Hexagon hexagon);
	Collection<Vertice> verticesNear(Vertice vertice);
	Collection<Vertice> verticesNear(Edge edge);
	Collection<Edge>	edgesNear(Hexagon hexagon);
	Collection<Edge>	edgesNear(Vertice vertice);
	Collection<Edge> 	edgesNear(Edge edge);
	
	boolean				contains(Coordinate coordinate);
}
