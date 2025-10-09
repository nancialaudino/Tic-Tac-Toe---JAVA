package games;

import game.Game;
import game.Player;
import user.View;


public class TicTacToe extends Game {
    public TicTacToe(Player p1, Player p2, View view) {
        super(3, 3, p1, p2, view);
    }

    @Override
    protected int[] getMoveFromPlayer(Player player) {
        return player.getMove(this);
    }

    @Override
    protected void applyMove(int[] move, Player player) {
        getBoard().getCell(move[0], move[1]).setOwner(player);
        // display can be done by external View, or keep it here temporarily:
        displayBoard();
    }

    @Override
    protected boolean hasWinner(Player player) {
        String rep = player.getRepresentation();
        int n = getBoard().getRows(); // 3
        // Rows
        for (int r = 0; r < n; r++) {
            boolean ok = true;
            for (int c = 0; c < n; c++) {
                if (!getBoard().getCell(r, c).getRepresentation().equals(rep)) { ok = false; break; }
            }
            if (ok) return true;
        }
        // Cols
        for (int c = 0; c < n; c++) {
            boolean ok = true;
            for (int r = 0; r < n; r++) {
                if (!getBoard().getCell(r, c).getRepresentation().equals(rep)) { ok = false; break; }
            }
            if (ok) return true;
        }
        // Diagonals
        boolean ok = true;
        for (int i = 0; i < n; i++) if (!getBoard().getCell(i, i).getRepresentation().equals(rep)) { ok = false; break; }
        if (ok) return true;
        ok = true;
        for (int i = 0; i < n; i++) if (!getBoard().getCell(i, n - 1 - i).getRepresentation().equals(rep)) { ok = false; break; }
        return ok;
    }

    // Temporary simple console display (you can replace by View later)
    private void displayBoard() {
        int size = getBoard().getRows();
        int sepLen = size * 4 + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sepLen; i++) sb.append('-');
        String sep = sb.toString();

        for (int r = 0; r < size; r++) {
            System.out.println(sep);
            for (int c = 0; c < size; c++) {
                System.out.print("|" + getBoard().getCell(r, c).getRepresentation());
            }
            System.out.println("|");
        }
        System.out.println(sep);
    }
}
