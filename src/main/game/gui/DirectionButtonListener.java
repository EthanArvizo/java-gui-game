package main.game.gui;

import main.game.model.Room;
import main.game.Player;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectionButtonListener implements ActionListener {
    private String direction;
    private Player player;
    private JTextPane displayArea;
    private GameGUI gameGUI; // Reference to the GameGUI instance

    /**
     * Constructor to initialize the direction, player, and display area.
     * @param direction The direction to move.
     * @param player The player object.
     * @param displayArea The text area to update with room descriptions.
     * @param gameGUI The GameGUI instance.
     */
    public DirectionButtonListener(String direction, Player player, JTextPane displayArea, GameGUI gameGUI) {
        this.direction = direction;
        this.player = player;
        this.displayArea = displayArea;
        this.gameGUI = gameGUI;
    }

    public DirectionButtonListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the room in the specified direction
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom != null) {
            // Move the player to the next room and update display
            player.setCurrentRoom(nextRoom);
            updateDisplay(player.getCurrentRoom().getDescription());
        } else {
            // Show a message if there's no exit in that direction
            String message = "You can't go that way.\n" + player.getCurrentRoom().getDescription();
            updateDisplay(message);
        }
    }

    private void updateDisplay(String text) {
        // Update the text area with the current room description
        SwingUtilities.invokeLater(() -> {
            displayArea.setText(player.getCurrentRoom().getDescription());

            // Reapply center alignment after setting new text
            StyledDocument doc = displayArea.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        });

        // Update the background display panel
        SwingUtilities.invokeLater(() -> {
            JPanel newDisplay = gameGUI.getDisplayManager().getDisplayForRoom(player.getCurrentRoom());
            gameGUI.updateDisplayPanel(newDisplay);
        });
    }
}