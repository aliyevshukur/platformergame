/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ziya
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1152, HEIGHT = 546;
    private Thread thread;
    private Handler handler;
    private Player player;
    private Camera camera;
    private HUD hud;
    boolean running = false;
    private Animation animation;

    public Game() {
        initGame();
        loadLevel();
    }

    private void initGame() {
        handler = GSpace.getHandler();
        player = GSpace.getPlayer();
        camera = GSpace.getCamera();
        hud = GSpace.getHud();

        animation = new Animation();
        
        
        addKeyListener(new KeyInput());
        new Window(WIDTH, HEIGHT, "Game Development", this);

//        this.start();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double ammountOfTicks = 60.0;
        double ns = 1000000000 / ammountOfTicks;
        double delta = 0;

        long timer = System.currentTimeMillis();
        float frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                frames++;
            }
            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                System.out.println("FPS :" + frames);

                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        camera.tick();
        animation.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        BufferedImage background = ImageLoader.loadImage("\\res\\background.png", this);
        g.drawImage(background, 0, 0, 384 * 3, 182 * 3, null);
        
//        g.setColor(Color.PINK);
//        g.fillRect(0, 0, WIDTH, HEIGHT);

        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(-camera.getX(), -camera.getY());
        handler.render(g);
        camera.render(g);
        g2d.translate(camera.getX(), camera.getY());

        hud.render(g);
        g.dispose();
        bs.show();

    }

    private void loadLevel() {
        ImageLoader.loadLevel(ImageLoader.loadImage("\\res\\world.png", this), handler, player);
    }

}
