package dunk.hexagongrid;

/**
 * This exception is to report that a non-existing {@code Hexagon} has been requested.
 */
public class HexagonOutOfBoundsException extends RuntimeException {

	public HexagonOutOfBoundsException() {
		super();
	}
	
	public HexagonOutOfBoundsException(String message) {
		super(message);
	}
}
