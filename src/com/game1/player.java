package com.game1;

public class player {

    private GamePanel g;
    public int health_points = 100;
    public boolean hit = false;

    public player(GamePanel g) {
        this.g = g;
    }
    void hit_goblin() {}
    void got_hit() {
        if(hit) health_points -= 10;
        hit = true;
    }
}
