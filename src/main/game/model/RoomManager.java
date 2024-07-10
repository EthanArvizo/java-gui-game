package main.game.model;

public class RoomManager {
    private Room initialRoom;


    public RoomManager() {
        createRooms();
    }

    private void createRooms() {
        Room room1 = new Room("You are in a small room. There is a door to the north.");
        Room room2 = new Room("You are in a large hall. There is a door to the south and east.");
        Room room3 = new Room("You are in a dark cave. There is an exit to the west.");

        room1.setExit("north", room2);
        room2.setExit("south", room1);
        room2.setExit("east", room3);
        room3.setExit("west", room2);

        initialRoom = room1;
    }

    public Room getInitialRoom() {
        return initialRoom;
    }
}

