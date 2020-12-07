package com.shane.game.entity;

public class Brain
{
    private double[] inputNodes;
    private double[] hiddenNodes;
    private double[] outputNodes;
    private double[] weightedConnections;
    private double fitness;
    private boolean active;
    private long distanceTraveled;
    
    public Brain(final int input, final int hidden, final int output) {
        this.fitness = 0.0;
        this.active = true;
        this.distanceTraveled = 0L;
        this.inputNodes = new double[input];
        this.hiddenNodes = new double[hidden];
        this.outputNodes = new double[output];
        this.weightedConnections = new double[input * hidden + hidden * output];
        this.createInitialWeights();
    }
    
    public Brain(final int input, final int hidden, final int output, final double[] weightedConnections) {
        this.fitness = 0.0;
        this.active = true;
        this.distanceTraveled = 0L;
        this.inputNodes = new double[input];
        this.hiddenNodes = new double[hidden];
        this.outputNodes = new double[output];
        this.weightedConnections = weightedConnections;
    }
    
    public void createInitialWeights() {
        for (int i = 0; i < this.weightedConnections.length; ++i) {
            this.weightedConnections[i] = Math.random() * 4.0 - 2.0;
        }
    }
    
    public void setWeights(final double[] weightedConnections) {
        this.weightedConnections = weightedConnections;
    }
    
    public double[] getWeights() {
        return this.weightedConnections;
    }
    
    public double[] getHidden() {
        return this.hiddenNodes;
    }
    
    public void setInput(final double[] inputNodes) {
        this.inputNodes = inputNodes;
    }
    
    public double sigmoid(final double d) {
        return 1.0 / (1.0 + Math.exp(-d));
    }
    
    public double[] think() {
        int weight = 0;
        for (int i = 0; i < this.hiddenNodes.length; ++i) {
            for (int j = 0; j < this.inputNodes.length; ++j) {
                final double[] hiddenNodes = this.hiddenNodes;
                final int n = i;
                hiddenNodes[n] += this.inputNodes[j] * this.weightedConnections[weight];
                ++weight;
            }
            this.hiddenNodes[i] = this.sigmoid(this.hiddenNodes[i]);
        }
        for (int i = 0; i < this.outputNodes.length; ++i) {
            for (int j = 0; j < this.hiddenNodes.length; ++j) {
                final double[] outputNodes = this.outputNodes;
                final int n2 = i;
                outputNodes[n2] += this.hiddenNodes[j] * this.weightedConnections[weight];
                ++weight;
            }
            this.outputNodes[i] = this.sigmoid(this.outputNodes[i]);
        }
        return this.outputNodes;
    }
    
    public double getFitness() {
        return this.fitness;
    }
    
    public void setFitness(final double fitness) {
        this.fitness = fitness;
    }
    
    public boolean compareTo(final Brain o) {
        return o != this && this.getFitness() > o.getFitness();
    }
    
    public void mutate() {
        final int a = (int)(Math.random() * this.weightedConnections.length - 1.0);
        int b;
        int c;
        do {
            b = (int)(Math.random() * this.weightedConnections.length - 1.0);
            c = (int)(Math.random() * this.weightedConnections.length - 1.0);
        } while (a == b || a == c || b == c);
        this.weightedConnections[a] = this.weightedConnections[a] + Math.random() - 0.5;
        this.weightedConnections[b] = this.weightedConnections[b] + Math.random() - 0.5;
        this.weightedConnections[c] = this.weightedConnections[c] + Math.random() - 0.5;
    }
    
    public Brain crossover(final Brain partner) {
        if (partner == this) {
            return this;
        }
        final int split1 = (int)(Math.random() * (this.weightedConnections.length - 1) / 2.0);
        final int split2 = (int)(Math.random() * (this.weightedConnections.length - 1) / 2.0) + (this.weightedConnections.length - 1) / 2;
        final double[] childWeight = new double[this.weightedConnections.length];
        for (int i = 0; i < this.weightedConnections.length; ++i) {
            if (i < (this.weightedConnections.length - 1) / 2) {
                if (i < split1) {
                    childWeight[i] = this.getWeights()[i];
                }
                else {
                    childWeight[i] = partner.getWeights()[i];
                }
            }
            else if (i < split2) {
                childWeight[i] = this.getWeights()[i];
            }
            else {
                childWeight[i] = partner.getWeights()[i];
            }
        }
        return new Brain(this.inputNodes.length, this.hiddenNodes.length, this.outputNodes.length, childWeight);
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public void disable() {
        this.active = false;
    }
    
    public void enable() {
        this.active = true;
    }
    
    public void setDistanceTraveled(final long distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }
    
    public void resetDistanceTraveled() {
        this.distanceTraveled = 0L;
    }
    
    public long getDistanceTraveled() {
        return this.distanceTraveled;
    }
    
    public void incrementFitness() {
        this.fitness += 0.25;
    }
}
