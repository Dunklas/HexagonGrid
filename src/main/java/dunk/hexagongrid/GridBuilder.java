package dunk.hexagongrid;

import java.util.Collection;

import dunk.hexagongrid.internal.FlatGrid;
import dunk.hexagongrid.internal.PointyGrid;

/**
 * Builder class for constructing Grid objects.
 */
public final class GridBuilder {

	private int radius = 3;
	private GridFormation formation = GridFormation.HEXAGONAL;
	private Hexagon.Orientation orientation = Hexagon.Orientation.POINTY;
	
	public int getRadius() {
		return radius;
	}
	public GridBuilder setRadius(final int radius) {
		this.radius = radius;
		return this;
	}
	public GridFormation getFormation() {
		return formation;
	}
	public GridBuilder setFormation(final GridFormation formation) {
		this.formation = formation;
		return this;
	}
	public Hexagon.Orientation getOrientation() {
		return orientation;
	}
	public GridBuilder setOrientation(final Hexagon.Orientation orientation) {
		this.orientation = orientation;
		return this;
	}
	
	public Grid build() {
		Collection<Coordinate> coords = formation.getStrategy().getCoordinates(this);
		Grid grid = null;
		if (orientation == Hexagon.Orientation.POINTY)
			grid = new PointyGrid(this.radius, coords);
		else 
			grid = new FlatGrid(this.radius, coords);
		return grid;
	}
}
