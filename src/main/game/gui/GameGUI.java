package main.game.gui;

import main.game.Player;
import main.game.model.RoomManager;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
    // GUI components
    private JFrame frame;
    private JTextPane displayArea;
    private JButton northButton, southButton, eastButton, westButton;
    private Player player;
    private DisplayManager displayManager;
    private JPanel displayPanel;

    public GameGUI() {
        RoomManager roomManager = new RoomManager();
        player = new Player(roomManager.getInitialRoom());
        displayManager = new DisplayManager();
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Adventure Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create and configure the display area for room descriptions
        displayArea = new JTextPane();
        displayArea.setEditable(false);
        displayArea.setBackground(Color.BLACK);

        // Set up the StyledDocument and center alignment
        StyledDocument doc = displayArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setForeground(center, Color.WHITE); // Set text color to white
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        // Set the text after setting the alignment
        displayArea.setText(player.getCurrentRoom().getDescription());

        // Create a panel for direction buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));

        // Create direction buttons
        northButton = new JButton("Go North");
        southButton = new JButton("Go South");
        eastButton = new JButton("Go East");
        westButton = new JButton("Go West");
        // Add buttons to the panel
        buttonPanel.add(northButton);
        buttonPanel.add(southButton);
        buttonPanel.add(eastButton);
        buttonPanel.add(westButton);
        // Add action listeners to the buttons
        northButton.addActionListener(new DirectionButtonListener("north", player, displayArea, this));
        southButton.addActionListener(new DirectionButtonListener("south", player, displayArea, this));
        eastButton.addActionListener(new DirectionButtonListener("east", player, displayArea, this));
        westButton.addActionListener(new DirectionButtonListener("west", player, displayArea, this));

        // Create display panel and add the initial room's display
        displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(displayManager.getDisplayForRoom(player.getCurrentRoom()), BorderLayout.CENTER);

        // Add components to the frame
        frame.getContentPane().add(BorderLayout.CENTER, displayPanel); // Changed this line
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.getContentPane().add(BorderLayout.NORTH, new JScrollPane(displayArea));

        // Adjust frame size based on components
        frame.setLocationRelativeTo(null); // Centers the frame on the screen
        frame.setVisible(true);
    }

    // Getter for displayManager
    public DisplayManager getDisplayManager() {
        return displayManager;
    }

    // Method to update the display panel
    public void updateDisplayPanel(JPanel newDisplay) {
        displayPanel.removeAll();
        displayPanel.add(newDisplay, BorderLayout.CENTER);
        displayPanel.revalidate();
        displayPanel.repaint();
    }
}
