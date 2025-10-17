package model;

/**
 * Represents the game board containing cells.
 */

public class Board {
    private final int rows;
    private final int cols;
    private final Cell[][] cells;

    /** Creates a board with the given size. */

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];
        for (int r=0;r<rows;r++)
            for (int c=0;c<cols;c++)
                cells[r][c] = new Cell();
    }

    /** Gets the cell content at given coordinates. */

    public Cell getCell(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            throw new IllegalArgumentException("Invalid cell coordinates: (" + r + "," + c + ")");
        }
        return cells[r][c];
    }
    public int getRows() { return rows; }
    public int getCols() { return cols; }


    /** Returns true if all cells are filled. */
    public boolean isFull() {
        for (Cell[] row : cells) for (Cell cell : row) if (cell.isEmpty()) return false;
        return true;
    }

    // convenience for view:
    public Cell[][] getCells() { return cells; }
}
