package model;

public class HumanPlayer implements Player {
    private final String representation;
    private final view.InteractionUtilisateur interaction;

    public HumanPlayer(String representation, view.InteractionUtilisateur interaction) {
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



