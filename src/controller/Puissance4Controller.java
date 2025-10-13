package controller;

import model.Player;
import model.Puissance4;
import view.View;

public class Puissance4Controller extends GameController {

    public Puissance4Controller(Puissance4 game, View view) {
        super(game, view);
    }

    @Override
    public void startGame() {
        state = GameState.INITIAL;
        displayBoard();
        state = GameState.WAITING_MOVE;

        while (state != GameState.WIN && state != GameState.DRAW) {
            nextMove();
        }
    }

    @Override
    protected void nextMove() {
        Player current = game.getCurrentPlayer();
        int[] move = game.getMoveFromPlayer(current);
        game.applyMove(move, current);
        displayBoard();

        if (game.hasWinner(current)) {
            state = GameState.WIN;
            endGame();
        } else if (game.getBoard().isFull()) {
            state = GameState.DRAW;
            endGame();
        } else {

            game.nextPlayer();
        }
    }

    @Override
    protected void checkWinner() {

    }

    @Override
    protected void endGame() {
        if (state == GameState.WIN) {
            System.out.println("Winner: " + game.getCurrentPlayer().getRepresentation());
        } else if (state == GameState.DRAW) {
            System.out.println("Draw");
        }
    }
}