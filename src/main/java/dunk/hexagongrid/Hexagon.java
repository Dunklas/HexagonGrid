package dunk.hexagongrid;

import java.util.Collection;

public interface Hexagon {

	public enum Orientation {
		POINTY,
		FLAT
	}
	
	Coordinate 			getCoordinate();
	Collection<Point> 	getPoints(GridLayout layout);
	Point				getCenterPoint(GridLayout layout);
	Vertice 			getVertice(Vertice.Type direction);
	Collection<Vertice> getVertices(); // Returns COPY to avoid modification
	Edge 				getEdge(Edge.Type direction);
	Collection<Edge>	getEdges(); // Returns COPY to avoid modification
}
