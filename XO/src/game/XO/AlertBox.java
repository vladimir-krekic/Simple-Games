package game.XO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Vladimir Krekic
 */

class AlertBox {

    public static void getAlertBox(String message){

        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);

        Label win = new Label(message);
        win.setAlignment(Pos.CENTER);
        win.setTextFill(Color.RED);
        win.setFont(new Font(15));

        Label question = new Label("Do you want to play again?");
        question.setAlignment(Pos.CENTER);
        question.setTextFill(Color.BLUE);
        question.setFont(new Font(15));

        Button yes = new Button("YES");
        yes.setMinWidth(100);
        yes.setAlignment(Pos.CENTER);
        yes.setOnAction(e -> {
            alertStage.close();
            Main.getXOStage().close();
            new Main().start(Main.getXOStage());
        });

        Button no = new Button("No");
        no.setMinWidth(100);
        no.setAlignment(Pos.CENTER);
        no.setOnAction(e -> {
            alertStage.close();
            Main.getXOStage().close();
        });

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(20);
        buttonBox.getChildren().addAll(yes, no);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        root.getChildren().setAll(win, question, buttonBox);

        Scene alertScene = new Scene(root);
        alertStage.setScene(alertScene);
        alertStage.setTitle("");
        alertStage.show();
    }

}
