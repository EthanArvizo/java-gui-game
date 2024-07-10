package main.game.gui;
import main.game.Player;
import javax.swing.*;
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
            displayArea.setText(player.getCurrentRoom().getDescription());
        } else {
            displayArea.setText(player.getCurrentRoom().getDescription()+ "\nYou can't move "+ direction + ".");
        }

        // Update the display panel with the new room's background
        JPanel newDisplay = gameGUI.getDisplayManager().getDisplayForRoom(player.getCurrentRoom());
        gameGUI.updateDisplayPanel(newDisplay);
    }
}