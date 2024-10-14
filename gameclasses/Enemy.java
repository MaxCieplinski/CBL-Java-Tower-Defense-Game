package gameclasses;

import java.util.ArrayList;

/**
 * Object of type Enemy.
 */
public class Enemy extends Entity {
    // Parameters initialization.
    Player player;
    int health;
    int damage;
    double speed;
    int gold;

    /**
     * Creates a new object of type Enemy.
     * @param player the main Player.
     * @param health the health of the Enemy class.
     * @param damage the damage Enemy class does.
     * @param gold the amount of gold Enemy class gives upon dying.
     * @param xPosition the x coordinate of Enemy position.
     * @param yPosition the y coordinate of Enemy position.
     */
    public Enemy(Player player, int health, int damage, double speed, int gold,
                 double xPosition, double yPosition, ArrayList<Entity> entities, 
                 double width, double height) {
                    
        super(xPosition, yPosition, entities, width, height);

        this.player = player;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.gold = gold;
    }

    /**
     * Method to award player gold.
     * @param player the player of type Player.
     */
    void giveGold(Player player) {
        player.addGold(this.gold);
    }

    /**
     * Method to get the current Enemy health.
     * @return Enemy health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Method to set the health of the Enemy.
     * @param health the new health value of Enemy.
     */
    void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method to make Enemy take damage.
     * @param damage the amount of damage Enemy should take.
     */
    void takeDamage(int damage) {
        this.health -= damage;
        checkForDeath();
    }

    /**
     * Method to check if the enemy health is below or 0.
     */
    public boolean checkForDeath() {
        if (this.health <= 0) {
            giveGold(player);
            return true;
        }

        return false;
    }
}