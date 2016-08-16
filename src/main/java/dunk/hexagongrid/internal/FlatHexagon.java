package dunk.hexagongrid.internal;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Edge;
import dunk.hexagongrid.Vertice;

final class FlatHexagon extends AbstractHexagon {
	
	private final Map<Vertice.Direction, Vertice> vertices = new EnumMap<>(Vertice.Direction.class);
	private final Map<Edge.Direction, Edge> edges = new EnumMap<>(Edge.Direction.class);
	
	FlatHexagon(Coordinate coordinate) {
		super(HexagonOrientation.FLAT, coordinate);
		
		vertices.put(Vertice.Direction.WEST, FlatVertice.of(this, Vertice.Direction.WEST));
		vertices.put(Vertice.Direction.EAST, FlatVertice.of(this, Vertice.Direction.EAST));
		edges.put(Edge.Direction.NORTH_WEST, FlatEdge.of(this, Edge.Direction.NORTH_WEST));
		edges.put(Edge.Direction.NORTH, FlatEdge.of(this, Edge.Direction.NORTH));
		edges.put(Edge.Direction.NORTH_EAST, FlatEdge.of(this, Edge.Direction.NORTH_EAST));
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
	public Collection<Edge> getEdges() {
		return new HashSet<>(edges.values());
	}
	
	@Override
	public Collection<Vertice> getVertices() {
		return new HashSet<>(vertices.values());
	}
}
