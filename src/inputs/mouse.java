package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class mouse implements MouseMotionListener {
    public int[] coordinates;

    public mouse() {
        coordinates = new int[2];
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        coordinates[0] = e.getX();
        coordinates[1] = e.getY();
    }
}
