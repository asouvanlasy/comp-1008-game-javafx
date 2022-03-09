package com.example.gamejavafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {

    private Image image;
    private int posX, posY, imageWidth, imageHeight, speed;
    private boolean alive;

    public Sprite(Image image, int posX, int posY, int imageWidth, int imageHeight, int speed) {
        setImage(image);
        setPosX(posX);
        setPosY(posY);
        setImageWidth(imageWidth);
        setImageHeight(imageHeight);
        setSpeed(speed);
        alive = true;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        int furthestRight = GameConfig.getGameWidth() - imageWidth;
        if (posX >= 0 && posX <= furthestRight) {
            this.posX = posX;
        }
        else {
            throw new IllegalArgumentException("posX must be in the range of 0-" + furthestRight);
        }
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        int furthestDown = GameConfig.getGameHeight() - imageHeight;
        if (posY >= 0 && posY <= furthestDown) {
            this.posY = posY;
        }
        else {
            throw new IllegalArgumentException("posY must be in the range of 0-" + furthestDown);
        }
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        if (imageWidth >= 10 && imageWidth <= GameConfig.getMaxSpriteWidth()) {
            this.imageWidth = imageWidth;
        }
        else {
            throw new IllegalArgumentException("imageHeight must be in the range of 10-" + GameConfig.getMaxSpriteHeight());
        }
    }

    public int getSpeed() {
        return speed;
    }

    /**
     * How many pixels should the sprite move each time it is drawn
     * @param speed
     */
    public void setSpeed(int speed) {
        if (speed >= 0 && speed <= 10) {
            this.speed = speed;
        }
        else {
            throw new IllegalArgumentException("speed must be in the range of 0-10");
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, posX, posY, imageWidth, imageHeight);
    }

    public void moveRight() {
        posX = posX + speed;
        if (posX >= GameConfig.getGameWidth() - imageWidth) {
            posX = GameConfig.getGameWidth() - imageWidth;
        }
    }
}
