package model;

public class Gomoku extends Game {
    public Gomoku(Player p1, Player p2, view.View view) {
        super(15, 15, p1, p2, view);
    }


    public int[] getMoveFromPlayer(Player player) {
        return player.getMove(this);
    }


    @Override
    public void applyMove(int[] move, Player player) {
        int row = move[0];
        int col = move[1];

        if (row < 0 || row >= getBoard().getRows() || col < 0 || col >= getBoard().getCols()) {
            throw new IllegalArgumentException("Invalid move coordinates.");
        }

        if (!getBoard().getCell(row, col).isEmpty()) {
            throw new IllegalStateException("Cell already occupied.");
        }

        getBoard().getCell(row, col).setOwner(player);
    }

    @Override
    public boolean hasWinner(Player player) {
        String rep = player.getRepresentation();
        int n = getBoard().getRows();
        int target = 5;

        // check rows
        for (int r = 0; r < n; r++) {
            int count = 0;
            for (int c = 0; c < n; c++) {
                if (getBoard().getCell(r, c).getRepresentation().equals(rep)) {
                    count++;
                    if (count == target) return true;
                } else {
                    count = 0;
                }
            }
        }

        // check columns
        for (int c = 0; c < n; c++) {
            int count = 0;
            for (int r = 0; r < n; r++) {
                if (getBoard().getCell(r, c).getRepresentation().equals(rep)) {
                    count++;
                    if (count == target) return true;
                } else {
                    count = 0;
                }
            }
        }

        // check diagonals
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (checkDirection(r, c, 1, 1, rep, target)) return true;  // diagonal ↘
                if (checkDirection(r, c, 1, -1, rep, target)) return true; // diagonal ↙
            }
        }

        return false;
    }

    private boolean checkDirection(int r, int c, int dr, int dc, String rep, int target) {
        int n = getBoard().getRows();
        int count = 0;
        for (int i = 0; i < target; i++) {
            int nr = r + dr * i, nc = c + dc * i;
            if (nr < 0 || nc < 0 || nr >= n || nc >= n) return false;
            if (getBoard().getCell(nr, nc).getRepresentation().equals(rep)) {
                count++;
            } else {
                break;
            }
        }
        return count == target;
    }
}
