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
        if(!g.game_start) return;
        if(g.orc1_attacking && g.flag_orc1_attacking && orc1.z == this.z) {
            health_points -= 10;
        }
        if(g.orc2_attacking && g.flag_orc2_attacking && orc2.z == this.z) {
            health_points -= 10;
        }
        g.hearts = health_points/10;
    }
}
