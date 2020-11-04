package Input;

import gameObjects.GameObject;
import gameObjects.Handler;
import gameObjects.Player;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import main.Main;

public class KeyInput extends KeyAdapter{

    private Handler handler;
    
    public KeyInput(Handler handler) {
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject obj = handler.objects.get(i);
            if (obj instanceof Player) {
                if (key == KeyEvent.VK_RIGHT) {Player.moveRight = true;}
                if (key == KeyEvent.VK_LEFT) {Player.moveLeft = true;}
                if (key == KeyEvent.VK_SPACE && !obj.isJumping()) {
                    obj.setJumping(true);
                    obj.setVely(-10);
                }
            }
        }
        if (Main.gameState == Main.GameState.MENU || Main.gameState == Main.GameState.DEATHSCREEN) {
            if (key == KeyEvent.VK_ENTER) {
                Main.gameState = Main.GameState.PLAY;
            }
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    } 
    
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject obj = handler.objects.get(i);
            if (obj instanceof Player) {
                if (key == KeyEvent.VK_RIGHT) {Player.moveRight = false;}
                if (key == KeyEvent.VK_LEFT) {Player.moveLeft = false;}
            }
        }        
    }
}