package com.shane.game.load;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;

public class ImageLoader
{
    public Image loadImage(final String path) {
        Image img = null;
        try {
            img = ImageIO.read(this.getClass().getResourceAsStream(path));
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return img;
    }
}
