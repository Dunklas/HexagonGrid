package dunk.hexagongrid.internal;

import dunk.hexagongrid.Edge;
import dunk.hexagongrid.GridLayout;
import dunk.hexagongrid.Hexagon;
import dunk.hexagongrid.Point;

abstract class AbstractEdge implements Edge {

	protected final AbstractHexagon parent;
	protected final Edge.Direction type;

	AbstractEdge(AbstractHexagon parent, Edge.Direction type) {
		if (parent == null || type == null)
			throw new NullPointerException();
		this.parent = parent;
		this.type = type;
	}
	
	@Override
	public abstract Point getFirstPoint(GridLayout layout);
	
	@Override
	public abstract Point getSecondPoint(GridLayout layout);
	
	@Override
	public Direction getType() {
		return type;
	}
	
	@Override
	public Hexagon getParent() {
		return parent;
	}
		
	@Override
	public Point getCenterPoint(GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		Point p1 = getFirstPoint(layout);
		Point p2 = getSecondPoint(layout);
		return Point.centerOf(p1, p2);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEdge other = (AbstractEdge) obj;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%s edge)", parent, type);
	}
}
