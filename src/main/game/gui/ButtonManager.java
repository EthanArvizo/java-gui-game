package main.game.gui;

import main.game.Player;
import main.game.model.Monster;

import javax.swing.*;
import java.awt.*;

public class ButtonManager {
    private Player player;
    private GameGUI gameGUI;
    private JButton northButton, southButton, eastButton, westButton, attackButton;

    public ButtonManager(Player player, GameGUI gameGUI) {
        this.player = player;
        this.gameGUI = gameGUI;
    }

    public JPanel setupButtonPanel() {
        JPanel buttonPanel = new JPanel(new BorderLayout()); // Main panel to hold directional and attack button panels

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
        attackPanel.setBackground(Color.BLACK); // Set background color to black
        attackButton = new JButton("Attack");
        attackButton.setBackground(Color.RED); // Set button background color to red
        attackButton.setForeground(Color.WHITE); // Set text color to white
        attackButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font and style
        attackPanel.add(attackButton);

        // Add directional and attack panels to the main button panel
        buttonPanel.add(directionalPanel, BorderLayout.CENTER);
        buttonPanel.add(attackPanel, BorderLayout.SOUTH);

        // Add action listeners to the directional buttons
        northButton.addActionListener(new DirectionButtonListener("north", player, gameGUI.getDisplayArea(), gameGUI));
        southButton.addActionListener(new DirectionButtonListener("south", player, gameGUI.getDisplayArea(), gameGUI));
        eastButton.addActionListener(new DirectionButtonListener("east", player, gameGUI.getDisplayArea(), gameGUI));
        westButton.addActionListener(new DirectionButtonListener("west", player, gameGUI.getDisplayArea(), gameGUI));

        // Add action listener to the attack button
        attackButton.addActionListener(new AttackButtonListener(player, gameGUI.getDisplayArea(), gameGUI));

        return buttonPanel;
    }

    public void updateButtonStates() {
        northButton.setEnabled(player.getCurrentRoom().getExit("north") != null);
        southButton.setEnabled(player.getCurrentRoom().getExit("south") != null);
        eastButton.setEnabled(player.getCurrentRoom().getExit("east") != null);
        westButton.setEnabled(player.getCurrentRoom().getExit("west") != null);

        // Check if there are monsters in the room
        boolean monstersExist = !player.getCurrentRoom().getMonsters().isEmpty();

        // Enable the attack button only if there are monsters in the room
        attackButton.setEnabled(monstersExist);

        // Check if all monsters are defeated
        boolean allMonstersDefeated = true;
        for (Monster monster : player.getCurrentRoom().getMonsters()) {
            if (monster.getHealth() > 0) {
                allMonstersDefeated = false;
                break;
            }
        }

        // Disable the attack button if all monsters are defeated
        if (!monstersExist || allMonstersDefeated) {
            attackButton.setEnabled(false);
        }
    }

}

