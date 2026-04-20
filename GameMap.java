/**
 * Represents the full game map as a grid of cells.
 * The map also tracks which cells are flooded and controls how the map
 * is displayed to the player.
 */
public class GameMap {
    private Cell[][] grid;
    private boolean[][] flooded;
    private int rows;
    private int cols;

    /**
     * Creates a game map with the given number of rows and columns.
     *
     * @param rows the number of rows in the map
     * @param cols the number of columns in the map
     */
    public GameMap(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        flooded = new boolean[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                grid[y][x] = new Cell(x, y);
                flooded[y][x] = false;
            }
        }
    }

    /**
     * Returns the cell at a specific location.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the cell at the location, or null if the location is invalid
     */
    public Cell getCell(int x, int y) {
        if (!isValidMove(x, y)) return null;
        return grid[y][x];
    }

    /**
     * Checks whether a location is inside the map.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return true if the location is valid, false otherwise
     */
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < cols && y >= 0 && y < rows;
    }

    /**
     * Places an event at a specific map location.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param event the event to place
     */
    public void placeEvent(int x, int y, Event event) {
        if (isValidMove(x, y)) {
            grid[y][x].setEvent(event);
        }
    }

    /**
     * Marks a map cell as flooded.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void setFlooded(int x, int y) {
        if (isValidMove(x, y)) {
            flooded[y][x] = true;
        }
    }

    /**
     * Checks whether a map cell is flooded.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return true if the cell is flooded, false otherwise
     */
    public boolean isFlooded(int x, int y) {
        if (!isValidMove(x, y)) return false;
        return flooded[y][x];
    }

    /**
     * Prints the map using symbols for the player, flooded cells,
     * and normal cells. The mother cat is hidden from the map.
     *
     * @param player the player-controlled cat
     * @param mother the mother cat
     */
    public void printMap(Cat player, MotherCat mother) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (player.getX() == x && player.getY() == y) {
                    System.out.print(" C ");
                } else if (flooded[y][x]) {
                    System.out.print(" ~ ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Returns a description of a location, including whether it is flooded.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return a description of the location
     */
    public String describeLocation(int x, int y) {
        if (!isValidMove(x, y)) {
            return "This place is outside the map.";
        }
        
        String description = grid[y][x].describe();
        if (flooded[y][x]) {
            description += " Floodwater moves around you.";
        }
        
        return description;
    }
}