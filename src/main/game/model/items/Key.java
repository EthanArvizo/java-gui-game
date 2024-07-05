package main.game.model.items;

import main.game.model.Item;

public class Key extends Item {
    private String doorType;

    public Key(String name, String description, String doorType) {
        super(name, description);
        this.doorType = doorType;
    }
}
