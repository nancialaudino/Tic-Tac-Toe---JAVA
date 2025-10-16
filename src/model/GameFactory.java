package model;

import controller.*;
import view.View;
import view.InteractionUtilisateur;

public class GameFactory {

    public static GameController createController(String option, InteractionUtilisateur interaction, View view) {
        switch (option) {
            case "1": // Tic Tac Toe
                TicTacToe tttGame = new TicTacToe(
                        new HumanPlayer(" O ", interaction),
                        new ArtificialPlayer(" X "), view);
                return new TicTacToeController(tttGame, view);

            case "2": // Gomoku
                Gomoku gomokuGame = new Gomoku(
                        new HumanPlayer(" O ", interaction),
                        new ArtificialPlayer(" X "), view);
                return new GomokuController(gomokuGame, view);

            case "3": // Puissance 4
                Puissance4 puissance4Game = new Puissance4(
                        new HumanPlayer(" O ", interaction),
                        new ArtificialPlayer(" X "), view);
                return new Puissance4Controller(puissance4Game, view);

            default:
                return null;
        }
    }
}
