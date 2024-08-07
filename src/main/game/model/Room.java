package main.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private String description;
    private Map<String, Room> exits; // stores exits of this room.
    private ArrayList<Monster> monsters;

    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
        this.monsters = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }
    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

}
