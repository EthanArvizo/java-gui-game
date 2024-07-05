package main.game.gui;

import main.game.model.Item;
import main.game.Player;

import javax.swing.*;

public class InventoryManager {
    private JTextArea inventoryArea;
    private Player player;

    public InventoryManager(JTextArea inventoryArea, Player player) {
        this.inventoryArea = inventoryArea;
        this.player = player;
    }

    // Method to update the inventory display
    public void updateInventoryDisplay() {
        // Clear current inventory display
        inventoryArea.setText("Inventory:\n");

        // Get player's inventory and update display
        for (Item item : player.getInventory()) {
            inventoryArea.append("- " + item.getName() + "\n");
        }
    }

    // Method to add an item to the player's inventory
    public void addItemToInventory(Item item) {
        player.addItemToInventory(item);
        updateInventoryDisplay(); // Update the inventory display when adding an item
    }
}
