package com.shane.game.state;

import java.awt.Component;
import com.shane.game.engine.Window;

public class SplashscreenDriver
{
    private Splashscreen screen;
    
    public SplashscreenDriver(final Window window) {
        (this.screen = new Splashscreen()).setLocationRelativeTo(null);
        this.screen.setMaxProgress(100);
        this.screen.setVisible(true);
        for (int i = 0; i <= 100; ++i) {
            this.screen.setProgress(i);
            int l;
            if (i == 100) {
                l = (int)(Math.random() * 4.0E7);
            }
            else {
                l = (int)(Math.random() * 3500000.0);
            }
            for (int j = 0; j < l; ++j) {
                new StringBuilder("aa").append(i * j).toString();
            }
        }
        this.screen.setVisible(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setAutoRequestFocus(true);
    }
}
