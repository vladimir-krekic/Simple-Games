package game.myMinesweeper;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

/**
 * @author Vladimir Krekic
 */

class Square extends StackPane {

    private Random random = new Random();

    private boolean isBomb;
    private Text text;
    private Rectangle rectangle;
    private final int X_POSITION;
    private final int Y_POSITION;

    public Square(int X_POSITION, int Y_POSITION) {
        this.isBomb = createIsBomb();
        this.text = createText(isBomb);
        this.rectangle = createRectangle();
        this.X_POSITION = X_POSITION;
        this.Y_POSITION = Y_POSITION;
        getChildren().addAll(text, rectangle);
    }

    private boolean createIsBomb(){
        return random.nextDouble() < StartNodes.getLevelPercentage();
    }

    public boolean getIsBomb() {
        return isBomb;
    }

    private Text createText(boolean isBomb){
        text = new Text();
        if(isBomb) {
            text.setText("X");
            text.setFill(Color.RED);
        }
        return text;
    }

    public Text getText() {
        return text;
    }

    private Rectangle createRectangle(){
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(18);
        rectangle.setWidth(18);
        return rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getX_POSITION() {
        return X_POSITION;
    }

    public int getY_POSITION() {
        return Y_POSITION;
    }
}