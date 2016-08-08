package dunk.hexagongrid;

import dunk.hexagongrid.internal.FlatGrid;
import dunk.hexagongrid.internal.PointyGrid;

public final class GridFactory {

	private GridFactory() {}
	
	public static Grid createGrid(int radius, Grid.Formation formation, Hexagon.Orientation orientation) {
		if (orientation == Hexagon.Orientation.POINTY)
			return new PointyGrid(radius, formation);
		else
			return new FlatGrid(radius, formation);
	}
}
