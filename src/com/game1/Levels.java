package com.game1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Levels {

    GamePanel panel;
    public BufferedImage map_level2;
    public BufferedImage map_Level2_Z0;
    public BufferedImage map_Level2_Z1;
    public boolean is_lvl2 = false;

    public Levels(GamePanel panel) {
        this.panel = panel;
    }

    public void loadImg() {
        is_lvl2 = true;
        InputStream is1 = getClass().getResourceAsStream("/New_Map.png");
        InputStream is2 = getClass().getResourceAsStream("/New_Map_Z0.png");
        InputStream is3 = getClass().getResourceAsStream("/New_Map_Z1.png");

        try{
            assert is1 != null;
            map_level2 = ImageIO.read(is1);
            assert is2 != null;
            map_Level2_Z0 = ImageIO.read(is2);
            assert is3 != null;
            map_Level2_Z1 = ImageIO.read(is3);
        }
        catch(Exception _) {}
        try {
            is1.close();
            assert is2 != null;
            is2.close();
            assert is3 != null;
            is3.close();
        }
        catch(IOException | NullPointerException  _) {}
    }

    public void level1(Graphics g) {
        if(panel.transition >= 0) {
            g.drawImage(panel.background,panel.map_posX,panel.map_posY,null);

            if(panel.player1.health_points > 0){
                if(panel.orc1.z == 0) drawOrc(g,panel.orc1,0);
                if(panel.orc2.z == 0) drawOrc(g,panel.orc2,1);
                if(panel.player1.z == 0) drawPlayer(g);

                // draw overlay layer
                g.drawImage(panel.map_layer,panel.map_posX+509,panel.map_posY+448,null);

                if(panel.orc1.z == 1) drawOrc(g,panel.orc1,0);
                if(panel.orc2.z == 1) drawOrc(g,panel.orc2,1);
                if(panel.player1.z == 1) drawPlayer(g);
            }
            else {
                panel.game_end = true;
            }
            g.drawImage(panel.coin.getSubimage(panel.coin_ani_ind*30,0,30,30),panel.map_posX + 1290,panel.map_posY + 685,null);

            if(panel.hearts >= 2 && panel.hearts <= 10){
                g.drawImage(panel.health.getSubimage(0,31*(5-panel.hearts/2),160,30),20,20,null);
            }
            if(380-panel.map_posX >= 1850 && 380-panel.map_posX <= 1930 && 156-panel.map_posY >= 700 && 156-panel.map_posY <= 750) {
                //if(panel.transition > 0) panel.drawTransition((Graphics2D)g);
                panel.currentLevel = 2;
            }
        }

        if(!panel.game_start) g.drawImage(panel.start_menu,0,0,null);
    }

    private void drawOrc(Graphics g,orc orc_i,int i) {

        if(orc_i.distance < 300 && orc_i.distance >= 30) {
            g.drawImage(panel.orc1_run_ani[i][orc_i.direction][panel.aniIndex],orc_i.x,orc_i.y,null);
            if(i == 0) panel.orc1_attacking = false;
            if(i == 1) panel.orc2_attacking = false;

        }
        else if(orc_i.distance < 30) {
            g.drawImage(panel.orc1_attack_ani[i][orc_i.direction][panel.aniIndex],orc_i.x,orc_i.y,null);
            if(i == 0) panel.orc1_attacking = true;
            if(i == 1) panel.orc2_attacking = true;

        }
        else {
            g.drawImage(panel.orc1_idle_ani[i][orc_i.direction][panel.aniIndex%4],orc_i.x,orc_i.y,null);
            if(i == 0) panel.orc1_attacking = false;
            if(i == 1) panel.orc2_attacking = false;

        }
    }

    public void drawPlayer(Graphics g) {
        if(panel.key.E) g.drawImage(panel.attack_right[panel.aniIndex%6],panel.posX-32,panel.posY-32,null);
        else{
            if(!(panel.key.right || panel.key.left || panel.key.up || panel.key.down)) {
                g.drawImage(panel.idle_ani[panel.direction-1][(int)panel.idle_ind%2],panel.posX,panel.posY,null);
            }
            else {
                g.drawImage(panel.run_ani[panel.direction-1][panel.aniIndex],panel.posX,panel.posY,null);
            }
        }
    }

    public void level2(Graphics g) {
        g.drawImage(map_level2,panel.map_posX,panel.map_posY,null);
        if(380 - panel.map_posX >= 1988 && 380 - panel.map_posX <= 2088 && 156 - panel.map_posY >= 810 && 156 - panel.map_posY <= 814) {
            panel.player1.z = 0;
        }
        else if(380 - panel.map_posX >= 1988 && 380 - panel.map_posX <= 2088 && 156 - panel.map_posY <= 670) {
            panel.player1.z = 1;
        }
        drawPlayer(g);
        if(panel.hearts >= 2 && panel.hearts <= 10){
            g.drawImage(panel.health.getSubimage(0,31*(5-panel.hearts/2),160,30),20,20,null);
        }
    }

}
