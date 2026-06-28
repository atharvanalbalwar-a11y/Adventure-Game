package com.game1;
import inputs.keyboard;

public class orc {


    private final GamePanel g;
    public int orcX;
    public int orcY;
    public int x = 500;
    public int y = 500;
    public int z;
    public int direction = 0;
    public int distance;
    public int health_point = 20;
    public boolean hit = false;

    public orc(GamePanel g,int orcX,int orcY,int z) {

        this.g = g;
        this.orcX = orcX;
        this.orcY = orcY;
        this.z = z;
    }

    public void changePosOrc() {

        distance = (int)Math.sqrt(Math.pow((g.posX - x),2) + Math.pow((g.posY - y),2));

        if(distance<=300) {
            if(g.posX - x > 0) {
                orcX += (g.velocity-1);
                if(g.isCollidingWithMap(orcX+32,orcY+32, z)) orcX -= (g.velocity-1);
                direction = 3;
            }
            if(g.posX - x < 0) {
                orcX -= (g.velocity-1);
                if(g.isCollidingWithMap(orcX+32,orcY+32 ,z)) orcX += (g.velocity-1);
                direction = 2;
            }
            if(g.posY - y > 0) {
                orcY += (g.velocity-1);
                if(g.isCollidingWithMap(orcX+32,orcY+32, z)) orcY -= (g.velocity-1);
                direction = 0;
            }
            if(g.posY - y < 0) {
                orcY -= (g.velocity-1);
                if(g.isCollidingWithMap(orcX+32,orcY+32, z)) orcY += (g.velocity-1);
                direction = 1;
            }
        }

        x = orcX + g.map_posX;
        y = orcY + g.map_posY;
        if(orcX+32 >= 950 && orcX+32 <= 1150) {
            if(orcY + 32 >= 892) z = 1;
            if(orcY + 32 >= 960) z = 0;
        }
    }

    public void got_hit() {
        if(hit) health_point -= 10;
        hit = false;
    }
}
