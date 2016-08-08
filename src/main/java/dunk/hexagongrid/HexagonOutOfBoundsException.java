package dunk.hexagongrid;

public class HexagonOutOfBoundsException extends RuntimeException {

	public HexagonOutOfBoundsException() {
		super();
	}
	
	public HexagonOutOfBoundsException(String message) {
		super(message);
	}
}
