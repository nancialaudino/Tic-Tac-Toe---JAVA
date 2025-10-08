public class Main {
    public static void main(String[] args) {
        // Example 1: 2 Human Players
         TicTacToe game = new TicTacToe(new HumanPlayer(" O "), new HumanPlayer(" X "));

        // Example 2: Human Player vs Artificial Player
        //TicTacToe game = new TicTacToe(new HumanPlayer(" O "), new ArtificialPlayer(" X "));

        // Example 3: 2 Artificial Players
        // TicTacToe game = new TicTacToe(new ArtificialPlayer(" O "), new ArtificialPlayer(" X "));

        game.play();
    }


}