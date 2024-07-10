package main.game;

import main.game.model.Monster;
import main.game.model.Room;

public class Player {
    private Room currentRoom;
    private int health;
    private int attackPower;

    public Player(Room initialRoom) {
        this.currentRoom = initialRoom;
        this.health = 100; // Initial health
        this.attackPower = 10; // Initial attack power
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

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void attack(Monster monster) {
        monster.takeDamage(attackPower);
        if (monster.isAlive()) {
            retaliate(monster);
        }
    }

    private void retaliate(Monster monster) {
        takeDamage(monster.getAttackPower());
    }
}
