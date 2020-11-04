package gameObjects;

import Image.Animation;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Main;

public class Obstacle extends GameObject {

    private int width = 32, height = 32;
    private BufferedImage waterImage; 
    private BufferedImage fireImage;
    private String obstacle;

    public Obstacle(int x, int y, String obstacle) {
        super(x, y);
        waterImage = Main.imageLoader.loadImage("/resources/water.png");
        fireImage = Main.imageLoader.loadImage("/resources/fire.png");
        this.obstacle = obstacle;
    }

    public void update() {
    }

    public void render(Graphics g) {
        if (obstacle == "water") {
            g.drawImage(waterImage,x,y,width,height,null);
        } else if (obstacle == "fire") {
            g.drawImage(fireImage,x,y,width,height,null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
