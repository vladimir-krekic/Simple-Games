package game.myMinesweeper;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Vladimir Krekic
 */

class GameNodes {

    private Label labelGenerated;
    private Label labelLeftToOpen;
    private Label generated;
    private Label leftToOpen;

    public GameNodes(){
        generateNodes();
    }

    private void generateNodes(){

        this.labelGenerated = new Label("Number of MINES: ");
        labelGenerated.setMinWidth(Game.getWidth() * 0.32);
        labelGenerated.setFont(Font.font(Game.getWidth() * 0.035));
        labelGenerated.setTextFill(Color.BLUE);

        this.labelLeftToOpen = new Label("Squares left to open: ");
        labelLeftToOpen.setMinWidth(Game.getWidth() * 0.24);
        labelLeftToOpen.setFont(Font.font(Game.getWidth() * 0.035));
        labelLeftToOpen.setTextFill(Color.BLUE);

        this.generated = new Label(Logic.numberOfBombsGenerated()+"");
        generated.setFont(Font.font(Game.getWidth() * 0.05));

        this.leftToOpen = new Label();
        leftToOpen.setFont(Font.font(Game.getWidth() * 0.05));
        leftToOpen.textProperty().bind(Logic.getLeftToOpen().textProperty());
    }

    public Label getLabelGenerated() {
        return labelGenerated;
    }

    public Label getLabelLeftToOpen() {
        return labelLeftToOpen;
    }

    public Label getGenerated() {
        return generated;
    }

    public Label getLeftToOpen() {
        return leftToOpen;
    }
}