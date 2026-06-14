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

    private int localX = posX-map_posX+32;
    private int localY = posY-map_posY+64;

    public keyboard key;
    public mouse Mouse;

    private Thread t;
    public boolean game_start = false;
    private final int FPS = 120;
    private int jump_Force = 10;
    private boolean jump_start = false;
    private double vertical_Vel = 0.0;
    private final double gravity = 0.3; //constant throughout
    public final int velocity = 2;

    private boolean is_up = false;

    public orc orc1;
    public player player1;

    private BufferedImage start_menu;
    private BufferedImage background;
    private BufferedImage map_layer;
    private BufferedImage map_hitbox;
    private BufferedImage map_hitbox_layer_2;
    private BufferedImage orc1_run;
    private BufferedImage orc1_idle;
    private BufferedImage orc1_attack;
    private BufferedImage characterimg;


    private BufferedImage[][] orc1_run_ani;
    private BufferedImage[][] orc1_idle_ani;
    private BufferedImage[][] orc1_attack_ani;
    private BufferedImage[] run_ani; // right
    private BufferedImage[] run_ani_back; // left
    private BufferedImage[] run_forward;
    private BufferedImage[] run_backward;
    private BufferedImage[] idle_ani;
    private BufferedImage[] attack_right;
    private int direction = 1;
    // 1 --> right
    // 2 --> left
    // 3 --> back
    // 4 --> forward
    // direction not implemented properly

    private int aniTick,aniIndex,aniSpeed=12;
    private double idle_ind = 0.0;

    public GamePanel() {

        key = new keyboard(this);
        Mouse = new mouse();
        orc1 = new orc(key,this);
        player1 = new player(this);
        //addMouseMotionListener(Mouse);
        addKeyListener(key);
        t = new Thread(this);

        setPanelSize();
        importImg();
        loadAnim();
        t.start();
    }

    private void loadAnim() {

        run_ani = new BufferedImage[8];
        idle_ani = new BufferedImage[2];//25
        run_ani_back = new BufferedImage[8];
        run_forward = new BufferedImage[8];
        run_backward = new BufferedImage[8];
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

            run_ani[i] = characterimg.getSubimage(64*i,64*41,64,64);
            run_ani_back[i] = characterimg.getSubimage(64*i,64*39,64,64);
            run_backward[i] = characterimg.getSubimage(64*i,64*38,64,64);
            run_forward[i] = characterimg.getSubimage(64*i,64*40,64,64);

            if(i<2) idle_ani[i] = characterimg.getSubimage(64*i,64*25,64,64);
            if(i<6) attack_right[i] = characterimg.getSubimage(128*i,4352,128,128);
            if(i<4) {
                orc1_idle_ani[0][i] = orc1_idle.getSubimage(64*i,0,64,64);
                orc1_idle_ani[1][i] = orc1_idle.getSubimage(64*i,64,64,64);
                orc1_idle_ani[2][i] = orc1_idle.getSubimage(64*i,128,64,64);
                orc1_idle_ani[3][i] = orc1_idle.getSubimage(64*i,192,64,64);
            }

        }
    }

    private void importImg()  {

        InputStream is1 = getClass().getResourceAsStream("/map2.png");
        InputStream is2 = getClass().getResourceAsStream("/character2.png");
        InputStream is3 = getClass().getResourceAsStream("/hitbox_final (1).png");
        InputStream is4 = getClass().getResourceAsStream("/orc1_run_with_shadow.png");
        InputStream is5 = getClass().getResourceAsStream("/orc1_idle_with_shadow.png");
        InputStream is6 = getClass().getResourceAsStream("/orc1_run_attack_front.png");
        InputStream is7 = getClass().getResourceAsStream("/map _layer.png");
        InputStream is8 = getClass().getResourceAsStream("/hitbox_layer_2.png");
        InputStream is9 = getClass().getResourceAsStream("/Start_menu.png");

        try{
            assert is1 != null;
            background = ImageIO.read(is1);
            assert is2 != null;
            characterimg = ImageIO.read(is2);
            assert is3 != null;
            map_hitbox = ImageIO.read(is3);
            assert is4 != null;
            orc1_run = ImageIO.read(is4);
            assert is5 != null;
            orc1_idle = ImageIO.read(is5);
            assert is6 != null;
            orc1_attack = ImageIO.read(is6);
            assert is7 != null;
            map_layer = ImageIO.read(is7);
            assert is8 != null;
            map_hitbox_layer_2 = ImageIO.read(is8);
            assert is9 != null;
            start_menu = ImageIO.read(is9);
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
                is4.close();
                assert is6 != null;
                is6.close();
                assert is7 != null;
                is7.close();
                assert is8 != null;
                is8.close();
                assert is9 != null;
                is9.close();

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

    public void changePos() {

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
//            if(posY <= 100) map_posY+=velocity;
//            else posY-=velocity;
            map_posY += velocity;
            direction = 3;

            if(isCollidingWithMap(posX-map_posX+32,posY-map_posY+64)) map_posY -= velocity;
        }
        if(key.down) {
//            if(posY >= 351) map_posY-=velocity;
//            else posY+=velocity;
            map_posY -= velocity;
            direction = 4;

            if(isCollidingWithMap(posX-map_posX+32,posY-map_posY+64)) map_posY += velocity;
        }
        if(key.left) {
//            if(posX <= 100) map_posX+=velocity;
//            else posX-=velocity;
            map_posX += velocity;
            direction = 2;

            if(isCollidingWithMap(posX-map_posX+32,posY-map_posY+64)) map_posX -= velocity;
        }
        if(key.right) {
//            if(posX >= 812) map_posX-=velocity;
//            else posX+=velocity;
            map_posX -= velocity;
            direction = 1;

            if(isCollidingWithMap(posX-map_posX+32,posY-map_posY+64)) map_posX += velocity;
        }

//        if(posY > 351) posY = 351;
//        if(posY < 0) posY = 0;
        if(map_posX <= -1580) map_posX = -1580;
        if(map_posY <= -1112) map_posY = -1112;
        if(map_posX >= -100) map_posX = -100;
        if(map_posY >= -300) map_posY = -300;

        if(posX-map_posX+32 >= 950 && posX-map_posX+32 <= 1150) {
            if(posY - map_posY == 892) is_up = true;
            if(posY - map_posY == 960) is_up = false;
        }
    }



    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(background,map_posX,map_posY,null);

        if(orc1.distance < 300 && orc1.distance >= 30) g.drawImage(orc1_run_ani[orc1.direction][aniIndex],orc1.x,orc1.y,null);
        else if(orc1.distance < 30) g.drawImage(orc1_attack_ani[orc1.direction][aniIndex],orc1.x,orc1.y,null);
        else g.drawImage(orc1_idle_ani[orc1.direction][aniIndex%4],orc1.x,orc1.y,null);

        if(key.E) g.drawImage(attack_right[aniIndex%6],posX-32,posY-32,null);
        else{
            if(!(key.right || key.left || key.up || key.down)) g.drawImage(idle_ani[(int)idle_ind%2],posX,posY,null);
            else {

                if(direction == 1) g.drawImage(run_ani[aniIndex],posX,posY,null);
                else if(direction == 2) g.drawImage(run_ani_back[aniIndex],posX,posY,null);
                else if(direction == 3) g.drawImage(run_backward[aniIndex],posX,posY,null);
                else if(direction == 4) g.drawImage(run_forward[aniIndex],posX,posY,null);
            }
        }
        if(!is_up) g.drawImage(map_layer,map_posX+445,map_posY+448,null);
        if(!game_start) g.drawImage(start_menu,0,0,null);
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
        changePos();
        orc1.changePosOrc();
    }

// update animation tick is used for animation after a certain frames are
// passed a tick is registered and index is shifted

    private void updateAniTick() {
        aniTick++;

        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            idle_ind += 0.25;

            if (aniIndex >= run_ani.length) aniIndex = 0;
            if (idle_ind >= idle_ani.length) {

                //System.out.println(orc1.orcX+" "+orc1.orcY);
                idle_ind = 0;
            }

        }
    }
    public boolean isCollidingWithMap(int X,int Y) {

        int pixelColor = map_hitbox.getRGB(X,Y);
        int pixel2 = map_hitbox_layer_2.getRGB(X,Y);
        int alpha = (pixelColor >> 24) & 0xff;
        int beta = (pixel2 >> 24) & 0xff;
        if(!is_up) return alpha > 0;
        return beta > 0 || alpha > 0;
    }
}
