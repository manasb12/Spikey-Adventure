package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Main;

public class Enemy extends GameObject{
    
    private int width = 32, height = 32;
    private BufferedImage enemyImage;
    
    public Enemy(int x, int y) {
        super(x, y);
        enemyImage = Main.imageLoader.loadImage("/resources/enemy.png");
    }

    public void update() {
    }

    public void render(Graphics g) {
        g.drawImage(enemyImage,x,y,width,height,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
