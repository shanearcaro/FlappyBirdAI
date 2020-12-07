package com.shane.game.util;

public enum State
{
    MENU("MENU", 0), 
    PLAYER("PLAYER", 1), 
    AIPAUSE("AIPAUSE", 2), 
    AI("AI", 3), 
    PAUSE("PAUSE", 4), 
    END("END", 5);
    
    private State(final String name, final int ordinal) {
    }
}
