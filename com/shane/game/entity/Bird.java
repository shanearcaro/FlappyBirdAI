package com.shane.game.entity;

import java.awt.Rectangle;
import com.shane.game.util.State;
import com.shane.game.util.StateManager;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Image;

public class Bird extends Entity
{
    private Brain brain;
    private int acceleration;
    private int speed;
    private final int MAXSPEED = 10;
    private final int AI_MAXSPEED = 10;
    private int imageIndex;
    private int distanceTraveled;
    private int score;
    private double degrees;
    
    public Bird(final Image image, final int x, final int y) {
        super(image, x, y);
        this.acceleration = 1;
        this.speed = 1;
        this.imageIndex = 0;
        this.distanceTraveled = 0;
        this.score = 0;
        this.degrees = 0.0;
    }
    
    public Bird() {
        this.acceleration = 1;
        this.speed = 1;
        this.imageIndex = 0;
        this.distanceTraveled = 0;
        this.score = 0;
        this.degrees = 0.0;
    }
    
    public void setImageIndex(final int imageIndex) {
        this.imageIndex = imageIndex;
    }
    
    public void setBird(final Bird bird) {
        this.setImage(bird.getImage());
        this.setX(bird.getX());
        this.setY(bird.getY());
        this.imageIndex = bird.getImageIndex();
        this.brain = bird.getBrain();
    }
    
    public int getImageIndex() {
        return this.imageIndex;
    }
    
    public Image rotateBy() {
        final int w = super.getImage().getWidth(null);
        final int h = super.getImage().getHeight(null);
        final double rads = Math.toRadians(this.degrees);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int newWidth = (int)Math.floor(w * cos + h * sin);
        final int newHeight = (int)Math.floor(h * cos + w * sin);
        final BufferedImage rotated = new BufferedImage(newWidth, newHeight, 2);
        final Graphics2D g2 = rotated.createGraphics();
        final AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);
        final int x = w / 2;
        final int y = h / 2;
        at.rotate(rads, x, y);
        g2.setTransform(at);
        g2.drawImage(super.getImage(), 0, 0, null);
        g2.dispose();
        return rotated;
    }
    
    public void createBrain(final int inputNodes, final int hiddenNodes, final int outputNodes) {
        this.brain = new Brain(inputNodes, hiddenNodes, outputNodes);
    }
    
    public void createBrain(final int inputNodes, final int hiddenNodes, final int outputNodes, final double[] weights) {
        this.brain = new Brain(inputNodes, hiddenNodes, outputNodes, weights);
    }
    
    public int getSpeed() {
        return this.speed;
    }
    
    public void jump() {
        this.speed -= 20;
    }
    
    public void update() {
        if (StateManager.getState() == State.AI) {
            this.speed += this.acceleration;
            if (this.speed < -10) {
                this.speed = -10;
            }
            else if (this.speed > 10) {
                this.speed = 10;
            }
            this.setY(this.getY() + this.speed);
            if (this.speed < 0) {
                this.degrees = this.speed * 2;
            }
            else if (this.speed == 10) {
                this.degrees = this.speed * 4;
            }
            else {
                this.degrees = this.speed * 3;
            }
        }
        else {
            this.speed += this.acceleration;
            if (this.speed < -10) {
                this.speed = -10;
            }
            else if (this.speed > 10) {
                this.speed = 10;
            }
            this.setY(this.getY() + this.speed);
            if (this.speed < 0) {
                this.degrees = this.speed * 2;
            }
            else if (this.speed == 10) {
                this.degrees = this.speed * 5;
            }
            else {
                this.degrees = this.speed * 4;
            }
        }
    }
    
    public void kill() {
        this.speed = -10;
    }
    
    public Brain getBrain() {
        return this.brain;
    }
    
    public void setBrain(final Brain newBrain) {
        this.brain = newBrain;
    }
    
    public void increaseDistanceTraveled() {
        ++this.distanceTraveled;
    }
    
    public void resetDistance() {
        this.distanceTraveled = 0;
    }
    
    public int getDistanceTraveled() {
        return this.distanceTraveled;
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(super.getX(), super.getY(), 34, 24);
    }
    
    public void setRotation(final double value) {
        this.degrees = value;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void resetScore() {
        this.score = 0;
    }
    
    public void incrementScore() {
        ++this.score;
    }
    
    public double getRotation() {
        return this.degrees;
    }
}
