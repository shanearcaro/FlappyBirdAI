package com.shane.game.state;

import javax.swing.event.ChangeEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import com.shane.game.load.ResourceManager;
import com.shane.game.load.SaveData;
import java.io.IOException;
import java.io.File;
import com.shane.game.load.Audio;
import java.awt.Font;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.BasicStroke;
import com.shane.game.entity.Pipe;
import java.awt.image.ImageObserver;
import com.shane.game.util.State;
import javax.swing.JTextField;
import java.util.Iterator;
import com.shane.game.engine.Window;
import javax.swing.JSlider;
import com.shane.game.engine.Motor;
import com.shane.game.engine.Engine;
import com.shane.game.util.StateManager;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import com.shane.game.entity.PipeManager;
import com.shane.game.entity.BirdManager;
import com.shane.game.entity.Bird;
import com.shane.game.networking.GeneticAlgorithm;
import com.shane.game.load.ScoreManager;
import java.awt.Image;
import java.util.List;
import com.shane.game.load.ImageBank;
import java.awt.event.MouseWheelListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Control implements MouseMotionListener, MouseListener, KeyListener, AdjustmentListener, MouseWheelListener
{
    private ImageBank bank;
    private List<Image> assets;
    private int background;
    private int offset;
    private ScoreManager score;
    private GeneticAlgorithm ga;
    private Bird bird;
    private BirdManager birdManager;
    private PipeManager pipeManager;
    private int[][] scoreManager;
    private boolean playerStarted;
    private int mouseX;
    private int mouseY;
    private long tick;
    private long imageUpdate;
    private boolean flap;
    private boolean reset;
    private boolean paused;
    private double previousGenerationFitness;
    private double thisGenerationFitness;
    private double bestGenerationFitness;
    private int bestGeneration;
    private int generationNumber;
    private double fitnessCounter;
    private int highScore;
    private int currentScore;
    private boolean newHighScore;
    private int birdOffset;
    private String directoryPath;
    private int populationSize;
    private double crossoverRate;
    private double mutationRate;
    private int sizeChange;
    private int adjustment;
    private int keyCounter;
    private boolean evolve;
    private int secondTimer;
    private int minuteTimer;
    private int addUpCounter;
    private int aiHighScore;
    private boolean updatePosition;
    private boolean dead;
    private boolean hit;
    private boolean sound;
    private int endX;
    private int tickCount;
    
    public Control() {
        this.background = 0;
        this.offset = 0;
        this.scoreManager = new int[3][4];
        this.playerStarted = false;
        this.mouseX = 0;
        this.mouseY = 0;
        this.tick = 0L;
        this.imageUpdate = 0L;
        this.flap = false;
        this.reset = false;
        this.paused = false;
        this.previousGenerationFitness = 0.0;
        this.thisGenerationFitness = 0.0;
        this.bestGenerationFitness = 0.0;
        this.bestGeneration = 0;
        this.generationNumber = 1;
        this.fitnessCounter = 0.0;
        this.highScore = 0;
        this.currentScore = 0;
        this.newHighScore = false;
        this.birdOffset = 0;
        this.directoryPath = "";
        this.populationSize = 50;
        this.crossoverRate = 80.0;
        this.mutationRate = 5.0;
        this.sizeChange = 0;
        this.adjustment = 0;
        this.keyCounter = 0;
        this.evolve = true;
        this.secondTimer = 0;
        this.minuteTimer = 0;
        this.addUpCounter = 0;
        this.aiHighScore = 0;
        this.updatePosition = false;
        this.dead = false;
        this.hit = false;
        this.sound = false;
        this.endX = -173;
        this.tickCount = 0;
        this.init();
    }
    
    public void init() {
        this.bank = new ImageBank();
        this.assets = this.bank.getAssets();
        this.pipeManager = new PipeManager();
        this.birdManager = new BirdManager(this.assets);
        this.createPlayerBird();
        this.score = new ScoreManager(this.assets, this.scoreManager);
        this.createDirectory();
        this.loadData();
        this.calculateScore();
        this.createGeneticAlgorithm();
        this.updateScrollBar();
    }
    
    public void render(final Graphics g) {
        final Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (!this.paused) {
            switch (StateManager.getState()) {
                case MENU: {
                    this.drawMenu(g2);
                    break;
                }
                case PLAYER: {
                    this.drawPlayer(g2);
                    break;
                }
                case AI: {
                    this.drawAI(g2);
                    break;
                }
                case PAUSE: {
                    this.drawPause(g2);
                    break;
                }
                case AIPAUSE: {
                    this.drawAIPause(g2);
                    break;
                }
                case END: {
                    this.drawEnd(g2);
                    break;
                }
            }
        }
    }
    
    public void update() {
        ++this.tick;
        if (!this.paused) {
            switch (StateManager.getState()) {
                case MENU: {
                    this.updateBackgroundLocation();
                    if (Engine.getScale() != 1.0) {
                        Engine.setScale(1.0);
                        Motor.setScale(1.0);
                        Motor.getLabels()[0].setText("Med");
                        Window.getSliders().get(0).setValue(2);
                    }
                    if (!this.updatePosition) {
                        for (final JSlider e : Window.getSliders()) {
                            e.setVisible(false);
                        }
                        JTextField[] labels;
                        for (int length = (labels = Motor.getLabels()).length, i = 0; i < length; ++i) {
                            final JTextField e2 = labels[i];
                            e2.setVisible(false);
                        }
                        Window.getScrollBar().setLocation(10000, 6);
                        this.updatePosition = true;
                        break;
                    }
                    break;
                }
                case PLAYER: {
                    if (!this.updatePosition) {
                        for (final JSlider e : Window.getSliders()) {
                            e.setVisible(false);
                        }
                        JTextField[] labels2;
                        for (int length2 = (labels2 = Motor.getLabels()).length, j = 0; j < length2; ++j) {
                            final JTextField e2 = labels2[j];
                            e2.setVisible(false);
                        }
                        Window.getScrollBar().setLocation(10000, 6);
                        this.updatePosition = true;
                    }
                    this.updatePlayer();
                    if (!this.dead) {
                        this.updateBackgroundLocation();
                        break;
                    }
                    break;
                }
                case AI: {
                    if (this.updatePosition) {
                        for (final JSlider e : Window.getSliders()) {
                            e.setVisible(true);
                        }
                        JTextField[] labels3;
                        for (int length3 = (labels3 = Motor.getLabels()).length, k = 0; k < length3; ++k) {
                            final JTextField e2 = labels3[k];
                            e2.setVisible(true);
                        }
                        Window.getScrollBar().setLocation(Window.getWIDTH() - 21, 6);
                        this.updatePosition = false;
                    }
                    this.updateAI();
                    this.updateBackgroundLocation();
                    break;
                }
                case END: {
                    this.updateEnd();
                    break;
                }
            }
        }
    }
    
    public void createGeneticAlgorithm() {
        (this.ga = new GeneticAlgorithm(this.populationSize, this.crossoverRate / 100.0, this.mutationRate / 100.0)).initPop();
        for (int i = 0; i < this.ga.getPopulation().size(); ++i) {
            final int b = (int)(Math.random() * 3.0);
            if (b == 0) {
                this.ga.getPopulation().get(i).setBird(this.birdManager.createYellowBird());
            }
            else if (b == 1) {
                this.ga.getPopulation().get(i).setBird(this.birdManager.createBlueBird());
            }
            else {
                this.ga.getPopulation().get(i).setBird(this.birdManager.createRedBird());
            }
            if (b == 0) {
                (this.bird = new Bird(this.assets.get(5), 400, 300)).setImageIndex(3);
            }
            else if (b == 1) {
                (this.bird = new Bird(this.assets.get(8), 400, 300)).setImageIndex(6);
            }
            else if (b == 2) {
                (this.bird = new Bird(this.assets.get(11), 400, 300)).setImageIndex(0);
            }
        }
        this.randomize();
    }
    
    public void updateBackgroundLocation() {
        if (this.offset < 767) {
            ++this.offset;
        }
        else {
            this.offset = 0;
        }
    }
    
    public void createPlayerBird() {
        final int r = (int)(Math.random() * 3.0);
        if (r == 0) {
            (this.bird = new Bird(this.assets.get(5), 400, 300)).setImageIndex(3);
        }
        else if (r == 1) {
            (this.bird = new Bird(this.assets.get(8), 400, 300)).setImageIndex(6);
        }
        else if (r == 2) {
            (this.bird = new Bird(this.assets.get(11), 400, 300)).setImageIndex(0);
        }
    }
    
    public void updateBirdImage() {
        if (StateManager.getState() == State.AI) {
            for (final Bird r : this.ga.getPopulation()) {
                if (r.getBrain().isActive()) {
                    if (r.getImageIndex() == 0) {
                        r.setImage(this.assets.get(11));
                        r.setImageIndex(1);
                    }
                    else if (r.getImageIndex() == 1) {
                        r.setImage(this.assets.get(12));
                        r.setImageIndex(2);
                    }
                    else if (r.getImageIndex() == 2) {
                        r.setImage(this.assets.get(10));
                        r.setImageIndex(0);
                    }
                    if (r.getImageIndex() == 3) {
                        r.setImage(this.assets.get(5));
                        r.setImageIndex(4);
                    }
                    else if (r.getImageIndex() == 4) {
                        r.setImage(this.assets.get(6));
                        r.setImageIndex(5);
                    }
                    else if (r.getImageIndex() == 5) {
                        r.setImage(this.assets.get(4));
                        r.setImageIndex(3);
                    }
                    if (r.getImageIndex() == 6) {
                        r.setImage(this.assets.get(8));
                        r.setImageIndex(7);
                    }
                    else if (r.getImageIndex() == 7) {
                        r.setImage(this.assets.get(9));
                        r.setImageIndex(8);
                    }
                    else {
                        if (r.getImageIndex() != 8) {
                            continue;
                        }
                        r.setImage(this.assets.get(7));
                        r.setImageIndex(6);
                    }
                }
            }
        }
        if (this.bird.getImageIndex() == 0) {
            this.bird.setImage(this.assets.get(11));
            this.bird.setImageIndex(1);
        }
        else if (this.bird.getImageIndex() == 1) {
            this.bird.setImage(this.assets.get(12));
            this.bird.setImageIndex(2);
        }
        else if (this.bird.getImageIndex() == 2) {
            this.bird.setImage(this.assets.get(10));
            this.bird.setImageIndex(0);
        }
    }
    
    public void drawMenu(final Graphics2D g2) {
        if (this.background == 0) {
            g2.drawImage(this.assets.get(13), 0, 0, null);
            g2.drawImage(this.assets.get(14), 0 - this.offset, 512, 2688, 122, null);
        }
        else {
            g2.drawImage(this.assets.get(44), 0, 0, null);
            g2.drawImage(this.assets.get(52), 0 - this.offset, 512, 2688, 122, null);
        }
        g2.drawImage(this.bird.getImage(), this.bird.getX(), this.bird.getY(), null);
        g2.drawImage(this.assets.get(34), 315, 200, 200, 50, null);
        g2.drawImage(this.assets.get(28), 500, 422, 150, 90, null);
        g2.drawImage(this.assets.get(29), 200, 422, 150, 90, null);
    }
    
    public void drawPlayer(final Graphics2D g2) {
        if (this.background == 0) {
            g2.drawImage(this.assets.get(13), 0, 0, null);
        }
        else {
            g2.drawImage(this.assets.get(44), 0, 0, null);
        }
        if (this.background == 0) {
            for (final Pipe e : this.pipeManager.getTopPipes()) {
                g2.drawImage(e.getImage(), e.getX(), e.getY(), null);
            }
            for (final Pipe e : this.pipeManager.getBottomPipes()) {
                g2.drawImage(e.getImage(), e.getX(), e.getY(), null);
            }
        }
        else {
            for (final Pipe e : this.pipeManager.getTopPipes()) {
                g2.drawImage(this.assets.get(45), e.getX(), e.getY(), null);
            }
            for (final Pipe e : this.pipeManager.getBottomPipes()) {
                g2.drawImage(this.assets.get(46), e.getX(), e.getY(), null);
            }
        }
        this.score.updateScoreManager(this.scoreManager);
        this.score.drawScore(g2, 350, 50, true);
        if (!this.playerStarted) {
            g2.drawImage(this.assets.get(36), 330, 150, 184, 50, null);
            g2.drawImage(this.assets.get(47), 335, 350, 171, 90, null);
        }
        if (this.background == 0) {
            g2.drawImage(this.assets.get(14), 0 - this.offset, 512, 2688, 122, null);
        }
        else {
            g2.drawImage(this.assets.get(52), 0 - this.offset, 512, 2688, 122, null);
        }
        g2.drawImage(this.bird.rotateBy(), this.bird.getX(), this.bird.getY(), null);
    }
    
    public void drawAI(final Graphics2D g2) {
        if (this.background == 0) {
            g2.drawImage(this.assets.get(0), 0, 0, null);
        }
        else {
            g2.drawImage(this.assets.get(43), 0, 0, null);
        }
        if (this.background == 0) {
            for (final Pipe e : this.pipeManager.getTopPipes()) {
                g2.drawImage(e.getImage(), e.getX(), e.getY(), null);
            }
            for (final Pipe e : this.pipeManager.getBottomPipes()) {
                g2.drawImage(e.getImage(), e.getX(), e.getY(), null);
            }
        }
        else {
            for (final Pipe e : this.pipeManager.getTopPipes()) {
                g2.drawImage(this.assets.get(45), e.getX(), e.getY(), null);
            }
            for (final Pipe e : this.pipeManager.getBottomPipes()) {
                g2.drawImage(this.assets.get(46), e.getX(), e.getY(), null);
            }
        }
        if (this.background == 0) {
            g2.drawImage(this.assets.get(1), 0 - this.offset, 512, 2688, 122, null);
        }
        else {
            g2.drawImage(this.assets.get(52), 0 - this.offset, 512, 2688, 122, null);
        }
        g2.drawString("Delay", 30, 563);
        g2.drawString("Pipe Gap", 10, 589);
        g2.drawString("Gen Size", 10, 612);
        g2.drawString("Mutation", 240, 563);
        g2.drawString("Crossover", 233, 589);
        g2.drawString("Frequency", 233, 612);
        g2.drawImage(this.assets.get(53), 450, 550, null);
        g2.drawImage(this.assets.get(38), 450, 590, null);
        g2.setStroke(new BasicStroke(1.0f));
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(566, 0, 300, 700);
        g2.drawRect(566, 0, 300, 700);
        for (int i = 0; i < this.ga.getPopulation().size(); ++i) {
            if (this.ga.getPopulation().get(i).getScore() > this.aiHighScore) {
                this.aiHighScore = this.ga.getPopulation().get(i).getScore();
            }
            if (this.ga.getPopulation().get(i).getBrain().isActive()) {
                g2.drawImage(this.ga.getPopulation().get(i).getImage(), 585, i * 58 + 60 - this.adjustment, null);
                g2.drawImage(this.ga.getPopulation().get(i).getImage(), this.ga.getPopulation().get(i).getX(), this.ga.getPopulation().get(i).getY(), null);
            }
            else {
                g2.drawImage(this.ga.getPopulation().get(i).getImage(), 585, i * 58 + 60 - this.adjustment, null);
                g2.drawImage(this.assets.get(48), 573, i * 58 + 48 - this.adjustment, 50, 50, null);
            }
            g2.setFont(new Font("Arial", 1, 15));
            g2.setColor(Color.BLACK);
            g2.drawString("Fitness:", 680, i * 58 + 74 - this.adjustment);
            g2.drawString("Score:", 690, i * 58 + 89 - this.adjustment);
            if (this.ga.getPopulation().get(i).getBrain().isActive()) {
                g2.setColor(Color.GREEN.darker());
                g2.drawString(new StringBuilder(String.valueOf((int)this.ga.getPopulation().get(i).getBrain().getFitness())).toString(), 740, i * 58 + 74 - this.adjustment);
                g2.drawString(new StringBuilder(String.valueOf(this.ga.getPopulation().get(i).getScore())).toString(), 740, i * 58 + 90 - this.adjustment);
            }
            else {
                g2.setColor(Color.RED);
                g2.drawString(new StringBuilder(String.valueOf((int)this.ga.getPopulation().get(i).getBrain().getFitness())).toString(), 740, i * 58 + 74 - this.adjustment);
                g2.drawString(new StringBuilder(String.valueOf(this.ga.getPopulation().get(i).getScore())).toString(), 740, i * 58 + 90 - this.adjustment);
            }
            g2.setColor(Color.black);
            g2.drawString(String.valueOf(i + 1) + " ", 625, i * 58 + 90 - this.adjustment);
            this.score.drawScore(g2, 240, 50, false);
        }
        g2.setColor(Color.GRAY);
        g2.fillRect(571, 5, 290, 47);
        g2.drawRect(571, 5, 290, 47);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", 1, 17));
        g2.drawString("BIRDS: " + this.ga.numberOfAliveBirds(), 580, 25);
        g2.drawString("GEN: " + this.generationNumber, 690, 25);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", 1, 10));
        g2.drawString("Best Gen: " + this.bestGeneration, 580, 40);
        g2.drawString("Avg. Fitness: " + String.format("%.2f", this.bestGenerationFitness), 580, 50);
        g2.drawString("Alive: " + String.format("%02d", this.minuteTimer) + ":" + String.format("%02d", this.secondTimer), 690, 40);
        g2.drawString("Highscore: " + this.aiHighScore, 690, 50);
        this.drawBars(g2);
    }
    
    public void drawEnd(final Graphics2D g2) {
        if (this.background == 0) {
            g2.drawImage(this.assets.get(13), 0, 0, null);
        }
        else {
            g2.drawImage(this.assets.get(44), 0, 0, null);
        }
        if (this.background == 0) {
            for (final Pipe e : this.pipeManager.getTopPipes()) {
                g2.drawImage(e.getImage(), e.getX(), e.getY(), null);
            }
            for (final Pipe e : this.pipeManager.getBottomPipes()) {
                g2.drawImage(e.getImage(), e.getX(), e.getY(), null);
            }
        }
        else {
            for (final Pipe e : this.pipeManager.getTopPipes()) {
                g2.drawImage(this.assets.get(45), e.getX(), e.getY(), null);
            }
            for (final Pipe e : this.pipeManager.getBottomPipes()) {
                g2.drawImage(this.assets.get(46), e.getX(), e.getY(), null);
            }
        }
        if (this.background == 0) {
            g2.drawImage(this.assets.get(14), 0 - this.offset, 512, 2688, 122, null);
        }
        else {
            g2.drawImage(this.assets.get(52), 0 - this.offset, 512, 2688, 122, null);
        }
        g2.drawImage(this.assets.get(35), this.endX + 73, 100, null);
        g2.drawImage(this.assets.get(41), this.endX, 200, 339, 174, null);
        this.score.updateScoreManager(this.scoreManager);
        this.score.drawMiniScore(g2, this.endX + 213, 250, 20, 30);
        this.score.drawMiniHighScore(g2, this.endX + 213, 320, 20, 30);
        if (this.newHighScore) {
            g2.drawImage(this.assets.get(39), this.endX + 211, 288, 40, 20, null);
        }
        if (this.currentScore > 9) {
            g2.drawImage(this.assets.get(30), this.endX + 33, 258, 75, 75, null);
        }
        if (this.currentScore > 19) {
            g2.drawImage(this.assets.get(31), this.endX + 33, 258, 75, 75, null);
        }
        if (this.currentScore > 29) {
            g2.drawImage(this.assets.get(32), this.endX + 33, 258, 75, 75, null);
        }
        if (this.currentScore > 39) {
            g2.drawImage(this.assets.get(33), this.endX + 33, 258, 75, 75, null);
        }
        g2.drawImage(this.assets.get(40), this.endX + 23, 400, 120, 42, null);
        g2.drawImage(this.assets.get(38), this.endX + 198, 400, 120, 42, null);
        g2.drawImage(this.bird.rotateBy(), this.bird.getX(), this.bird.getY(), null);
    }
    
    public void drawPause(final Graphics2D g2) {
        this.drawPlayer(g2);
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, Window.getWIDTH(), Window.getHEIGHT());
        g2.drawImage(this.assets.get(38), 350, 200, 160, 56, null);
        g2.drawImage(this.assets.get(40), 350, 300, 160, 56, null);
    }
    
    public void drawAIPause(final Graphics2D g2) {
        this.drawAI(g2);
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, Window.getWIDTH(), Window.getHEIGHT());
        g2.drawImage(this.assets.get(38), 220, 200, 160, 56, null);
        g2.drawImage(this.assets.get(40), 220, 300, 160, 56, null);
    }
    
    public void drawBars(final Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.drawRect(566, 0, 5, 700);
        g2.fillRect(566, 0, 5, 700);
        g2.drawRect(566, 0, 300, 5);
        g2.fillRect(566, 0, 300, 5);
        g2.drawRect(794, 0, 5, 700);
        g2.fillRect(794, 0, 5, 700);
        g2.drawRect(566, 625, 300, 5);
        g2.fillRect(566, 625, 300, 5);
        g2.drawRect(566, 52, 300, 5);
        g2.fillRect(566, 52, 300, 5);
    }
    
    public void updatePlayer() {
        if (this.endX != 173) {
            this.endX = -173;
        }
        if (this.currentScore > this.highScore) {
            this.highScore = this.currentScore;
            this.newHighScore = true;
            this.saveData();
        }
        if (this.bird.getX() != 400) {
            this.bird.setX(400);
        }
        this.pipeManager.setPipeCounter(this.pipeManager.getPipeCounter() + 1);
        if (this.playerStarted && !this.dead) {
            this.pipeManager.updatePipeLocation();
            this.pipeManager.generatePipes();
        }
        this.pipeManager.setTick(this.pipeManager.getTick() + 1);
        if (this.pipeManager.getPipeCounter() % 10 == 0) {
            if (this.imageUpdate == 3L) {
                this.imageUpdate = 0L;
            }
            ++this.imageUpdate;
            this.updateBirdImage();
        }
        for (final Pipe r : this.pipeManager.getTopPipes()) {
            if (r.getBounds().intersects(this.bird.getBounds())) {
                this.loadData();
                this.calculateScore();
                this.dead = true;
                if (!this.hit) {
                    Audio.playSound("/Audio/hit.wav");
                }
                this.hit = true;
            }
        }
        for (final Pipe r : this.pipeManager.getBottomPipes()) {
            if (r.getBounds().intersects(this.bird.getBounds())) {
                this.loadData();
                this.calculateScore();
                this.dead = true;
                if (!this.hit) {
                    Audio.playSound("/Audio/hit.wav");
                }
                this.hit = true;
            }
        }
        if (this.bird.getY() < 10) {
            this.loadData();
            this.calculateScore();
            this.dead = true;
            if (!this.hit) {
                Audio.playSound("/Audio/hit.wav");
            }
            this.hit = true;
        }
        if (this.bird.getY() > 480) {
            StateManager.setState(State.END);
            this.loadData();
            this.calculateScore();
            this.dead = true;
            if (!this.hit) {
                Audio.playSound("/Audio/hit.wav");
            }
            this.hit = true;
            this.reset();
        }
        if (this.reset) {
            StateManager.setState(State.END);
            this.loadData();
            this.reset();
            this.reset = false;
            this.dead = false;
            this.calculateScore();
        }
        if (this.playerStarted && this.tick % 2L == 0L) {
            this.bird.update();
        }
        for (final Pipe r : this.pipeManager.getTopPipes()) {
            if (r.getX() == this.bird.getX()) {
                ++this.currentScore;
                Audio.playSound("/Audio/score.wav");
                if (this.scoreManager[0][3] < 9) {
                    final int[] array = this.scoreManager[0];
                    final int n = 3;
                    ++array[n];
                }
                else {
                    this.scoreManager[0][3] = 0;
                    if (this.scoreManager[0][2] < 9) {
                        final int[] array2 = this.scoreManager[0];
                        final int n2 = 2;
                        ++array2[n2];
                    }
                    else {
                        this.scoreManager[0][2] = 0;
                        final int[] array3 = this.scoreManager[0];
                        final int n3 = 3;
                        ++array3[n3];
                    }
                }
            }
        }
    }
    
    public void updateAI() {
        this.changedValues();
        this.sound = false;
        ++this.addUpCounter;
        if (this.secondTimer == 59) {
            this.secondTimer = 0;
            ++this.minuteTimer;
        }
        if (this.addUpCounter % 60 == 0) {
            ++this.secondTimer;
        }
        if (this.thisGenerationFitness > this.bestGenerationFitness) {
            this.bestGenerationFitness = this.thisGenerationFitness;
            this.bestGeneration = this.generationNumber;
        }
        this.pipeManager.updatePipeLocation();
        this.pipeManager.generatePipes();
        this.pipeManager.setPipeCounter(this.pipeManager.getPipeCounter() + 1);
        ++this.tick;
        ++this.birdOffset;
        if (this.pipeManager.getPipeCounter() % 10 == 0) {
            if (this.imageUpdate == 3L) {
                this.imageUpdate = 0L;
            }
            ++this.imageUpdate;
            this.updateBirdImage();
        }
        if (this.ga.isActive()) {
            for (int i = 0; i < this.pipeManager.getBottomPipes().size(); ++i) {
                if (this.pipeManager.getBottomPipes().get(i).getX() == 288) {
                    if (this.scoreManager[1][3] < 9) {
                        final int[] array = this.scoreManager[1];
                        final int n = 3;
                        ++array[n];
                    }
                    else if (this.scoreManager[1][3] == 9) {
                        this.scoreManager[1][3] = 0;
                        if (this.scoreManager[1][2] < 9) {
                            final int[] array2 = this.scoreManager[1];
                            final int n2 = 2;
                            ++array2[n2];
                        }
                        else if (this.scoreManager[1][2] == 9) {
                            this.scoreManager[1][2] = 0;
                            if (this.scoreManager[1][1] < 9) {
                                final int[] array3 = this.scoreManager[1];
                                final int n3 = 1;
                                ++array3[n3];
                            }
                            else if (this.scoreManager[1][1] == 9) {
                                final int[] array4 = this.scoreManager[1];
                                final int n4 = 0;
                                ++array4[n4];
                                this.scoreManager[1][1] = 0;
                                this.scoreManager[1][2] = 0;
                                this.scoreManager[1][3] = 0;
                            }
                        }
                    }
                    Audio.playSound("/Audio/score.wav");
                }
            }
            int numberOfAlive = 0;
            int aliveIndex = 0;
            for (int j = 0; j < this.ga.getPopulation().size(); ++j) {
                if (this.ga.getPopulation().get(j).getBrain().isActive()) {
                    ++numberOfAlive;
                    aliveIndex = j;
                }
            }
            this.thisGenerationFitness = this.ga.getAverageFitness();
            this.ga.setTotalDistance(this.tick);
            this.fitnessCounter += 0.25;
            for (int j = 0; j < this.ga.getPopulation().size(); ++j) {
                for (int k = 0; k < this.pipeManager.getTopPipes().size(); ++k) {
                    if (this.ga.getPopulation().get(j).getBounds().intersects(this.pipeManager.getTopPipes().get(k).getBounds())) {
                        this.ga.getPopulation().get(j).getBrain().disable();
                    }
                }
                for (int k = 0; k < this.pipeManager.getBottomPipes().size(); ++k) {
                    if (this.ga.getPopulation().get(j).getBounds().intersects(this.pipeManager.getBottomPipes().get(k).getBounds())) {
                        this.ga.getPopulation().get(j).getBrain().disable();
                    }
                }
                if (this.ga.getPopulation().get(j).getY() > 480 || this.ga.getPopulation().get(j).getY() < 10) {
                    this.ga.getPopulation().get(j).getBrain().disable();
                }
                if (this.ga.getPopulation().get(j).getX() != 288) {
                    this.ga.getPopulation().get(j).setX(288);
                }
                this.ga.getPopulation().get(j).update();
                this.ga.getPopulation().get(j).getBrain().setInput(new double[] { this.pipeManager.closestPipe().getX() + 52 - this.ga.getPopulation().get(j).getX(), this.pipeManager.closestPipe().getY() - 50 - this.ga.getPopulation().get(j).getY() });
                if (this.ga.getPopulation().get(j).getBrain().think()[0] > 0.5) {
                    this.ga.getPopulation().get(j).jump();
                }
                if (this.ga.getPopulation().get(j).getBrain().isActive()) {
                    for (int k = 0; k < this.pipeManager.getBottomPipes().size(); ++k) {
                        if (this.ga.getPopulation().get(j).getX() == this.pipeManager.getBottomPipes().get(k).getX()) {
                            this.ga.getPopulation().get(j).incrementScore();
                        }
                    }
                }
                if (this.ga.getPopulation().get(j).getBrain().isActive()) {
                    this.ga.getPopulation().get(j).getBrain().setFitness(this.fitnessCounter);
                }
                if (this.ga.getPopulation().get(j).getBrain().getFitness() == this.fitnessCounter || this.ga.getPopulation().get(j).getBrain().getFitness() > this.fitnessCounter) {
                    this.ga.getPopulation().get(j).getBrain().setFitness(this.fitnessCounter - 0.25);
                }
            }
            if (numberOfAlive == 1) {
                final double topFitness = this.ga.getPopulation().get(aliveIndex).getBrain().getFitness();
                double totalFitness = 0.0;
                for (int l = 0; l < this.ga.getPopulation().size(); ++l) {
                    if (l != aliveIndex) {
                        totalFitness += this.ga.getPopulation().get(l).getBrain().getFitness();
                    }
                }
                if (topFitness > totalFitness) {
                    this.scoreManager[1][0] = 0;
                    this.scoreManager[1][1] = 0;
                    this.scoreManager[1][2] = 0;
                    this.scoreManager[1][3] = 0;
                    this.previousGenerationFitness = this.ga.getAverageFitness();
                    this.ga.newGeneration();
                    this.ga.setTotalDistance(0L);
                    ++this.generationNumber;
                    this.tick = 0L;
                    this.fitnessCounter = 0.0;
                    this.reset();
                }
            }
        }
        else {
            this.scoreManager[1][0] = 0;
            this.scoreManager[1][1] = 0;
            this.scoreManager[1][2] = 0;
            this.scoreManager[1][3] = 0;
            this.previousGenerationFitness = this.ga.getAverageFitness();
            this.ga.newGeneration();
            while (this.sizeChange != 0) {
                if (this.sizeChange > 0) {
                    final int r = (int)(Math.random() * 3.0);
                    Bird a = null;
                    if (r == 0) {
                        a = new Bird(this.assets.get(5), 288, (int)(Math.random() * 100.0) + 250);
                        a.setImageIndex(3);
                    }
                    else if (r == 1) {
                        a = new Bird(this.assets.get(8), 288, (int)(Math.random() * 100.0) + 250);
                        a.setImageIndex(6);
                    }
                    else {
                        a = new Bird(this.assets.get(11), 288, (int)(Math.random() * 100.0) + 250);
                        a.setImageIndex(0);
                    }
                    a.createBrain(2, 6, 1);
                    this.ga.addBird(a);
                    this.ga.setPopulationSize(this.ga.getPopulationSize() + 1);
                    --this.sizeChange;
                    this.updateScrollBar();
                }
                else {
                    if (this.sizeChange >= 0) {
                        continue;
                    }
                    this.ga.removeBird(this.ga.getPopulation().size() - 1);
                    this.ga.setPopulationSize(this.ga.getPopulationSize() - 1);
                    this.updateScrollBar();
                    ++this.sizeChange;
                }
            }
            this.ga.setTotalDistance(0L);
            ++this.generationNumber;
            this.tick = 0L;
            this.fitnessCounter = 0.0;
            this.reset();
        }
    }
    
    public void updateEnd() {
        if (this.endX < 227) {
            if (this.endX == -163) {
                Audio.playSound("/Audio/swoosh.wav");
            }
            this.endX += 10;
        }
        if (this.bird.getRotation() != 50.0) {
            this.bird.setRotation(50.0);
        }
    }
    
    public void createDirectory() {
        String path = String.valueOf(System.getProperty("user.home")) + File.separator + "Documents";
        path = String.valueOf(path) + File.separator + "FlappyBird";
        final File customDir = new File(path);
        this.directoryPath = path;
        if (customDir.exists()) {
            System.out.println(customDir + " already exsists.");
        }
        else if (customDir.mkdirs()) {
            System.out.println(customDir + " was created");
        }
        else {
            System.out.println(customDir + " was not created");
        }
        final File file = new File(String.valueOf(path) + File.separator + "1.save");
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Highscore file created");
        }
        else {
            System.out.println("Highscore file already exists");
        }
    }
    
    public void loadData() {
        try {
            SaveData data = null;
			try {
				data = (SaveData)ResourceManager.load(String.valueOf(this.directoryPath) + File.separator + "1.save");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            this.highScore = data.getHighscore();
        }
        catch (Exception e) {
            System.out.println("Couldn't load save data: " + e.getMessage());
            this.highScore = 0;
        }
    }
    
    public void saveData() {
        final SaveData data = new SaveData();
        data.setHighScore(this.highScore);
        try {
            try {
				ResourceManager.save(data, String.valueOf(this.directoryPath) + File.separator + "1.save");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        catch (Exception e) {
            System.out.println("Couldn't save: " + e.getMessage());
        }
    }
    
    public void randomize() {
        for (int i = 0; i < this.ga.getPopulation().size(); ++i) {
            final int random = (int)(Math.random() * (this.ga.getPopulation().size() - i)) + i;
            final int index = this.ga.getPopulation().get(random).getImageIndex();
            final Image image = this.ga.getPopulation().get(random).getImage();
            this.ga.getPopulation().get(random).setImageIndex(this.ga.getPopulation().get(i).getImageIndex());
            this.ga.getPopulation().get(random).setImage(this.ga.getPopulation().get(random).getImage());
            this.ga.getPopulation().get(i).setImageIndex(index);
            this.ga.getPopulation().get(i).setImage(image);
        }
    }
    
    public void reset() {
        this.reset = false;
        this.dead = false;
        this.hit = false;
        this.tick = 0L;
        this.pipeManager.setPipeCounter(0);
        this.offset = 0;
        if (StateManager.getState() != State.PLAYER && StateManager.getState() != State.END) {
            for (final Pipe r : this.pipeManager.getTopPipes()) {
                this.pipeManager.addJunkTopPipes(r);
            }
            for (final Pipe r : this.pipeManager.getBottomPipes()) {
                this.pipeManager.addJunkBottomPipes(r);
            }
            this.pipeManager.deletePipes();
        }
        this.playerStarted = false;
        this.pipeManager.setPipePipe(0);
        this.birdOffset = 0;
        this.bird.setRotation(0.0);
    }
    
    public void calculateScore() {
        if (this.highScore > 999) {
            int a = this.highScore;
            this.scoreManager[2][3] = a % 10;
            a /= 10;
            this.scoreManager[2][2] = a % 10;
            a /= 10;
            this.scoreManager[2][1] = a % 10;
            a /= 10;
            this.scoreManager[2][0] = a % 10;
        }
        else if (this.highScore > 99) {
            int a = this.highScore;
            this.scoreManager[2][3] = a % 10;
            a /= 10;
            this.scoreManager[2][2] = a % 10;
            a /= 10;
            this.scoreManager[2][1] = a % 10;
        }
        else if (this.highScore > 9) {
            int a = this.highScore;
            this.scoreManager[2][3] = a % 10;
            a /= 10;
            this.scoreManager[2][2] = a % 10;
        }
        else {
            final int a = this.highScore;
            this.scoreManager[2][3] = a % 10;
        }
    }
    
    public void changedValues() {
        Window.getSliders().get(0).addChangeListener(ChangeListener -> {
            if (Window.getSliders().get(0).getValue() == 1) {
                Engine.setScale(2.0);
                Motor.setScale(2.0);
                Motor.getLabels()[0].setText("Slow");
            }
            else if (Window.getSliders().get(0).getValue() == 2) {
                Engine.setScale(1.0);
                Motor.setScale(1.0);
                Motor.getLabels()[0].setText("Med");
            }
            else if (Window.getSliders().get(0).getValue() == 3) {
                Engine.setScale(0.5);
                Motor.setScale(0.5);
                Motor.getLabels()[0].setText("Fast");
            }
            return;
        });
        Window.getSliders().get(1).addChangeListener(ChangeListener -> {
            this.pipeManager.setPipeGap(Window.getSliders().get(1).getValue());
            Motor.getLabels()[1].setText(String.valueOf(Window.getSliders().get(1).getValue() - 1000));
            return;
        });
        Window.getSliders().get(2).addChangeListener(ChangeListener -> {
            this.populationSize = Window.getSliders().get(2).getValue();
            while (this.populationSize > this.ga.getPopulation().size() + this.sizeChange) {
                ++this.sizeChange;
            }
            while (this.populationSize < this.ga.getPopulation().size() + this.sizeChange) {
                --this.sizeChange;
            }
            System.out.println(String.valueOf(this.sizeChange) + " changed");
            Motor.getLabels()[2].setText(String.valueOf(Window.getSliders().get(2).getValue()));
            return;
        });
        Window.getSliders().get(3).addChangeListener(ChangeListener -> {
            this.ga.setCrossoverRate(Window.getSliders().get(2).getValue());
            Motor.getLabels()[3].setText(String.valueOf(Window.getSliders().get(3).getValue()));
            return;
        });
        Window.getSliders().get(4).addChangeListener(ChangeListener -> {
            this.ga.setMutationRate(Window.getSliders().get(2).getValue());
            Motor.getLabels()[4].setText(String.valueOf(Window.getSliders().get(4).getValue()));
            return;
        });
        Window.getSliders().get(5).addChangeListener(ChangeListener -> {
            this.pipeManager.setPipeFrequency(Window.getSliders().get(5).getValue());
            Motor.getLabels()[5].setText(String.valueOf(Window.getSliders().get(5).getValue()));
        });
    }
    
    public void updateScrollBar() {
        final int pop = this.ga.getPopulation().size() - 10;
        if (pop != 0) {
            Window.getScrollBar().setVisible(true);
            final int max = 61 * pop - (pop - 1) * 3;
            Window.getScrollBar().getModel().setExtent(max / 10);
            Window.getScrollBar().setMaximum(61 * pop - (pop - 1) * 3 + Window.getScrollBar().getModel().getExtent());
            Window.getScrollBar().setOpaque(false);
        }
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
        final int k = e.getKeyCode();
        if (StateManager.getState() == State.PLAYER) {
            if (k == 32) {
                this.playerStarted = true;
                if (!this.flap && !this.dead) {
                    this.bird.jump();
                    Audio.playSound("/Audio/flap.wav");
                }
                this.flap = true;
            }
            else if (k == 80 || k == 27) {
                StateManager.setState(State.PAUSE);
            }
        }
        else if (StateManager.getState() == State.PAUSE && (k == 80 || k == 27)) {
            StateManager.setState(State.PLAYER);
        }
        else if (StateManager.getState() == State.AI) {
            if (k == 82) {
                this.scoreManager[1][0] = 0;
                this.scoreManager[1][1] = 0;
                this.scoreManager[1][2] = 0;
                this.scoreManager[1][3] = 0;
                this.previousGenerationFitness = this.ga.getAverageFitness();
                this.ga.newGeneration();
                this.ga.setTotalDistance(0L);
                ++this.generationNumber;
                this.tick = 0L;
                this.fitnessCounter = 0.0;
                this.reset();
            }
            else if (k == 80 || k == 27) {
                StateManager.setState(State.AIPAUSE);
            }
        }
        else if (StateManager.getState() == State.AIPAUSE && (k == 80 || k == 27)) {
            StateManager.setState(State.AI);
        }
        else if (StateManager.getState() == State.END && k == 10) {
            Audio.playSound("/Audio/press.wav");
            this.reset();
            this.scoreManager[0][0] = 0;
            this.scoreManager[0][1] = 0;
            this.scoreManager[0][2] = 0;
            this.scoreManager[0][3] = 0;
            this.currentScore = 0;
            this.newHighScore = false;
            for (final Pipe r : this.pipeManager.getTopPipes()) {
                this.pipeManager.addJunkTopPipes(r);
            }
            for (final Pipe r : this.pipeManager.getBottomPipes()) {
                this.pipeManager.addJunkBottomPipes(r);
            }
            this.pipeManager.deletePipes();
            this.bird.setLocation(400, 300);
            StateManager.setState(State.PLAYER);
        }
    }
    
    @Override
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        this.adjustment = e.getValue();
    }
    
    @Override
    public void mouseWheelMoved(final MouseWheelEvent e) {
        final int rotation = this.ga.getPopulation().size() / 2;
        if (StateManager.getState() == State.AI) {
            if (e.getWheelRotation() < 0 && Window.getScrollBar().getValue() > rotation) {
                Window.getScrollBar().setValue(Window.getScrollBar().getValue() - rotation);
            }
            else if (e.getWheelRotation() < 0 && Window.getScrollBar().getValue() <= rotation) {
                Window.getScrollBar().setValue(0);
            }
            else if (e.getWheelRotation() > 0 && Window.getScrollBar().getValue() < Window.getScrollBar().getMaximum() - rotation) {
                Window.getScrollBar().setValue(Window.getScrollBar().getValue() + rotation);
            }
            else if (e.getWheelRotation() > 0 && Window.getScrollBar().getValue() >= Window.getScrollBar().getMaximum() - rotation) {
                Window.getScrollBar().setValue(Window.getScrollBar().getMaximum());
            }
        }
    }
    
    @Override
    public void keyReleased(final KeyEvent e) {
        final int k = e.getKeyCode();
        if (k == 32) {
            this.flap = false;
        }
    }
    
    @Override
    public void mouseMoved(final MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
        final int mouse = e.getButton();
        if (StateManager.getState() == State.MENU) {
            if (mouse == 1) {
                if (this.mouseX > 203 && this.mouseX < 352 && this.mouseY > 448 && this.mouseY < 536) {
                    StateManager.setState(State.PLAYER);
                    for (final Pipe r : this.pipeManager.getTopPipes()) {
                        this.pipeManager.addJunkTopPipes(r);
                    }
                    for (final Pipe r : this.pipeManager.getBottomPipes()) {
                        this.pipeManager.addJunkBottomPipes(r);
                    }
                    this.pipeManager.deletePipes();
                    Audio.playSound("/Audio/press.wav");
                }
                if (this.mouseX > 505 && this.mouseX < 651 && this.mouseY > 448 && this.mouseY < 536) {
                    StateManager.setState(State.AI);
                    Audio.playSound("/Audio/press.wav");
                }
                if (this.mouseX > 404 && this.mouseX < 435 && this.mouseY > 326 && this.mouseY < 348) {
                    if (this.bird.getImageIndex() == 0) {
                        this.bird.setImage(this.assets.get(5));
                        this.bird.setImageIndex(3);
                    }
                    else if (this.bird.getImageIndex() == 3) {
                        this.bird.setImage(this.assets.get(8));
                        this.bird.setImageIndex(6);
                    }
                    else if (this.bird.getImageIndex() == 6) {
                        this.bird.setImage(this.assets.get(11));
                        this.bird.setImageIndex(0);
                    }
                    Audio.playSound("/Audio/press.wav");
                }
                if (this.mouseY < 540 && (this.mouseX < 203 || this.mouseX > 352 || this.mouseY < 448 || this.mouseY > 536) && (this.mouseX < 505 || this.mouseX > 651 || this.mouseY < 448 || this.mouseY > 536) && (this.mouseX < 404 || this.mouseX > 435 || this.mouseY < 326 || this.mouseY > 348)) {
                    if (this.background == 1) {
                        this.background = 0;
                    }
                    else {
                        this.background = 1;
                    }
                    Audio.playSound("/Audio/press.wav");
                }
            }
        }
        else if (StateManager.getState() == State.END) {
            if (this.mouseX > 253 && this.mouseX < 373 && this.mouseY > 426 && this.mouseY < 467) {
                Audio.playSound("/Audio/press.wav");
                this.reset();
                this.scoreManager[0][0] = 0;
                this.scoreManager[0][1] = 0;
                this.scoreManager[0][2] = 0;
                this.scoreManager[0][3] = 0;
                this.currentScore = 0;
                this.newHighScore = false;
                for (final Pipe r : this.pipeManager.getTopPipes()) {
                    this.pipeManager.addJunkTopPipes(r);
                }
                for (final Pipe r : this.pipeManager.getBottomPipes()) {
                    this.pipeManager.addJunkBottomPipes(r);
                }
                this.pipeManager.deletePipes();
                this.bird.setLocation(400, 300);
                StateManager.setState(State.PLAYER);
            }
            else if (this.mouseX > 428 && this.mouseX < 547 && this.mouseY > 426 && this.mouseY < 467) {
                Audio.playSound("/Audio/press.wav");
                this.reset();
                this.scoreManager[0][0] = 0;
                this.scoreManager[0][1] = 0;
                this.scoreManager[0][2] = 0;
                this.scoreManager[0][2] = 0;
                this.currentScore = 0;
                this.newHighScore = false;
                for (final Pipe r : this.pipeManager.getTopPipes()) {
                    this.pipeManager.addJunkTopPipes(r);
                }
                for (final Pipe r : this.pipeManager.getBottomPipes()) {
                    this.pipeManager.addJunkBottomPipes(r);
                }
                this.pipeManager.deletePipes();
                this.bird.setLocation(400, 300);
                StateManager.setState(State.MENU);
            }
        }
        else if (StateManager.getState() == State.PAUSE) {
            if (this.mouseX > 358 && this.mouseX < 518 && this.mouseY > 231 && this.mouseY < 287) {
                StateManager.setState(State.MENU);
                for (final Pipe r : this.pipeManager.getTopPipes()) {
                    this.pipeManager.addJunkTopPipes(r);
                }
                for (final Pipe r : this.pipeManager.getBottomPipes()) {
                    this.pipeManager.addJunkBottomPipes(r);
                }
                this.pipeManager.deletePipes();
                Audio.playSound("/Audio/press.wav");
                this.reset();
                this.scoreManager[0][0] = 0;
                this.scoreManager[0][1] = 0;
                this.scoreManager[0][2] = 0;
                this.scoreManager[0][3] = 0;
                this.currentScore = 0;
                this.newHighScore = false;
                for (final Pipe r : this.pipeManager.getTopPipes()) {
                    this.pipeManager.addJunkTopPipes(r);
                }
                for (final Pipe r : this.pipeManager.getBottomPipes()) {
                    this.pipeManager.addJunkBottomPipes(r);
                }
                this.pipeManager.deletePipes();
                this.bird.setLocation(400, 300);
            }
            else if (this.mouseX > 358 && this.mouseX < 517 && this.mouseY > 331 && this.mouseY < 386) {
                StateManager.setState(State.PLAYER);
                Audio.playSound("/Audio/press.wav");
            }
        }
        else if (StateManager.getState() == State.AIPAUSE) {
            if (this.mouseX > 228 && this.mouseX < 338 && this.mouseY > 231 && this.mouseY < 287) {
                StateManager.setState(State.MENU);
                Audio.playSound("/Audio/press.wav");
                this.bird.setLocation(400, 300);
            }
            else if (this.mouseX > 228 && this.mouseX < 388 && this.mouseY > 331 && this.mouseY < 387) {
                StateManager.setState(State.AI);
                Audio.playSound("/Audio/press.wav");
            }
        }
        else if (StateManager.getState() == State.AI) {
            if (this.mouseX > 458 && this.mouseX < 538 && this.mouseY > 571 && this.mouseY < 609) {
                Audio.playSound("/Audio/press.wav");
                Window.getSliders().get(0).setValue(2);
                Window.getSliders().get(1).setValue(1060);
                Window.getSliders().get(2).setValue(50);
                Window.getSliders().get(3).setValue(5);
                Window.getSliders().get(4).setValue(80);
                Window.getSliders().get(5).setValue(105);
                Motor.getLabels()[0].setText("Med");
                Motor.getLabels()[1].setText(new StringBuilder(String.valueOf(Window.getSliders().get(1).getValue() - 1000)).toString());
                Motor.getLabels()[2].setText(new StringBuilder(String.valueOf(Window.getSliders().get(2).getValue())).toString());
                Motor.getLabels()[3].setText(new StringBuilder(String.valueOf(Window.getSliders().get(3).getValue())).toString());
                Motor.getLabels()[4].setText(new StringBuilder(String.valueOf(Window.getSliders().get(4).getValue())).toString());
                Motor.getLabels()[5].setText(new StringBuilder(String.valueOf(Window.getSliders().get(5).getValue())).toString());
            }
            else if (this.mouseX > 458 && this.mouseX < 538 && this.mouseY > 621 && this.mouseY < 648) {
                StateManager.setState(State.MENU);
                Audio.playSound("/Audio/press.wav");
            }
        }
    }
    
    @Override
    public void mouseDragged(final MouseEvent e) {
    }
    
    @Override
    public void mouseClicked(final MouseEvent arg0) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent arg0) {
    }
    
    @Override
    public void mouseExited(final MouseEvent arg0) {
    }
    
    @Override
    public void mouseReleased(final MouseEvent arg0) {
    }
    
    @Override
    public void keyTyped(final KeyEvent e) {
    }
}
