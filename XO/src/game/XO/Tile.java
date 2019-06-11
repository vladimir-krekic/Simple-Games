package game.XO;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author Vladimir Krekic
 */

class Tile extends StackPane{

    private Rectangle rect;
    private Text text;

    public Tile() {
    }

    /**
     * Adds attributes and functionality to a tile
     * as a basic element of the XOstage.
     */
    public void addAttributesAndFunctionality(Tile tile) {

        rect = new Rectangle();
        rect.setHeight(150);
        rect.setWidth(150);
        rect.setOpacity(.3);

        text = new Text();
        text.setFont(new Font(80));

        tile.getChildren().add(rect);
        tile.getChildren().add(text);

        tile.setOnMouseClicked(e -> onClick(tile));
    }

    /**
     * An auxiliary method that defines the behaviour of the tile after clicking on it.
     * It alternates between "X" and "O" in blank fields and checks whether the columns,
     * rows or diagonals are filled with identical characters.
     * @param tile - type Tile
     */
    private static void onClick(Tile tile) {
        int counter = Logic.count(Main.list);
        if(tile.getText().getText().isEmpty()) {
            if(counter % 2 == 0) {
                tile.getText().setText("X");
            }else {
                tile.getText().setText("O");
            }
            Logic.check(Main.list.size(), 3, 1, 0); //Vertical
            Logic.check(3, 1, 3, Main.list.size()); //Horizontal
            Logic.check(2, 2, 7); //Right diagonal
            Logic.check(0, 4, Main.list.size()); //Left diagonal
            if(counter == 8) AlertBox.getAlertBox("Nobody wins"); //If grid is filled and nobody wins
        }
    }

    public Text getText() {
        return text;
    }

}
