package game.myMinesweeper;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author Vladimir Krekic
 */

class Game {

    private static double width;
    private static double height;
    private static Stage gameStage;

    public static void play (){

        gameStage = new Stage();
        width = StartNodes.getNumberOfTiles() * 20 + 2;
        height = StartNodes.getNumberOfTiles() * 20 + 42;

        GridPane gameRoot = new GridPane();
        gameRoot.setGridLinesVisible(true);
        gameRoot.setHgap(2);
        gameRoot.setVgap(2);
        Logic.populateRoot(gameRoot);

        GameNodes gameNodes = new GameNodes();

        HBox hb = new HBox();
        hb.setStyle("-fx-border-color:grey; -fx-background-color:grey;");
        hb.setSpacing(width * 0.04);
        hb.setAlignment(Pos.CENTER);
        hb.setMinHeight(40);
        hb.getChildren().addAll(gameNodes.getLabelGenerated(), gameNodes.getGenerated(), gameNodes.getLabelLeftToOpen(), gameNodes.getLeftToOpen());

        gameRoot.add(hb, 1, Logic.getNumberOfTiles() +2, Logic.getNumberOfTiles(), 2);

        Scene gameScene = new Scene(gameRoot, width, height);
        gameStage.setTitle("MyMinesweeper");
        gameStage.setScene(gameScene);
        gameStage.show();
    }

    public static double getWidth() {
        return width;
    }

    public static Stage getGameStage() {
        return gameStage;
    }
}