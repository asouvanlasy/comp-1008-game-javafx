package com.example.gamejavafx;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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

        // Load background
        Image background = new Image(getClass().getResource("img/space.png").toExternalForm());

        // Load ship
        Image shipImg = new Image(getClass().getResource("img/ship.png").toExternalForm());
        Sprite ship = new Sprite(shipImg, 50, 50, 100, 40, 5);

        // Add a timer
        // Each update redraws the sprites
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.drawImage(background, 0, 0, GameConfig.getGameWidth(), GameConfig.getGameHeight());
                ship.draw(gc);
                ship.moveRight();
            }
        };
        timer.start();
    }
}
