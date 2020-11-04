package HUD;

import gameObjects.Collectible;
import gameObjects.Handler;
import gameObjects.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import main.Main;
import static main.Main.gameHeight;
import static main.Main.gameWidth;

public class HUD {

    public void update() {

    }

    private void changeBackground(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(0, 0, gameWidth, gameHeight);
    }

    public void render(Graphics g) {
        switch (Handler.level) {
            case 1:
                changeBackground(g, Color.blue);
                break;
            case 2:
                changeBackground(g, Color.green);
                break;
            case 3:
                changeBackground(g, Color.gray);
                break;
            default:
                changeBackground(g, Color.cyan);
                break;
        }

    }

}
