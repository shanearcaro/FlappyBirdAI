package com.shane.game.engine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import com.shane.game.load.Audio;
import com.shane.game.state.SplashscreenDriver;
import java.awt.Component;
import java.util.concurrent.Executors;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import com.shane.game.state.Control;

public class Display
{
    public static void main(final String[] args) throws InterruptedException {
        final Window window = new Window("Flappy Bird", 800, 653);
        final Control controller = new Control();
        final Engine engine = new Engine(controller);
        final Motor motor = new Motor(controller);
        window.setFocusable(true);
        window.setAutoRequestFocus(true);
        window.addMouseMotionListener(engine.getController());
        window.addMouseListener(engine.getController());
        window.addKeyListener(engine.getController());
        window.addMouseWheelListener(engine.getController());
        Window.getScrollBar().addAdjustmentListener(engine.getController());
        final ExecutorService service = Executors.newFixedThreadPool(2);
        window.add(engine);
        window.add(motor);
        new SplashscreenDriver(window);
        window.setAutoRequestFocus(true);
        Audio.playSound("/Audio/score.wav");
        service.submit(engine);
        service.submit(motor);
        service.shutdown();
        service.awaitTermination(1L, TimeUnit.DAYS);
        System.exit(0);
    }
}