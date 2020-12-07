package com.shane.game.engine;

import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.JTextField;
import com.shane.game.state.Control;
import java.awt.Image;
import java.util.List;
import com.shane.game.load.ImageBank;
import javax.swing.JComponent;

public class Engine extends JComponent implements Runnable
{
    private static final long serialVersionUID = 1L;
    private Thread thread;
    private boolean running;
    private static double UPDATE_CAP;
    private ImageBank bank;
    private List<Image> assets;
    private static double scale;
    private Control controller;
    private static JTextField[] labels;
    
    static {
        Engine.UPDATE_CAP = 0.01;
        Engine.scale = 1.0;
    }
    
    public Engine(final Control c) {
        this.running = false;
        this.bank = new ImageBank();
        this.setLayout(null);
        this.setFocusable(true);
        this.assets = this.bank.getAssets();
        this.repaint();
        this.controller = c;
    }
    
    public synchronized void start() {
        (this.thread = new Thread(this)).run();
    }
    
    public synchronized void stop() {
        this.running = false;
    }
    
    @Override
    public synchronized void run() {
        this.running = true;
        boolean render = false;
        double firstTime = 0.0;
        double lastTime = System.nanoTime() / 1.0E9;
        double passedTime = 0.0;
        double unprocessedTime = 0.0;
        double frameTime = 0.0;
        int frames = 0;
        int fps = 0;
        while (this.running) {
            render = false;
            firstTime = System.nanoTime() / 1.0E9;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;
            unprocessedTime += passedTime;
            frameTime += passedTime;
            while (unprocessedTime >= Engine.UPDATE_CAP * Engine.scale) {
                unprocessedTime -= Engine.UPDATE_CAP * Engine.scale;
                render = true;
                this.update();
                if (frameTime >= 1.0) {
                    frameTime = 0.0;
                    fps = frames;
                    frames = 0;
                }
            }
            if (render) {
                ++frames;
            }
            else {
                try {
                    Thread.sleep(1L);
                }
                catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        this.dispose();
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.controller.render(g2);
    }
    
    public void render() {
        this.repaint();
    }
    
    public void update() {
        this.controller.update();
    }
    
    public void dispose() {
        System.exit(0);
    }
    
    public Control getController() {
        return this.controller;
    }
    
    public static double getUpdateCap() {
        return Engine.UPDATE_CAP;
    }
    
    public static void setUpdateCap(final double update) {
        Engine.UPDATE_CAP = update;
    }
    
    public static void setScale(final double scal) {
        Engine.scale = scal;
    }
    
    public static double getScale() {
        return Engine.scale;
    }
}
