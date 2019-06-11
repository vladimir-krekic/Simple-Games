package game.myMinesweeper;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Vladimir Krekic
 */

class AlertBox {

    public static void alert(String message){

        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);

        Label alertMessage = new Label(message);
        alertMessage.setAlignment(Pos.CENTER);
        alertMessage.setTextFill(Color.RED);
        alertMessage.setFont(new Font(15));

        Label endGame = new Label("GAME OVER");
        endGame.setAlignment(Pos.CENTER);
        endGame.setTextFill(Color.RED);
        endGame.setFont(new Font(25));

        Label question = new Label("Do you want to play again?");
        question.setAlignment(Pos.CENTER);
        question.setTextFill(Color.BLUE);
        question.setFont(new Font(15));

        Button yesButton = new Button("YES");
        yesButton.setMinWidth(100);
        yesButton.setAlignment(Pos.CENTER);
        yesButton.setOnAction(e -> {
            alertStage.close();
            Game.getGameStage().close();
            new Start().start(Start.getWindow());
        });

        Button noButton = new Button("No");
        noButton.setMinWidth(100);
        noButton.setAlignment(Pos.CENTER);
        noButton.setOnAction(e -> {
            alertStage.close();
            Game.getGameStage().close();
        });

        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(yesButton, noButton);

        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: lightgrey");
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(alertMessage, endGame, question, hBox);

        Scene alertScene = new Scene(vBox);
        alertStage.setTitle("Alert");
        alertStage.setScene(alertScene);
        alertStage.showAndWait();
    }
}