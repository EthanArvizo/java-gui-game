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
    private GameGUI gameGUI;

    public DirectionButtonListener(String direction, Player player, JTextPane displayArea, GameGUI gameGUI) {
        this.direction = direction;
        this.player = player;
        this.displayArea = displayArea;
        this.gameGUI = gameGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move(direction);
        displayArea.setText(player.getCurrentRoom().getDescription());

        // Update the display panel with the new room's background
        JPanel newDisplay = gameGUI.getDisplayManager().getDisplayForRoom(player.getCurrentRoom());
        gameGUI.updateDisplayPanel(newDisplay);
    }
}