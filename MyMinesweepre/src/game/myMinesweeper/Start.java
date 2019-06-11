package game.myMinesweeper;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Vladimir Krekic
 */

public class Start extends Application {

    private static Stage window = new Stage();

    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;

        GridPane startRoot = new GridPane();
        startRoot.setAlignment(Pos.CENTER);
        startRoot.setHgap(5);
        startRoot.setVgap(15);
        startRoot.setPadding(new Insets(30));
        startRoot.setStyle("-fx-background-color: lightgrey");

        StartNodes startNodes = new StartNodes();

        startRoot.add(startNodes.getDimension(), 0, 0);
        startRoot.add(startNodes.getDimensionT(), 1, 0);
        startRoot.add(startNodes.getLevel(), 0, 1);
        startRoot.add(startNodes.getLevelCombo(), 1, 1);
        startRoot.add(startNodes.getButton(), 1, 2);

        Scene startScene = new Scene(startRoot);
        window.setTitle("Game settings");
        window.setScene(startScene);
        window.show();
    }

    public static void close(){
        window.close();
    }

    public static Stage getWindow() {
        return window;
    }

    public static void main(String[] args) {
        launch(args);
    }
}