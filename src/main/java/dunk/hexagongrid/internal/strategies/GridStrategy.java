package dunk.hexagongrid.internal.strategies;

import java.util.Set;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.GridBuilder;

public abstract class GridStrategy {

	public abstract Set<Coordinate> getCoordinates(GridBuilder builder);
	public abstract void verifyBuilder(GridBuilder builder);
}
