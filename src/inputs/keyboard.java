package inputs;

import com.game1.GamePanel;
import com.game1.sound_effect;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboard implements KeyListener {

    public boolean up=false, down=false, left=false, right=false, space=false, E=false, enter=false;
    public boolean w=false,a=false,s=false,d=false;
    public sound_effect sound;
    public keyboard(){
        sound = new sound_effect();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //sound.play();
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP :
                up = true;
                break;
            case KeyEvent.VK_DOWN :
                down = true;
                break;
            case KeyEvent.VK_LEFT :
                left = true;
                break;
            case KeyEvent.VK_RIGHT :
                right = true;
                break;
            case KeyEvent.VK_SPACE :
                space = true;
                break;
            case KeyEvent.VK_E :
                E = true;
                break;
            case KeyEvent.VK_ENTER :
                enter = true;
                break;
            case KeyEvent.VK_W :
                w = true;
                break;
            case KeyEvent.VK_A :
                a = true;
                break;
            case KeyEvent.VK_S :
                s = true;
                break;
            case KeyEvent.VK_D :
                d = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP :
                up = false;
                break;
            case KeyEvent.VK_DOWN :
                down = false;
                break;
            case KeyEvent.VK_LEFT :
                left = false;
                break;
            case KeyEvent.VK_RIGHT :
                right = false;
                break;
            case KeyEvent.VK_SPACE :
                space = false;
                break;
            case KeyEvent.VK_E :
                E = false;
                break;
            case KeyEvent.VK_ENTER :
                enter = false;
                break;
            case KeyEvent.VK_W :
                w = false;
                break;
            case KeyEvent.VK_A :
                a = false;
                break;
            case KeyEvent.VK_S :
                s = false;
                break;
            case KeyEvent.VK_D :
                d = false;
                break;
        }
    }
}
