package com.ecommerce.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
    //class inheriting from Jpanel. this class was constructed in order to override JPanel's paintComponent, so that we set a custom background(with an image) instead of the default(black white one)
    final private Image backgroundImage;

    //constructor
    public BackgroundPanel(ImageIcon imageIcon, LayoutManager layout) {
        //call the jpanel mother class constructor
        
        super(layout);
        this.backgroundImage = imageIcon.getImage();
        // Set preferred size if necessary, to make sure it fits the image or desired dimensions
        setPreferredSize(new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
