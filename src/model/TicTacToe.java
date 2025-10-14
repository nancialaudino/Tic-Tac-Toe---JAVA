package model;

import view.View;

public class TicTacToe extends Game {

    public TicTacToe(Player p1, Player p2, View view) {
        super(3, 3, p1, p2, view);
    }

    @Override
    public void applyMove(int[] move, Player player) {
        getBoard().getCell(move[0], move[1]).setOwner(player);
    }

    @Override
    public boolean hasWinner(Player player) {
        String rep = player.getRepresentation();
        int n = getBoard().getRows();

        // Rows and columns
        for (int i = 0; i < n; i++) {
            if (checkLine(i, 0, 0, 1, rep) || checkLine(0, i, 1, 0, rep)) return true;
        }

        // Diagonals
        return checkLine(0, 0, 1, 1, rep) || checkLine(0, n - 1, 1, -1, rep);
    }

    private boolean checkLine(int r, int c, int dr, int dc, String rep) {
        int n = getBoard().getRows();
        for (int i = 0; i < n; i++) {
            if (!getBoard().getCell(r + dr * i, c + dc * i).getRepresentation().equals(rep)) return false;
        }
        return true;
    }
}
