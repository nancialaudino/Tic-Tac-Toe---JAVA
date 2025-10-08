
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
    public int[] getMove(TicTacToe game) {
        return interaction.askMove(game.SIZE);
    }
}
