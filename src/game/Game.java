/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ziya
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1300, HEIGHT = 2 * WIDTH / 3;
    private Thread thread;
    private final Spawn spawn;
    boolean running = false;
    private HUD hud;

    public Game() {
        initGame();
        this.spawn = new Spawn();
        new Window(WIDTH, HEIGHT, "My First Game", this);
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
        int frames = 0;

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
        SharedVariables.getHandler().tick();
        spawn.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(122, 133, 150));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        SharedVariables.getHandler().render(g);
        hud.render(g);
        g.dispose();
        bs.show();

    }

    private void initGame() {
        Handler handler = new Handler();
        SharedVariables.setHandler(handler);
        SharedVariables.setPlayer(new Player(100, 100, ID.FIRST_PLAYER));
        SharedVariables.setSecondPlayer(new Player(1100, 100, ID.SECOND_PLAYER));

        this.hud = new HUD();

        handler.addObject(new BasicEnemy(100, 200, ID.BASIC_ENEMY));
        handler.addObject(new BasicEnemy(700, 200, ID.BASIC_ENEMY));
        handler.addObject(SharedVariables.getPlayer());
        handler.addObject(SharedVariables.getSecondPlayer());

        addKeyListener(new FirstPlayerKeyInput());
        addKeyListener(new SecondPlayerKeyInput());

    }

}
