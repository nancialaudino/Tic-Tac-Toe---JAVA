package view;
import model.Cell;

/**
 * Responsible for displaying the game board and messages to the user.
 */
public class View {
    /**
     * Displays the board in the console.
     *
     * @param board The matrix of cells.
     * @param rows  Number of rows.
     * @param cols  Number of columns.
     */
        public void displayBoard(Cell[][] board, int rows, int cols) {
            int sepLen = cols * 4 + 1;
            StringBuilder sbSep = new StringBuilder();
            for (int k = 0; k < sepLen; k++) sbSep.append('-');
            String sep = sbSep.toString();

            for (int i = 0; i < rows; i++) {
                System.out.println(sep);
                for (int j = 0; j < cols; j++) {
                    String cell = board[i][j].getRepresentation().trim();
                    System.out.print("| " + (cell.isEmpty() ? " " : cell) + " ");
                }
                System.out.println("|");
            }
            System.out.println(sep);
        }

    /**
     * Displays a message to the user.
     *
     * @param message The message to show.
     */

        public void showMessage(String message) {
            System.out.println(message);
        }
    }

