package view;

import model.Game;

import java.util.Scanner;

public class InteractionUtilisateur {
    private final Scanner scanner;
    private final View view;

    public InteractionUtilisateur(View view, Scanner scanner) {
        this.view = view;
        this.scanner = scanner; // centralized scanner
    }

    // General method for row+col games (TicTacToe, Gomoku)
    public int[] askMove(Game game) {
        int rows = game.getBoard().getRows();
        int cols = game.getBoard().getCols();

        while (true) {
            view.showMessage("Enter row (1-" + rows + ") and column (1-" + cols + ") separated by a space: ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) { view.showMessage("Empty entry. Please try again."); continue; }
            String[] parts = line.split("\\s+");
            if (parts.length != 2) { view.showMessage("Please insert two numbers. Example: 2 3"); continue; }
            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                if (row < 1 || row > rows || col < 1 || col > cols) {
                    view.showMessage("Coordinates out of range. Try again."); continue;
                }
                int r = row - 1, c = col - 1;
                if (!game.getBoard().getCell(r, c).isEmpty()) {
                    view.showMessage("Cell already taken. Choose another one."); continue;
                }
                return new int[]{r, c};
            } catch (NumberFormatException e) {
                view.showMessage("Invalid input. Please use integer numbers.");
            }
        }
    }

    // Method specifically for Puissance4 (only ask column)
    public int askColumnPuissance4(int cols) {
        int col = -1;
        while (col < 0 || col >= cols) {
            view.showMessage("Enter column (1-" + cols + "): ");
            String line = scanner.nextLine().trim();
            try {
                col = Integer.parseInt(line) - 1;
                if (col < 0 || col >= cols) {
                    view.showMessage("Column out of range. Try again.");
                }
            } catch (NumberFormatException e) {
                view.showMessage("Invalid input. Enter an integer number.");
            }
        }
        return col;
    }
}
