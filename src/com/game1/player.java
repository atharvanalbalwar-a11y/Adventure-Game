package com.game1;

public class player {

    private GamePanel g;
    public int health_points = 100;
    public boolean hit = false;

    public int z = 0;

    orc orc1;
    orc orc2;

    public player(GamePanel g,orc orc1,orc orc2) {
        this.orc1 = orc1;
        this.orc2 = orc2;
        this.g = g;
    }
    void hit_goblin() {}
    void got_hit() {
        if(g.is_orc_attacking && orc1.distance < 30) {
            health_points -= 10;
        }
        if(g.is_orc_attacking && orc2.distance < 30) {
            health_points -= 10;
        }
    }
}
