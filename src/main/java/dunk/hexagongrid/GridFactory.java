package dunk.hexagongrid;

import dunk.hexagongrid.internal.FlatGrid;
import dunk.hexagongrid.internal.PointyGrid;

public final class GridFactory {

	private GridFactory() {}
	
	/**
	 * Creates an instance of a {@link Grid}
	 * @param radius an integer value between 0 and 500
	 * @param formation the formation of the {@link Grid} to be created. E.g. Hexagon, Rectangle, Triangle
	 * @param orientation the orientation of the {@link Hexagon} instances in the {@link Grid} to be created
	 * @throws NullPointerException	If formation or orientation are null
	 * @throws IllegalArgumentException If radius is less than or equal to 0, or more than 500
	 * @return an instance of a {@link Grid}
	 */
	public static Grid createGrid(int radius, Grid.Formation formation, Hexagon.Orientation orientation) {
		if (formation == null || orientation == null) throw new NullPointerException();
		if (radius <= 0 || radius > 500) throw new IllegalArgumentException();
		
		if (orientation == Hexagon.Orientation.POINTY)
			return new PointyGrid(radius, formation);
		else
			return new FlatGrid(radius, formation);
	}
}
