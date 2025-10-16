package model;

import view.View;

public class Puissance4 extends Game {
    private final int inARow = 4; // number of pieces in a row to win

    public Puissance4(Player p1, Player p2, View view) {
        super(6, 7, p1, p2, view); // 6 rows, 7 columns
    }


    @Override
    public void applyMove(int[] move, Player player) {
        int row = move[0];
        int col = move[1];

        if (col < 0 || col >= board.getCols()) {
            throw new IllegalArgumentException("Invalid column: " + (col + 1));
        }

        if (row == -1 || row >= board.getRows()) {
            throw new IllegalStateException("Column " + (col + 1) + " is full.");
        }

        board.getCell(row, col).setOwner(player);
        displayBoard();
    }

    @Override
    public boolean hasWinner(Player player) {
        int rows = board.getRows();
        int cols = board.getCols();

        // Horizontal
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols - inARow; c++) {
                if (checkLine(player, r, c, 0, 1)) return true;
            }
        }

        // Vertical
        for (int r = 0; r <= rows - inARow; r++) {
            for (int c = 0; c < cols; c++) {
                if (checkLine(player, r, c, 1, 0)) return true;
            }
        }

        // Diagonal down-right
        for (int r = 0; r <= rows - inARow; r++) {
            for (int c = 0; c <= cols - inARow; c++) {
                if (checkLine(player, r, c, 1, 1)) return true;
            }
        }

        // Diagonal up-right
        for (int r = inARow - 1; r < rows; r++) {
            for (int c = 0; c <= cols - inARow; c++) {
                if (checkLine(player, r, c, -1, 1)) return true;
            }
        }

        return false;
    }

    private boolean checkLine(Player player, int startRow, int startCol, int dRow, int dCol) {
        for (int i = 0; i < inARow; i++) {
            Cell cell = board.getCell(startRow + i * dRow, startCol + i * dCol);
            if (cell.isEmpty() || !cell.getOwner().getRepresentation().equals(player.getRepresentation())) {
                return false;
            }
        }
        return true;
    }

    // Given a column, calculate the lowest available row
    public int dropToRow(int col) {
        for (int r = board.getRows() - 1; r >= 0; r--) {
            if (board.getCell(r, col).isEmpty()) return r;
        }
        return -1; // column full
    }

    @Override
    protected void onDraw() {
        view.showMessage("The board is full. It's a draw!");
    }
}
