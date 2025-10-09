package user;

import java.util.Scanner;
import game.Game;

public class InteractionUtilisateur {
    private final Scanner scanner = new Scanner(System.in);
    private final View view;

    public InteractionUtilisateur(View view) {
        this.view = view;
    }

    public int[] askMove(Game game) {
        int rows = game.getBoard().getRows();
        int cols = game.getBoard().getCols();

        while (true) {
            view.showMessage("Enter row (1-" + rows + ") and column (1-" + cols + ") separated by a space: ");
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                view.showMessage("Empty entry. Please try again.");
                continue;
            }

            String[] parts = line.split("\\s+");
            if (parts.length != 2) {
                view.showMessage("Please insert two numbers. Example: 2 3");
                continue;
            }

            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                if (row < 1 || row > rows || col < 1 || col > cols) {
                    view.showMessage("Coordinates out of range. Try again.");
                    continue;
                }

                int r = row - 1, c = col - 1;
                if (!game.getBoard().getCell(r, c).isEmpty()) {
                    view.showMessage("Cell already taken. Choose another one.");
                    continue;
                }

                return new int[]{r, c};
            } catch (NumberFormatException e) {
                view.showMessage("Invalid input. Please use integer numbers.");
            }
        }
    }


}
