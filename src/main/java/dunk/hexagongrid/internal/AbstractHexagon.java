package dunk.hexagongrid.internal;

import java.util.Collection;
import java.util.Vector;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Edge;
import dunk.hexagongrid.GridLayout;
import dunk.hexagongrid.Hexagon;
import dunk.hexagongrid.Point;
import dunk.hexagongrid.Vertice;

abstract class AbstractHexagon implements Hexagon {

	private final HexagonOrientation orientation;
	private final Coordinate coordinate;
	
	AbstractHexagon(HexagonOrientation orientation, 
					Coordinate coordinate) {
		if (orientation == null || coordinate == null)
			throw new NullPointerException();
		
		this.orientation = orientation;
		this.coordinate = coordinate;
	}
	
	@Override
	public Coordinate getCoordinate() { return coordinate; }
	
	@Override
	public abstract Vertice getVertice(dunk.hexagongrid.Vertice.Type direction);
	
	@Override
	public abstract Edge getEdge(dunk.hexagongrid.Edge.Type direction);
	
	@Override
	public abstract Collection<Edge> getEdges();
	
	@Override
	public abstract Collection<Vertice> getVertices();
	
	@Override
	public Collection<Point> getPoints(GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		Collection<Point> points = new Vector<>(6);
		for (int i = 0; i < 6; i++) {
			points.add(getCornerPoint(layout, i));
		}
		return points;
	}
	
	protected Point getCornerPoint(GridLayout layout, int corner) {
		if (layout == null) throw new NullPointerException();
		if (corner < 0 || corner > 5) throw new IllegalArgumentException();
		
		Point center = getCenter(layout);
		Point offset = getHexCornerOffset(layout, corner);
		return new Point(center.getX() + offset.getX(),
						 center.getY() + offset.getY());
	}
	
	@Override
	public Point getCenter(GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		HexagonOrientation o = this.orientation;
		Coordinate c = this.coordinate;
		double xCord = (o.f0 * c.getX() + o.f1 * c.getY()) * layout.sizeX;
		double yCord = (o.f2 * c.getX() + o.f3 * c.getY()) * layout.sizeY;
		return new Point(xCord + layout.origin.getX(), yCord + layout.origin.getY());
	}
	
	private Point getHexCornerOffset(GridLayout layout, int corner) {
		if (layout == null) throw new NullPointerException();
		if (corner < 0 || corner > 5) throw new IllegalArgumentException();
		
		double angle = 2.0 * Math.PI * (orientation.startAngle - corner) / 6;
		return new Point(layout.sizeX * Math.cos(angle), layout.sizeY * Math.sin(angle));
	}
	
	HexagonOrientation getOrientation() {
		return orientation;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		AbstractHexagon other = (AbstractHexagon) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (orientation != other.orientation)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("H: %s)", coordinate);
	}
	
	
}
