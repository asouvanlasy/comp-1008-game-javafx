package com.example.gamejavafx;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button startBtn;

    /**
     * Set is a data structure that prevents duplicates,
     * otherwise it's similar to an ArrayList
     * KeyCode is the character pressed on the keyboard
     */
    private HashSet<KeyCode> activeKeys;

    @FXML
    private void startGame(ActionEvent event) {
        Canvas canvas = new Canvas(GameConfig.getGameHeight(), GameConfig.getGameWidth());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        anchorPane.getChildren().add(canvas);

        // Initialize keyboard input
        activeKeys = new HashSet<>();

        // This creates a listener that will add the key pressed to the Set
        anchorPane.getScene().setOnKeyPressed(keyPressed -> {
            System.out.println("Keys currently pressed -> "+activeKeys);
            activeKeys.add(keyPressed.getCode());
        });

        anchorPane.getScene().setOnKeyReleased(keyReleased -> {
            activeKeys.remove(keyReleased.getCode());
        });

        // Load background
        Image background = new Image(getClass().getResource("img/space.png").toExternalForm());

        // Load ship
        Ship ship = new Ship(100, 100, 100, 60, 5);

        // Load missile
        Missile missile = new Missile(100,100);

        // Create ArrayList to hold multiple aliens and RNG to randomly place them
        ArrayList<Alien> aliens = new ArrayList<>();
        SecureRandom rng = new SecureRandom();

        //Add Alien's to the scene
        for (int i = 1; i<=5 ; i++) {
            aliens.add(new Alien(rng.nextInt(600,900),
            rng.nextInt(30,GameConfig.getGameHeight()-GameConfig.getAlienHeight())));
        }

        // Add a timer
        // Each update redraws the sprites
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gc.drawImage(background, 0, 0, GameConfig.getGameWidth(), GameConfig.getGameHeight());
                updateShipLocation(ship);
                ship.draw(gc);

                for (Alien alien : aliens) {
                    alien.draw(gc);

                    for (Missile missile : ship.getActiveMissiles()) {
                        if (missile.collidesWith(alien)) {
                            // Show an explosion
                            missile.setAlive(false);
                            alien.setAlive(false);
                        }
                    }
                }
            }
        };
        timer.start();
    }

    private void updateShipLocation(Ship ship) {
        if (activeKeys.contains(KeyCode.LEFT)) {
            ship.moveLeft();
        }
        if (activeKeys.contains(KeyCode.RIGHT)) {
            ship.moveRight();
        }
        if (activeKeys.contains(KeyCode.UP)) {
            ship.moveUp();
        }
        if (activeKeys.contains(KeyCode.DOWN)) {
            ship.moveDown();
        }
        if (activeKeys.contains(KeyCode.SPACE)) {
            ship.shootMissile();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the set to hold the keycode pressed by the user
        activeKeys = new HashSet<>();
    }
}
