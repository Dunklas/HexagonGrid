package dunk.hexagongrid;

import dunk.hexagongrid.internal.FlatGrid;
import dunk.hexagongrid.internal.PointyGrid;

/**
 * Factory class for vending {@code Grid} objects.
 */
public final class GridFactory {

	private GridFactory() {}
	
	/**
	 * Creates {@code Grid} of the specified {@code radius}, {@code formation} and hexagonal {@code orientation}.
	 * 
	 * @param radius  an integer value, between 0 and 500
	 * @param formation  the formation of the {@link Grid} to be created, not null
	 * @param orientation  the orientation of the {@link Hexagon}s in the {@code Grid} to be created, not null
	 * @return the {@code Grid} object
	 * @throws IllegalArgumentException if {@code radius} is less than 1, or more than 500
	 */
	public static Grid createGrid(int radius, Grid.Formation formation, Hexagon.Orientation orientation) {
		if (formation == null || orientation == null) throw new NullPointerException();
		if (radius < 1 || radius > 500) throw new IllegalArgumentException();
		
		if (orientation == Hexagon.Orientation.POINTY)
			return new PointyGrid(radius, formation);
		else
			return new FlatGrid(radius, formation);
	}
}
