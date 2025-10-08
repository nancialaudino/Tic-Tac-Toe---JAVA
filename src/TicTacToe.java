import java.util.Scanner;

public class TicTacToe {
    public final int SIZE = 3;
    public Cell[][] board;
    private final Player player1;
    private final Player player2;

    public TicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    /** Displays the current state of the game board in the console. */
    public void display() {
        int sepLen = SIZE * 4 + 1;
        StringBuilder sbSep = new StringBuilder();
        for (int k = 0; k < sepLen; k++) sbSep.append('-');
        String sep = sbSep.toString();

        for (int i = 0; i < SIZE; i++) {
            System.out.println(sep);
            for (int j = 0; j < SIZE; j++) {
                System.out.print("|" + board[i][j].getRepresentation());
            }
            System.out.println("|");
        }
        System.out.println(sep);
    }

    /** Main game loop.
     * Players take turns until someone wins or the board is full (draw).
     */
    public void play() {
        Player currentPlayer = player1;
        display();

        while (true) {
            System.out.println("Current player: " + currentPlayer.getRepresentation());
            int[] move = currentPlayer.getMove(this);
            setOwner(move[0], move[1], currentPlayer);
            display();

            //Check if the current player has won
            if (hasWon(currentPlayer)) {
                System.out.println(" The winner is Player " + currentPlayer.getRepresentation());
                break;
            }

            //Check if the board is full (draw)
            if (isBoardFull()) {
                System.out.println("It's a draw! The board is full.");
                break;
            }

            // Switch players after each turn
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }

    /**
     * Checks if all cells are occupied.
     * @return true if no cell is empty (board full)
     */
    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].isEmpty()) return false;
            }
        }
        return true;
    }

    /**
     * Assigns ownership of a cell to a given player. Throws an exception if coordinates are invalid.
     */
    public void setOwner(int row, int col, Player player) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Invalid coordinates: out of bounds.");
        }
        board[row][col].setOwner(player);
    }

    /*
     * Checks if the given player has aligned 3 marks horizontally, vertically, or diagonally.
     * @param player the player to check for victory
     * @return true if the player has won
     */
    private boolean hasWon(Player player) {
        String rep = player.getRepresentation();

        // Check all rows
        for (int i = 0; i < SIZE; i++) {
            if (checkLine(rep, board[i][0], board[i][1], board[i][2])) return true;
        }

        // Check all columns
        for (int j = 0; j < SIZE; j++) {
            if (checkLine(rep, board[0][j], board[1][j], board[2][j])) return true;
        }

        // Check diagonals
        if (checkLine(rep, board[0][0], board[1][1], board[2][2])) return true;
        if (checkLine(rep, board[0][2], board[1][1], board[2][0])) return true;

        return false;
    }

    /* Helper method to check if three cells in a line belong to the same player. */
    private boolean checkLine(String rep, Cell c1, Cell c2, Cell c3) {
        return !c1.isEmpty() &&
                c1.getRepresentation().equals(rep) &&
                c2.getRepresentation().equals(rep) &&
                c3.getRepresentation().equals(rep);
    }
}

