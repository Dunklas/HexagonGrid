# HexagonGrid

A library for creating grids of hexagons. May be rendered on a canvas or similar.

Typical usage:

  Create a grid:

    final Grid grid = new GridBuilder()
        .setRadius(3)
        .setFormation(GridFormation.HEXAGONAL)
        .setOrientation(Hexagon.Orientation.POINTY)
        .build();


  Create a GridLayout (this object is required to obtain the points used for rendering):
    
    // For a canvas of 1024x768, where 50 is size of each hexagon
    GridLayout gl = new GridLayout(1024/2, 768/2, 50);

  Obtain points for each Hexagon:

    for (Hexagon h : grid.getHexagons()) {
        List<Point> points = h.getPoints(gl);
         
    }

  This example would render the following grid (displayed in a simple Swing GUI): 
  
  ![Image of grid]
  (grid.png)
