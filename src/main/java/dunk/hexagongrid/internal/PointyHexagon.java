package dunk.hexagongrid.internal;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Edge;
import dunk.hexagongrid.Vertice;

class PointyHexagon extends AbstractHexagon {
	
	private final Map<Vertice.Direction, Vertice> vertices = new EnumMap<>(Vertice.Direction.class);
	private final Map<Edge.Direction, Edge> edges = new EnumMap<>(Edge.Direction.class);
	
	PointyHexagon(Coordinate coordinate) {
		super(HexagonOrientation.POINTY, coordinate);
		
		vertices.put(Vertice.Direction.NORTH, PointyVertice.of(this, Vertice.Direction.NORTH));
		vertices.put(Vertice.Direction.SOUTH, PointyVertice.of(this, Vertice.Direction.SOUTH));
		edges.put(Edge.Direction.NORTH_WEST, PointyEdge.of(this, Edge.Direction.NORTH_WEST));
		edges.put(Edge.Direction.WEST, PointyEdge.of(this, Edge.Direction.WEST));
		edges.put(Edge.Direction.SOUTH_WEST, PointyEdge.of(this, Edge.Direction.SOUTH_WEST));
	}

	@Override
	public Vertice getVertice(Vertice.Direction direction) {
		if (vertices.containsKey(direction))
			return vertices.get(direction);
		else
			throw new IllegalArgumentException("Illegal Vertice.Type direction");
	}

	@Override
	public Edge getEdge(Edge.Direction direction) {
		if (edges.containsKey(direction))
			return edges.get(direction);
		else
			throw new IllegalArgumentException("Illegal Edge.Type direction"); 
	}
	
	@Override
	public Set<Edge> getEdges() {
		return new HashSet<>(edges.values());
	}
	
	@Override
	public Set<Vertice> getVertices() {
		return new HashSet<>(vertices.values());
	}
}
