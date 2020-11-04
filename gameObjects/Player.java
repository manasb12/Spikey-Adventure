package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Main;

public class Player extends GameObject {

    private int width = 32;
    private int height = 32;
    private BufferedImage player;
    public static boolean moveRight, moveLeft;
    private float gravity = 0.4f;
    private final float MAX_SPD = 10;
    private Handler handler;
    private boolean gameOver = false;
    public static int coinCount = 0;

    public Player(int x, int y, Handler handler) {
        super(x, y);
        player = Main.imageLoader.loadImage("/resources/player.png");
        this.handler = handler;
    }

    public void update() {
        x += velx;
        y += vely;

        if (moveRight) {
            setVelx(5);
        } else if (moveLeft) {
            setVelx(-5);
        } else {
            setVelx(0);
        }

        if (falling || jumping) {
            vely += gravity;
            if (vely > MAX_SPD) {
                vely = MAX_SPD;
            }
        }
        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject obj = handler.objects.get(i);
            if (obj instanceof Block) {
                if (getBoundsTop().intersects(obj.getBounds())) {
                    y = obj.getY() + height;
                    vely = 0;
                }
                if (getBounds().intersects(obj.getBounds())) {
                    y = obj.getY() - height;
                    vely = 0;
                    falling = false;
                    jumping = false;
                } else {
                    falling = true;
                }
                if (getBoundsRight().intersects(obj.getBounds())) {
                    x = obj.getX() - width;
                }
                if (getBoundsLeft().intersects(obj.getBounds())) {
                    x = obj.getX() + 32;
                }
            } else if (obj instanceof Spike || obj instanceof Obstacle) {
                if (getBoundsAll().intersects(obj.getBounds())) {
                    gameOver = true;
                    Main.gameState = Main.GameState.DEATHSCREEN;
                }
            } else if (obj instanceof MovingBlock) {
                if (getBounds().intersects(obj.getBounds())) {
                    y = obj.getY() - height;
                    x = obj.getX();
                    vely = 0;
                    falling = false;
                    jumping = false;
                } else {
                    falling = true;
                }
            }
        } 
    }

    public void render(Graphics g) {
        g.drawImage(player, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x + width / 4, y + height / 2, width / 2, height / 2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle(x + width / 4, y, width / 2, height / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle(x + width - 5, y + 5, 5, height - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle(x, y + 5, 5, height - 10);
    }

    public Rectangle getBoundsAll() {
        return new Rectangle(x, y, width, height);
    }
}
