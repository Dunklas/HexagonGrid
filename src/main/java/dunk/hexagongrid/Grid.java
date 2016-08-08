package dunk.hexagongrid;

import java.util.Collection;

public interface Grid {

	public enum Formation {
		HEXAGON,
	}
	
	Collection<Hexagon> getHexagons(); // Returns COPY (defensive protection)
	Collection<Hexagon> getRange(Hexagon center, int radius);
	Collection<Hexagon> getRing(Hexagon center, int radius);
	Hexagon				getHexagon(Coordinate coordinate);
	Hexagon				getHexagon(Point pixel, GridLayout layout) throws AlienHexagonException;
	Vertice				getVertice(Point pixel, GridLayout layout) throws AlienHexagonException;
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
