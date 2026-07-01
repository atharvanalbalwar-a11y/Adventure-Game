package com.game1;

import inputs.keyboard;
import inputs.mouse;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class GamePanel extends JPanel implements Runnable {

    public int posX = 380;
    public int posY = 156;

    public int map_posX = -1000;
    public int map_posY = -1000;

    public keyboard key;
    public mouse Mouse;
    public Levels level_obj;

    private Thread t;
    public boolean game_start = false;
    public boolean game_end = false;
    private final int FPS = 120;
    public int transition = 120;
    private final int jump_Force = 10;
    private boolean jump_start = false;
    private double vertical_Vel = 0.0;
    private final double gravity = 0.3; //constant throughout
    public final int velocity = 2;

    public int hearts = 5;
    public int currentLevel = 1;

    public boolean orc1_attacking = false;
    public boolean orc2_attacking = false;
    public boolean flag_orc1_attacking = false;
    public boolean flag_orc2_attacking = false;

    public orc orc1;
    public orc orc2;
    public player player1;

    public BufferedImage start_menu;
    private BufferedImage game_over_screen;
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


    public BufferedImage[][][] orc1_run_ani;
    public BufferedImage[][][] orc1_idle_ani;
    public BufferedImage[][][] orc1_attack_ani;
    public BufferedImage[][] run_ani; // right
    public BufferedImage[][] idle_ani;
    public BufferedImage[] attack_right;
    public int direction = 1;
    // 1 --> right
    // 2 --> left
    // 3 --> back
    // 4 --> forward


    public int aniTick,aniIndex,aniSpeed=12;
    public int coin_ani_ind = 0;
    public double idle_ind = 0.0;

    public GamePanel() {

        key = new keyboard(this);
        Mouse = new mouse();
        orc1 = new orc(this,1000,1000,0);
        orc2 = new orc(this,900,900,1);
        player1 = new player(this,orc1,orc2);
        level_obj = new Levels(this);
        level_obj.loadImg();
        addKeyListener(key);
        t = new Thread(this);

        setPanelSize();
        importImg();
        loadAnim();
        t.start();
    }

    private void loadAnim() {

        run_ani = new BufferedImage[4][8];
        idle_ani = new BufferedImage[4][2];
        attack_right = new BufferedImage[6];

        orc1_run_ani = new BufferedImage[2][4][8];
        orc1_idle_ani = new BufferedImage[2][4][4];
        orc1_attack_ani = new BufferedImage[2][4][8];

        for(int i=0; i<8;i++) {
            for(int j=0; j<2;j++) {
                orc1_run_ani[j][0][i] = orc1_run.getSubimage(64*i,0,64,64);
                orc1_run_ani[j][1][i] = orc1_run.getSubimage(64*i,64,64,64);
                orc1_run_ani[j][2][i] = orc1_run.getSubimage(64*i,128,64,64);
                orc1_run_ani[j][3][i] = orc1_run.getSubimage(64*i,64*3,64,64);

                orc1_attack_ani[j][0][i] = orc1_attack.getSubimage(64*i,0,64,64);
                orc1_attack_ani[j][1][i] = orc1_attack.getSubimage(64*i,64,64,64);
                orc1_attack_ani[j][2][i] = orc1_attack.getSubimage(64*i,128,64,64);
                orc1_attack_ani[j][3][i] = orc1_attack.getSubimage(64*i,192,64,64);

                if(i<4) {
                    orc1_idle_ani[j][0][i] = orc1_idle.getSubimage(64*i,0,64,64);
                    orc1_idle_ani[j][1][i] = orc1_idle.getSubimage(64*i,64,64,64);
                    orc1_idle_ani[j][2][i] = orc1_idle.getSubimage(64*i,128,64,64);
                    orc1_idle_ani[j][3][i] = orc1_idle.getSubimage(64*i,192,64,64);
                }
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
            game_over_screen = ImageIO.read(is10);
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

    //not using the frame.setsize() because it make the frame including the whole window
    private void setPanelSize() {

        Dimension size = new Dimension(960,512);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changePos(BufferedImage map_z0, BufferedImage map_z1) {

        if(key.space && !jump_start) {
            vertical_Vel = -8;
            jump_start = true;
        }
        if(jump_start) {
            posY+=vertical_Vel;
            vertical_Vel+=gravity;

            if(posY >= 251) {
                posY = 251;
                jump_start = false;
                vertical_Vel = 0.0;
            }
        }


        if(key.up) {
            map_posY += velocity;
            direction = 3;

            if(isCollidingWithMap(posX-map_posX+32,posY-map_posY+64, player1.z,map_z0,map_z1)) map_posY -= velocity;
        }
        if(key.down) {
            map_posY -= velocity;
            direction = 4;

            if(isCollidingWithMap(posX-map_posX+32,posY-map_posY+64, player1.z,map_z0,map_z1)) map_posY += velocity;
        }
        if(key.left) {
            map_posX += velocity;
            direction = 2;

            if(isCollidingWithMap(posX-map_posX+32,posY-map_posY+64, player1.z,map_z0,map_z1)) map_posX -= velocity;
        }
        if(key.right) {
            map_posX -= velocity;
            direction = 1;

            if(isCollidingWithMap(posX-map_posX+32,posY-map_posY+64, player1.z,map_z0,map_z1)) map_posX += velocity;
        }

        if(posX-map_posX+32 >= 950 && posX-map_posX+32 <= 1150) {
            if(posY - map_posY >= 892) player1.z = 0;
            if(posY - map_posY <= 960) player1.z = 1;
        }
    }



    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        if(currentLevel == 1) {
            level_obj.level1(g);
        }
        else if(currentLevel == 2) {
            //if(!level_obj.is_lvl2) level_obj.loadImg();
            level_obj.level2(g);
        }
        if(game_end) {
            if(transition >= 0) {
                drawTransition((Graphics2D)g);
            }
            else {
                g.drawImage(game_over_screen,0,0,null);
                drawTransition((Graphics2D)g);
            }
        }

    }

    @Override
    public void run() {

        // 1 billion is used for determining time in nanoseconds
        double Time_per_Frame = 1000000000.0/FPS;
        long Last_Frame = System.nanoTime();
        long now = System.nanoTime();
        while(true) {
            now = System.nanoTime();
            if(now - Last_Frame >= Time_per_Frame) {
                Last_Frame = now;
                update();
                repaint();

            }
        }
    }
    public void update() {
        if(key.enter) game_start = true;
        updateAniTick();
        if(game_start && !game_end) {
            if(currentLevel == 1) {
                changePos(map_hitbox_z0, map_hitbox_z1);
            }
            else if(currentLevel == 2) {
                changePos(level_obj.map_Level2_Z0, level_obj.map_Level2_Z1);
            }
            orc1.changePosOrc();
            orc2.changePosOrc();

        }
        if(game_end && transition > -120) {
            transition-=6;
        }
    }

// update animation tick is used for animation after a certain frames are
// passed a tick is registered and index is shifted

    private void updateAniTick() {
        aniTick++;

        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            coin_ani_ind++;
            idle_ind += 0.25;

            player1.got_hit();

            if(orc1_attacking) flag_orc1_attacking = !flag_orc1_attacking;
            if(orc2_attacking) flag_orc2_attacking = !flag_orc2_attacking;
            if(aniIndex >= run_ani.length) aniIndex = 0;
            if(coin_ani_ind >= 8) coin_ani_ind = 0;
            if (idle_ind >= idle_ani.length) {
                System.out.println(player1.z);
                idle_ind = 0;
            }
        }
    }
    public boolean isCollidingWithMap(int X,int Y,int Z,BufferedImage z0,BufferedImage z1) {

        int pixelColor = z0.getRGB(X,Y);
        int pixel2 = z1.getRGB(X,Y);
        int alpha = (pixelColor >> 24) & 0xff;
        int beta = (pixel2 >> 24) & 0xff;
        if(Z == 0) return alpha > 0;
        return beta > 0;
    }



    public void drawTransition(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(transition*8, 0,960, getHeight());

    }
}