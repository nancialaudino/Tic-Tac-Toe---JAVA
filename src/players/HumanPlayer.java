package players;

import game.Game;
import game.Player;
import games.Puissance4;
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
        if (game instanceof Puissance4) {
            int col = interaction.getColumnForPuissance4();
            return new int[]{0, col};
        } else {
            return interaction.askMove(game);
        }
    }
}



