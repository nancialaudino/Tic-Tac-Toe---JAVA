public class TicTacToe {
    public final int SIZE = 3;
    public Cell[][] board;
    private final Player player1;
    private final Player player2;
    private final View view;

    public TicTacToe(Player player1, Player player2, View view) {
        this.player1 = player1;
        this.player2 = player2;
        this.view = view;
        this.board = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void play() {
        Player currentPlayer = player1;
        view.displayBoard(board, SIZE);

        while (!isBoardFull()) {
            view.showMessage("Current player: " + currentPlayer.getRepresentation());
            int[] move = currentPlayer.getMove(this);
            setOwner(move[0], move[1], currentPlayer);
            view.displayBoard(board, SIZE);

            // Check win condition
            if (hasWinner(currentPlayer)) {
                view.showMessage("The winner is player '" + currentPlayer.getRepresentation() + "'");
                return;
            }

            // Switch players
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        view.showMessage("The board is full. End of the game.");
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].isEmpty()) return false;
            }
        }
        return true;
    }

    private boolean hasWinner(Player player) {
        String rep = player.getRepresentation();

        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if (checkLine(player, i, 0, 0, 1)) return true; // row
            if (checkLine(player, 0, i, 1, 0)) return true; // column
        }

        // Check diagonals
        return checkLine(player, 0, 0, 1, 1) || checkLine(player, 0, SIZE - 1, 1, -1);
    }

    private boolean checkLine(Player player, int startRow, int startCol, int dRow, int dCol) {
        for (int k = 0; k < SIZE; k++) {
            if (!board[startRow + k * dRow][startCol + k * dCol].getRepresentation().equals(player.getRepresentation())) {
                return false;
            }
        }
        return true;
    }

    public void setOwner(int row, int col, Player player) {
        board[row][col].setOwner(player);
    }
}

