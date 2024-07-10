package main.game.model;

public class Monster {
    private String name;
    private String description;
    private int health;
    private int attackPower;

    public Monster(String name, String description) {
        this.name = name;
        this.description = description;
        this.health = 50; // Initial health
        this.attackPower = 5; // Initial attack power
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public int getAttackPower() {
        return attackPower;
    }
}
