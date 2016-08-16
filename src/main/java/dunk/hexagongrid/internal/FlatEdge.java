package dunk.hexagongrid.internal;

import dunk.hexagongrid.Edge;
import dunk.hexagongrid.GridLayout;
import dunk.hexagongrid.Point;

final class FlatEdge extends AbstractEdge {
	
	private FlatEdge(final AbstractHexagon parent, final Edge.Direction edgeType) {
		super(parent, edgeType);
	}
	
	static FlatEdge of(final AbstractHexagon parent, final Edge.Direction edgeType) {
		if (edgeType == Edge.Direction.WEST ||
			edgeType == Edge.Direction.SOUTH_WEST)
			throw new IllegalArgumentException("edgeType must be NORTH_WEST, NORTH or NORTH_EAST");
		return new FlatEdge(parent, edgeType);
	}
	
	@Override
	public Point getFirstPoint(GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		if (type == Edge.Direction.NORTH_WEST)
			return parent.getCornerPoint(layout, 3);
		else if (type == Edge.Direction.NORTH)
			return parent.getCornerPoint(layout, 2);
		else
			return parent.getCornerPoint(layout, 1);
	}

	@Override
	public Point getSecondPoint(GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		if (type == Edge.Direction.NORTH_WEST)
			return parent.getCornerPoint(layout, 2);
		else if (type == Edge.Direction.NORTH)
			return parent.getCornerPoint(layout, 1);
		else
			return parent.getCornerPoint(layout, 0);
	}
}
