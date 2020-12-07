package com.shane.game.entity;

import java.awt.Image;
import java.util.List;

public class BirdManager
{
    private List<Image> assets;
    
    public BirdManager(final List<Image> assets) {
        this.assets = assets;
    }
    
    public Bird createYellowBird() {
        final Bird bird = new Bird();
        final int random = (int)(Math.random() * 3.0);
        if (random == 0) {
            bird.setImage(this.assets.get(10));
            bird.setImageIndex(0);
        }
        else if (random == 1) {
            bird.setImage(this.assets.get(11));
            bird.setImageIndex(1);
        }
        else {
            bird.setImage(this.assets.get(12));
            bird.setImageIndex(2);
        }
        bird.setLocation(400, 300);
        bird.createBrain(2, 6, 1);
        return bird;
    }
    
    public Bird createBlueBird() {
        final Bird bird = new Bird();
        final int random = (int)(Math.random() * 3.0);
        if (random == 0) {
            bird.setImage(this.assets.get(4));
            bird.setImageIndex(3);
        }
        else if (random == 1) {
            bird.setImage(this.assets.get(5));
            bird.setImageIndex(4);
        }
        else {
            bird.setImage(this.assets.get(6));
            bird.setImageIndex(5);
        }
        bird.setLocation(400, 300);
        bird.createBrain(2, 6, 1);
        return bird;
    }
    
    public Bird createRedBird() {
        final Bird bird = new Bird();
        final int random = (int)(Math.random() * 3.0);
        if (random == 0) {
            bird.setImage(this.assets.get(7));
            bird.setImageIndex(6);
        }
        else if (random == 1) {
            bird.setImage(this.assets.get(8));
            bird.setImageIndex(7);
        }
        else {
            bird.setImage(this.assets.get(9));
            bird.setImageIndex(8);
        }
        bird.setLocation(400, 300);
        bird.createBrain(2, 6, 1);
        return bird;
    }
}
