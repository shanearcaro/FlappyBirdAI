package com.shane.game.entity;

import java.awt.Rectangle;
import java.awt.Image;

public class Pipe extends Entity
{
    public Pipe(final Image image, final int x, final int y) {
        super(image, x, y);
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY(), 52, 960);
    }
}
