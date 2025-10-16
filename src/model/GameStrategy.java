package model;

import view.View;

public interface GameStrategy {
    void play();                      // main gameplay loop
    void applyMove(int[] move, Player player);
    boolean hasWinner(Player player);
    Board getBoard();
    Player getCurrentPlayer();
    void nextPlayer();
    void initialize();                // optional: for setup before play
    int[] getMoveFromPlayer(Player player);
}
