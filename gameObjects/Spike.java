package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Main;

public class Spike extends GameObject {

    private int width = 32;
    private int height = 32;
    private int face;

    private BufferedImage [] spikeImage = new BufferedImage[4];

    public Spike(int x, int y, int face) {
        super(x, y);
        spikeImage[0] = Main.imageLoader.loadImage("/resources/spike.png");
        spikeImage[1] = Main.imageLoader.loadImage("/resources/spikeDown.png");
        spikeImage[2] = Main.imageLoader.loadImage("/resources/spikeRight.png");
        spikeImage[3] = Main.imageLoader.loadImage("/resources/spikeLeft.png");
        this.face = face;
    }

    public void update() {

    }

    public void render(Graphics g) {
        if (face == 1) { //spikeTop
            g.drawImage(spikeImage[0], x, y, width, height, null);
        } else if (face == 2) { //spikeDown
            g.drawImage(spikeImage[1], x, y, width, height, null);
        } else if (face == 3) { //spikeRight
            g.drawImage(spikeImage[2], x, y, width, height, null);
        } else if (face == 4) { //spikeLeft
            g.drawImage(spikeImage[3], x, y, width, height, null);
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
