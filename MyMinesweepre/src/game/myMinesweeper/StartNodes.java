package game.myMinesweeper;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Vladimir Krekic
 */

class StartNodes {

    private Label dimension;
    private Label level;
    private TextField dimensionT;
    private ComboBox<String> levelCombo;
    private Button button;
    private static int numberOfTiles;
    private static double levelPercentage;

    public StartNodes(){
        generateNodes();
    }

    private void generateNodes(){

        this.dimension = new Label("Number of tiles in line: ");
        dimension.setMinWidth(130);
        dimension.setAlignment(Pos.CENTER_RIGHT);

        this.level = new Label("Chose level: ");
        level.setMinWidth(130);
        level.setAlignment(Pos.CENTER_RIGHT);

        this.dimensionT = new TextField();
        dimensionT.setMaxWidth(100);
        dimensionT.setPromptText("from 15 to 30");
        dimensionT.setFocusTraversable(false);

        this.levelCombo = new ComboBox<String>();
        levelCombo.getItems().addAll("Easy", "Moderate", "Hard", "Pro", "Insane");
        levelCombo.setPromptText("Moderate");
        levelCombo.setFocusTraversable(false);

        this.button = new Button("OK");
        button.setMinWidth(100);
        button.setAlignment(Pos.CENTER);
        button.setOnAction(e->{
            try {
                numberOfTiles = Integer.parseInt(dimensionT.getText());
                if(numberOfTiles < 15)
                    numberOfTiles = 15;
                if(numberOfTiles > 30)
                    numberOfTiles = 30;
            }catch (Exception ex){
                numberOfTiles = 20;
            }
            try {
                switch (levelCombo.getSelectionModel().getSelectedItem()){

                    case "Easy": levelPercentage = 0.1;
                        break;
                    case "Hard": levelPercentage = 0.2;
                        break;
                    case "Pro": levelPercentage = 0.25;
                        break;
                    case "Insane": levelPercentage = 0.3;
                        break;
                    default: levelPercentage = 0.15;
                }
            }catch (Exception ex){
                levelPercentage = 0.15;
            }
            Start.close();
            Logic.inputParameters(getNumberOfTiles());
            Game.play();
        });
    }

    public Label getDimension() {
        return dimension;
    }

    public Label getLevel() {
        return level;
    }

    public TextField getDimensionT() {
        return dimensionT;
    }

    public ComboBox<String> getLevelCombo(){
        return levelCombo;
    }

    public Button getButton(){
        return button;
    }

    public static int getNumberOfTiles() {
        return numberOfTiles;
    }

    public static double getLevelPercentage() {
        return levelPercentage;
    }

}