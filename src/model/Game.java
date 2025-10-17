package model;

import view.View;

/**
 * Abstract base class representing a generic board game.
 * Defines the main game loop and common behaviors.
 */

public abstract class Game implements GameStrategy {
    protected final Board board;
    protected final Player[] players;
    protected final View view;
    protected int currentPlayerIndex = 0;

    /**
     * Creates a new game with given dimensions, players and view.
     */

    protected Game(int rows, int cols, Player p1, Player p2, View view) {
        this.board = new Board(rows, cols);
        this.players = new Player[]{p1, p2};
        this.view = view;
    }

    /**
     * Template method: runs the main game loop.
     */
    @Override
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



    /**
     * Optional hook for subclasses to initialize the game.
     */
    @Override
    public void initialize() { /* optional override */ }

    /**
     * Gets the next move from a player.
     */

    @Override
    public int[] getMoveFromPlayer(Player player) {
        return player.getMove(this);
    }

    /**
     * Displays the board through the view.
     */

    protected void displayBoard() {
        if (view != null) view.displayBoard(board.getCells(), board.getRows(), board.getCols());
    }
    /** Switches to the next player. */
    @Override
    public void nextPlayer() { currentPlayerIndex = 1 - currentPlayerIndex; }

    /** Returns the current player. */

    @Override
    public Player getCurrentPlayer() { return players[currentPlayerIndex]; }

    /** Returns the game board. */

    @Override
    public Board getBoard() { return board; }

    /** Applies a move to the board. */

    @Override
    public abstract void applyMove(int[] move, Player player);

    /** Checks if a player has won. */

    @Override
    public abstract boolean hasWinner(Player player);

    /** Displays a winning message. */

    protected void onWin(Player player) {
        if (view != null) view.showMessage("Winner: " + player.getRepresentation());
    }
    /** Displays a draw message. */
    protected void onDraw() {
        if (view != null) view.showMessage("Draw");
    }
}
