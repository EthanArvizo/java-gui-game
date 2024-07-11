package main.game.gui;
import main.game.Player;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        boolean moved = player.move(direction);
        if (moved) {
            gameGUI.updateDisplayPanel(gameGUI.getDisplayManager().getDisplayForRoom(player.getCurrentRoom()));
        } else {
            gameGUI.updateButtonStates(); // Update button states even if the move fails
        }
    }
}
