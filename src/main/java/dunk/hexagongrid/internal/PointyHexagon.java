package dunk.hexagongrid.internal;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Edge;
import dunk.hexagongrid.Vertice;

class PointyHexagon extends AbstractHexagon {
	
	private final Map<Vertice.Type, Vertice> vertices = new EnumMap<>(Vertice.Type.class);
	private final Map<Edge.Type, Edge> edges = new EnumMap<>(Edge.Type.class);
	
	PointyHexagon(Coordinate coordinate) {
		super(HexagonOrientation.POINTY, coordinate);
		
		vertices.put(Vertice.Type.NORTH, PointyVertice.of(this, Vertice.Type.NORTH));
		vertices.put(Vertice.Type.SOUTH, PointyVertice.of(this, Vertice.Type.SOUTH));
		edges.put(Edge.Type.NORTH_WEST, PointyEdge.of(this, Edge.Type.NORTH_WEST));
		edges.put(Edge.Type.WEST, PointyEdge.of(this, Edge.Type.WEST));
		edges.put(Edge.Type.SOUTH_WEST, PointyEdge.of(this, Edge.Type.SOUTH_WEST));
	}

	@Override
	public Vertice getVertice(Vertice.Type direction) {
		if (vertices.containsKey(direction))
			return vertices.get(direction);
		else
			throw new IllegalArgumentException("Illegal Vertice.Type direction");
	}

	@Override
	public Edge getEdge(Edge.Type direction) {
		if (edges.containsKey(direction))
			return edges.get(direction);
		else
			throw new IllegalArgumentException("Illegal Edge.Type direction"); 
	}
	
	@Override
	public Collection<Edge> getEdges() {
		return new HashSet<>(edges.values());
	}
	
	@Override
	public Collection<Vertice> getVertices() {
		return new HashSet<>(vertices.values());
	}
}
