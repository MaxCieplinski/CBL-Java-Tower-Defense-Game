package gameclasses;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Contains all code for a special type of enemy that spawns every 5 waves. 
 */
public class EnemyBoss extends Enemy {

    private int health = GameSettings.ENEMY_BOSS_HEALTH;
    private int maxHealth = GameSettings.ENEMY_BOSS_HEALTH;
    private double speed = GameSettings.ENEMY_BOSS_SPEED;
    private int gold = GameSettings.ENEMY_BOSS_REWARD;
    private Color color = GameSettings.ENEMY_BOSS_COLOR;

    /**
     * Creates a new object of type EnemyBoss.
     * @param gamePanel the game panel associated with the enemy boss.
     * @param player the main Player.
     * @param xPosition the x coordinate of EnemyBoss position.
     * @param yPosition the y coordinate of EnemyBoss position.
     * @param entities list of all entities in the game.
     * @param width the width of the EnemyBoss.
     * @param height the height of the EnemyBoss.
     */
    public EnemyBoss(GamePanel gamePanel, Player player,
                     double xPosition, double yPosition, ArrayList<Entity> entities,
                     double width, double height) {

        // Call the constructor of the Enemy class
        super(gamePanel, player, xPosition, yPosition, entities, width, height,
             GameSettings.ENEMY_BOSS_REWARD);
        super.damage = GameSettings.ENEMY_BOSS_DAMAGE;
        super.buildingDamage = GameSettings.ENEMY_BOSS_BUILDING_DAMAGE;
        super.sprite = null;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public int getReward() {
        return this.gold;
    }

    @Override
    public void giveGold(Player player) {
        player.addGold(getReward());
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }
}