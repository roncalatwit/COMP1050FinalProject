package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * CheckersGame class represents the main logic of the checkers game.
 * This class extends JavaFX Application to handle GUI.
 *
 * @author Christian and Aaron
 */
public class CheckersGame extends Application {
    private final int BOARD_SIZE = 8;
    private final int CELL_SIZE = 60;
    private final Circle[][] board = new Circle[BOARD_SIZE][BOARD_SIZE];
    private int currentPlayer = 1; // Player 1 starts the game

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        initializeBoard(gridPane);

        Scene scene = new Scene(gridPane, BOARD_SIZE * CELL_SIZE, BOARD_SIZE * CELL_SIZE);
        primaryStage.setTitle("Checkers Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Initializes the game board with checker pieces.
     *
     * @param gridPane The grid pane to which checker pieces are added.
     */
    private void initializeBoard(GridPane gridPane) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Circle circle = createCheckerPiece();
                if ((row + col) % 2 == 1) { // Add checker pieces to black cells
                    if (row < 3) {
                        circle.setFill(Color.RED);
                    } else if (row > 4) {
                        circle.setFill(Color.BLUE);
                    }
                    board[row][col] = circle;
                    gridPane.add(circle, col, row);
                }
            }
        }
    }

    /**
     * Creates a checker piece (circle object).
     *
     * @return The created checker piece.
     */
    private Circle createCheckerPiece() {
        Circle circle = new Circle(CELL_SIZE / 2);
        circle.setStroke(Color.BLACK);
        return circle;
    }

    /**
     * Moves a checker piece to a new location on the board.
     *
     * @param fromRow  The current row of the checker piece.
     * @param fromCol  The current column of the checker piece.
     * @param toRow    The new row to move the checker piece to.
     * @param toCol    The new column to move the checker piece to.
     * @param captured Whether the move captures an opponent's piece.
     */
    private void moveCheckerPiece(int fromRow, int fromCol, int toRow, int toCol, boolean captured) {
        Circle checkerPiece = board[fromRow][fromCol];
        board[toRow][toCol] = checkerPiece;
        board[fromRow][fromCol] = null;
        GridPane.setColumnIndex(checkerPiece, toCol);
        GridPane.setRowIndex(checkerPiece, toRow);
        if (captured) {
            // Remove the captured checker piece from the board
            // IMPLEMENT THE LOGIC TO UPDATE PLAYER'S INFORMATION HERE
            board[toRow][toCol] = null;
        }
    }

    /**
     * Main method to launch the JavaFX application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

