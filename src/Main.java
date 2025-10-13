import model.Gomoku;
import model.Puissance4;
import model.TicTacToe;
import model.ArtificialPlayer;
import model.HumanPlayer;

import java.util.Scanner;
import model.Game;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static boolean unfinished = true;
    public static void main(String[] args) {

        view.View view = new view.View();
        view.InteractionUtilisateur interaction = new view.InteractionUtilisateur(view);

        while (true) {
            System.out.println(" Game Library ");
            System.out.println("1 - Tic Tac Toe");
            System.out.println("2 - Gomoku");
            System.out.println("3 - Puissance 4");
            System.out.print("Select the game you want to play: ");

            String option = scanner.nextLine().trim();
            boolean gamePlayed = false;

            switch (option) {
                case "1":
                    Game tictactoe = new TicTacToe(
                            new HumanPlayer(" O ", interaction),
                            new ArtificialPlayer(" X "), view);
                    tictactoe.play();
                    gamePlayed = true;
                    break;

                case "2":
                    Game gomoku = new Gomoku (new HumanPlayer(" O ", interaction),
                        new ArtificialPlayer(" X "), view);
                    gomoku.play();
                    gamePlayed = true;
                    break;

                case "3":
                    Game puissance4 = new Puissance4(new HumanPlayer(" O ", interaction),
                            new ArtificialPlayer(" X "), view);
                    puissance4.play();
                    gamePlayed = true;
                    break;

                default:
                    System.out.println("Insert a valid option!");
            }

            if (gamePlayed) {
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
