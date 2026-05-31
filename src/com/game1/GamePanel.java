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

    public int posX = 480;
    public int posY = 256;

    private int map_posX = -1000;
    private int map_posY = -1000;

    public keyboard key;
    public mouse Mouse;

    private Thread t;
    private final int FPS = 120;
    private int jump_Force = 10;
    private boolean jump_start = false;
    private double vertical_Vel = 0.0;
    private double gravity = 0.3; //constant throughout
    private int velocity = 2;


    private BufferedImage background;
    private BufferedImage characterimg;
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

        key = new keyboard();
        Mouse = new mouse();
        addMouseMotionListener(Mouse);
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

        for(int i=0; i<8;i++) {

            run_ani[i] = characterimg.getSubimage(64*i,64*41,64,64);
            run_ani_back[i] = characterimg.getSubimage(64*i,64*39,64,64);
            run_backward[i] = characterimg.getSubimage(64*i,64*38,64,64);
            run_forward[i] = characterimg.getSubimage(64*i,64*40,64,64);
            if(i<2) idle_ani[i] = characterimg.getSubimage(64*i,64*25,64,64);
            if(i<6) attack_right[i] = characterimg.getSubimage(128*i,4352,128,128);

        }
    }

    private void importImg()  {

        InputStream is1 = getClass().getResourceAsStream("/map2.png");
        InputStream is2 = getClass().getResourceAsStream("/character2.png");

        try{
            assert is1 != null;
            background = ImageIO.read(is1);
            assert is2 != null;
            characterimg = ImageIO.read(is2);
        }
        catch(Exception _){}
        finally {
            try {
                assert is1 != null;
                is1.close();
                assert is2 != null;
                is2.close();
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
        }
        if(key.down) {
//            if(posY >= 351) map_posY-=velocity;
//            else posY+=velocity;
            map_posY -= velocity;
            direction = 4;
        }
        if(key.left) {
//            if(posX <= 100) map_posX+=velocity;
//            else posX-=velocity;
            map_posX += velocity;
            direction = 2;
        }
        if(key.right) {
//            if(posX >= 812) map_posX-=velocity;
//            else posX+=velocity;
            map_posX -= velocity;
            direction = 1;
        }

//        if(posY > 351) posY = 351;
//        if(posY < 0) posY = 0;
        if(map_posX <= -1480) map_posX = -1480;
        if(map_posY <= -1012) map_posY = -1012;
        if(map_posX >= 0) map_posX = 0;
        if(map_posY >= -200) map_posY = -200;

    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,map_posX,map_posY,null);

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
        updateAniTick();
        changePos();
    }

// update animation tick is used for animation after a certain frames are
// passed a tick is registered and index is shifted

    private void updateAniTick() {
        aniTick++;

        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            idle_ind+=0.25;
            if(aniIndex >= run_ani.length) aniIndex = 0;
            if(idle_ind >= idle_ani.length) {
                //System.out.println(map_posX+" "+map_posY);
                idle_ind = 0;
            }

        }
    }
}
