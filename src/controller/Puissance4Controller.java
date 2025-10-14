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
        view.displayBoard(game.getBoard().getCells(), game.getBoard().getRows(), game.getBoard().getCols());
        state = GameState.WAITING_MOVE;

        while (state != GameState.WIN && state != GameState.DRAW) {
            nextMove();
        }
    }
}