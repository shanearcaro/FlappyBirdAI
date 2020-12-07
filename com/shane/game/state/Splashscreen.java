package com.shane.game.state;

import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.Icon;
import java.awt.Image;
import com.shane.game.load.ImageBank;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JWindow;

public class Splashscreen extends JWindow
{
    private static final long serialVersionUID = 1L;
    private BorderLayout layout;
    private JLabel imgLabel;
    private JPanel southPanel;
    private FlowLayout southFlow;
    private JProgressBar progressBar;
    private ImageIcon imgIcon;
    private ImageBank bank;
    
    public Splashscreen() {
        this.bank = new ImageBank();
        this.setAlwaysOnTop(true);
        this.imgIcon = new ImageIcon(this.bank.getAssets().get(55));
        this.layout = new BorderLayout();
        this.imgLabel = new JLabel();
        this.southPanel = new JPanel();
        this.southFlow = new FlowLayout();
        (this.progressBar = new JProgressBar()).setStringPainted(true);
        try {
            this.init();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    private void init() throws Exception {
        this.imgLabel.setIcon(this.imgIcon);
        this.getContentPane().setLayout(this.layout);
        this.southPanel.setLayout(this.southFlow);
        this.southPanel.setOpaque(false);
        this.getContentPane().add(this.imgLabel, "Center");
        this.getContentPane().add(this.southPanel, "South");
        this.southPanel.add(this.progressBar, null);
        this.progressBar.setOpaque(false);
        this.pack();
    }
    
    public void setMaxProgress(final int maxProgress) {
        this.progressBar.setMaximum(maxProgress);
    }
    
    public void setProgress(final int progress) {
        final float percentage = progress / (float)this.progressBar.getMaximum() * 100.0f;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Splashscreen.this.progressBar.setValue(progress);
                Splashscreen.this.progressBar.setString("Loading: " + (int)percentage + "%");
            }
        });
    }
}
