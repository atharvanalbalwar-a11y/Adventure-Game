package com.game1;
import inputs.keyboard;

import java.awt.image.BufferedImage;

public class character_position {




    private final keyboard key;
    private final player player1;
    private currentMap map;
    private Collision c;
    private int velocity = 4;
    public int posX = 380;
    public int posY = 156;



    public character_position(keyboard key,  player player1, Collision c, currentMap map) {

        this.map = map;
        this.c = c;
        this.player1 = player1;
        this.key = key;
    }



    public void changePos(BufferedImage map_z0, BufferedImage map_z1) {

        if(key.up) {
            map.mapPosY += velocity;
            player1.direction = 3;

            if(c.isCollidingWithMap(posX-map.mapPosX+32,posY-map.mapPosY+64, player1.z,map_z0,map_z1)) map.mapPosY -= velocity;
        }
        if(key.down) {
            map.mapPosY -= velocity;
            player1.direction = 4;

            if(c.isCollidingWithMap(posX-map.mapPosX+32,posY-map.mapPosY+64, player1.z,map_z0,map_z1)) map.mapPosY += velocity;
        }
        if(key.left) {
            map.mapPosX += velocity;
            player1.direction = 2;

            if(c.isCollidingWithMap(posX-map.mapPosX+32,posY-map.mapPosY+64, player1.z,map_z0,map_z1)) map.mapPosX -= velocity;
        }
        if(key.right) {
            map.mapPosX -= velocity;
            player1.direction = 1;

            if(c.isCollidingWithMap(posX-map.mapPosX+32,posY-map.mapPosY+64, player1.z,map_z0,map_z1)) map.mapPosX += velocity;
        }

//        if(posX-map_posX+32 >= 950 && posX-map_posX+32 <= 1150) {
//            if(posY - map_posY >= 892) player1.z = 0;
//            if(posY - map_posY <= 960) player1.z = 1;
//        }
    }
}
