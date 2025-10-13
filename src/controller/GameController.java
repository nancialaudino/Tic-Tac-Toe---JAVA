package controller;

import model.Game;
import view.View;

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
    protected abstract void nextMove();
    protected abstract void checkWinner();
    protected void displayBoard() {
        view.displayBoard(game.getBoard().getCells(), game.getBoard().getRows(), game.getBoard().getCols());
    }
    protected abstract void endGame();
}
