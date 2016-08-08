package dunk.hexagongrid;

public interface Edge {

	public enum Type {
		NORTH,
		WEST,
		NORTH_WEST,
		NORTH_EAST,
		SOUTH_WEST
	}
	
	Point 		getFirstPoint(GridLayout layout);
	Point 		getSecondPoint(GridLayout layout);
	Point 		getCenterPoint(GridLayout layout);
	Hexagon 	getParent();
	Edge.Type 	getType();
}
