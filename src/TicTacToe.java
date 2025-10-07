import java.util.Scanner;

public class TicTacToe {
    public final int SIZE = 3;
    public Cell [][] board;
    public Player player1;
    public Player player2;
    private Scanner scanner;


    public TicTacToe() {
        this.board = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Cell();
            }
        }
        player1 = new Player(" O ");
        player2 = new Player(" X ");
        scanner = new Scanner(System.in);

    }



    public int[] getMoveFromPlayer(){
        while (true){
            System.out.print("Write a number between 1 and " + SIZE + " separated by a space.");
            String line = scanner.nextLine();

            if (line.isEmpty()) {
                System.out.println("Empty entry. Please try again.");
                continue;
            }

            String[] parts = line.split("\\s+");
            if (parts.length < 2) {
                System.out.println("Please insert two numbers. Ex: 2 2");
                continue;
            }

            int row = getValidNumber(parts[0]);
            int col = getValidNumber(parts[1]);

            if (row == -1 || col == -1) {
                continue; // invalid format
            }

            if (!isValidMove(row, col)) {
                continue; // invalid position
            }

            return new int[]{ row - 1, col - 1 };
        }



    }


    public void display() {
        int sepLen = SIZE * 4 + 1;
        StringBuilder sbSep = new StringBuilder();
        for (int k = 0; k < sepLen; k++) sbSep.append('-');
        String sep = sbSep.toString();

        for (int i = 0; i < SIZE; i++) {
            System.out.println(sep);
            for (int j = 0; j < SIZE; j++) {
                System.out.print("|" + board[i][j].getRepresentation());
            }
            System.out.println("|");
        }
        System.out.println(sep);

    }

    /*
    private int askNumber() {
        // Ask a number to the user
        //
    }
    */



    public void play() {
        Player currentPlayer = player1; // Player1 starts the game
        display();

        while (!isBoardFull()) {
            System.out.println("Current player is: " + currentPlayer.getRepresentation());
            int[] move = getMoveFromPlayer();
            setOwner(move[0], move[1], currentPlayer);
            display();

            // To alternate player
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        System.out.println("The board is full. End of the game");

    }



    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].isEmpty()) return false;
            }
        }
        return true;
    }



    private int getValidNumber(String value) {
        //To convert a string into an Integer. Returns -1 if value is not valid
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("Invalid. Insert integer numbers.");
            return -1;
        }
    }


    private boolean isValidMove (int row, int col) {
        // To validate coordinates inside the board and check if cell is free. Returns true if its a valid move, otherwise returns false.

        if (row < 1 || row > SIZE || col < 1 || col > SIZE) {
            System.out.println("Coordinate not valid. Insert a number between 1 and " + SIZE);
            return false;
        }

        int r = row - 1;
        int c = col - 1;

        if (!board[r][c].isEmpty()) {
            System.out.println("Cell is busy. Choose another one");
            return false;
        }

        return true;
    }


    public void setOwner(int row, int col, Player player) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Invalid. Coordinates are out of bounds");
        }
        board[row][col].setOwner(player);
    }
}