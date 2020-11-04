package gameObjects;

import Image.ImageLoader;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import main.Main;
import static main.Main.handler;

public class Handler {

    public LinkedList<GameObject> objects = new LinkedList<GameObject>();
    private BufferedImage level2 = null, level3 = null;
    public static int level = 1;

    public Handler() {
        ImageLoader imageLoader = new ImageLoader();
        level2 = imageLoader.loadImage("/resources/Level2.png");
        level3 = imageLoader.loadImage("/resources/Level3.png");
    }

    public void update() {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).render(g);
        }
    }

    public void addObject(GameObject obj) {
        objects.add(obj);
    }

    public void removeObject(GameObject obj) {
        objects.remove(obj);
    }

    public void clearLevel() {
        objects.clear();
    }

    public static void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int pixels = image.getRGB(x, y);
                int red = (pixels >> 16) & 0xff;
                int green = (pixels >> 8) & 0xff;
                int blue = (pixels) & 0xff;
                if (red == 0 & green == 0 & blue == 255) {
                    handler.addObject(new Player(x * 32, y * 32, handler));
                }
                if (red == 0 & green == 0 & blue == 0) {
                    handler.addObject(new Block(x * 32, y * 32));
                }
                if (red == 255 & green == 0 && blue == 0) {
                    handler.addObject(new Spike(x * 32, y * 32, 1));
                }
                if (red == 255 & green == 100 && blue == 0) {
                    handler.addObject(new Spike(x * 32, y * 32, 2));
                }
                if (red == 255 & green == 216 && blue == 0) {
                    handler.addObject(new Collectible(x * 32, y * 32, "coin", handler));
                }
                if (red == 255 & green == 150 && blue == 0) {
                    handler.addObject(new Collectible(x * 32, y * 32, "key", handler));
                }
                if (red == 255 & green == 0 && blue == 110) {
                    handler.addObject(new Door(x * 32, y * 32, "normal", handler, 2)); //2 means no sliding
                }
                if (red == 100 & green == 0 && blue == 0) {
                    handler.addObject(new Door(x * 32, y * 32, "blocking", handler, 0));
                }
                if (red == 100 & green == 50 && blue == 0) {
                    handler.addObject(new Door(x * 32, y * 32, "blocking", handler, 1));
                }
                if (red == 96 & green == 96 && blue == 96) {
                    handler.addObject(new Spike(x * 32, y * 32, 4));
                }
                if (red == 56 & green == 56 && blue == 56) {
                    handler.addObject(new MovingBlock(x * 32, y * 32, handler));
                }
                if (red == 0 & green == 148 && blue == 255) {
                    handler.addObject(new Obstacle(x * 32, y * 32, "water"));
                }
                if (red == 226 & green == 86 && blue == 0) {
                    handler.addObject(new Obstacle(x * 32, y * 32, "fire"));
                }
            }
        }
    }

    public void switchLevel() {
        clearLevel();

        switch (level) {
            case 1:
                loadLevel(level3);
                break;
            case 2:
                loadLevel(level3);
                break;
            case 3:
                loadLevel(level3);
                break;
            default: 
                loadLevel(Main.level1);
                break;
        }
        level++;
    }
}