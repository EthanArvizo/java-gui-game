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
        updateButtonStates(); // Initialize button states after GUI is set up
    }

    private void initializeGUI() {
        setupFrame();
        setupDisplayArea();
        setupButtonPanel();
        setupDisplayPanel();
        addComponentsToFrame();
        frame.setVisible(true);
        updateDisplayArea(); // Update display area after frame is visible
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
    }

    private void setupButtonPanel() {
        buttonPanel = new JPanel(new BorderLayout()); // Main panel to hold directional and attack button panels

        // Panel for directional buttons
        JPanel directionalPanel = new JPanel(new GridLayout(2, 2));
        northButton = new JButton("Go North");
        southButton = new JButton("Go South");
        eastButton = new JButton("Go East");
        westButton = new JButton("Go West");
        directionalPanel.add(northButton);
        directionalPanel.add(southButton);
        directionalPanel.add(eastButton);
        directionalPanel.add(westButton);

        // Panel for attack button
        JPanel attackPanel = new JPanel();
        attackButton = new JButton("Attack");
        attackButton.setBackground(Color.RED); // Example: Set background color to red
        attackButton.setForeground(Color.WHITE); // Set text color to white
        attackButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font and style
        attackPanel.add(attackButton);

        // Add directional and attack panels to the main button panel
        buttonPanel.add(directionalPanel, BorderLayout.CENTER);
        buttonPanel.add(attackPanel, BorderLayout.SOUTH);

        // Add action listeners to the directional buttons
        northButton.addActionListener(new DirectionButtonListener("north", player, displayArea, this));
        southButton.addActionListener(new DirectionButtonListener("south", player, displayArea, this));
        eastButton.addActionListener(new DirectionButtonListener("east", player, displayArea, this));
        westButton.addActionListener(new DirectionButtonListener("west", player, displayArea, this));

        // Add action listener to the attack button
        attackButton.addActionListener(new AttackButtonListener(player, displayArea, this));

        // Add button panel to the frame
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
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

    // Method to update the button states based on the current room and its exits
    void updateButtonStates() {
        northButton.setEnabled(player.getCurrentRoom().getExit("north") != null);
        southButton.setEnabled(player.getCurrentRoom().getExit("south") != null);
        eastButton.setEnabled(player.getCurrentRoom().getExit("east") != null);
        westButton.setEnabled(player.getCurrentRoom().getExit("west") != null);

        // Enable the attack button only if there are monsters in the room
        attackButton.setEnabled(!player.getCurrentRoom().getMonsters().isEmpty());
    }

    // Method to update the display panel and button states
    public void updateDisplayPanel(JPanel newDisplay) {
        displayPanel.removeAll();
        displayPanel.add(newDisplay, BorderLayout.CENTER);
        displayPanel.revalidate();
        displayPanel.repaint();
        updateDisplayArea();
        updateButtonStates();
    }

    // Method to update the display area with room description and monster information
    public void updateDisplayArea() {
        String displayText = player.getCurrentRoom().getDescription();
        List<Monster> monsters = player.getCurrentRoom().getMonsters();

        if (!monsters.isEmpty()) {
            displayText += "\nMonsters in this room:\n";
            for (Monster monster : monsters) {
                displayText += monster.getName() + ": " + monster.getDescription() + " (Health: " + monster.getHealth() + ")\n";
                if (monster.getHealth() <= 0) {
                    displayText = player.getCurrentRoom().getDescription() + "\n\nThe monster in this room has been defeated.";
                }
            }
        }
        displayArea.setText(displayText);
    }

    // Getter for displayManager
    public DisplayManager getDisplayManager() {
        return displayManager;
    }

}