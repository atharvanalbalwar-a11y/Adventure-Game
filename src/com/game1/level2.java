package com.game1;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class level2 {

    private final GamePanel panel;
    private loadResources load;
    private Mecha_Golem mecha_golem;

    public level2(GamePanel panel, loadResources load, Mecha_Golem mecha_golem) {
        this.panel = panel;
        this.load = load;
        this.mecha_golem = mecha_golem;
    }

    public void level_2(Graphics g) {


        g.drawImage(load.map_level2,panel.map_posX,panel.map_posY,null);
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
        g.drawImage(load.npc_1_ani[1][panel.ind_10],panel.map_posX+1640+200,panel.map_posY+1310+156,64,64,null);
        //g.drawImage(db1,panel.map_posX+1640+250,panel.map_posY+1310+156,64,32,null);
        //g.drawImage(golem_1_ani[7][panel.ind_10], mecha_golem.x, mecha_golem.y,100,100,null);

        if(panel.player1.z == 0) {
            g.drawImage(load.map_Level2_layer,panel.map_posX,panel.map_posY,null);
        }
        if(panel.hearts >= 2 && panel.hearts <= 10){
            g.drawImage(panel.health.getSubimage(0,31*(5-panel.hearts/2),160,30),20,20,null);
        }
        g.drawImage(load.sfx.getSubimage((panel.ind_6)*256,0,256,128),panel.map_posX+1640+270, panel.map_posY+360+156,null);
        drawGolem(g);
    }

    void drawGolem(Graphics g){
        if(mecha_golem.distance <= 400) {
            if(mecha_golem.direction == 3) g.drawImage(load.golem_1_ani[0][1][panel.ind_8], mecha_golem.x, mecha_golem.y,120,120,null);
            else g.drawImage(load.golem_1_ani[1][1][7-panel.ind_8], mecha_golem.x, mecha_golem.y,120,120,null);
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform tx = new AffineTransform();
            tx.translate(mecha_golem.x+50,mecha_golem.y+30);
            //tx.rotate(panel.ind_10*Math.PI/6);
            tx.rotate(mecha_golem.radians);
            tx.translate(-45, -30);
            g2d.drawImage(load.laser.getSubimage(0,80*panel.ind_10,240,80), tx, null);

        }
        else {
            if(mecha_golem.direction == 3) g.drawImage(load.golem_1_ani[0][7][panel.ind_10], mecha_golem.x, mecha_golem.y,120,120,null);
            //else g.drawImage(golem_1_ani[1][7][9-panel.ind_10], mecha_golem.x, mecha_golem.y,120,120,null);
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
}
