package main.game.gui;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class GUIInitializer {

    public JFrame setupFrame() {
        JFrame frame = new JFrame("Adventure Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Set background color for the entire frame content
        frame.getContentPane().setBackground(Color.DARK_GRAY); // Adjust the color as per your theme

        // Centers the frame on the screen
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public JTextPane setupDisplayArea() {
        JTextPane displayArea = new JTextPane();
        displayArea.setEditable(false);
        displayArea.setBackground(Color.BLACK);

        // Set up the StyledDocument and center alignment
        StyledDocument doc = displayArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setForeground(center, Color.WHITE); // Set text color to white
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        return displayArea;
    }

    public JPanel setupDisplayPanel(JPanel displayForRoom) {
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(displayForRoom, BorderLayout.CENTER);
        return displayPanel;
    }
}
