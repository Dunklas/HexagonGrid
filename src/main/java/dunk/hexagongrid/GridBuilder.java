package dunk.hexagongrid;

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
		verifyBuilder();
		if (orientation == Hexagon.Orientation.POINTY)
			return new PointyGrid(this);
		else 
			return new FlatGrid(this);
	}
		
	private void verifyBuilder() {
		if (radius <= 0)
			throw new IllegalArgumentException("Illegal radius: " + radius + ". Must be greater than 0.");
		if (formation == null)
			throw new IllegalArgumentException("Illegal formation: " + formation + ". Must not be null.");
		if (orientation == null)
			throw new IllegalArgumentException("Illegal orientation: " + orientation + ". Must not be null.");
		formation.getStrategy().verifyBuilder(this);
	}
}
