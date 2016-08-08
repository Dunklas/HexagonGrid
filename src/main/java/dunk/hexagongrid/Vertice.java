package dunk.hexagongrid;

public interface Vertice {

	public enum Type {
		NORTH,
		SOUTH,
		WEST,
		EAST,
	}
	
	Point 			getPoint(GridLayout layout);
	Hexagon 		getParent();
	Vertice.Type 	getType();
}
