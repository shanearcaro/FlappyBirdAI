package com.shane.game.entity;

import java.awt.Rectangle;
import java.awt.Image;

public abstract class Entity
{
    private Image image;
    private int x;
    private int y;
    
    public Entity(final Image image, final int x, final int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }
    
    public Entity() {
        this.image = null;
        this.x = 0;
        this.y = 0;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public void setImage(final Image image) {
        this.image = image;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public void setLocation(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public abstract Rectangle getBounds();
}
