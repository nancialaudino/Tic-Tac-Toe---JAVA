package players;

import game.Game;
import game.Player;
import user.InteractionUtilisateur;

public class HumanPlayer implements Player {
    private final String representation;
    private final InteractionUtilisateur interaction;

    public HumanPlayer(String representation, InteractionUtilisateur interaction) {
        this.representation = representation;
        this.interaction = interaction;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }

    @Override
    public int[] getMove(Game game) {
        // The InteractionUtilisateur handles user input entirely
        return interaction.askMove(game);
    }
}


