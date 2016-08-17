package dunk.hexagongrid.internal;

import java.util.Collection;
import java.util.HashSet;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Edge;
import dunk.hexagongrid.GridBuilder;
import dunk.hexagongrid.Hexagon;
import dunk.hexagongrid.Vertice;

public final class PointyGrid extends AbstractGrid {

	public PointyGrid(final GridBuilder builder) {
		super(builder);
	}

	@Override
	public Collection<Hexagon> hexagonsNear(Vertice vertice) {
		if (vertice == null) throw new NullPointerException();
		
		Hexagon parent = vertice.getParent();
		Collection<Hexagon> hexagons = new HashSet<>();
		Coordinate c1; Coordinate c2;
		
		if (vertice.getType() == Vertice.Direction.NORTH) {
			c1 = Neighbourhood.adjacentTo(
							parent.getCoordinate(),
							Neighbourhood.Regular.N3);
			c2 = Neighbourhood.adjacentTo(
							parent.getCoordinate(),
							Neighbourhood.Regular.N2);
		} else if (vertice.getType() == Vertice.Direction.SOUTH) {
			c1 = Neighbourhood.adjacentTo(
							parent.getCoordinate(),
							Neighbourhood.Regular.N5);
			c2 = Neighbourhood.adjacentTo(
							parent.getCoordinate(),
							Neighbourhood.Regular.N6);
		} else
			throw new IllegalArgumentException("Illegal vertice: " + vertice);
		hexagons.addAll(verifyCoordinates(c1, c2));
		hexagons.add(parent);
		return hexagons;
	}

	@Override
	public Collection<Hexagon> hexagonsNear(Edge edge) {
		if (edge == null) throw new NullPointerException();
		
		Hexagon parent = edge.getParent();
		Collection<Hexagon> hexagons = new HashSet<>();
		Coordinate c1;
		
		if (edge.getType() == Edge.Direction.NORTH_WEST) {
			c1 = Neighbourhood.adjacentTo(
					parent.getCoordinate(),
					Neighbourhood.Regular.N3);
		} else if (edge.getType() == Edge.Direction.WEST) {
			c1 = Neighbourhood.adjacentTo(
					parent.getCoordinate(),
					Neighbourhood.Regular.N4);
		} else if (edge.getType() == Edge.Direction.SOUTH_WEST) {
			c1 = Neighbourhood.adjacentTo(
					parent.getCoordinate(),
					Neighbourhood.Regular.N5);
		} else 
			throw new IllegalArgumentException("Illegal edge: " + edge);
		hexagons.addAll(verifyCoordinates(c1));
		hexagons.add(parent);
		return hexagons;
	}

	@Override
	public Collection<Vertice> verticesNear(Hexagon hexagon) {
		if (hexagon == null) throw new NullPointerException();
		
		Collection<Vertice> vertices = new HashSet<>();
		vertices.add(hexagon.getVertice(Vertice.Direction.NORTH));
		vertices.add(hexagon.getVertice(Vertice.Direction.SOUTH));
		
		Coordinate nw = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N3);
		Coordinate ne = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N2);
		Coordinate sw = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N5);
		Coordinate se = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N6);
		
		for (Hexagon h : verifyCoordinates(nw, ne)) {
			vertices.add(h.getVertice(Vertice.Direction.SOUTH));
		}
		for (Hexagon h : verifyCoordinates(sw, se)) {
			vertices.add(h.getVertice(Vertice.Direction.NORTH));
		}
		return vertices;
	}

	@Override
	public Collection<Vertice> verticesNear(Vertice vertice) {
		if (vertice == null) throw new NullPointerException();
		
		Vertice.Direction dir = vertice.getType();
		Coordinate c1, c2, c3;
		
		if (dir == Vertice.Direction.NORTH) {
			c1 = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N3);
			c2 = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N2);
			c3 = Neighbourhood.diagonalTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Diagonal.D4);
		} else if (dir == Vertice.Direction.SOUTH) {
			c1 = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N5);
			c2 = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N6);
			c3 = Neighbourhood.diagonalTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Diagonal.D1);
		} else {
			throw new IllegalArgumentException("Illegal vertice: " + vertice);
		}
		
		if (dir == Vertice.Direction.NORTH) dir = Vertice.Direction.SOUTH;
		else dir = Vertice.Direction.NORTH; // I do this to get correct vertices in below loop
		
		Collection<Vertice> vertices = new HashSet<>();
		for (Hexagon h : verifyCoordinates(c1, c2, c3)) {
			vertices.add(h.getVertice(dir));
		}
		return vertices;
	}

	@Override
	public Collection<Vertice> verticesNear(Edge edge) {
		if (edge == null) throw new NullPointerException();
		
		Collection<Vertice> vertices = new HashSet<>();
		
		if (edge.getType() == Edge.Direction.NORTH_WEST) {
			vertices.add(edge.getParent().getVertice(Vertice.Direction.NORTH));
			
			Coordinate c = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N3);
			for (Hexagon h : verifyCoordinates(c)) {
				vertices.add(h.getVertice(Vertice.Direction.SOUTH));
			}
			
		} else if (edge.getType() == Edge.Direction.WEST) {
			Coordinate c1 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N3);
			for (Hexagon h : verifyCoordinates(c1)) {
				vertices.add(h.getVertice(Vertice.Direction.SOUTH));
			}
			Coordinate c2 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N5);
			for (Hexagon h : verifyCoordinates(c2)) {
				vertices.add(h.getVertice(Vertice.Direction.NORTH));
			}
			
		} else if (edge.getType() == Edge.Direction.SOUTH_WEST) {
			vertices.add(edge.getParent().getVertice(Vertice.Direction.SOUTH));
			
			Coordinate c = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N5);
			for (Hexagon h : verifyCoordinates(c)) {
				vertices.add(h.getVertice(Vertice.Direction.NORTH));
			}
		} else {
			throw new IllegalArgumentException("Illegal edge: " + edge);
		}
		return vertices;
	}

	@Override
	public Collection<Edge> edgesNear(Hexagon hexagon) {
		if (hexagon == null) throw new NullPointerException();
		
		Collection<Edge> edges = new HashSet<>();
		edges.addAll(hexagon.getEdges());
		
		Coordinate c1 = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N2);
		for (Hexagon h : verifyCoordinates(c1)) {
			edges.add(h.getEdge(Edge.Direction.SOUTH_WEST));
		}
		
		Coordinate c2 = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N1);
		for (Hexagon h : verifyCoordinates(c2)) {
			edges.add(h.getEdge(Edge.Direction.WEST));
		}
		
		Coordinate c3 = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N6);
		for (Hexagon h : verifyCoordinates(c3)) {
			edges.add(h.getEdge(Edge.Direction.NORTH_WEST));
		}
		
		return edges;
	}

	@Override
	public Collection<Edge> edgesNear(Vertice vertice) {
		if (vertice == null) throw new NullPointerException();
		
		Collection<Edge> edges = new HashSet<>();	
		
		if (vertice.getType() == Vertice.Direction.NORTH) {
			edges.add(vertice.getParent().getEdge(Edge.Direction.NORTH_WEST));
			
			Coordinate c = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N2);
			for (Hexagon h : verifyCoordinates(c)) {
				edges.add(h.getEdge(Edge.Direction.WEST));
				edges.add(h.getEdge(Edge.Direction.SOUTH_WEST));
			}
			
		} else if (vertice.getType() == Vertice.Direction.SOUTH) {
			edges.add(vertice.getParent().getEdge(Edge.Direction.SOUTH_WEST));
			
			Coordinate c = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N6);
			for (Hexagon h : verifyCoordinates(c)) {
				edges.add(h.getEdge(Edge.Direction.WEST));
				edges.add(h.getEdge(Edge.Direction.NORTH_WEST));
			}
		} else {
			throw new IllegalArgumentException("Illegal vertice: " + vertice);
		}
		return edges;
	}

	@Override
	public Collection<Edge> edgesNear(Edge edge) {
		if (edge == null) throw new NullPointerException();
		
		Collection<Edge> edges = new HashSet<>();
		
		if (edge.getType() == Edge.Direction.NORTH_WEST) {
			edges.add(edge.getParent().getEdge(Edge.Direction.WEST));
			
			Coordinate c1 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N3);
			for (Hexagon h : verifyCoordinates(c1)) {
				edges.add(h.getEdge(Edge.Direction.SOUTH_WEST));
			}
			
			Coordinate c2 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N2);
			for (Hexagon h : verifyCoordinates(c2)) {
				edges.add(h.getEdge(Edge.Direction.WEST));
				edges.add(h.getEdge(Edge.Direction.SOUTH_WEST));
			}
			
		} else if (edge.getType() == Edge.Direction.WEST) {
			edges.add(edge.getParent().getEdge(Edge.Direction.NORTH_WEST));
			edges.add(edge.getParent().getEdge(Edge.Direction.SOUTH_WEST));
			
			Coordinate c1 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N3);
			for (Hexagon h : verifyCoordinates(c1)) {
				edges.add(h.getEdge(Edge.Direction.SOUTH_WEST));
			}
			
			Coordinate c2 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N5);
			for (Hexagon h : verifyCoordinates(c2)) {
				edges.add(h.getEdge(Edge.Direction.NORTH_WEST));
			}
			
		} else if (edge.getType() == Edge.Direction.SOUTH_WEST) {
			edges.add(edge.getParent().getEdge(Edge.Direction.WEST));
			
			Coordinate c1 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N5);
			for (Hexagon h : verifyCoordinates(c1)) {
				edges.add(h.getEdge(Edge.Direction.NORTH_WEST));
			}
			
			Coordinate c2 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N6);
			for (Hexagon h : verifyCoordinates(c2)) {
				edges.add(h.getEdge(Edge.Direction.NORTH_WEST));
				edges.add(h.getEdge(Edge.Direction.WEST));
			}
		} else {
			throw new IllegalArgumentException("Illegal edge: " + edge);
		}
		return edges;
	}
}
