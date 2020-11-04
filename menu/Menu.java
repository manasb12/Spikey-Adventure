package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Main;

public class Menu {
	
	private BufferedImage playerImage;
	private BufferedImage spikeImageRight, spikeImageUp, spikeImageDown, spikeImageLeft, coinImage;
	
    public Menu() {
       playerImage = Main.imageLoader.loadImage("/resources/player.png");
       spikeImageRight = Main.imageLoader.loadImage("/resources/spikeRight.png");
       spikeImageLeft = Main.imageLoader.loadImage("/resources/spikeLeft.png");
       spikeImageUp = Main.imageLoader.loadImage("/resources/spike.png");
       spikeImageDown = Main.imageLoader.loadImage("/resources/spikeDown.png");
       coinImage = Main.imageLoader.loadImage("/resources/coin.png");
    }
    
    public void update() {
        
    }
    
    public void render(Graphics g) {
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, Main.gameWidth, Main.gameHeight);
    	g.setFont(new Font("Arial", Font.BOLD, 40));
    	g.setColor(Color.RED);
    	g.drawString("THE SPIKEY ADVENTURE!", 150, 200);
    	g.drawImage(playerImage, Main.gameWidth/2-50, 230, 100, 100, null);
    	for (int i = 0; i < Main.gameHeight; i+=32) {
    		g.drawImage(spikeImageRight, 0, i, 32, 32, null);
    		g.drawImage(spikeImageLeft, Main.gameWidth-32, i, 32, 32, null);
    	}
    	for (int i = 0; i < Main.gameWidth; i+=32) {
    		g.drawImage(coinImage, i, 0, 32, 32, null);
    		g.drawImage(coinImage, i, Main.gameHeight-32, 32, 32, null);
    	}
    	g.setFont(new Font("Arial", Font.BOLD, 25));
    	g.setColor(Color.YELLOW);
    	g.drawString("'ENTER'  To Play", 150, 400);
    	g.drawString("'ESCAPE' To Quit", 450, 400);
    	g.setFont(new Font("Arial", Font.PLAIN, 18));
    	g.setColor(Color.WHITE);
    	g.drawString("A Game by Manas Bansal", 290, 500);
    }
}
