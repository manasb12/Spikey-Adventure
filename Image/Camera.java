package Image;

import gameObjects.GameObject;
import main.Main;

public class Camera {

    private int x, y;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(GameObject obj) {
        x += ((obj.getX() - x) - Main.gameWidth / 2) * 0.05f;
        if (x <= 0) x = 0;
        if (x >= Main.gameWidth*4+345) x = Main.gameWidth*4+345;
        if (y <= 0) y = 0;
   
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}
