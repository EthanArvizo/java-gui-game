package main.game;

import main.game.model.Monster;
import main.game.model.Room;

import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private int health;
    private int attackPower;
    private SoundManager soundManager;

    public Player(Room initialRoom) {
        this.currentRoom = initialRoom;
        this.health = 100; // Initial health
        this.attackPower = 10; // Initial attack power
        soundManager = new SoundManager();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            soundManager.playFootstepSound();

            // Check for monsters in the room and play the appropriate sound
            ArrayList<Monster> monstersInRoom = nextRoom.getMonsters();
            if (!monstersInRoom.isEmpty()) {

                Monster monster = monstersInRoom.get(0);
                if (monster.getHealth() > 0) {
                    if (monster.getName().equalsIgnoreCase("Troll")) {
                        soundManager.playTrollSound();
                    } else if (monster.getName().equalsIgnoreCase("Goblin")) {
                        soundManager.playGoblinSound();
                    }
                }
            }
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
        soundManager.playSwordSound();

        if (!monster.isAlive()) {
            // Play death sound based on monster type
            if (monster.getName().equalsIgnoreCase("Goblin")) {
                soundManager.playGoblinDeathSound();
            } else if (monster.getName().equalsIgnoreCase("Troll")) {
                soundManager.playTrollDeathSound();
            }
        } else {
            retaliate(monster);
        }
    }


    private void retaliate(Monster monster) {
        takeDamage(monster.getAttackPower());
    }
}
