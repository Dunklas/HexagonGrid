package dunk.hexagongrid.internal;

import dunk.hexagongrid.GridLayout;
import dunk.hexagongrid.Point;
import dunk.hexagongrid.Vertice;

final class FlatVertice extends AbstractVertice {

	private FlatVertice(final AbstractHexagon parent, final Vertice.Type verticeType) {
		super(parent, verticeType);
	}
	
	static FlatVertice of(final AbstractHexagon parent, final Vertice.Type verticeType) {
		if (verticeType == Vertice.Type.NORTH ||
			verticeType == Vertice.Type.SOUTH)
			throw new IllegalArgumentException("verticeType must be WEST or EAST");
		return new FlatVertice(parent, verticeType);
	}
	
	@Override
	public Point getPoint(GridLayout layout) {
		if (type == Vertice.Type.WEST)
			return parent.getCornerPoint(layout, 3);
		else
			return parent.getCornerPoint(layout, 0);
		//0 = E, 1 = NE, 2 = NW, 3 = W, 4 = SW, 5 = SE, 
	}
}
