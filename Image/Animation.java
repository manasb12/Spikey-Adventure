package Image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;

    private BufferedImage [] images;
    private BufferedImage currentImage;

    public Animation(int speed, BufferedImage...args) {
        this.speed = speed;
        images = new BufferedImage[args.length];
        for (int i = 0; i < args.length; i++) {
            images[i] = args[i];
        }
        frames = args.length;
    }

    public void nextFrame() {
        if (count < frames) {
            currentImage = images[count];
            count++;
        } else { count = 0; }
    }

    public void runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();
        }

    }

    public void drawAnimation(Graphics g, int x, int y) {
        g.drawImage(currentImage, x, y, null);
    }
}