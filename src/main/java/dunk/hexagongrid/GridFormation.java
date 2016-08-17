package dunk.hexagongrid;

import dunk.hexagongrid.internal.strategies.GridStrategy;
import dunk.hexagongrid.internal.strategies.HexagonalGridStrategy;

/**
 * Represents the formation of a {@code Grid}. 
 * In other words, how {@link Hexagon}s are aligned with each other in a {@code Grid}.
 */
public enum GridFormation {

	HEXAGONAL(new HexagonalGridStrategy());
	
	private GridStrategy strategy;
	
	private GridFormation(final GridStrategy strategy) {
		this.strategy = strategy;
	}
	
	public GridStrategy getStrategy() {
		return strategy;
	}
}
