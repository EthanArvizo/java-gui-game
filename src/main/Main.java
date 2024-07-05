package main;

import main.game.gui.GameGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new); // Create and run the game GUI on the Swing event dispatch thread
    }
}