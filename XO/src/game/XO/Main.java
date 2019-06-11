package game.XO;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

/**
 * @author Vladimir Krekic
 */

public class Main extends Application {

    public static List<Tile> list;
    private static Stage XOStage;

    @Override
    public void start(Stage primaryStage) {

        XOStage = primaryStage;

        try {
            GridPane root = new GridPane();
            root.setGridLinesVisible(true);
            root.setPadding(new Insets(2));
            root.setHgap(2);
            root.setVgap(2);

            list = new ArrayList<>();

            for(int i = 0; i <3; i++) {
                for(int j = 0; j <3; j++) {
                    Tile tile = new Tile();
                    tile.addAttributesAndFunctionality(tile);
                    root.add(tile, i, j);
                    list.add(tile);
                }
            }

            Scene scene = new Scene(root,458,458);
            XOStage.setScene(scene);
            XOStage.setTitle("Iks-Oks");
            XOStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Stage getXOStage() {
        return XOStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
