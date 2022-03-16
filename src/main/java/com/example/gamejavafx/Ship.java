package com.example.gamejavafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Ship extends Sprite {
    private ArrayList<Missile> activeMissiles;
    private int missilesRemaining;
    private final int REFRESH_RATE = 20;
    private int missilePause;

    public Ship(int posX, int posY, int imageWidth, int imageHeight, int speed) {
        super(posX, posY, imageWidth, imageHeight, speed);
        image = new Image(Main.class.getResource("img/ship.png").toExternalForm());
        activeMissiles = new ArrayList<>();
        missilesRemaining = GameConfig.getInitialMissileCount();
        missilePause = REFRESH_RATE;
    }

    public void moveDown() {
        int furthestDown = GameConfig.getGameHeight() - imageHeight;
        if (posY >= furthestDown) {
            this.posY = furthestDown;
        }
        else {
            posY += speed;
        }
    }

    public void moveUp() {
        if (posY < 0) {
            this.posY = 0;
        }
        else {
            posY -= speed;
        }
    }

    public void moveLeft() {
        posX = posX - speed;
        if (posX <0) {
            posX = 0;
        }
    }

    public void draw(GraphicsContext gc) {
        missilePause--;

        gc.drawImage(image, posX, posY, imageWidth, imageHeight);

        for (Missile missile : activeMissiles) {
            missile.draw(gc);
        }
    }

    public void shootMissile() {
        if (missilePause < 0) {
            missilesRemaining--;
            activeMissiles.add(new Missile(posX + imageWidth, posY + (imageHeight / 2 - GameConfig.getMissileHeight() / 2)));
            missilePause = REFRESH_RATE;
        }
    }

    public ArrayList<Missile> getActiveMissiles() {
        return activeMissiles;
    }
}
