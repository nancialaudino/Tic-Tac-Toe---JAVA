package model;

public class Board {
    private final int rows;
    private final int cols;
    private final Cell[][] cells;

    public Board(int rows, int cols) {
        this.rows = rows; this.cols = cols;
        cells = new Cell[rows][cols];
        for (int r=0;r<rows;r++) for (int c=0;c<cols;c++) cells[r][c] = new Cell();
    }

    public Cell getCell(int r,int c) { return cells[r][c]; }
    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public boolean isFull() {
        for (Cell[] row : cells) for (Cell cell : row) if (cell.isEmpty()) return false;
        return true;
    }

    // convenience for view:
    public Cell[][] getCells() { return cells; }
}
