package com.example.gamejavafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Missile extends Sprite {

    public Missile(int posX, int posY) {
        super(posX, posY, GameConfig.getMissileWidth(), GameConfig.getMissileHeight(), GameConfig.getMissileSpeed());
        image = new Image(Main.class.getResource("img/missile.png").toExternalForm());
    }

    // Missiles can move only right, so this ensures that nothing happens if they move left, up or down
    public void moveUp() {}
    public void moveDown() {}
    public void moveLeft() {}

    @Override
    public void moveRight() {
        if (getPosX() > GameConfig.getGameWidth()) {
            setAlive(false);
        }
        else {
            setPosX(getPosX() + getSpeed());
        }
    }

    public void draw(GraphicsContext gc) {
        if (isAlive()) {
            gc.drawImage(image, getPosX(), getPosY(), getImageWidth(), getImageHeight());
            moveRight();
        }
    }
}
