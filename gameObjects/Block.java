package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Main;

public class Block extends GameObject {

    private int width = 32, height = 32;
    private BufferedImage BlockImage;

    public Block(int x, int y) {
        super(x, y);
        BlockImage = Main.imageLoader.loadImage("/resources/Block.png");
    }

    public void update() {
        x += velx;
        y += vely;
    }

    public void render(Graphics g) {
        g.drawImage(BlockImage, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
