package model;
import java.security.SecureRandom;

public class ArtificialPlayer implements Player {
    private final String representation;
    private final SecureRandom random = new SecureRandom();

    public ArtificialPlayer(String representation) {
        this.representation = representation;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }

    @Override
    public int[] getMove(Game game) {
        int rows = game.getBoard().getRows();
        int cols = game.getBoard().getCols();
        int row, col;
        do {
            row = random.nextInt(rows);
            col = random.nextInt(cols);
        } while (!game.getBoard().getCell(row, col).isEmpty());

        System.out.println("Artificial player (" + representation + ") plays: " + (row + 1) + " " + (col + 1));
        return new int[]{row, col};
    }

}
