package com.game1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class loadResources {



    public BufferedImage start_menu;
    private BufferedImage gameOverScreen;
    public BufferedImage background;
    public BufferedImage map_layer;
    public BufferedImage map_hitbox_z0;
    public BufferedImage map_hitbox_z1;
    private BufferedImage orc1_run;
    private BufferedImage orc1_idle;
    private BufferedImage orc1_attack;
    public BufferedImage health;
    public BufferedImage coin;
    private BufferedImage characterimg;


    public BufferedImage[][] orc1_run_ani;
    public BufferedImage[][] orc1_idle_ani;
    public BufferedImage[][] orc1_attack_ani;
    public BufferedImage[][] run_ani; // right
    public BufferedImage[][] idle_ani;
    public BufferedImage[] attack_right;




    public BufferedImage map_level2;
    public BufferedImage map_Level2_Z0;
    public BufferedImage map_Level2_Z1;
    public BufferedImage map_Level2_layer;
    public BufferedImage sfx;
    public BufferedImage npc_1;
    public BufferedImage db1;
    public BufferedImage golem_right;
    public BufferedImage golem_left;
    public BufferedImage laser;

    public BufferedImage[][] npc_1_ani;
    public BufferedImage[][][] golem_1_ani;





    private void loadAnim() {

        run_ani = new BufferedImage[4][8];
        idle_ani = new BufferedImage[4][2];
        attack_right = new BufferedImage[6];

        orc1_run_ani = new BufferedImage[4][8];
        orc1_idle_ani = new BufferedImage[4][4];
        orc1_attack_ani = new BufferedImage[4][8];

        for(int i=0; i<8;i++) {

            orc1_run_ani[0][i] = orc1_run.getSubimage(64*i,0,64,64);
            orc1_run_ani[1][i] = orc1_run.getSubimage(64*i,64,64,64);
            orc1_run_ani[2][i] = orc1_run.getSubimage(64*i,128,64,64);
            orc1_run_ani[3][i] = orc1_run.getSubimage(64*i,64*3,64,64);

            orc1_attack_ani[0][i] = orc1_attack.getSubimage(64*i,0,64,64);
            orc1_attack_ani[1][i] = orc1_attack.getSubimage(64*i,64,64,64);
            orc1_attack_ani[2][i] = orc1_attack.getSubimage(64*i,128,64,64);
            orc1_attack_ani[3][i] = orc1_attack.getSubimage(64*i,192,64,64);

            if(i<4) {
                orc1_idle_ani[0][i] = orc1_idle.getSubimage(64 * i, 0, 64, 64);
                orc1_idle_ani[1][i] = orc1_idle.getSubimage(64 * i, 64, 64, 64);
                orc1_idle_ani[2][i] = orc1_idle.getSubimage(64 * i, 128, 64, 64);
                orc1_idle_ani[3][i] = orc1_idle.getSubimage(64 * i, 192, 64, 64);
            }
            run_ani[0][i] = characterimg.getSubimage(64*i,64*41,64,64);
            run_ani[1][i] = characterimg.getSubimage(64*i,64*39,64,64);
            run_ani[2][i] = characterimg.getSubimage(64*i,64*38,64,64);
            run_ani[3][i] = characterimg.getSubimage(64*i,64*40,64,64);

            if(i<2) {
                idle_ani[0][i] = characterimg.getSubimage(64*i,64*25,64,64);
                idle_ani[1][i] = characterimg.getSubimage(64*i,64*23,64,64);
                idle_ani[2][i] = characterimg.getSubimage(64*i,64*22,64,64);
                idle_ani[3][i] = characterimg.getSubimage(64*i,64*24,64,64);
            }
            if(i<6) attack_right[i] = characterimg.getSubimage(128*i,4352,128,128);
        }
    }

    private void importImg()  {

        InputStream is1 = getClass().getResourceAsStream("/map2.png");
        InputStream is2 = getClass().getResourceAsStream("/character2.png");
        InputStream is3 = getClass().getResourceAsStream("/map_Z0_hitbox.png");
        InputStream is4 = getClass().getResourceAsStream("/orc1_run_with_shadow.png");
        InputStream is5 = getClass().getResourceAsStream("/orc1_idle_with_shadow.png");
        InputStream is6 = getClass().getResourceAsStream("/orc1_run_attack_front.png");
        InputStream is7 = getClass().getResourceAsStream("/map_overlay_when_z0.png");
        InputStream is8 = getClass().getResourceAsStream("/map_Z1_hitbox.png");
        InputStream is9 = getClass().getResourceAsStream("/Start_menu.png");
        InputStream is10 = getClass().getResourceAsStream("/GAME OVER.png");
        InputStream is11 = getClass().getResourceAsStream("/healthbar.png");
        InputStream is12 = getClass().getResourceAsStream("/01coin-240x30.png");
        try{
            assert is1 != null;
            background = ImageIO.read(is1);
            assert is2 != null;
            characterimg = ImageIO.read(is2);
            assert is3 != null;
            map_hitbox_z0 = ImageIO.read(is3);
            assert is4 != null;
            orc1_run = ImageIO.read(is4);
            assert is5 != null;
            orc1_idle = ImageIO.read(is5);
            assert is6 != null;
            orc1_attack = ImageIO.read(is6);
            assert is7 != null;
            map_layer = ImageIO.read(is7);
            assert is8 != null;
            map_hitbox_z1 = ImageIO.read(is8);
            assert is9 != null;
            start_menu = ImageIO.read(is9);
            assert is10 != null;
            gameOverScreen = ImageIO.read(is10);
            assert is11 != null;
            health = ImageIO.read(is11);
            assert is12 != null;
            coin = ImageIO.read(is12);
        }
        catch(Exception _){}
        finally {
            try {
                assert is1 != null;
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
                assert is10 != null;
                is10.close();
                assert is11 != null;
                is11.close();
                assert is12 != null;
                is12.close();

            } catch (IOException | NullPointerException _) {}
        }
    }



    public void loadImg() {
        //is_lvl2 = true;
        InputStream is1 = getClass().getResourceAsStream("/New_Map.png");
        InputStream is2 = getClass().getResourceAsStream("/New_Map_Z0.png");
        InputStream is3 = getClass().getResourceAsStream("/New_Map_Z1.png");
        InputStream is4 = getClass().getResourceAsStream("/New_Map_layer.png");
        InputStream is5 = getClass().getResourceAsStream("/quake_0.png");
        InputStream is6 = getClass().getResourceAsStream("/npc1.png");
        InputStream is7 = getClass().getResourceAsStream("/dialogue_box_v1.png");
        InputStream is8 = getClass().getResourceAsStream("/Mecha_Golem.png");
        InputStream is9 = getClass().getResourceAsStream("/Mecha_Golem_Laser.png");
        InputStream is10 = getClass().getResourceAsStream("/Mecha_Golem_dir.png");

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
            golem_right = ImageIO.read(is8);
            assert is9 != null;
            laser = ImageIO.read(is9);
            assert is10 != null;
            golem_left = ImageIO.read(is10);
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
            assert is10 != null;
            is10.close();
        }
        catch(IOException | NullPointerException  _) {}
        npc_1_ani = new BufferedImage[2][10];
        golem_1_ani = new BufferedImage[2][10][10];
        for(int i=0; i<10; i++) {
            npc_1_ani[0][i] = npc_1.getSubimage(i*64,64,64,64);
            npc_1_ani[1][i] = npc_1.getSubimage(i*64,320,64,64);
            for(int j=0; j<10; j++) {
                golem_1_ani[0][i][j] = golem_right.getSubimage(j*120,120*i,120,120);
                golem_1_ani[1][i][j] = golem_left.getSubimage(j*120,120*i,120,120);
            }
        }
    }
}
