package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Main;

public class Door extends GameObject {

    private int width = 64;
    private int height = 64;
    private BufferedImage doorImage;
    private BufferedImage blockingDoorImage;
    private int blockingDoorHeight = 64, blockingDoorWidth = 64;
    private String doorType;
    private Handler handler;
    public static boolean blockingDoorUnlocked = false;
    private int blockingDoorSide;

    public Door(int x, int y, String doorType, Handler handler, int blockingDoorSide) {
        super(x, y);
        this.doorType = doorType;
        this.handler = handler;
        this.blockingDoorSide = blockingDoorSide;
        doorImage = Main.imageLoader.loadImage("/resources/door.png");
        blockingDoorImage = Main.imageLoader.loadImage("/resources/blockingDoor.png");
    }

    public void update() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject obj = handler.objects.get(i);
            if (obj instanceof Player) {
                if (doorType == "normal") {
                    if (((Player) obj).getBoundsAll().intersects(getBounds())) {
                        handler.switchLevel();
                    }
                } else if (doorType == "blocking") {
                    if (blockingDoorUnlocked) {
                        if (blockingDoorSide == 0) {
                            blockingDoorHeight -= 1;
                            if (blockingDoorHeight <= 10) {
                                blockingDoorHeight = 10;
                            }
                        } else if (blockingDoorSide == 1) {
                            blockingDoorWidth -= 1;
                            if (blockingDoorWidth <= 10) {
                                blockingDoorWidth = 10;
                            }
                        }
                    } else {
                        if (((Player) obj).getBoundsLeft().intersects(getBounds())) {
                            obj.setX(x + width);
                        }
                    }
                }
            }
        }
    }

    public void render(Graphics g) {
        if (doorType == "normal") {
            g.drawImage(doorImage, x, y, width, height, null);
        } else if (doorType == "blocking") {
            g.drawImage(blockingDoorImage, x, y, blockingDoorWidth, blockingDoorHeight, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
