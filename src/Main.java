public class Main {
    public static void main(String[] args) {
        View view = new View();
        InteractionUtilisateur interaction = new InteractionUtilisateur(view);

        Game game = new Game(
                new HumanPlayer(" O ", interaction),
                new ArtificialPlayer(" X "), view);

        game.play();

    }

}