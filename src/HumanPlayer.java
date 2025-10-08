import java.util.Scanner;

public class HumanPlayer implements Player {
    private final String representation;
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String representation) {
        this.representation = representation;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }

    @Override
    public int[] getMove(TicTacToe game) {
        while (true) {
            System.out.print("Write a number between 1 and " + game.SIZE + " separated by a space: ");
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                System.out.println("Empty entry. Please try again.");
                continue;
            }

            String[] parts = line.split("\\s+");
            if (parts.length < 2) {
                System.out.println("Please insert two numbers. Ex: 2 2");
                continue;
            }

            int row, col;
            try {
                row = Integer.parseInt(parts[0]);
                col = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid. Insert integer numbers.");
                continue;
            }

            if (row < 1 || row > game.SIZE || col < 1 || col > game.SIZE) {
                System.out.println("Coordinate not valid. Insert a number between 1 and " + game.SIZE);
                continue;
            }

            int r = row - 1;
            int c = col - 1;

            if (!game.board[r][c].isEmpty()) {
                System.out.println("Cell is busy. Choose another one.");
                continue;
            }

            return new int[]{r, c};
        }
    }
}
