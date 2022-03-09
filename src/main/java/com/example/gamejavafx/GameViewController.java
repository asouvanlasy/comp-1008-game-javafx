package com.example.gamejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GameViewController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startBtn;

    @FXML
    private void startGame(ActionEvent event) {
        Canvas canvas = new Canvas(GameConfig.getGameHeight(), GameConfig.getGameWidth());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        anchorPane.getChildren().add(canvas);
    }
}
