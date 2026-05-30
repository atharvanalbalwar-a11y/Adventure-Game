package com.game1;

public class Game {
    private GamePanel panel1;
    private GameWindow window;


    public Game() {

        panel1 = new GamePanel();
        window = new GameWindow(panel1);
        panel1.requestFocus();
    }
}
