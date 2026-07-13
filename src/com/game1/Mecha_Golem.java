package com.game1;

public class Mecha_Golem {
    private final GamePanel g;
    public int golX;
    public int golY;
    public int x = 500;
    public int y = 500;
    public int z;
    public int direction = 0;
    public int distance;
    public double radians;

    public boolean hit = false;

    public Mecha_Golem(GamePanel g,int golX,int golY,int z) {
        this.g = g;
        this.golX = golX;
        this.golY = golY;
        this.z = z;
    }
    public void changePosGol() {

        distance = (int)Math.sqrt(Math.pow((g.posX - x),2) + Math.pow((g.posY - y),2));

            if(g.posX - x > 20) {
                golX += (g.velocity-1);
                direction = 3;
            }
            if(g.posX - x < -20) {
                golX -= (g.velocity-1);
                direction = 2;
            }
            if(g.posY - y > 20) {
                golY += (g.velocity-1);
                direction = 0;
            }
            if(g.posY - y < -20) {
                golY -= (g.velocity-1);
                direction = 1;
            }

        x = golX + g.map_posX;
        y = golY + g.map_posY;
        radians = Math.atan2((( - g.map_posY - golY)*1.0),( - g.map_posX - golX));
        //System.out.println(radians);
        //radians = 0.0;
    }
}
