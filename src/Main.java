import model.Gomoku;
import model.Puissance4;
import model.TicTacToe;
import model.ArtificialPlayer;
import model.HumanPlayer;
import model.Game;
import controller.*;
import view.View;
import view.InteractionUtilisateur;

import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static boolean unfinished = true;

    public static void main(String[] args) {

        View view = new View();
        InteractionUtilisateur interaction = new InteractionUtilisateur(view, scanner);

        while (true) {
            System.out.println(" Game Library ");
            System.out.println("1 - Tic Tac Toe");
            System.out.println("2 - Gomoku");
            System.out.println("3 - Puissance 4");
            System.out.print("Select the game you want to play: ");

            String option = scanner.nextLine().trim();
            boolean gamePlayed = false;

            GameController controller = null;

            switch (option) {
                case "1":
                    TicTacToe tttGame = new TicTacToe(
                            new HumanPlayer(" O ", interaction),
                            new ArtificialPlayer(" X "), view);
                    controller = new TicTacToeController(tttGame, view);
                    gamePlayed = true;
                    break;

                case "2":
                    Gomoku gomokuGame = new Gomoku(
                            new HumanPlayer(" O ", interaction),
                            new ArtificialPlayer(" X "), view);
                    controller = new GomokuController(gomokuGame, view);
                    gamePlayed = true;
                    break;

                case "3":
                    Puissance4 puissance4Game = new Puissance4(
                            new HumanPlayer(" O ", interaction),
                            new ArtificialPlayer(" X "), view);
                    controller = new Puissance4Controller(puissance4Game, view);
                    gamePlayed = true;
                    break;

                default:
                    System.out.println("Insert a valid option!");
            }

            if (gamePlayed && controller != null) {
                controller.startGame();

                while (true) {
                    System.out.println("\nDo you want to play again?");
                    System.out.println("0 - Yes");
                    System.out.println("1 - No");
                    System.out.print("Option: ");

                    String playAgain = scanner.nextLine().trim();
                    if (playAgain.equals("0")) {
                        break; // volta ao menu principal
                    } else if (playAgain.equals("1")) {
                        System.out.println("Bye Bye");
                        return;
                    } else {
                        System.out.println("Invalid option. Enter 0 or 1.");
                    }
                }
            }
        }
    }
}
