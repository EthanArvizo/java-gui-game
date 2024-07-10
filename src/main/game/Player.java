package main.game;

import main.game.model.Room;

public class Player {
    private Room currentRoom;

    public Player(Room initialRoom) {
        this.currentRoom = initialRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            return true;
        } else {
            return false;
        }
    }
}
