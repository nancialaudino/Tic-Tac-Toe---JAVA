package model;

import view.InteractionUtilisateur;

/**
 * Represents a human-controlled player.
 */

public class HumanPlayer implements Player {
    private final String representation;
    private final InteractionUtilisateur interaction;

    /** Creates a new human player with user interaction. */
    public HumanPlayer(String representation, InteractionUtilisateur interaction) {
        this.representation = representation;
        this.interaction = interaction;
    }

    @Override
    public String getRepresentation() { return representation; }

    /** Gets a move from the user via console input. */
    @Override
    public int[] getMove(Game game) {
        return interaction.askMove(game);
    }
}
