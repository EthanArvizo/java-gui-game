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
            Monster monster = monsters.get(0); // Assume attacking the first monster in the list
            player.attack(monster);
            gameGUI.updateDisplayPanel(gameGUI.getDisplayManager().getDisplayForRoom(player.getCurrentRoom()));
        }
    }
}
