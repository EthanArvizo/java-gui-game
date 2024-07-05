package main.game.model;

import java.util.HashMap;
import java.util.Map;
import main.game.model.items.Key;

public class Room {
    // Description of the room
    private String description;
    // Map to store exits from this room, mapping direction strings to main.game.Room objects
    private Map<String, Room> exits;
    private Key key;
    // Constructor to create a room with a given description.
    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
        this.key = null;
    }
    //Sets an exit from this room in the specified direction.
    public void setExit(String direction, Room room){
        exits.put(direction, room);
    }
    //Gets the description of the room.
    public String getDescription(){
        return description;
    }
    //Gets the room in the specified direction.
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    // Method to add a key to the room
    public void addKey(Key key) {
        this.key = key;
    }

    // Method to retrieve the key from the room
    public Key getKey() {
        return key;
    }

    // Method to remove the key from the room (optional, depending on your game logic)
    public void removeKey() {
        this.key = null;
    }
}
