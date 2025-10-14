package controller;

import model.Gomoku;
import model.Player;
import view.View;

public class GomokuController extends GameController {

    public GomokuController(Gomoku game, View view) {
        super(game, view);
    }

    @Override
    public void startGame() {
        state = GameState.INITIAL;
        view.displayBoard(game.getBoard().getCells(), game.getBoard().getRows(), game.getBoard().getCols());
        state = GameState.WAITING_MOVE;

        while (state != GameState.WIN && state != GameState.DRAW) {
            nextMove();
        }
    }

    @Override
    protected void nextMove() {
        Player current = game.getCurrentPlayer();
        int[] move = current.getMove(game);

        game.applyMove(move, current);
        view.displayBoard(game.getBoard().getCells(), game.getBoard().getRows(), game.getBoard().getCols());

        if (game.hasWinner(current)) {
            state = GameState.WIN;
            view.showMessage("Winner: " + current.getRepresentation());
        } else if (game.getBoard().isFull()) {
            state = GameState.DRAW;
            view.showMessage("Draw!");
        } else {
            game.nextPlayer();
        }
    }
}
