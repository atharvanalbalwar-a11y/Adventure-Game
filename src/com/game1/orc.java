package com.game1;
import inputs.keyboard;

public class orc {

    private keyboard k;
    private GamePanel g;
    public int orcX=0;
    public int orcY=0;
    public int x = 500;
    public int y = 500;
    private int direction;

    public orc(keyboard k,GamePanel g) {
        this.k = k;
        this.g = g;
    }

    public void changePosOrc() {
//        x = orcX - g.map_posX;
//        y = orcY - g.map_posY;
//        x =  - g.map_posX;
//        y =  - g.map_posY;


        if(g.posX - x > 32) {
            orcX += g.velocity;
            //if(g.isCollidingWithMap(x,y)) orcX -= g.velocity;
        }
//        if(g.posX - x < 32) {
//            orcX -= g.velocity;
//            //if(g.isCollidingWithMap(x,y)) orcX += g.velocity;
//        }
        if(g.posY - y > 32) {
            orcY += g.velocity;
            //if(g.isCollidingWithMap(x,y)) orcY -= g.velocity;
        }
        if(g.posY - y < 32) {
            orcY -= g.velocity;
            //if(g.isCollidingWithMap(x,y)) orcY += g.velocity;
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
