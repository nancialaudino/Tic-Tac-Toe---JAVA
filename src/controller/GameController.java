package controller;

import model.Game;
import view.View;
import model.Player;

/**
 * Abstract controller for managing a game's flow.
 * Implements the main loop and common behaviors.
 */

public abstract class GameController {
    protected final Game game;
    protected final View view;
    protected GameState state;

    /** Creates a new controller with a game and its view. */

    public GameController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.state = GameState.INITIAL;
    }
    /** Starts the game loop (implemented by subclasses). */
    public abstract void startGame();

    /** Template method for executing the next move. */
    protected void nextMove() {
        Player current = game.getCurrentPlayer();
        int[] move = current.getMove(game); // avoid instanceof, delegate to Player
        game.applyMove(move, current);
        view.displayBoard(game.getBoard().getCells(), game.getBoard().getRows(), game.getBoard().getCols());
        checkWinner(current);
    }

    /** Checks if the current player won or if the game is a draw. */
    protected void checkWinner(Player player) {
        if (game.hasWinner(player)) {
            state = GameState.WIN;
            endGame();
        } else if (game.getBoard().isFull()) {
            state = GameState.DRAW;
            endGame();
        } else {
            game.nextPlayer();
            state = GameState.WAITING_MOVE;
        }
    }

    /** Ends the game and displays the result. */
    protected void endGame() {
        if (state == GameState.WIN) {
            view.showMessage("Winner: " + game.getCurrentPlayer().getRepresentation());
        } else if (state == GameState.DRAW) {
            view.showMessage("Draw");
        }
    }
}
