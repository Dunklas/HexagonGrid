package dunk.hexagongrid.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Edge;
import dunk.hexagongrid.Grid;
import dunk.hexagongrid.GridBuilder;
import dunk.hexagongrid.GridLayout;
import dunk.hexagongrid.Hexagon;
import dunk.hexagongrid.HexagonOutOfBoundsException;
import dunk.hexagongrid.Point;
import dunk.hexagongrid.Vertice;

abstract class AbstractGrid implements Grid {

	private int radius;
	private HexagonOrientation orientation;
	
	private Map<Coordinate, Hexagon> hexagonMap = new HashMap<>();
	
	AbstractGrid(final GridBuilder builder) {
		this.radius = builder.getRadius();
		Set<Coordinate> coordinates = builder.getFormation().getStrategy().getCoordinates(builder);
		
		if (builder.getOrientation() == Hexagon.Orientation.POINTY) {
			this.orientation = HexagonOrientation.POINTY;
			for (Coordinate c : coordinates) {
				Hexagon temp = new PointyHexagon(c);
				hexagonMap.put(c, temp);
			}
		}
		else {
			this.orientation = HexagonOrientation.FLAT;
			for (Coordinate c : coordinates) {
				Hexagon temp = new FlatHexagon(c);
				hexagonMap.put(c, temp);
			}
		}	
	}
	
	@Override
	public Set<Hexagon> getHexagons() {
		return new HashSet<>(hexagonMap.values());
	}

	@Override
	public Hexagon getHexagon(Coordinate coordinate) {
		if (coordinate == null) throw new NullPointerException();
		if (contains(coordinate))
			return hexagonMap.get(coordinate);
		else
			throw new HexagonOutOfBoundsException("Coordinate: " + coordinate
					+ " not in grid of size: " + radius);
	}

	@Override 
	public Hexagon getHexagon(double x, double y, GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		HexagonOrientation o = orientation;
		Point pixel = new Point(x, y);
		
		Point pt = new Point((pixel.getX() - layout.origin.getX()) / layout.sizeX,
				 			 (pixel.getY() - layout.origin.getY()) / layout.sizeY);
		double newX = o.b0 * pt.getX() + o.b1 * pt.getY();
		double newY = o.b2 * pt.getX() + o.b3 * pt.getY();
		
		FractionalCoordinate tempCoord = FractionalCoordinate.from(newX, newY);
		Coordinate finalCoord = CoordinateCalculator.round(tempCoord);
		
		return getHexagon(finalCoord);
	}

	@Override
	public Vertice getVertice(double x, double y, GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		Hexagon center = getHexagon(x, y, layout);
		Collection<Hexagon> range = getRange(center, 1);
		Vertice closestVertice = null;
		double closestDistance = Double.MAX_VALUE;
		
		Point pixel = new Point(x, y);
		for (Hexagon h : range) {
			for (Vertice v : h.getVertices()) {
				double distance = pixel.distanceTo(v.getPoint(layout));
				if (distance < closestDistance) {
					closestDistance = distance;
					closestVertice = v;
				}	
			}	
		}
		return closestVertice;
	}

	@Override
	public Edge getEdge(double x, double y, GridLayout layout) {
		if (layout == null) throw new NullPointerException();
		
		Hexagon center = getHexagon(x, y, layout);
		Collection<Hexagon> range = getRange(center, 1);
		Edge closestEdge = null;
		double closestDistance = Double.MAX_VALUE;
		
		Point pixel = new Point(x, y);
		for (Hexagon h : range) {
			for (Edge e : h.getEdges()) {
				double distance = pixel.distanceTo(e.getCenterPoint(layout));
				if (distance < closestDistance) {
					closestDistance = distance;
					closestEdge = e;
				}
			}
		}
		return closestEdge;
	}

	@Override
	public boolean contains(Coordinate coordinate) {
		if (coordinate == null) throw new NullPointerException();
		return hexagonMap.containsKey(coordinate);
	}
	
	@Override
	public Set<Hexagon> getRange(Hexagon center, int radius) {
		if (center == null) throw new NullPointerException();
		if (radius < 0 || radius > this.radius || !contains(center.getCoordinate())) 
			throw new IllegalArgumentException();
		
		Set<Hexagon> hexagons = new HashSet<>();
		
		for (int x = -radius; x <= radius; x++) {
			for (int z = Math.max(-radius, -x - radius); z <= Math.min(radius, -x+radius); z++) {
				final int y = -x-z;
				Coordinate temp = Coordinate.from(x, y);
				temp = CoordinateCalculator.add(temp, center.getCoordinate());
				if (contains(temp))
					hexagons.add(getHexagon(temp));
			}
		}
		return hexagons;
	}
	
	@Override
	public Set<Hexagon> getRing(Hexagon center, int radius) {
		if (center == null) throw new NullPointerException();
		if (radius < 1 || radius > this.radius || !contains(center.getCoordinate())) 
			throw new IllegalArgumentException();
		
		Coordinate tempCoord = CoordinateCalculator.add(
				center.getCoordinate(),
				CoordinateCalculator.multiply(
						Neighbourhood.Regular.N5.coordinate,
						radius));
				
		Set<Hexagon> hexagons = new HashSet<>();
		for (Neighbourhood.Regular n : Neighbourhood.Regular.values()) {
			for (int i = 0; i < radius; i++) {
				if (contains(tempCoord)) {
					hexagons.add(getHexagon(tempCoord));
				}
				tempCoord = Neighbourhood.adjacentTo(tempCoord, n);
			}
		}
		return hexagons;
	}

	@Override
	public Set<Hexagon> hexagonsNear(Hexagon hexagon) {
		if (hexagon == null) throw new NullPointerException();
		if (!contains(hexagon.getCoordinate())) throw new IllegalArgumentException();
		return getRing(hexagon, 1);
	}
	
	// Helper method for PointyGrid / FlatGrid, to avoid exceptions when requesting a set of neighbours
	protected Collection<Hexagon> verifyCoordinates(Coordinate... coordinates) {
		Collection<Hexagon> list = new HashSet<>();
		for (Coordinate c : coordinates) {
			if (contains(c))
				list.add(getHexagon(c));
		}
		return list;
	}
	
}
