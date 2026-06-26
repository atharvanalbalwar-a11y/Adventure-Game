package com.game1;

public class player {

    private GamePanel g;
    public int health_points = 100;
    //public boolean hit = false;
    orc orc1;
    orc orc2;

    public player(GamePanel g,orc orc1,orc orc2) {
        this.orc1 = orc1;
        this.orc2 = orc2;
        this.g = g;
    }
    void hit_goblin() {}
    void got_hit(boolean hitting) {
        if(hitting && (orc1.distance <= 30 || orc2.distance <= 30)) {
            health_points -= 10;
        }
    }
}
