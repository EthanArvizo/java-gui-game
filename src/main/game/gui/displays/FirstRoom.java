package main.game.gui.displays;

import javax.swing.*;
import java.awt.*;

public class FirstRoom extends JPanel {
    public FirstRoom(){
        setPreferredSize(new Dimension(600,400));
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        // Draw the background for the first room
        g.setColor(Color.BLUE); // Example background color
        g.fillRect(0, 0, getWidth(), getHeight());
        // You can add more custom drawing code here

    }
}
