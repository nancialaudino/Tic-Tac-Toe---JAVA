package model;

/**
 * Interface representing a player in the game.
 */
public interface Player {
    String getRepresentation();
    int[] getMove(Game game);
}
