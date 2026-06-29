package com.game1;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    public GameWindow(GamePanel panel) {

        JFrame frame = new JFrame();
        //frame.setSize(400,400);
        frame.setTitle("My game");
        frame.add(panel);
        frame.pack();
        // sets size of window to panel size
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
