package controller;

import model.Game;
import view.View;
import model.Player;

public abstract class GameController {
    protected final Game game;
    protected final View view;
    protected GameState state;

    public GameController(Game game, View view) {
        this.game = game;
        this.view = view;
        this.state = GameState.INITIAL;
    }

    public abstract void startGame();

    /* Template method for next move */
    protected void nextMove() {
        Player current = game.getCurrentPlayer();
        int[] move = current.getMove(game); // avoid instanceof, delegate to Player
        game.applyMove(move, current);
        view.displayBoard(game.getBoard().getCells(), game.getBoard().getRows(), game.getBoard().getCols());
        checkWinner(current);
    }

    /* Check if the current player has won or draw, update state */
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

    protected void endGame() {
        if (state == GameState.WIN) {
            view.showMessage("Winner: " + game.getCurrentPlayer().getRepresentation());
        } else if (state == GameState.DRAW) {
            view.showMessage("Draw");
        }
    }
}
