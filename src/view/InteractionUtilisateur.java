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


    public int[] askMove(Game game) {
        int rows = game.getBoard().getRows();
        int cols = game.getBoard().getCols();

        while (true) {
            try {
                view.showMessage("Enter row (1-" + rows + ") and column (1-" + cols + ") separated by a space: ");
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    view.showMessage("Input cannot be empty. Try again.");
                    continue;
                }
                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    view.showMessage("Please enter two integers separated by space.");
                    continue;
                }

                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;

                if (row < 0 || row >= rows || col < 0 || col >= cols) {
                    view.showMessage("Coordinates out of bounds. Try again.");
                    continue;
                }
                if (!game.getBoard().getCell(row, col).isEmpty()) {
                    view.showMessage("Cell already occupied. Choose another.");
                    continue;
                }
                return new int[]{row, col};
            } catch (NumberFormatException e) {
                view.showMessage("Invalid format. Use numbers only.");
            } catch (Exception e) {
                view.showMessage("Unexpected error: " + e.getMessage());
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
