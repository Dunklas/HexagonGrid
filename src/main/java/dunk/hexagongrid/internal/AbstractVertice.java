package dunk.hexagongrid.internal;

import dunk.hexagongrid.GridLayout;
import dunk.hexagongrid.Hexagon;
import dunk.hexagongrid.Point;
import dunk.hexagongrid.Vertice;

abstract class AbstractVertice implements Vertice {

	protected final AbstractHexagon parent;
	protected final Vertice.Direction type;
	
	protected AbstractVertice(AbstractHexagon parent, Vertice.Direction type) {
		if (parent == null || type == null) 
			throw new NullPointerException();
		this.parent = parent;
		this.type = type;
	}
	
	@Override
	public abstract Point getPoint(GridLayout layout);
	
	@Override
	public Hexagon getParent() {
		return parent;
	}
	
	@Override
	public Vertice.Direction getType() {
		return type;
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
		AbstractVertice other = (AbstractVertice) obj;
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
		return String.format("%s (%s vertice)", parent, type);
	}
}
