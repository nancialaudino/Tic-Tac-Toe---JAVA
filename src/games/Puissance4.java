package games;
import game.Game;
import game.Player;
import game.Cell;
import user.View;

public class Puissance4 extends Game {
    private final int inARow = 4; // number of pieces in a row to win

    public Puissance4(Player p1, Player p2, View view) {
        super(6, 7, p1, p2, view); // 6 rows, 7 columns
    }

    @Override
    protected int[] getMoveFromPlayer(Player player) {
        // Player returns a column index, we calculate row by gravity
        int col = player.getMove(this)[0];
        int row = dropToRow(col);
        return new int[]{row, col};
    }

    private int dropToRow(int col) {
        for (int r = board.getRows() - 1; r >= 0; r--) {
            if (board.getCell(r, col).isEmpty()) return r;
        }
        throw new IllegalArgumentException("Column full");
    }

    @Override
    protected void applyMove(int[] move, Player player) {
        board.getCell(move[0], move[1]).setOwner(player);
    }

    @Override
    protected boolean hasWinner(Player player) {
        int rows = board.getRows();
        int cols = board.getCols();

        // Check horizontal
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols - inARow; c++) {
                if (checkLine(player, r, c, 0, 1)) return true;
            }
        }

        // Check vertical
        for (int r = 0; r <= rows - inARow; r++) {
            for (int c = 0; c < cols; c++) {
                if (checkLine(player, r, c, 1, 0)) return true;
            }
        }

        // Check diagonal down-right
        for (int r = 0; r <= rows - inARow; r++) {
            for (int c = 0; c <= cols - inARow; c++) {
                if (checkLine(player, r, c, 1, 1)) return true;
            }
        }

        // Check diagonal up-right
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

    @Override
    protected void onDraw() {
        System.out.println("The board is full. It's a draw!");
    }

}
