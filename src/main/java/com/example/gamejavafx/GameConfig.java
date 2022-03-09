package com.example.gamejavafx;

public class GameConfig {
    private static int gameHeight = 600;
    private static int gameWidth = 800;

    public static int getGameHeight() {
        return gameHeight;
    }

    public static void setGameHeight(int gameHeight) {
        GameConfig.gameHeight = gameHeight;
    }

    public static int getGameWidth() {
        return gameWidth;
    }

    public static void setGameWidth(int gameWidth) {
        GameConfig.gameWidth = gameWidth;
    }
}
