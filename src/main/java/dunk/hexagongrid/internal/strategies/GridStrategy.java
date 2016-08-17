package dunk.hexagongrid.internal.strategies;

import java.util.Collection;

import dunk.hexagongrid.Coordinate;
import dunk.hexagongrid.GridBuilder;

public abstract class GridStrategy {

	public abstract Collection<Coordinate> getCoordinates(GridBuilder builder);
}
