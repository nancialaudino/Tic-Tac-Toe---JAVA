package model;

import view.View;

public abstract class Game {
    protected final Board board;
    protected final Player[] players;
    protected final View view;
    protected int currentPlayerIndex = 0;

    protected Game(int rows, int cols, Player p1, Player p2, View view) {
        this.board = new Board(rows, cols);
        this.players = new Player[]{p1, p2};
        this.view = view;
    }

    // Template method for playing a game
    public void play() {
        displayBoard();
        while (true) {
            Player current = getCurrentPlayer();
            int[] move = current.getMove(this);
            applyMove(move, current);
            displayBoard();

            if (hasWinner(current)) {
                onWin(current);
                break;
            }

            if (board.isFull()) {
                onDraw();
                break;
            }

            nextPlayer();
        }
    }

    // Hook method: subclasses can override
    protected void initialize() { /* optional override */ }

    // Hook method: subclasses can override to customize move input
    public int[] getMoveFromPlayer(Player player) {
        return player.getMove(this);
    }


    protected void displayBoard() {
        if (view != null) view.displayBoard(board.getCells(), board.getRows(), board.getCols());
    }

    public void nextPlayer() { currentPlayerIndex = 1 - currentPlayerIndex; }

    public Player getCurrentPlayer() { return players[currentPlayerIndex]; }

    public Board getBoard() { return board; }

    public abstract void applyMove(int[] move, Player player);
    public abstract boolean hasWinner(Player player);

    protected void onWin(Player player) {
        if (view != null) view.showMessage("Winner: " + player.getRepresentation());
    }

    protected void onDraw() {
        if (view != null) view.showMessage("Draw");
    }
}
