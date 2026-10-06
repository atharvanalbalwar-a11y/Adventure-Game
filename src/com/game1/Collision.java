package com.game1;

import java.awt.image.BufferedImage;

public class Collision {



    public boolean isCollidingWithMap(int X, int Y, int Z, BufferedImage z0, BufferedImage z1) {

        int pixelColor = z0.getRGB(X,Y);
        int pixel2 = z1.getRGB(X,Y);

        int alpha = (pixelColor >> 24) & 0xff;
        int beta = (pixel2 >> 24) & 0xff;

        if(Z == 0) return alpha > 0;
        return beta > 0;
    }
}
