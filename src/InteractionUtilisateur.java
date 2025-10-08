import java.util.Scanner;

public class InteractionUtilisateur {
    private final Scanner scanner = new Scanner(System.in);
    private final View view;

    public InteractionUtilisateur(View view) {
        this.view = view;
    }

    public int[] askMove(int size) {
        while (true) {
            view.showMessage("Write a number between 1 and " + size + " separated by a space: ");
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                view.showMessage("Empty entry. Please try again.");
                continue;
            }

            String[] parts = line.split("\\s+");
            if (parts.length < 2) {
                view.showMessage("Please insert two numbers. Ex: 2 2");
                continue;
            }

            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                if (row < 1 || row > size || col < 1 || col > size) {
                    view.showMessage("Coordinate not valid. Insert a number between 1 and " + size);
                    continue;
                }
                return new int[]{row - 1, col - 1};
            } catch (NumberFormatException e) {
                view.showMessage("Invalid. Insert integer numbers.");
            }
        }
    }


}
