package com.shane.game.engine;

import java.util.Iterator;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Window extends JFrame
{
    private static final long serialVersionUID = 1L;
    private final String TITLE;
    private static int WIDTH;
    private static int HEIGHT;
    private static ArrayList<JSlider> sliders;
    private static JScrollBar scrollBar;
    
    static {
        Window.sliders = new ArrayList<JSlider>();
        Window.scrollBar = new JScrollBar(1, 0, 250, 0, 500);
    }
    
    public Window(final String TITLE, final int WIDTH, final int HEIGHT) {
        this.TITLE = TITLE;
        Window.WIDTH = WIDTH;
        Window.HEIGHT = HEIGHT;
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(3);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(false);
        Window.scrollBar.setSize(15, HEIGHT - 35);
        Window.scrollBar.setLocation(WIDTH, 6);
        this.add(Window.scrollBar);
        this.createSliders();
        for (final JSlider e : Window.sliders) {
            this.add(e);
        }
        this.setSize(800, 653);
        this.setLocation(0, 0);
    }
    
    public void createSliders() {
        Window.sliders.add(new JSlider(0, 1, 3, 2));
        Window.sliders.add(new JSlider(0, 900, 1400, 1060));
        Window.sliders.add(new JSlider(0, 10, 1000, 50));
        Window.sliders.add(new JSlider(0, 1, 100, 5));
        Window.sliders.add(new JSlider(0, 1, 100, 80));
        Window.sliders.add(new JSlider(0, 1, 200, 105));
        for (int i = 0; i < Window.sliders.size(); ++i) {
            Window.sliders.get(i).setSize(new Dimension(100, 20));
            Window.sliders.get(i).setFocusable(false);
            if (i < 3) {
                Window.sliders.get(i).setLocation(60, 550 + i * 25);
            }
            else {
                Window.sliders.get(i).setLocation(290, 550 + (i - 3) * 25);
            }
            Window.sliders.get(i).setVisible(false);
        }
    }
    
    public static ArrayList<JSlider> getSliders() {
        return Window.sliders;
    }
    
    public static JScrollBar getScrollBar() {
        return Window.scrollBar;
    }
    
    public static int getWIDTH() {
        return Window.WIDTH;
    }
    
    public static int getHEIGHT() {
        return Window.HEIGHT;
    }
    
    public Window getWindow() {
        return this;
    }
}
