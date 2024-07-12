package main.game.gui;

import main.game.Player;
import main.game.SoundManager;
import main.game.model.RoomManager;

import javax.swing.*;
import java.awt.*;

public class GameGUI {
    private JFrame frame;
    private JTextPane displayArea;
    private Player player;
    private DisplayManager displayManager;
    private JPanel displayPanel;
    private ButtonManager buttonManager;
    private SoundManager soundManager;

    public GameGUI() {
        soundManager = new SoundManager();
        RoomManager roomManager = new RoomManager();
        player = new Player(roomManager.getInitialRoom());
        displayManager = new DisplayManager();
        buttonManager = new ButtonManager(player, this);
        initializeGUI();
        updateButtonStates(); // Initialize button states after GUI is set up
    }

    private void initializeGUI() {
        GUIInitializer guiInitializer = new GUIInitializer();
        frame = guiInitializer.setupFrame();
        displayArea = guiInitializer.setupDisplayArea();
        displayPanel = guiInitializer.setupDisplayPanel(displayManager.getDisplayForRoom(player.getCurrentRoom()));
        JPanel buttonPanel = buttonManager.setupButtonPanel();

        frame.getContentPane().add(BorderLayout.CENTER, displayPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.getContentPane().add(BorderLayout.NORTH, new JScrollPane(displayArea));
        frame.setVisible(true);

        updateDisplayArea(); // Update display area after frame is visible
    }

    public void updateButtonStates() {
        buttonManager.updateButtonStates();
    }

    public void updateDisplayPanel(JPanel newDisplay) {
        displayPanel.removeAll();
        displayPanel.add(newDisplay, BorderLayout.CENTER);
        displayPanel.revalidate();
        displayPanel.repaint();
        updateDisplayArea();
        updateButtonStates();
    }

    public void updateDisplayArea() {
        String displayText = player.getCurrentRoom().getDescription();
        var monsters = player.getCurrentRoom().getMonsters();

        if (!monsters.isEmpty()) {
            displayText += "\nMonsters in this room:\n";
            for (var monster : monsters) {
                displayText += monster.getName() + ": " + monster.getDescription() + " (Health: " + monster.getHealth() + ")\n";
                if (monster.getHealth() <= 0) {
                    displayText = player.getCurrentRoom().getDescription()+ "\n\nThe monster in this has been defeated.";
                }
            }
        }
        displayArea.setText(displayText);
    }

    public Player getPlayer() {
        return player;
    }


    public DisplayManager getDisplayManager() {
        return displayManager;
    }

    public JTextPane getDisplayArea() {
        return displayArea;
    }
}
