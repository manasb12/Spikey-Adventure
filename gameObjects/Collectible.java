package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Main;

public class Collectible extends GameObject{

    private int width = 32;
    private int height = 32;
    
    private BufferedImage coinImage;
    private BufferedImage keyImage;
    private String id;
    private Handler handler;
    
    public Collectible(int x, int y, String id, Handler handler) {
        super(x, y);
        this.id = id;
        coinImage = Main.imageLoader.loadImage("/resources/coin.png");
        keyImage = Main.imageLoader.loadImage("/resources/key.png");
        this.handler = handler;
    }

    public void update() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject obj = handler.objects.get(i);
            if (obj instanceof Player) {
                if (id == "key") {
                    if (((Player) obj).getBoundsAll().intersects(getBounds())) {
                        Door.blockingDoorUnlocked = true;
                        handler.removeObject(this);
                    } else {
                        Door.blockingDoorUnlocked = false;
                    }
                } else if (id == "coin") {
                    if (((Player) obj).getBoundsAll().intersects(getBounds())) {
                        handler.removeObject(this);
                    }
                }
            }
        }
    }
    
    public void render(Graphics g) {
        if (id == "coin") {
            g.drawImage(coinImage, x, y, width,height,null);            
        } else if (id == "key") {
            g.drawImage(keyImage, x, y, width,height,null);            
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
