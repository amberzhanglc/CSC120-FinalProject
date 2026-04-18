public class GameMap {
    private Cell[][] grid;
    private int rows;
    private int cols;

    public GameMap(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                grid[y][x] = new Cell(x, y);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (!isValidMove(x, y)) return null;
        return grid[y][x];
    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < cols && y >= 0 && y < rows;
    }

    public void placeEvent(int x, int y, Event event) {
        if (isValidMove(x, y)) {
            grid[y][x].setEvent(event);
        }
    }

    public void printMap(Cat player, MotherCat mother) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (player.getX() == x && player.getY() == y) {
                    System.out.print(" C ");
                } else if (mother.getX() == x && mother.getY() == y) {
                    System.out.print(" M ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }
}