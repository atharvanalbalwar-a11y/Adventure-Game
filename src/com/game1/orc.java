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
    public int health_point = 20;
    public boolean hit = false;

    public orc(keyboard k,GamePanel g) {
        this.k = k;
        this.g = g;
    }

    public void changePosOrc() {

        distance = (int)Math.sqrt(Math.pow((g.posX - x),2) + Math.pow((g.posY - y),2));

        if(distance<=300) {
            if(g.posX - x > 0) {
                orcX += (g.velocity-1);
                if(g.isCollidingWithMap(orcX+32,orcY+32)) orcX -= (g.velocity-1);
                direction = 3;
            }
            if(g.posX - x < 0) {
                orcX -= (g.velocity-1);
                if(g.isCollidingWithMap(orcX+32,orcY+32)) orcX += (g.velocity-1);
                direction = 2;
            }
            if(g.posY - y > 0) {
                orcY += (g.velocity-1);
                if(g.isCollidingWithMap(orcX+32,orcY+32)) orcY -= (g.velocity-1);
                direction = 0;
            }
            if(g.posY - y < 0) {
                orcY -= (g.velocity-1);
                if(g.isCollidingWithMap(orcX+32,orcY+32)) orcY += (g.velocity-1);
                direction = 1;
            }
        }

        x = orcX + g.map_posX;
        y = orcY + g.map_posY;
    }

    public void got_hit() {
        if(hit) health_point -= 10;
        hit = false;
    }
}
