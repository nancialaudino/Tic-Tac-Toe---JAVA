package model;
import view.View;

public abstract class Game {
    protected final Board board;
    protected final Player[] players;
    private final view.View view;
    protected int currentPlayerIndex = 0;

    protected Game(int rows, int cols, Player p1, Player p2, view.View view) {
        this.board = new Board(rows, cols);
        this.players = new Player[] { p1, p2 };
        this.view = view;
    }

    /* Template game loop. Subclasses can override helpers. */
    public final void play() {
        initialize();
        viewDisplay();
        while (true) {
            Player current = players[currentPlayerIndex];
            int[] move = getMoveFromPlayer(current);
            applyMove(move, current);
            viewDisplay();

            if (hasWinner(current)) {
                onWin(current);
                break;
            }
            if (board.isFull()) {
                onDraw();
                break;
            }
            currentPlayerIndex = 1 - currentPlayerIndex;
        }
    }

    protected void viewDisplay() {
        if (view != null) {
            view.displayBoard(board.getCells(), board.getRows(), board.getCols());
        }
    }

    protected void initialize() { /* optional override */ }

    public int[] getMoveFromPlayer(Player player) {
        return player.getMove(this);
    }

    public abstract void applyMove(int[] move, Player player);
    public abstract boolean hasWinner(Player player);

    protected void onWin(Player player) {
        System.out.println("Winner: " + player.getRepresentation());
    }

    protected void onDraw() {
        System.out.println("Draw");
    }

    public Board getBoard() { return board; }
    public Player getCurrentPlayer() { return players[currentPlayerIndex]; }

    protected view.View getView() {
        return view;
    }

    public void nextPlayer() {
        currentPlayerIndex = 1 - currentPlayerIndex;
    }
}



