package game.myMinesweeper;

import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Vladimir Krekic
 */

class Logic {

    private static int[] NEIGHBORS = {-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1};
    private static Label leftToOpen = new Label();
    private static int numberOfTiles;
    private static Square[][] MATRIX;

    /**
     * Resets parameters for next game
     * @param tilesNumber int - number of tiles in a line for next game
     */
    public static void inputParameters(int tilesNumber){
        numberOfTiles = tilesNumber;
        MATRIX = new Square[tilesNumber][tilesNumber];
    }

    /**
     * Generates Squares, populates MATRIX, calculates neighbours
     * and then populates root with Squares and number of neighbours.
     * Also sets an initial number of cells needed to be open for win game.
     * @param root - GridPane
     */
    public static void populateRoot(GridPane root) {
        populateMatrix();
        leftToOpen.setText((numberOfTiles * numberOfTiles - numberOfBombsGenerated()) + "");
        calculateNeighbors();
        for (int i = 1; i <= numberOfTiles; i++) {
            for (int j = 1; j <= numberOfTiles; j++) {
                root.add(MATRIX[i - 1][j - 1], i, j);
            }
        }
    }

    /**
     * Generates Squares, populates MATRIX
     */
    private static void populateMatrix() {
        for (int i = 0; i < numberOfTiles; i++) {
            for (int j = 0; j < numberOfTiles; j++) {
                MATRIX[i][j] = createSquare(i, j);
            }
        }
    }

    /**
     * Generates Squares
     * @param X_POSITION - int : X position of square in MATRIX
     * @param Y_POSITION - int : Y position of square in MATRIX
     * @return Square
     */
    private static Square createSquare(int X_POSITION, int Y_POSITION) {
        Square square = new Square(X_POSITION, Y_POSITION);
        clicked(square);
        return square;
    }

    /**
     * Generates setOnMouseClicked function for Square
     * @param square - Square
     */
    private static void clicked(Square square) {
        square.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) { //right mouse button
                if(square.getRectangle().getFill() == Color.RED){ //if already marked
                    square.getRectangle().setFill(Color.BLACK); //remove mark
                }else{
                    square.getRectangle().setFill(Color.RED); //else mark
                }
            } else { //left mouse button
                if(square.getRectangle().getFill() == Color.RED){ //if marked, do nothing
                    return;
                }else if (square.getIsBomb()) { //if bomb
                    square.getRectangle().setVisible(false);  //show text
                    AlertBox.alert("You stepped on the MINE"); //game over
                }
                openEmpty(square); //if not bomb
                int lto = numberOfSquaresToOpen() - numberOfBombsGenerated();
                leftToOpen.setText(lto + "");
                if(lto == 0){ //if no more unopened empty squares
                    AlertBox.alert("* * * You WON * * *"); //game over, you won
                }
            }
        });
    }

    /**
     * Opens all connected empty Squares, recursively
     * @param square - Square
     */
    private static void openEmpty(Square square) {
        square.getRectangle().setVisible(false);
        if (square.getText().getText().isEmpty()) {
            List<Square> neighbours = getNeighbours(square.getX_POSITION(), square.getY_POSITION());
            neighbours.stream()
                      .filter(s -> s.getRectangle()
                      .isVisible())
                      .forEach(Logic::openEmpty);
        }
    }

    /**
     * Checks for neighbours for all Squares
     */
    private static void calculateNeighbors() {
        for (int i = 0; i < numberOfTiles; i++) {
            for (int j = 0; j < numberOfTiles; j++) {
                if (MATRIX[i][j].getIsBomb()) {
                    continue;
                }
                //Calculates number of neighbours for each square and
                //sets text for each square with no bomb
                String text = getNeighbours(i, j)
                                .stream()
                                .filter(Square::getIsBomb)
                                .count() + "";
                if (text.equals("0")) { // if O no text
                    continue;
                }
                MATRIX[i][j].getText().setText(text);
            }
        }
    }

    /**
     * Getters "isBomb" neighbours for each Square
     * @param x - int: x position of square in MATRIX
     * @param y - int: y position of square in MATRIX
     * @return - List<Square> of neighbours with bombs
     */
    private static List<Square> getNeighbours(int x, int y) {
        List<Square> listOfNeighbours = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            if (x + NEIGHBORS[i] < 0 || y + NEIGHBORS[i + 1] < 0 || x + NEIGHBORS[i] >= numberOfTiles || y + NEIGHBORS[i + 1] >= numberOfTiles) {
                i++;
                continue;
            }
            listOfNeighbours.add(MATRIX[x + NEIGHBORS[i]][y + NEIGHBORS[++i]]);
        }
        return listOfNeighbours;
    }

    /**
     * Calculates number of generated bombs
     * @return - int: number of generated bombs
     */
    public static int numberOfBombsGenerated() {
        return (int) Stream.of(MATRIX)
                .flatMap(Stream::of)
                .filter(Square::getIsBomb)
                .count();
    }

    /**
     * Calculates number of empty cells still unopened
     * @return - int: number of unopened, empty cells
     */
    public static int numberOfSquaresToOpen() {
        return (int) Stream.of(MATRIX)
                .flatMap(Stream::of)
                .map(Square::getRectangle)
                .filter(Rectangle::isVisible)
                .count();
    }

    public static int getNumberOfTiles() {
        return numberOfTiles;
    }

    public static Label getLeftToOpen() {
        return leftToOpen;
    }

}