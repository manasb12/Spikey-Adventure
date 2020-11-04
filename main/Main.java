package main;

import HUD.HUD;
import Image.ImageLoader;
import Input.KeyInput;
import gameObjects.Handler;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import menu.Menu;

public class Main extends Canvas implements Runnable {

    public static int gameWidth;
    public static int gameHeight;
    private String title;

    private JFrame frame;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    private boolean running = false;

    public static Handler handler;
    public static ImageLoader imageLoader;
    private HUD hud;
    private Menu menu;

    public static BufferedImage level1 = null;
    
    public static enum GameState {
        PLAY,
        MENU,
        DEATHSCREEN;
    }

    public static GameState gameState = GameState.MENU;

    public Main(int gameWidth, int gameHeight, String title) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.title = title;
        init();
    }

    private void init() {
        createWindow();
        handler = new Handler();
        requestFocus();
        setFocusable(true);
        addKeyListener(new KeyInput(handler));
        imageLoader = new ImageLoader();
        //camera = new Camera(0, 0);
        level1 = imageLoader.loadImage("/resources/Level1.png");
        hud = new HUD();
        menu = new Menu();
        handler.loadLevel(level1);
    }

    private void createWindow() {
        frame = new JFrame(title);
        frame.setSize(gameWidth, gameHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        setMaximumSize(new Dimension(gameWidth, gameHeight));
        setMinimumSize(new Dimension(gameWidth, gameHeight));
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    private synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this, "GameThread");
        thread.start();
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        if (gameState == GameState.PLAY) {
            hud.update();
            handler.update();
        } else if (gameState == GameState.MENU || gameState == GameState.DEATHSCREEN) {
            menu.update();
        }
    }

    private void render() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
       g = bs.getDrawGraphics();
        //Graphics2D g2d = (Graphics2D) g;
        ///////////////
        g.clearRect(0, 0, gameWidth, gameHeight);
        
        
        if (gameState == GameState.PLAY) {
            g.setColor(Color.BLUE);
            g.fillRect(0,0,gameWidth,gameHeight);
            hud.render(g);
            handler.render(g);
            
        } else if (gameState == GameState.MENU || gameState == GameState.DEATHSCREEN) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public void run() {
        while (running) {
            update();
            render();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main(800, 540, "The Spikey Adventure!");
        main.start();
    }
}