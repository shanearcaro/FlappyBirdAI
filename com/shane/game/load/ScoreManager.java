package com.shane.game.load;

import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

public class ScoreManager
{
    private List<Image> assets;
    private int[][] scoreManager;
    
    public ScoreManager(final List<Image> assets, final int[][] scoreManager) {
        this.assets = assets;
        this.scoreManager = scoreManager;
    }
    
    public void updateScoreManager(final int[][] scoreManager) {
        this.scoreManager = scoreManager;
    }
    
    public void drawScore(final Graphics2D g2, final int x, final int y, final boolean value) {
        int c = 0;
        int r = 0;
        if (value) {
            switch (this.scoreManager[0][0]) {
                case 1: {
                    g2.drawImage(this.assets.get(16), x, y, null);
                    break;
                }
                case 2: {
                    g2.drawImage(this.assets.get(17), x, y, null);
                    break;
                }
                case 3: {
                    g2.drawImage(this.assets.get(18), x, y, null);
                    break;
                }
                case 4: {
                    g2.drawImage(this.assets.get(19), x, y, null);
                    break;
                }
                case 5: {
                    g2.drawImage(this.assets.get(20), x, y, null);
                    break;
                }
                case 6: {
                    g2.drawImage(this.assets.get(21), x, y, null);
                    break;
                }
                case 7: {
                    g2.drawImage(this.assets.get(22), x, y, null);
                    break;
                }
                case 8: {
                    g2.drawImage(this.assets.get(23), x, y, null);
                    break;
                }
                case 9: {
                    g2.drawImage(this.assets.get(24), x, y, null);
                    break;
                }
            }
            if (this.scoreManager[0][0] == 1) {
                r += this.assets.get(16).getWidth(null) - 2;
            }
            else {
                r += this.assets.get(17).getWidth(null) - 2;
            }
            switch (this.scoreManager[0][1]) {
                case 0: {
                    if (this.scoreManager[0][0] != 0) {
                        g2.drawImage(this.assets.get(15), x + r, y, null);
                        break;
                    }
                    break;
                }
                case 1: {
                    g2.drawImage(this.assets.get(16), x + r, y, null);
                    break;
                }
                case 2: {
                    g2.drawImage(this.assets.get(17), x + r, y, null);
                    break;
                }
                case 3: {
                    g2.drawImage(this.assets.get(18), x + r, y, null);
                    break;
                }
                case 4: {
                    g2.drawImage(this.assets.get(19), x + r, y, null);
                    break;
                }
                case 5: {
                    g2.drawImage(this.assets.get(20), x + r, y, null);
                    break;
                }
                case 6: {
                    g2.drawImage(this.assets.get(21), x + r, y, null);
                    break;
                }
                case 7: {
                    g2.drawImage(this.assets.get(22), x + r, y, null);
                    break;
                }
                case 8: {
                    g2.drawImage(this.assets.get(23), x + r, y, null);
                    break;
                }
                case 9: {
                    g2.drawImage(this.assets.get(24), x + r, y, null);
                    break;
                }
            }
            if (this.scoreManager[0][1] == 1) {
                r += this.assets.get(16).getWidth(null) - 2;
            }
            else {
                r += this.assets.get(17).getWidth(null) - 2;
            }
            switch (this.scoreManager[0][2]) {
                case 0: {
                    if (this.scoreManager[0][1] != 0 || this.scoreManager[0][0] != 0) {
                        g2.drawImage(this.assets.get(15), x + r, y, null);
                        break;
                    }
                    break;
                }
                case 1: {
                    g2.drawImage(this.assets.get(16), x + r, y, null);
                    break;
                }
                case 2: {
                    g2.drawImage(this.assets.get(17), x + r, y, null);
                    break;
                }
                case 3: {
                    g2.drawImage(this.assets.get(18), x + r, y, null);
                    break;
                }
                case 4: {
                    g2.drawImage(this.assets.get(19), x + r, y, null);
                    break;
                }
                case 5: {
                    g2.drawImage(this.assets.get(20), x + r, y, null);
                    break;
                }
                case 6: {
                    g2.drawImage(this.assets.get(21), x + r, y, null);
                    break;
                }
                case 7: {
                    g2.drawImage(this.assets.get(22), x + r, y, null);
                    break;
                }
                case 8: {
                    g2.drawImage(this.assets.get(23), x + r, y, null);
                    break;
                }
                case 9: {
                    g2.drawImage(this.assets.get(24), x + r, y, null);
                    break;
                }
            }
            if (this.scoreManager[0][2] == 1) {
                r += this.assets.get(16).getWidth(null) - 2;
            }
            else {
                r += this.assets.get(17).getWidth(null) - 2;
            }
            switch (this.scoreManager[0][3]) {
                case 0: {
                    g2.drawImage(this.assets.get(15), x + r, y, null);
                    break;
                }
                case 1: {
                    g2.drawImage(this.assets.get(16), x + r, y, null);
                    break;
                }
                case 2: {
                    g2.drawImage(this.assets.get(17), x + r, y, null);
                    break;
                }
                case 3: {
                    g2.drawImage(this.assets.get(18), x + r, y, null);
                    break;
                }
                case 4: {
                    g2.drawImage(this.assets.get(19), x + r, y, null);
                    break;
                }
                case 5: {
                    g2.drawImage(this.assets.get(20), x + r, y, null);
                    break;
                }
                case 6: {
                    g2.drawImage(this.assets.get(21), x + r, y, null);
                    break;
                }
                case 7: {
                    g2.drawImage(this.assets.get(22), x + r, y, null);
                    break;
                }
                case 8: {
                    g2.drawImage(this.assets.get(23), x + r, y, null);
                    break;
                }
                case 9: {
                    g2.drawImage(this.assets.get(24), x + r, y, null);
                    break;
                }
            }
        }
        else {
            switch (this.scoreManager[1][0]) {
                case 1: {
                    g2.drawImage(this.assets.get(16), x, y, null);
                    break;
                }
                case 2: {
                    g2.drawImage(this.assets.get(17), x, y, null);
                    break;
                }
                case 3: {
                    g2.drawImage(this.assets.get(18), x, y, null);
                    break;
                }
                case 4: {
                    g2.drawImage(this.assets.get(19), x, y, null);
                    break;
                }
                case 5: {
                    g2.drawImage(this.assets.get(20), x, y, null);
                    break;
                }
                case 6: {
                    g2.drawImage(this.assets.get(21), x, y, null);
                    break;
                }
                case 7: {
                    g2.drawImage(this.assets.get(22), x, y, null);
                    break;
                }
                case 8: {
                    g2.drawImage(this.assets.get(23), x, y, null);
                    break;
                }
                case 9: {
                    g2.drawImage(this.assets.get(24), x, y, null);
                    break;
                }
            }
            if (this.scoreManager[1][0] == 1) {
                c += this.assets.get(16).getWidth(null) - 2;
            }
            else {
                c += this.assets.get(17).getWidth(null) - 2;
            }
            switch (this.scoreManager[1][1]) {
                case 0: {
                    if (this.scoreManager[1][0] != 0) {
                        g2.drawImage(this.assets.get(15), x + c, y, null);
                        break;
                    }
                    break;
                }
                case 1: {
                    g2.drawImage(this.assets.get(16), x + c, y, null);
                    break;
                }
                case 2: {
                    g2.drawImage(this.assets.get(17), x + c, y, null);
                    break;
                }
                case 3: {
                    g2.drawImage(this.assets.get(18), x + c, y, null);
                    break;
                }
                case 4: {
                    g2.drawImage(this.assets.get(19), x + c, y, null);
                    break;
                }
                case 5: {
                    g2.drawImage(this.assets.get(20), x + c, y, null);
                    break;
                }
                case 6: {
                    g2.drawImage(this.assets.get(21), x + c, y, null);
                    break;
                }
                case 7: {
                    g2.drawImage(this.assets.get(22), x + c, y, null);
                    break;
                }
                case 8: {
                    g2.drawImage(this.assets.get(23), x + c, y, null);
                    break;
                }
                case 9: {
                    g2.drawImage(this.assets.get(24), x + c, y, null);
                    break;
                }
            }
            if (this.scoreManager[1][1] == 1) {
                c += this.assets.get(16).getWidth(null) - 2;
            }
            else {
                c += this.assets.get(17).getWidth(null) - 2;
            }
            switch (this.scoreManager[1][2]) {
                case 0: {
                    if (this.scoreManager[1][1] != 0 || this.scoreManager[1][0] != 0) {
                        g2.drawImage(this.assets.get(15), x + c, y, null);
                        break;
                    }
                    break;
                }
                case 1: {
                    g2.drawImage(this.assets.get(16), x + c, y, null);
                    break;
                }
                case 2: {
                    g2.drawImage(this.assets.get(17), x + c, y, null);
                    break;
                }
                case 3: {
                    g2.drawImage(this.assets.get(18), x + c, y, null);
                    break;
                }
                case 4: {
                    g2.drawImage(this.assets.get(19), x + c, y, null);
                    break;
                }
                case 5: {
                    g2.drawImage(this.assets.get(20), x + c, y, null);
                    break;
                }
                case 6: {
                    g2.drawImage(this.assets.get(21), x + c, y, null);
                    break;
                }
                case 7: {
                    g2.drawImage(this.assets.get(22), x + c, y, null);
                    break;
                }
                case 8: {
                    g2.drawImage(this.assets.get(23), x + c, y, null);
                    break;
                }
                case 9: {
                    g2.drawImage(this.assets.get(24), x + c, y, null);
                    break;
                }
            }
            if (this.scoreManager[1][2] == 1) {
                c += this.assets.get(16).getWidth(null) - 2;
            }
            else {
                c += this.assets.get(17).getWidth(null) - 2;
            }
            switch (this.scoreManager[1][3]) {
                case 0: {
                    g2.drawImage(this.assets.get(15), x + c, y, null);
                    break;
                }
                case 1: {
                    g2.drawImage(this.assets.get(16), x + c, y, null);
                    break;
                }
                case 2: {
                    g2.drawImage(this.assets.get(17), x + c, y, null);
                    break;
                }
                case 3: {
                    g2.drawImage(this.assets.get(18), x + c, y, null);
                    break;
                }
                case 4: {
                    g2.drawImage(this.assets.get(19), x + c, y, null);
                    break;
                }
                case 5: {
                    g2.drawImage(this.assets.get(20), x + c, y, null);
                    break;
                }
                case 6: {
                    g2.drawImage(this.assets.get(21), x + c, y, null);
                    break;
                }
                case 7: {
                    g2.drawImage(this.assets.get(22), x + c, y, null);
                    break;
                }
                case 8: {
                    g2.drawImage(this.assets.get(23), x + c, y, null);
                    break;
                }
                case 9: {
                    g2.drawImage(this.assets.get(24), x + c, y, null);
                    break;
                }
            }
        }
    }
    
    public void drawMiniScore(final Graphics2D g2, final int x, final int y, final int width, final int height) {
        int r = 0;
        switch (this.scoreManager[0][0]) {
            case 1: {
                g2.drawImage(this.assets.get(16), x, y, (int)(width / 1.5), height, null);
                break;
            }
            case 2: {
                g2.drawImage(this.assets.get(17), x, y, width, height, null);
                break;
            }
            case 3: {
                g2.drawImage(this.assets.get(18), x, y, width, height, null);
                break;
            }
            case 4: {
                g2.drawImage(this.assets.get(19), x, y, width, height, null);
                break;
            }
            case 5: {
                g2.drawImage(this.assets.get(20), x, y, width, height, null);
                break;
            }
            case 6: {
                g2.drawImage(this.assets.get(21), x, y, width, height, null);
                break;
            }
            case 7: {
                g2.drawImage(this.assets.get(22), x, y, width, height, null);
                break;
            }
            case 8: {
                g2.drawImage(this.assets.get(23), x, y, width, height, null);
                break;
            }
            case 9: {
                g2.drawImage(this.assets.get(24), x, y, width, height, null);
                break;
            }
        }
        if (this.scoreManager[0][0] == 1) {
            r += this.assets.get(16).getWidth(null) - 2;
        }
        else {
            r += this.assets.get(17).getWidth(null) - 2;
        }
        switch (this.scoreManager[0][1]) {
            case 0: {
                if (this.scoreManager[0][0] != 0) {
                    g2.drawImage(this.assets.get(15), x + r, y, width, height, null);
                    break;
                }
                break;
            }
            case 1: {
                g2.drawImage(this.assets.get(16), x + r, y, (int)(width / 1.5), height, null);
                break;
            }
            case 2: {
                g2.drawImage(this.assets.get(17), x + r, y, width, height, null);
                break;
            }
            case 3: {
                g2.drawImage(this.assets.get(18), x + r, y, width, height, null);
                break;
            }
            case 4: {
                g2.drawImage(this.assets.get(19), x + r, y, width, height, null);
                break;
            }
            case 5: {
                g2.drawImage(this.assets.get(20), x + r, y, width, height, null);
                break;
            }
            case 6: {
                g2.drawImage(this.assets.get(21), x + r, y, width, height, null);
                break;
            }
            case 7: {
                g2.drawImage(this.assets.get(22), x + r, y, width, height, null);
                break;
            }
            case 8: {
                g2.drawImage(this.assets.get(23), x + r, y, width, height, null);
                break;
            }
            case 9: {
                g2.drawImage(this.assets.get(24), x + r, y, width, height, null);
                break;
            }
        }
        if (this.scoreManager[0][1] == 1) {
            r += this.assets.get(16).getWidth(null) - 2;
        }
        else {
            r += this.assets.get(17).getWidth(null) - 2;
        }
        switch (this.scoreManager[0][2]) {
            case 0: {
                if (this.scoreManager[0][1] != 0 || this.scoreManager[0][0] != 0) {
                    g2.drawImage(this.assets.get(15), x + r, y, width, height, null);
                    break;
                }
                break;
            }
            case 1: {
                g2.drawImage(this.assets.get(16), x + r, y, (int)(width / 1.5), height, null);
                break;
            }
            case 2: {
                g2.drawImage(this.assets.get(17), x + r, y, width, height, null);
                break;
            }
            case 3: {
                g2.drawImage(this.assets.get(18), x + r, y, width, height, null);
                break;
            }
            case 4: {
                g2.drawImage(this.assets.get(19), x + r, y, width, height, null);
                break;
            }
            case 5: {
                g2.drawImage(this.assets.get(20), x + r, y, width, height, null);
                break;
            }
            case 6: {
                g2.drawImage(this.assets.get(21), x + r, y, width, height, null);
                break;
            }
            case 7: {
                g2.drawImage(this.assets.get(22), x + r, y, width, height, null);
                break;
            }
            case 8: {
                g2.drawImage(this.assets.get(23), x + r, y, width, height, null);
                break;
            }
            case 9: {
                g2.drawImage(this.assets.get(24), x + r, y, width, height, null);
                break;
            }
        }
        if (this.scoreManager[0][2] == 1) {
            r += this.assets.get(16).getWidth(null) - 2;
        }
        else {
            r += this.assets.get(17).getWidth(null) - 2;
        }
        switch (this.scoreManager[0][3]) {
            case 0: {
                g2.drawImage(this.assets.get(15), x + r, y, width, height, null);
                break;
            }
            case 1: {
                g2.drawImage(this.assets.get(16), x + r, y, (int)(width / 1.5), height, null);
                break;
            }
            case 2: {
                g2.drawImage(this.assets.get(17), x + r, y, width, height, null);
                break;
            }
            case 3: {
                g2.drawImage(this.assets.get(18), x + r, y, width, height, null);
                break;
            }
            case 4: {
                g2.drawImage(this.assets.get(19), x + r, y, width, height, null);
                break;
            }
            case 5: {
                g2.drawImage(this.assets.get(20), x + r, y, width, height, null);
                break;
            }
            case 6: {
                g2.drawImage(this.assets.get(21), x + r, y, width, height, null);
                break;
            }
            case 7: {
                g2.drawImage(this.assets.get(22), x + r, y, width, height, null);
                break;
            }
            case 8: {
                g2.drawImage(this.assets.get(23), x + r, y, width, height, null);
                break;
            }
            case 9: {
                g2.drawImage(this.assets.get(24), x + r, y, width, height, null);
                break;
            }
        }
    }
    
    public void drawMiniHighScore(final Graphics2D g2, final int x, final int y, final int width, final int height) {
        int r = 0;
        switch (this.scoreManager[2][0]) {
            case 1: {
                g2.drawImage(this.assets.get(16), x, y, (int)(width / 1.5), height, null);
                break;
            }
            case 2: {
                g2.drawImage(this.assets.get(17), x, y, width, height, null);
                break;
            }
            case 3: {
                g2.drawImage(this.assets.get(18), x, y, width, height, null);
                break;
            }
            case 4: {
                g2.drawImage(this.assets.get(19), x, y, width, height, null);
                break;
            }
            case 5: {
                g2.drawImage(this.assets.get(20), x, y, width, height, null);
                break;
            }
            case 6: {
                g2.drawImage(this.assets.get(21), x, y, width, height, null);
                break;
            }
            case 7: {
                g2.drawImage(this.assets.get(22), x, y, width, height, null);
                break;
            }
            case 8: {
                g2.drawImage(this.assets.get(23), x, y, width, height, null);
                break;
            }
            case 9: {
                g2.drawImage(this.assets.get(24), x, y, width, height, null);
                break;
            }
        }
        if (this.scoreManager[2][0] == 1) {
            r += this.assets.get(16).getWidth(null) - 2;
        }
        else {
            r += this.assets.get(17).getWidth(null) - 2;
        }
        switch (this.scoreManager[2][1]) {
            case 0: {
                if (this.scoreManager[2][0] != 0) {
                    g2.drawImage(this.assets.get(15), x + r, y, width, height, null);
                    break;
                }
                break;
            }
            case 1: {
                g2.drawImage(this.assets.get(16), x + r, y, (int)(width / 1.5), height, null);
                break;
            }
            case 2: {
                g2.drawImage(this.assets.get(17), x + r, y, width, height, null);
                break;
            }
            case 3: {
                g2.drawImage(this.assets.get(18), x + r, y, width, height, null);
                break;
            }
            case 4: {
                g2.drawImage(this.assets.get(19), x + r, y, width, height, null);
                break;
            }
            case 5: {
                g2.drawImage(this.assets.get(20), x + r, y, width, height, null);
                break;
            }
            case 6: {
                g2.drawImage(this.assets.get(21), x + r, y, width, height, null);
                break;
            }
            case 7: {
                g2.drawImage(this.assets.get(22), x + r, y, width, height, null);
                break;
            }
            case 8: {
                g2.drawImage(this.assets.get(23), x + r, y, width, height, null);
                break;
            }
            case 9: {
                g2.drawImage(this.assets.get(24), x + r, y, width, height, null);
                break;
            }
        }
        if (this.scoreManager[2][1] == 1) {
            r += this.assets.get(16).getWidth(null) - 2;
        }
        else {
            r += this.assets.get(17).getWidth(null) - 2;
        }
        switch (this.scoreManager[2][2]) {
            case 0: {
                if (this.scoreManager[2][1] != 0 || this.scoreManager[2][0] != 0) {
                    g2.drawImage(this.assets.get(15), x + r, y, width, height, null);
                    break;
                }
                break;
            }
            case 1: {
                g2.drawImage(this.assets.get(16), x + r, y, (int)(width / 1.5), height, null);
                break;
            }
            case 2: {
                g2.drawImage(this.assets.get(17), x + r, y, width, height, null);
                break;
            }
            case 3: {
                g2.drawImage(this.assets.get(18), x + r, y, width, height, null);
                break;
            }
            case 4: {
                g2.drawImage(this.assets.get(19), x + r, y, width, height, null);
                break;
            }
            case 5: {
                g2.drawImage(this.assets.get(20), x + r, y, width, height, null);
                break;
            }
            case 6: {
                g2.drawImage(this.assets.get(21), x + r, y, width, height, null);
                break;
            }
            case 7: {
                g2.drawImage(this.assets.get(22), x + r, y, width, height, null);
                break;
            }
            case 8: {
                g2.drawImage(this.assets.get(23), x + r, y, width, height, null);
                break;
            }
            case 9: {
                g2.drawImage(this.assets.get(24), x + r, y, width, height, null);
                break;
            }
        }
        if (this.scoreManager[2][2] == 1) {
            r += this.assets.get(16).getWidth(null) - 2;
        }
        else {
            r += this.assets.get(17).getWidth(null) - 2;
        }
        switch (this.scoreManager[2][3]) {
            case 0: {
                g2.drawImage(this.assets.get(15), x + r, y, width, height, null);
                break;
            }
            case 1: {
                g2.drawImage(this.assets.get(16), x + r, y, (int)(width / 1.5), height, null);
                break;
            }
            case 2: {
                g2.drawImage(this.assets.get(17), x + r, y, width, height, null);
                break;
            }
            case 3: {
                g2.drawImage(this.assets.get(18), x + r, y, width, height, null);
                break;
            }
            case 4: {
                g2.drawImage(this.assets.get(19), x + r, y, width, height, null);
                break;
            }
            case 5: {
                g2.drawImage(this.assets.get(20), x + r, y, width, height, null);
                break;
            }
            case 6: {
                g2.drawImage(this.assets.get(21), x + r, y, width, height, null);
                break;
            }
            case 7: {
                g2.drawImage(this.assets.get(22), x + r, y, width, height, null);
                break;
            }
            case 8: {
                g2.drawImage(this.assets.get(23), x + r, y, width, height, null);
                break;
            }
            case 9: {
                g2.drawImage(this.assets.get(24), x + r, y, width, height, null);
                break;
            }
        }
    }
}
