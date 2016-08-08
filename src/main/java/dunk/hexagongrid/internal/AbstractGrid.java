package dunk.hexagongrid.internal;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import dunk.hexagongrid.AlienHexagonException;
import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.Edge;
import dunk.hexagongrid.Grid;
import dunk.hexagongrid.GridLayout;
import dunk.hexagongrid.Hexagon;
import dunk.hexagongrid.HexagonOutOfBoundsException;
import dunk.hexagongrid.Point;
import dunk.hexagongrid.Vertice;

abstract class AbstractGrid implements Grid {

	private int radius;
	private HexagonOrientation orientation;
	
	private Collection<Coordinate> coordinates 	= new HashSet<>();
	private Map<Coordinate, Hexagon> hexagonMap = new HashMap<>();
	
	AbstractGrid(final int radius, final Grid.Formation formation, final Hexagon.Orientation orientation) {
		this.radius = radius;
		this.coordinates = GridProducer.getCoordinates(formation, radius);
		
		if (orientation == Hexagon.Orientation.POINTY) {
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
	public Collection<Hexagon> getHexagons() {
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

	@Override // Throws AlienHexagonException if pixel is outside hexagon grid
	public Hexagon getHexagon(Point pixel, GridLayout layout) throws AlienHexagonException {
		if (pixel == null || layout == null) throw new NullPointerException();
		
		HexagonOrientation o = orientation;
		
		Point pt = new Point((pixel.getX() - layout.origin.getX()) / layout.sizeX,
				 			 (pixel.getY() - layout.origin.getY()) / layout.sizeY);
		double x = o.b0 * pt.getX() + o.b1 * pt.getY();
		double y = o.b2 * pt.getX() + o.b3 * pt.getY();
		
		FractionalCoordinate tempCoord = FractionalCoordinate.from(x, y);
		Coordinate finalCoord = CoordinateCalculator.round(tempCoord);
		
		try {
			return getHexagon(finalCoord);
		} catch (HexagonOutOfBoundsException hoobe) {
			throw new AlienHexagonException("Point pixel not within grid");
		}
	}

	@Override // Throws AlienHexagonException if pixel is outside hexagon grid
	public Vertice getVertice(Point pixel, GridLayout layout) throws AlienHexagonException {
		if (pixel == null || layout == null) throw new NullPointerException();
		Hexagon center = getHexagon(pixel, layout);
		Collection<Hexagon> range = getRange(center, 1);
		Vertice closestVertice = null;
		double closestDistance = Double.MAX_VALUE;
		
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

	@Override // Throws AlienHexagonException if pixel is outside hexagon grid
	public Edge getEdge(Point pixel, GridLayout layout) throws AlienHexagonException {
		if (pixel == null || layout == null) throw new NullPointerException();
		Hexagon center = getHexagon(pixel, layout);
		Collection<Hexagon> range = getRange(center, 1);
		Edge closestEdge = null;
		double closestDistance = Double.MAX_VALUE;
		
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
		return coordinates.contains(coordinate);
	}
	
	@Override
	public Collection<Hexagon> getRange(Hexagon center, int radius) {
		if (center == null) throw new NullPointerException();
		if (radius < 0 || radius > this.radius || !contains(center.getCoordinate())) 
			throw new IllegalArgumentException();
		
		Collection<Hexagon> hexagons = new HashSet<>();
		
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
	public Collection<Hexagon> getRing(Hexagon center, int radius) {
		if (center == null) throw new NullPointerException();
		if (radius < 1 || radius > this.radius || !contains(center.getCoordinate())) 
			throw new IllegalArgumentException();
		
		Coordinate tempCoord = CoordinateCalculator.add(
				center.getCoordinate(),
				CoordinateCalculator.multiply(
						Neighbourhood.Regular.N5.coordinate,
						radius));
				
		Collection<Hexagon> hexagons = new HashSet<>();
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
	public Collection<Hexagon> hexagonsNear(Hexagon hexagon) {
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
