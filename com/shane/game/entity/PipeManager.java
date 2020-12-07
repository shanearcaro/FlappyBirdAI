package com.shane.game.entity;

import java.util.Iterator;
import com.shane.game.util.State;
import com.shane.game.util.StateManager;
import java.util.Collection;
import java.awt.Image;
import java.util.List;
import com.shane.game.load.ImageBank;
import java.util.ArrayList;

public class PipeManager
{
    private ArrayList<Pipe> topPipes;
    private ArrayList<Pipe> bottomPipes;
    private ArrayList<Pipe> junkTopPipes;
    private ArrayList<Pipe> junkBottomPipes;
    private ImageBank bank;
    private List<Image> assets;
    private int tick;
    private int pipeCounter;
    private int pipeFrequency;
    private int pipeGap;
    private int pipePipe;
    
    public PipeManager() {
        this.init();
        this.bank = new ImageBank();
        this.assets = this.bank.getAssets();
    }
    
    public void init() {
        this.topPipes = new ArrayList<Pipe>();
        this.bottomPipes = new ArrayList<Pipe>();
        this.junkTopPipes = new ArrayList<Pipe>();
        this.junkBottomPipes = new ArrayList<Pipe>();
        this.tick = 0;
        this.pipeCounter = 0;
        this.pipeFrequency = 100;
        this.pipeGap = 1060;
        this.pipePipe = 0;
    }
    
    public void setTick(final int tick) {
        this.tick = tick;
    }
    
    public int getTick() {
        return this.tick;
    }
    
    public void setPipeCounter(final int pipeCounter) {
        this.pipeCounter = pipeCounter;
    }
    
    public int getPipeCounter() {
        return this.pipeCounter;
    }
    
    public void setPipeFrequency(final int pipeFrequency) {
        this.pipeFrequency = pipeFrequency;
    }
    
    public int getPipeFrequency() {
        return this.pipeFrequency;
    }
    
    public void setPipeGap(final int pipeGap) {
        this.pipeGap = pipeGap;
    }
    
    public int getPipeGap() {
        return this.pipeGap;
    }
    
    public void setPipePipe(final int pipePipe) {
        this.pipePipe = pipePipe;
    }
    
    public int getPipePipe() {
        return this.pipePipe;
    }
    
    public void addTopPipe(final Pipe p) {
        this.topPipes.add(p);
    }
    
    public void addBottomPipe(final Pipe p) {
        this.bottomPipes.add(p);
    }
    
    public void addJunkTopPipes(final Pipe p) {
        this.junkTopPipes.add(p);
    }
    
    public void addJunkBottomPipes(final Pipe p) {
        this.junkBottomPipes.add(p);
    }
    
    public void deletePipes() {
        this.topPipes.removeAll(this.junkTopPipes);
        this.bottomPipes.removeAll(this.junkBottomPipes);
    }
    
    public ArrayList<Pipe> getTopPipes() {
        return this.topPipes;
    }
    
    public ArrayList<Pipe> getBottomPipes() {
        return this.bottomPipes;
    }
    
    public ArrayList<Pipe> getJunkTopPipes() {
        return this.junkTopPipes;
    }
    
    public ArrayList<Pipe> getJunkBottomPipes() {
        return this.junkBottomPipes;
    }
    
    public void generatePipes() {
        final int out = 1075;
        if (StateManager.getState() == State.PLAYER) {
            if (this.pipeCounter == 0 || this.pipeCounter % this.pipeFrequency == 0) {
                final int difference = 250;
                int a = (int)(Math.random() * difference) + 600;
                a *= -1;
                this.addTopPipe(new Pipe(this.assets.get(2), 850, a));
                this.addBottomPipe(new Pipe(this.assets.get(3), 850, a + out));
            }
        }
        else if (StateManager.getState() == State.AI && (this.pipeCounter == 0 || this.pipeCounter % this.pipeFrequency == 0)) {
            final int difference = 250;
            int a = (int)(Math.random() * difference) + 600;
            a *= -1;
            this.addTopPipe(new Pipe(this.assets.get(2), 850, a));
            int p = a + this.pipeGap;
            if (p > 480) {
                p = 480;
            }
            this.addBottomPipe(new Pipe(this.assets.get(3), 850, p));
        }
        for (final Pipe e : this.getTopPipes()) {
            if (e.getX() < -100) {
                this.addJunkTopPipes(e);
            }
        }
        for (final Pipe e : this.getBottomPipes()) {
            if (e.getX() < -100) {
                this.addJunkBottomPipes(e);
            }
        }
        this.deletePipes();
    }
    
    public void updatePipeLocation() {
        for (final Pipe e : this.getTopPipes()) {
            e.setX(e.getX() - 2);
        }
        for (final Pipe e : this.getBottomPipes()) {
            e.setX(e.getX() - 2);
        }
    }
    
    public Pipe closestPipe() {
        for (int i = 0; i < this.bottomPipes.size(); ++i) {
            if (this.bottomPipes.get(i).getX() > 240) {
                return this.bottomPipes.get(i);
            }
        }
        return null;
    }
}
