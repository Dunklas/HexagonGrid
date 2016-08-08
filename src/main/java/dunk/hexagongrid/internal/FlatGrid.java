package dunk.hexagongrid.internal;

import java.util.Collection;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Hexagon> hexagonsNear(Edge edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Vertice> verticesNear(Hexagon hexagon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Vertice> verticesNear(Vertice vertice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Vertice> verticesNear(Edge edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Edge> edgesNear(Hexagon hexagon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Edge> edgesNear(Vertice vertice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Edge> edgesNear(Edge edge) {
		// TODO Auto-generated method stub
		return null;
	}
}
