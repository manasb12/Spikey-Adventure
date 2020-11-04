package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int x, y;
    protected float velx, vely;
    protected boolean falling = false;
    protected boolean jumping = false;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelx() {
        return velx;
    }

    public void setVelx(float velx) {
        this.velx = velx;
    }

    public float getVely() {
        return vely;
    }

    public void setVely(float vely) {
        this.vely = vely;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isFalling() {
        return falling;
    }
}