public class Main {
    public static void main(String[] args) {
        Player player = new Player(" O "); //  " X " or " O "
        TicTacToe ticTacToe = new TicTacToe(player);

        System.out.println("Initial Board:");
        ticTacToe.display();

        // Ask for a movement and put it in action
        int[] move = ticTacToe.getMoveFromPlayer();
        ticTacToe.setOwner(move[0], move[1], player);

        System.out.println("After the move:");
        ticTacToe.display();
    }
}