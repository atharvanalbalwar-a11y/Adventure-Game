package com.game1;

import java.awt.*;

public class level1 {


    private GamePanel panel;
    private loadResources load;
    private currentMap map;
    private orc orc1;
    private orc orc2;


    public level1(GamePanel panel, loadResources load, currentMap map) {
        this.panel = panel;
        this.load = load;
        this.map = map;
    }
    public void level_1(Graphics g) {
        if(panel.transition >= 0) {
            g.drawImage(load.background,map.mapPosX,map.mapPosY,null);

            if(panel.player1.health_points > 0){
                if(orc1.z == 0) drawOrc(g,panel.orc1,0);
                if(orc2.z == 0) drawOrc(g,panel.orc2,1);
                if(panel.player1.z == 0) drawPlayer(g);

                // draw overlay layer
                g.drawImage(load.map_layer,map.mapPosX+509,map.mapPosY+448,null);

                if(orc1.z == 1) drawOrc(g,orc1,0);
                if(orc2.z == 1) drawOrc(g,panel.orc2,1);
                if(panel.player1.z == 1) drawPlayer(g);
            }
            else {
                panel.game_end = true;
            }
            g.drawImage(load.coin.getSubimage(panel.ind_8 *30,0,30,30),panel.map_posX + 1290,panel.map_posY + 685,null);

            if(panel.hearts >= 2 && panel.hearts <= 10){
                g.drawImage(load.health.getSubimage(0,31*(5-panel.hearts/2),160,30),20,20,null);
            }
            if(380-map.mapPosX >= 1850 && 380-map.mapPosX <= 1930 && 156-map.mapPosY >= 700 && 156-map.mapPosY <= 750 && panel.key.up) {
                //if(panel.transition > 0) panel.drawTransition((Graphics2D)g);
                panel.map_posX = -1640;
                panel.map_posY = -360;
                panel.player1.z = 1;
                panel.currentLevel = 2;
            }
        }

        if(!panel.game_start) g.drawImage(panel.start_menu,0,0,null);
    }

    private void drawOrc(Graphics g,orc orc_i,int i) {

        if(orc_i.distance < 300 && orc_i.distance >= 30) {
            g.drawImage(panel.orc1_run_ani[orc_i.direction][panel.aniIndex],orc_i.x,orc_i.y,null);
            if(i == 0) panel.orc1_attacking = false;
            if(i == 1) panel.orc2_attacking = false;
        }
        else if(orc_i.distance < 30) {
            g.drawImage(panel.orc1_attack_ani[orc_i.direction][panel.aniIndex],orc_i.x,orc_i.y,null);
            if(i == 0) panel.orc1_attacking = true;
            if(i == 1) panel.orc2_attacking = true;
        }
        else {
            g.drawImage(panel.orc1_idle_ani[orc_i.direction][panel.aniIndex%4],orc_i.x,orc_i.y,null);
            if(i == 0) panel.orc1_attacking = false;
            if(i == 1) panel.orc2_attacking = false;
        }
    }
    public void drawPlayer(Graphics g) {
        if(panel.key.E) g.drawImage(load.attack_right[panel.aniIndex%6],panel.posX-32,panel.posY-32,null);
        else{
            if(!(panel.key.right || panel.key.left || panel.key.up || panel.key.down)) {
                g.drawImage(load.idle_ani[panel.direction-1][(int)panel.idle_ind%2],panel.posX,panel.posY,null);
            }
            else {
                g.drawImage(load.run_ani[panel.direction-1][panel.aniIndex],panel.posX,panel.posY,null);
            }
        }
    }
}
