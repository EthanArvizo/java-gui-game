import java.util.HashMap;
import java.util.Map;

public class Room {
    // Description of the room
    private String description;
    // Map to store exits from this room, mapping direction strings to Room objects
    private Map<String, Room> exits;
    // Constructor to create a room with a given description.
    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
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
}
