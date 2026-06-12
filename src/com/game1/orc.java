package com.game1;
import inputs.keyboard;

public class orc {

    private keyboard k;
    private final GamePanel g;
    public int orcX=1000;
    public int orcY=1000;
    public int x = 500;
    public int y = 500;
    public int direction = 0;
    public int distance;

    public orc(keyboard k,GamePanel g) {
        this.k = k;
        this.g = g;
    }

    public void changePosOrc() {
//        x = orcX - g.map_posX;
//        y = orcY - g.map_posY;
//        x =  - g.map_posX;
//        y =  - g.map_posY;
        distance = (int)Math.sqrt(Math.pow((g.posX - x),2) + Math.pow((g.posY - y),2));

        if(distance<=200) {
            if(g.posX - x > 32) {
                orcX += g.velocity;
                if(g.isCollidingWithMap(orcX+32,orcY+32)) orcX -= g.velocity;
                direction = 3;
            }
            if(g.posX - x < 32) {
                orcX -= g.velocity;
                if(g.isCollidingWithMap(orcX+32,orcY+32)) orcX += g.velocity;
                direction = 2;
            }
            if(g.posY - y > 32) {
                orcY += g.velocity;
                if(g.isCollidingWithMap(orcX+32,orcY+32)) orcY -= g.velocity;
                direction = 0;
            }
            if(g.posY - y < 32) {
                orcY -= g.velocity;
                if(g.isCollidingWithMap(orcX+32,orcY+32)) orcY += g.velocity;
                direction = 1;
            }
        }

//
//        if(x<0) x=0;
//        if(x>2000) x=2000;
//        if(y<0) y=0;
//        if(y>1500) y=1500;
        x = orcX + g.map_posX;
        y = orcY + g.map_posY;
    }
}
