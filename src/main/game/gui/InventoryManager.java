package main.game.gui;

import main.game.model.Item;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Item> items;

    public InventoryManager() {
        items = new ArrayList<>();
    }

    public void addItemToInventory(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
