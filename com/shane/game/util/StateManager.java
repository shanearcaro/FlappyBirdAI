// 
// Decompiled by Procyon v0.5.36
// 

package com.shane.game.util;

public class StateManager
{
    private static State state;
    
    static {
        StateManager.state = State.MENU;
    }
    
    public static void setState(final State newState) {
        StateManager.state = newState;
    }
    
    public static State getState() {
        return StateManager.state;
    }
}
