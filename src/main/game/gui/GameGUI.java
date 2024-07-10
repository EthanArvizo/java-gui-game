package main.game.gui;

import main.game.Player;
import main.game.model.Monster;
import main.game.model.RoomManager;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.List;

public class GameGUI {
    // GUI components
    private JFrame frame;
    private JTextPane displayArea;
    private JButton northButton, southButton, eastButton, westButton, attackButton;
    private Player player;
    private DisplayManager displayManager;
    private JPanel displayPanel;
    private JPanel buttonPanel;

    public GameGUI() {
        RoomManager roomManager = new RoomManager();
        player = new Player(roomManager.getInitialRoom());
        displayManager = new DisplayManager();
        initializeGUI();
    }

    private void initializeGUI() {
        setupFrame();
        setupDisplayArea();
        buttonPanel = setupButtonPanel();
        setupDisplayPanel();
        addComponentsToFrame();
        frame.setVisible(true);
    }

    private void setupFrame() {
        frame = new JFrame("Adventure Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Centers the frame on the screen
    }

    private void setupDisplayArea() {
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
        updateDisplayArea();
    }

    private JPanel setupButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3)); // Adjusted layout to include attack button

        // Create direction buttons
        northButton = new JButton("Go North");
        southButton = new JButton("Go South");
        eastButton = new JButton("Go East");
        westButton = new JButton("Go West");
        attackButton = new JButton("Attack");

        // Add buttons to the panel
        buttonPanel.add(northButton);
        buttonPanel.add(southButton);
        buttonPanel.add(eastButton);
        buttonPanel.add(westButton);
        buttonPanel.add(attackButton);

        // Add action listeners to the buttons
        northButton.addActionListener(new DirectionButtonListener("north", player, displayArea, this));
        southButton.addActionListener(new DirectionButtonListener("south", player, displayArea, this));
        eastButton.addActionListener(new DirectionButtonListener("east", player, displayArea, this));
        westButton.addActionListener(new DirectionButtonListener("west", player, displayArea, this));
        attackButton.addActionListener(new AttackButtonListener(player, displayArea, this));

        return buttonPanel;
    }

    private void setupDisplayPanel() {
        displayPanel = new JPanel(new BorderLayout());
        displayPanel.add(displayManager.getDisplayForRoom(player.getCurrentRoom()), BorderLayout.CENTER);
    }

    private void addComponentsToFrame() {
        frame.getContentPane().add(BorderLayout.CENTER, displayPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.getContentPane().add(BorderLayout.NORTH, new JScrollPane(displayArea));
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
        updateDisplayArea();
    }

    // Method to update the display area with room description and monster information
    public void updateDisplayArea() {
        String displayText = player.getCurrentRoom().getDescription();
        List<Monster> monsters = player.getCurrentRoom().getMonsters();

        if (!monsters.isEmpty()) {
            displayText += "\nMonsters in this room:\n";
            for (Monster monster : monsters) {
                displayText += monster.getName() + ": " + monster.getDescription() + " (Health: " + monster.getHealth() + ")\n";
            }
        }

        displayArea.setText(displayText);
    }


    // Add getters for the buttons to be used in AttackButtonListener
    public JButton getNorthButton() {
        return northButton;
    }

    public JButton getSouthButton() {
        return southButton;
    }

    public JButton getEastButton() {
        return eastButton;
    }

    public JButton getWestButton() {
        return westButton;
    }

    public JButton getAttackButton() {
        return attackButton;
    }
}
