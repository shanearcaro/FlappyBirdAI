package com.shane.game.load;

import java.io.Serializable;

public class SaveData implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int highscore;
    
    public int getHighscore() {
        return this.highscore;
    }
    
    public void setHighScore(final int highscore) {
        this.highscore = highscore;
    }
}
