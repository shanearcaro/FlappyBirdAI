package com.shane.game.engine;

import java.awt.Component;
import javax.swing.JSlider;
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

public class Motor extends JComponent implements Runnable
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
        Motor.UPDATE_CAP = 0.01;
        Motor.scale = 1.0;
    }
    
    public Motor(final Control c) {
        this.running = false;
        this.bank = new ImageBank();
        this.setLayout(null);
        this.setFocusable(true);
        this.createLabels();
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
            while (unprocessedTime >= Motor.UPDATE_CAP * Motor.scale) {
                unprocessedTime -= Motor.UPDATE_CAP * Motor.scale;
                render = true;
                if (frameTime >= 1.0) {
                    frameTime = 0.0;
                    fps = frames;
                    frames = 0;
                }
            }
            if (render) {
                ++frames;
                this.render();
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
    
    public static void setScale(final double scal) {
        Motor.scale = scal;
    }
    
    public void createLabels() {
        Motor.labels = new JTextField[6];
        for (int i = 0; i < Motor.labels.length; ++i) {
            (Motor.labels[i] = new JTextField()).setSize(50, 25);
            Motor.labels[i].setFocusable(false);
            Motor.labels[i].setEditable(false);
            Motor.labels[i].setHorizontalAlignment(0);
            if (i == 0) {
                Motor.labels[i].setText("Med");
            }
            else if (i == 1) {
                Motor.labels[i].setText(String.valueOf(Window.getSliders().get(i).getValue() - 1000));
            }
            else {
                Motor.labels[i].setText(String.valueOf(Window.getSliders().get(i).getValue()));
            }
            if (i < 3) {
                Motor.labels[i].setLocation(160, 548 + i * 25);
            }
            else {
                Motor.labels[i].setLocation(380, 548 + (i - 3) * 25);
            }
            this.add(Motor.labels[i]);
            Motor.labels[i].setVisible(false);
        }
    }
    
    public static JTextField[] getLabels() {
        return Motor.labels;
    }
    
    public static double getScale() {
        return Motor.scale;
    }
}
