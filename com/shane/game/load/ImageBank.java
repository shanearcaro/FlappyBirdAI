package com.shane.game.load;

import java.util.ArrayList;
import java.awt.Image;
import java.util.List;

public class ImageBank
{
    private List<Image> assets;
    private ImageLoader loader;
    
    public ImageBank() {
        this.assets = new ArrayList<Image>();
        this.loader = new ImageLoader();
        this.assets.add(this.loader.loadImage("/Background/brightBackground.png"));
        this.assets.add(this.loader.loadImage("/Background/base.png"));
        this.assets.add(this.loader.loadImage("/Pipe/greenpipedown.png"));
        this.assets.add(this.loader.loadImage("/Pipe/greenpipe.png"));
        this.assets.add(this.loader.loadImage("/Birds/bluebird-downflap.png"));
        this.assets.add(this.loader.loadImage("/Birds/bluebird-midflap.png"));
        this.assets.add(this.loader.loadImage("/Birds/bluebird-upflap.png"));
        this.assets.add(this.loader.loadImage("/Birds/redbird-downflap.png"));
        this.assets.add(this.loader.loadImage("/Birds/redbird-midflap.png"));
        this.assets.add(this.loader.loadImage("/Birds/redbird-upflap.png"));
        this.assets.add(this.loader.loadImage("/Birds/yellowbird-downflap.png"));
        this.assets.add(this.loader.loadImage("/Birds/yellowbird-midflap.png"));
        this.assets.add(this.loader.loadImage("/Birds/yellowbird-upflap.png"));
        this.assets.add(this.loader.loadImage("/Background/playerBackground.png"));
        this.assets.add(this.loader.loadImage("/Background/playerBase.png"));
        this.assets.add(this.loader.loadImage("/Numbers/0.png"));
        this.assets.add(this.loader.loadImage("/Numbers/1.png"));
        this.assets.add(this.loader.loadImage("/Numbers/2.png"));
        this.assets.add(this.loader.loadImage("/Numbers/3.png"));
        this.assets.add(this.loader.loadImage("/Numbers/4.png"));
        this.assets.add(this.loader.loadImage("/Numbers/5.png"));
        this.assets.add(this.loader.loadImage("/Numbers/6.png"));
        this.assets.add(this.loader.loadImage("/Numbers/7.png"));
        this.assets.add(this.loader.loadImage("/Numbers/8.png"));
        this.assets.add(this.loader.loadImage("/Numbers/9.png"));
        this.assets.add(this.loader.loadImage("/Birds/graybird-downflap.png"));
        this.assets.add(this.loader.loadImage("/Birds/graybird-midflap.png"));
        this.assets.add(this.loader.loadImage("/Birds/graybird-upflap.png"));
        this.assets.add(this.loader.loadImage("/Buttons/aiPlay.png"));
        this.assets.add(this.loader.loadImage("/Buttons/play.png"));
        this.assets.add(this.loader.loadImage("/Medals/bronze.png"));
        this.assets.add(this.loader.loadImage("/Medals/silver.png"));
        this.assets.add(this.loader.loadImage("/Medals/gold.png"));
        this.assets.add(this.loader.loadImage("/Medals/platinum.png"));
        this.assets.add(this.loader.loadImage("/Main/FlappyBird.png"));
        this.assets.add(this.loader.loadImage("/Main/gameover.png"));
        this.assets.add(this.loader.loadImage("/Main/GetReady.png"));
        this.assets.add(this.loader.loadImage("/Buttons/Load.png"));
        this.assets.add(this.loader.loadImage("/Buttons/menu.png"));
        this.assets.add(this.loader.loadImage("/Main/new.png"));
        this.assets.add(this.loader.loadImage("/Buttons/okay.png"));
        this.assets.add(this.loader.loadImage("/Main/score.png"));
        this.assets.add(this.loader.loadImage("/Main/Tap.png"));
        this.assets.add(this.loader.loadImage("/Background/darkBackground.png"));
        this.assets.add(this.loader.loadImage("/Background/playerBackgroundNight.png"));
        this.assets.add(this.loader.loadImage("/Pipe/redpipedown.png"));
        this.assets.add(this.loader.loadImage("/Pipe/redpipe.png"));
        this.assets.add(this.loader.loadImage("/Main/taptap.png"));
        this.assets.add(this.loader.loadImage("/Main/x.png"));
        this.assets.add(this.loader.loadImage("/Main/plus.png"));
        this.assets.add(this.loader.loadImage("/Main/minus.png"));
        this.assets.add(this.loader.loadImage("/Background/playerBaseNight.png"));
        this.assets.add(this.loader.loadImage("/Background/basenight.png"));
        this.assets.add(this.loader.loadImage("/Buttons/default.png"));
        this.assets.add(this.loader.loadImage("/Buttons/reset.png"));
        this.assets.add(this.loader.loadImage("/Background/splash.png"));
    }
    
    public List<Image> getAssets() {
        return this.assets;
    }
}
