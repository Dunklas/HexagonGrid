package dunk.hexagongrid.internal;

import java.util.Collection;
import java.util.HashSet;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Edge;
import dunk.hexagongrid.Grid;
import dunk.hexagongrid.Hexagon;
import dunk.hexagongrid.Vertice;

public final class FlatGrid extends AbstractGrid {

	public FlatGrid(final int radius, final Grid.Formation formation) {
		super(radius, formation, Hexagon.Orientation.FLAT);
	}

	@Override
	public Collection<Hexagon> hexagonsNear(Vertice vertice) {
		if (vertice == null) throw new NullPointerException();
		
		Hexagon parent = vertice.getParent();
		Collection<Hexagon> hexagons = new HashSet<>();
		Coordinate c1; Coordinate c2;
		
		if (vertice.getType() == Vertice.Type.WEST) {
			c1 = Neighbourhood.adjacentTo(
							parent.getCoordinate(),
							Neighbourhood.Regular.N4);
			c2 = Neighbourhood.adjacentTo(
							parent.getCoordinate(),
							Neighbourhood.Regular.N5);
		} else if (vertice.getType() == Vertice.Type.EAST) {
			c1 = Neighbourhood.adjacentTo(
							parent.getCoordinate(),
							Neighbourhood.Regular.N1);
			c2 = Neighbourhood.adjacentTo(
							parent.getCoordinate(),
							Neighbourhood.Regular.N2);
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
		
		if (edge.getType() == Edge.Type.NORTH_WEST) {
			c1 = Neighbourhood.adjacentTo(
					parent.getCoordinate(),
					Neighbourhood.Regular.N4);
		} else if (edge.getType() == Edge.Type.NORTH) {
			c1 = Neighbourhood.adjacentTo(
					parent.getCoordinate(),
					Neighbourhood.Regular.N3);
		} else if (edge.getType() == Edge.Type.NORTH_EAST) {
			c1 = Neighbourhood.adjacentTo(
					parent.getCoordinate(),
					Neighbourhood.Regular.N2);
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
		vertices.add(hexagon.getVertice(Vertice.Type.WEST));
		vertices.add(hexagon.getVertice(Vertice.Type.EAST));
		
		Coordinate nw = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N4);
		Coordinate ne = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N2);
		Coordinate sw = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N5);
		Coordinate se = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N1);
		
		for (Hexagon h : verifyCoordinates(nw, sw)) {
			vertices.add(h.getVertice(Vertice.Type.EAST));
		}
		for (Hexagon h : verifyCoordinates(ne, se)) {
			vertices.add(h.getVertice(Vertice.Type.WEST));
		}
		return vertices;
	}

	@Override
	public Collection<Vertice> verticesNear(Vertice vertice) {
		if (vertice == null) throw new NullPointerException();
		
		Vertice.Type dir = vertice.getType();
		Coordinate c1, c2, c3;
		
		if (dir == Vertice.Type.WEST) {
			c1 = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N4);
			c2 = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N5);
			c3 = Neighbourhood.diagonalTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Diagonal.D2);
		} else if (dir == Vertice.Type.EAST) {
			c1 = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N2);
			c2 = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N1);
			c3 = Neighbourhood.diagonalTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Diagonal.D5);
		} else {
			throw new IllegalArgumentException("Illegal vertice: " + vertice);
		}
		
		if (dir == Vertice.Type.WEST) dir = Vertice.Type.EAST;
		else dir = Vertice.Type.WEST; // I do this to get correct vertices in below loop
		
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
		
		if (edge.getType() == Edge.Type.NORTH_WEST) {
			vertices.add(edge.getParent().getVertice(Vertice.Type.WEST));
			
			Coordinate c = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N4);
			for (Hexagon h : verifyCoordinates(c)) {
				vertices.add(h.getVertice(Vertice.Type.EAST));
			}
			
		} else if (edge.getType() == Edge.Type.NORTH) {
			Coordinate c1 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N4);
			for (Hexagon h : verifyCoordinates(c1)) {
				vertices.add(h.getVertice(Vertice.Type.EAST));
			}
			Coordinate c2 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N2);
			for (Hexagon h : verifyCoordinates(c2)) {
				vertices.add(h.getVertice(Vertice.Type.WEST));
			}
			
		} else if (edge.getType() == Edge.Type.NORTH_EAST) {
			vertices.add(edge.getParent().getVertice(Vertice.Type.EAST));
			
			Coordinate c = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N2);
			for (Hexagon h : verifyCoordinates(c)) {
				vertices.add(h.getVertice(Vertice.Type.WEST));
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
				Neighbourhood.Regular.N5);
		for (Hexagon h : verifyCoordinates(c1)) {
			edges.add(h.getEdge(Edge.Type.NORTH_EAST));
		}
		
		Coordinate c2 = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N6);
		for (Hexagon h : verifyCoordinates(c2)) {
			edges.add(h.getEdge(Edge.Type.NORTH));
		}
		
		Coordinate c3 = Neighbourhood.adjacentTo(
				hexagon.getCoordinate(),
				Neighbourhood.Regular.N1);
		for (Hexagon h : verifyCoordinates(c3)) {
			edges.add(h.getEdge(Edge.Type.NORTH_WEST));
		}
		
		return edges;
	}

	@Override
	public Collection<Edge> edgesNear(Vertice vertice) {
		if (vertice == null) throw new NullPointerException();
		
		Collection<Edge> edges = new HashSet<>();	
		
		if (vertice.getType() == Vertice.Type.WEST) {
			edges.add(vertice.getParent().getEdge(Edge.Type.NORTH_WEST));
			
			Coordinate c = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N5);
			for (Hexagon h : verifyCoordinates(c)) {
				edges.add(h.getEdge(Edge.Type.NORTH));
				edges.add(h.getEdge(Edge.Type.NORTH_EAST));
			}
			
		} else if (vertice.getType() == Vertice.Type.EAST) {
			edges.add(vertice.getParent().getEdge(Edge.Type.NORTH_EAST));
			
			Coordinate c = Neighbourhood.adjacentTo(
					vertice.getParent().getCoordinate(),
					Neighbourhood.Regular.N1);
			for (Hexagon h : verifyCoordinates(c)) {
				edges.add(h.getEdge(Edge.Type.NORTH));
				edges.add(h.getEdge(Edge.Type.NORTH_WEST));
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
		
		if (edge.getType() == Edge.Type.NORTH_WEST) {
			edges.add(edge.getParent().getEdge(Edge.Type.NORTH));
			
			Coordinate c1 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N4);
			for (Hexagon h : verifyCoordinates(c1)) {
				edges.add(h.getEdge(Edge.Type.NORTH_EAST));
			}
			
			Coordinate c2 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N5);
			for (Hexagon h : verifyCoordinates(c2)) {
				edges.add(h.getEdge(Edge.Type.NORTH));
				edges.add(h.getEdge(Edge.Type.NORTH_EAST));
			}
			
		} else if (edge.getType() == Edge.Type.NORTH) {
			edges.add(edge.getParent().getEdge(Edge.Type.NORTH_WEST));
			edges.add(edge.getParent().getEdge(Edge.Type.NORTH_EAST));
			
			Coordinate c1 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N4);
			for (Hexagon h : verifyCoordinates(c1)) {
				edges.add(h.getEdge(Edge.Type.NORTH_EAST));
			}
			
			Coordinate c2 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N2);
			for (Hexagon h : verifyCoordinates(c2)) {
				edges.add(h.getEdge(Edge.Type.NORTH_WEST));
			}
			
		} else if (edge.getType() == Edge.Type.NORTH_EAST) {
			edges.add(edge.getParent().getEdge(Edge.Type.NORTH));
			
			Coordinate c1 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N2);
			for (Hexagon h : verifyCoordinates(c1)) {
				edges.add(h.getEdge(Edge.Type.NORTH_WEST));
			}
			
			Coordinate c2 = Neighbourhood.adjacentTo(
					edge.getParent().getCoordinate(),
					Neighbourhood.Regular.N1);
			for (Hexagon h : verifyCoordinates(c2)) {
				edges.add(h.getEdge(Edge.Type.NORTH_WEST));
				edges.add(h.getEdge(Edge.Type.NORTH));
			}
		} else {
			throw new IllegalArgumentException("Illegal edge: " + edge);
		}
		return edges;
	}
}
