// 
// Decompiled by Procyon v0.5.36
// 

package com.shane.game.networking;

import java.util.Iterator;
import java.awt.Image;
import com.shane.game.load.ImageBank;
import com.shane.game.entity.Bird;
import java.util.ArrayList;

public class GeneticAlgorithm
{
    private ArrayList<Bird> population;
    private int populationSize;
    private double crossoverRate;
    private double mutationRate;
    private double totalDistance;
    private double setDistance;
    private boolean reset;
    private ImageBank bank;
    private ArrayList<Double> averageGenerationFitness;
    
    public GeneticAlgorithm(final int populationSize, final double crossoverRate, final double mutationRate) {
        this.totalDistance = 0.0;
        this.setDistance = 0.0;
        this.reset = false;
        this.bank = new ImageBank();
        this.averageGenerationFitness = new ArrayList<Double>();
        this.populationSize = populationSize;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.population = new ArrayList<Bird>();
    }
    
    public void initPop() {
        for (int i = 0; i < this.populationSize; ++i) {
            this.population.add(new Bird(this.bank.getAssets().get(10), 288, (int)(Math.random() * 100.0) + 250));
            this.population.get(i).createBrain(2, 6, 1);
        }
    }
    
    public void setMutationRate(final double rate) {
        this.mutationRate = rate;
    }
    
    public void setCrossoverRate(final double rate) {
        this.crossoverRate = rate;
    }
    
    public void updateFitness() {
        for (int i = 0; i < this.populationSize; ++i) {
            final double fitness = this.population.get(i).getBrain().getFitness();
            final double distance = this.totalDistance;
            this.population.get(i).getBrain().setFitness(fitness / distance);
        }
    }
    
    public double getAverageFitness() {
        double fitness = 0.0;
        for (int i = 0; i < this.populationSize; ++i) {
            fitness += this.population.get(i).getBrain().getFitness();
        }
        return fitness / this.populationSize;
    }
    
    public void resetPopulation() {
        this.reset = true;
    }
    
    public void newGeneration() {
        boolean resetStuck = true;
        boolean mutateAll = false;
        for (int i = 0; i < this.populationSize; ++i) {
            if (this.population.get(i).getBrain().getFitness() > 50.0) {
                resetStuck = false;
            }
        }
        if (resetStuck) {
            for (int i = 0; i < this.populationSize; ++i) {
                this.population.get(i).createBrain(2, 6, 1);
            }
        }
        else {
            this.setDistance = this.totalDistance / 4.0;
            final ArrayList<Bird> genePool = new ArrayList<Bird>();
            final ArrayList<Bird> organized = new ArrayList<Bird>();
            final ArrayList<Bird> unOrganized = new ArrayList<Bird>();
            for (final Bird e : this.population) {
                unOrganized.add(e);
            }
            double averageFitness = 0.0;
            for (int j = 0; j < this.populationSize; ++j) {
                averageFitness += this.population.get(j).getBrain().getFitness();
            }
            averageFitness /= this.populationSize;
            this.averageGenerationFitness.add(averageFitness);
            double fitness = 0.0;
            int index = 0;
            for (int k = 0; k < this.populationSize; ++k) {
                for (int l = 0; l < unOrganized.size(); ++l) {
                    if (unOrganized.get(l).getBrain().getFitness() > fitness) {
                        index = l;
                        fitness = unOrganized.get(l).getBrain().getFitness();
                    }
                }
                organized.add(unOrganized.remove(index));
                fitness = 0.0;
                index = 0;
            }
            final double[] fitnessStored = new double[this.populationSize];
            for (int m = 0; m < this.populationSize; ++m) {
                fitnessStored[m] = organized.get(m).getBrain().getFitness();
            }
            for (int m = 0; m < organized.size(); ++m) {
                if (organized.get(m).getBrain().getFitness() > organized.get(0).getBrain().getFitness()) {
                    organized.get(m).getBrain().setFitness(organized.get(0).getBrain().getFitness());
                }
            }
            for (int m = 0; m < organized.size(); ++m) {
                final double b = this.setDistance;
                organized.get(m).getBrain().setFitness((double)Math.round(fitnessStored[m] / b * 100.0));
            }
            if (organized.get(0).getBrain().getFitness() - organized.get(organized.size() - 1).getBrain().getFitness() <= 10.0) {
                mutateAll = true;
            }
            for (int m = 0; m < organized.size(); ++m) {
                if (organized.get(m).getBrain().getFitness() < 40.0) {
                    organized.get(m).getBrain().setFitness(organized.get(m).getBrain().getFitness() * 1.5);
                }
                for (int n = 0; n < organized.get(m).getBrain().getFitness(); ++n) {
                    genePool.add(organized.get(m));
                }
            }
            this.population.get(0).setBrain(organized.get(0).getBrain());
            this.population.get(1).setBrain(organized.get(1).getBrain());
            for (int m = 2; m < this.populationSize; ++m) {
                if (Math.random() < this.crossoverRate) {
                    int a;
                    int b2;
                    for (a = (int)(Math.random() * genePool.size() - 1.0), b2 = (int)(Math.random() * genePool.size() - 1.0); a == b2; b2 = (int)(Math.random() * genePool.size() - 1.0)) {}
                    this.population.get(m).setBrain(genePool.get(a).getBrain().crossover(genePool.get(b2).getBrain()));
                }
            }
            for (int m = 2; m < this.populationSize; ++m) {
                if (Math.random() < this.mutationRate) {
                    this.population.get(m).getBrain().mutate();
                }
            }
            if (mutateAll) {
                for (int m = (this.populationSize - 1) / 2; m < this.populationSize; ++m) {
                    this.population.get(m).getBrain().mutate();
                }
            }
        }
        for (int i = 0; i < this.populationSize; ++i) {
            this.population.get(i).getBrain().setDistanceTraveled(0L);
            this.population.get(i).getBrain().setFitness(0.0);
            this.population.get(i).setLocation(288, (int)(Math.random() * 100.0) + 250);
            this.population.get(i).getBrain().enable();
            this.population.get(i).resetScore();
        }
        this.totalDistance = 0.0;
        this.setDistance = 0.0;
        if (this.reset) {
            for (final Bird e2 : this.population) {
                e2.createBrain(2, 6, 1);
            }
            this.reset = false;
        }
    }
    
    public void addBird(final Bird e) {
        this.population.add(e);
    }
    
    public void removeBird(final int index) {
        this.population.remove(index);
    }
    
    public ArrayList<Bird> getPopulation() {
        return this.population;
    }
    
    public int numberOfAliveBirds() {
        int alive = 0;
        for (int i = 0; i < this.populationSize; ++i) {
            if (this.population.get(i).getBrain().isActive()) {
                ++alive;
            }
        }
        return alive;
    }
    
    public boolean isActive() {
        for (int i = 0; i < this.populationSize; ++i) {
            if (this.population.get(i).getBrain().isActive()) {
                return true;
            }
        }
        return false;
    }
    
    public void setTotalDistance(final long totalDistance) {
        this.totalDistance = (double)totalDistance;
    }
    
    public void resetDistance() {
        this.totalDistance = 0.0;
    }
    
    public double getTotalDistance() {
        return this.totalDistance;
    }
    
    public void setPopulationSize(final int populationSize) {
        this.populationSize = populationSize;
    }
    
    public int getPopulationSize() {
        return this.populationSize;
    }
}
