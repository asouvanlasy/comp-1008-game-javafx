package com.example.gamejavafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Alien extends Sprite {
    public Alien(int posX, int posY) {
        super(posX, posY, GameConfig.getAlienWidth(), GameConfig.getAlienHeight(), GameConfig.getAlienSpeed());
        image = new Image(Main.class.getResource("img/alien.png").toExternalForm());
    }

    public void moveUp() {}
    public void moveDown() {}
    public void moveRight() {}

    public void moveLeft() {
        if (posX < 0) {
            posX = GameConfig.getGameWidth();
        }
        else {
            posX -= speed;
        }
    }

    public void draw(GraphicsContext gc) {
        super.draw(gc);
        moveLeft();
    }
}
