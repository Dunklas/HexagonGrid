package dunk.hexagongrid.internal;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Edge;
import dunk.hexagongrid.Vertice;

final class FlatHexagon extends AbstractHexagon {
	
	private final Map<Vertice.Type, Vertice> vertices = new EnumMap<>(Vertice.Type.class);
	private final Map<Edge.Type, Edge> edges = new EnumMap<>(Edge.Type.class);
	
	FlatHexagon(Coordinate coordinate) {
		super(HexagonOrientation.FLAT, coordinate);
		
		vertices.put(Vertice.Type.WEST, FlatVertice.of(this, Vertice.Type.WEST));
		vertices.put(Vertice.Type.EAST, FlatVertice.of(this, Vertice.Type.EAST));
		edges.put(Edge.Type.NORTH_WEST, FlatEdge.of(this, Edge.Type.NORTH_WEST));
		edges.put(Edge.Type.NORTH, FlatEdge.of(this, Edge.Type.NORTH));
		edges.put(Edge.Type.NORTH_EAST, FlatEdge.of(this, Edge.Type.NORTH_EAST));
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
