package dunk.hexagongrid.internal;

import dunk.hexagongrid.GridLayout;
import dunk.hexagongrid.Point;
import dunk.hexagongrid.Vertice;

final class PointyVertice extends AbstractVertice {
	
	private PointyVertice(final AbstractHexagon parent, final Vertice.Direction verticeType) {
		super(parent, verticeType);
	}
	
	static PointyVertice of(final AbstractHexagon parent, final Vertice.Direction verticeType) {
		if (verticeType == Vertice.Direction.WEST || 
			verticeType == Vertice.Direction.EAST)
			throw new IllegalArgumentException("verticeType must be NORTH or SOUTH");
		return new PointyVertice(parent, verticeType);
	}
	
	@Override
	public Point getPoint(GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		if (type == Vertice.Direction.NORTH)
			return parent.getCornerPoint(layout, 2);
		else
			return parent.getCornerPoint(layout, 5);
		
		//0 = SE, 1 = NE, 2 = N, 3 = NW, 4 = SW, 5 = S, 
	}
}
