package model;

public class Gomoku extends Game {
    public  Gomoku (Player p1, Player p2, view.View view) {
        super(15, 15, p1, p2, view);
    }

    @Override
    public int[] getMoveFromPlayer(Player player) {
        return player.getMove(this);
    }

    @Override
    public void applyMove(int[] move, Player player) {
        getBoard().getCell(move[0], move[1]).setOwner(player);
        // display can be done by external View, or keep it here temporarily:
        //displayBoard();
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

    // helper
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
