package main.game.gui;

import main.game.Player;
import main.game.model.Monster;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AttackButtonListener implements ActionListener {
    private Player player;
    private JTextPane displayArea;
    private GameGUI gameGUI;

    public AttackButtonListener(Player player, JTextPane displayArea, GameGUI gameGUI) {
        this.player = player;
        this.displayArea = displayArea;
        this.gameGUI = gameGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<Monster> monsters = player.getCurrentRoom().getMonsters();
        if (!monsters.isEmpty()) {
            Monster monster = monsters.get(0); // Attack the first monster in the room
            player.attack(monster);

            if (!monster.isAlive()) {
                player.getCurrentRoom().getMonsters().remove(monster);
            }

            if (!player.isAlive()) {
                displayArea.setText("You have been defeated by the " + monster.getName() + ".");
                disableAllButtons();
            } else {
                gameGUI.updateDisplayArea();
            }
        } else {
            displayArea.setText(player.getCurrentRoom().getDescription() + "\nThere are no monsters to attack.");
        }
    }

    private void disableAllButtons() {
        gameGUI.getNorthButton().setEnabled(false);
        gameGUI.getSouthButton().setEnabled(false);
        gameGUI.getEastButton().setEnabled(false);
        gameGUI.getWestButton().setEnabled(false);
        gameGUI.getAttackButton().setEnabled(false);
    }
}
