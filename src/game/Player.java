package game;
import games.Puissance4;

public interface Player {
    String getRepresentation();
    int[] getMove(Game game);
}
