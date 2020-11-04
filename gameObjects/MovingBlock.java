package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Main;

public class MovingBlock extends GameObject {

    private int width = 64, height = 64;
    private BufferedImage movingBlockImage;
    private Handler handler;

    public MovingBlock(int x, int y, Handler handler) {
        super(x, y);
        movingBlockImage = Main.imageLoader.loadImage("/resources/movingBlock.png");
        this.handler = handler;
        velx = 2;
    }

    public void update() {
        x += velx;
        y += vely;

        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject obj = handler.objects.get(i);
            if (obj instanceof Block) {
                if (getBoundsRight().intersects(obj.getBounds())) {
                    velx = -2;
                } else if (getBoundsLeft().intersects(obj.getBounds())) {
                    velx = 2;
                }
            } 
        }
    }

    public void render(Graphics g) {
        g.drawImage(movingBlockImage, x, y, width, height, null);
    }

    public Rectangle getBoundsRight() { //Right
        return new Rectangle(x + width - 5, y + 5, 5, height - 10);
    }

    public Rectangle getBoundsLeft() { //Left
        return new Rectangle(x, y + 5, 5, height - 10);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
