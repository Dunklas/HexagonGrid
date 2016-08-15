package dunk.hexagongrid.internal;

import dunk.hexagongrid.Edge;
import dunk.hexagongrid.GridLayout;
import dunk.hexagongrid.Point;

final class PointyEdge extends AbstractEdge {
	
	private PointyEdge(final AbstractHexagon parent, final Edge.Type edgeType) {
		super(parent, edgeType);
	}
	
	static PointyEdge of(final AbstractHexagon parent, final Edge.Type edgeType) {
		if (edgeType == Edge.Type.NORTH ||
			edgeType == Edge.Type.NORTH_EAST)
			throw new IllegalArgumentException("edgeType must be NORTH_WEST, WEST or SOUTH_WEST");
		return new PointyEdge(parent, edgeType);
	}
	
	// Left-most or upper point is first one
	@Override
	public Point getFirstPoint(GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		if (type == Edge.Type.NORTH_WEST)
			return parent.getCornerPoint(layout, 3);
		else if (type == Edge.Type.WEST)
			return parent.getCornerPoint(layout, 3);
		else 
			return parent.getCornerPoint(layout, 4);	
	}

	@Override
	public Point getSecondPoint(GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		if (type == Edge.Type.NORTH_WEST)
			return parent.getCornerPoint(layout, 2);
		else if (type == Edge.Type.WEST)
			return parent.getCornerPoint(layout, 4);
		else 
			return parent.getCornerPoint(layout, 5);
	}
}
