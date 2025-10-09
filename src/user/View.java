package user;
import game.Cell;

public class View {
    public void displayBoard(Cell[][] board, int size) {
        int sepLen = size * 4 + 1;
        StringBuilder sbSep = new StringBuilder();
        for (int k = 0; k < sepLen; k++) sbSep.append('-');
        String sep = sbSep.toString();

        for (int i = 0; i < size; i++) {
            System.out.println(sep);
            for (int j = 0; j < size; j++) {
                String cell = board[i][j].getRepresentation().trim();
                System.out.print("| " + (cell.isEmpty() ? " " : cell) + " ");
            }
            System.out.println("|");
        }
        System.out.println(sep);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
