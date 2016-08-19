package dunk.hexagongrid.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	public abstract Vertice getVertice(dunk.hexagongrid.Vertice.Direction direction);
	
	@Override
	public abstract Edge getEdge(dunk.hexagongrid.Edge.Direction direction);
	
	@Override
	public abstract Set<Edge> getEdges();
	
	@Override
	public abstract Set<Vertice> getVertices();
	
	@Override
	public List<Point> getPoints(GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		List<Point> points = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			points.add(getCornerPoint(layout, i));
		}
		return points;
	}
	
	protected Point getCornerPoint(GridLayout layout, int corner) {
		if (layout == null) throw new NullPointerException();
		if (corner < 0 || corner > 5) throw new IllegalArgumentException();
		
		Point center = getCentrePoint(layout);
		Point offset = getHexCornerOffset(layout, corner);
		return new Point(center.getX() + offset.getX(),
						 center.getY() + offset.getY());
	}
	
	@Override
	public Point getCentrePoint(GridLayout layout) {
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
		if (!(obj instanceof Hexagon)) return false;
		
		Hexagon other = (Hexagon) obj;
		if (coordinate == null) {
			if (other.getCoordinate() != null)
				return false;
		} else if (!coordinate.equals(other.getCoordinate()))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("H: %s)", coordinate);
	}
	
	
}
