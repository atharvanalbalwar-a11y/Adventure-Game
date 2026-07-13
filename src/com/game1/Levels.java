package com.game1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;

public class Levels {

    GamePanel panel;
    public BufferedImage map_level2;
    public BufferedImage map_Level2_Z0;
    public BufferedImage map_Level2_Z1;
    public BufferedImage map_Level2_layer;
    public BufferedImage sfx;
    public BufferedImage npc_1;
    public BufferedImage db1;
    public BufferedImage golem;
    public BufferedImage laser;

    public BufferedImage[][] npc_1_ani;
    public BufferedImage[][] golem_1_ani;
    public boolean is_lvl2 = false;

    public Mecha_Golem mecha_golem;

    public Levels(GamePanel panel) {
        this.panel = panel;
        mecha_golem = new Mecha_Golem(panel,1000,1000,0);
    }

    public void loadImg() {
        is_lvl2 = true;
        InputStream is1 = getClass().getResourceAsStream("/New_Map.png");
        InputStream is2 = getClass().getResourceAsStream("/New_Map_Z0.png");
        InputStream is3 = getClass().getResourceAsStream("/New_Map_Z1.png");
        InputStream is4 = getClass().getResourceAsStream("/New_Map_layer.png");
        InputStream is5 = getClass().getResourceAsStream("/quake_0.png");
        InputStream is6 = getClass().getResourceAsStream("/npc1.png");
        InputStream is7 = getClass().getResourceAsStream("/dialogue_box_v1.png");
        InputStream is8 = getClass().getResourceAsStream("/Mecha_Golem.png");
        InputStream is9 = getClass().getResourceAsStream("/Mecha_Golem_Laser.png");

        try{
            assert is1 != null;
            map_level2 = ImageIO.read(is1);
            assert is2 != null;
            map_Level2_Z0 = ImageIO.read(is2);
            assert is3 != null;
            map_Level2_Z1 = ImageIO.read(is3);
            assert is4 != null;
            map_Level2_layer = ImageIO.read(is4);
            assert is5 != null;
            sfx = ImageIO.read(is5);
            assert is6 != null;
            npc_1 = ImageIO.read(is6);
            assert is7 != null;
            db1 = ImageIO.read(is7);
            assert is8 != null;
            golem = ImageIO.read(is8);
            assert is9 != null;
            laser = ImageIO.read(is9);
        }
        catch(Exception _) {}
        try {
            is1.close();
            assert is2 != null;
            is2.close();
            assert is3 != null;
            is3.close();
            assert is4 != null;
            is4.close();
            assert is5 != null;
            is5.close();
            assert is6 != null;
            is6.close();
            assert is7 != null;
            is7.close();
            assert is8 != null;
            is8.close();
            assert is9 != null;
            is9.close();
        }
        catch(IOException | NullPointerException  _) {}
        npc_1_ani = new BufferedImage[2][10];
        golem_1_ani = new BufferedImage[10][10];
        for(int i=0; i<10; i++) {
            npc_1_ani[0][i] = npc_1.getSubimage(i*64,64,64,64);
            npc_1_ani[1][i] = npc_1.getSubimage(i*64,320,64,64);
            for(int j=0; j<10; j++) {
                golem_1_ani[i][j]=golem.getSubimage(j*120,120*i,120,120);
            }
        }
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
            if(380-panel.map_posX >= 1850 && 380-panel.map_posX <= 1930 && 156-panel.map_posY >= 700 && 156-panel.map_posY <= 750 && panel.key.up) {
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
        if((380 - panel.map_posX >= 1988 && 380 - panel.map_posX <= 2088 && 156 - panel.map_posY >= 810 && 156 - panel.map_posY <= 814) ||
           (380 - panel.map_posX >= 1876 && 380 - panel.map_posX <= 1975 && 156 - panel.map_posY >= 1426 && 156 - panel.map_posY <= 1428) ||
           (380 - panel.map_posX >= 2266 && 380 - panel.map_posX <= 2372 && 156 - panel.map_posY >= 1697 && 156 - panel.map_posY <= 1700) ||
           (380 - panel.map_posX >= 2615 && 380 - panel.map_posX <= 2617 && 156 - panel.map_posY >= 1395 && 156 - panel.map_posY <= 1475) ||
           (380 - panel.map_posX >= 1185 && 380 - panel.map_posX <= 1187 && 156 - panel.map_posY >= 1336 && 156 - panel.map_posY <= 1414) ||
           (380 - panel.map_posX >= 1430 && 380 - panel.map_posX <= 1525 && 156 - panel.map_posY >= 2089 && 156 - panel.map_posY <= 2091)) {
            panel.player1.z = 0;
        }
        else if((380 - panel.map_posX >= 1988 && 380 - panel.map_posX <= 2088 && 156 - panel.map_posY <= 670 && 156 - panel.map_posY >= 668 ) ||
                (380 - panel.map_posX >= 1876 && 380 - panel.map_posX <= 1975 && 156 - panel.map_posY <= 1287 && 156 - panel.map_posY >= 1285) ||
                (380 - panel.map_posX >= 2266 && 380 - panel.map_posX <= 2372 && 156 - panel.map_posY <= 1573 && 156 - panel.map_posY >= 1570) ||
                (380 - panel.map_posX >= 2615 && 380 - panel.map_posX <= 2617 && 156 - panel.map_posY <= 1400 && 156 - panel.map_posY >= 1332) ||
                (380 - panel.map_posX >= 1320 && 380 - panel.map_posX <= 1322 && 156 - panel.map_posY <= 1352 && 156 - panel.map_posY >= 1265) ||
                (380 - panel.map_posX >= 1430 && 380 - panel.map_posX <= 1525 && 156 - panel.map_posY <= 1953 && 156 - panel.map_posY >= 1950)) {
            panel.player1.z = 1;
        }

        drawPlayer(g);
        g.drawImage(npc_1_ani[1][panel.npc_ind],panel.map_posX+1640+200,panel.map_posY+1310+156,64,64,null);
        //g.drawImage(db1,panel.map_posX+1640+250,panel.map_posY+1310+156,64,32,null);
        //g.drawImage(golem_1_ani[7][panel.npc_ind], mecha_golem.x, mecha_golem.y,100,100,null);

        if(panel.player1.z == 0) {
            g.drawImage(map_Level2_layer,panel.map_posX,panel.map_posY,null);
        }
        if(panel.hearts >= 2 && panel.hearts <= 10){
            g.drawImage(panel.health.getSubimage(0,31*(5-panel.hearts/2),160,30),20,20,null);
        }
        g.drawImage(sfx.getSubimage((panel.sfx_ind)*256,0,256,128),panel.map_posX+1640+270, panel.map_posY+360+156,null);
        drawGolem(g);
    }

    void drawGolem(Graphics g){
        if(mecha_golem.distance <= 500) {
            g.drawImage(golem_1_ani[1][panel.npc_ind%8], mecha_golem.x, mecha_golem.y,120,120,null);
            Graphics2D g2d = (Graphics2D) g;

            double centerX = 130;
            double centerY = 50;

            AffineTransform tx = new AffineTransform();
            tx.translate(mecha_golem.x+125,mecha_golem.y+45);
            tx.rotate(0);
            tx.translate(-centerX, -centerY);

            g2d.drawImage(laser.getSubimage(0,panel.npc_ind*80,240,80), tx, null);
        }
        else g.drawImage(golem_1_ani[7][panel.npc_ind], mecha_golem.x, mecha_golem.y,120,120,null);
    }
}
