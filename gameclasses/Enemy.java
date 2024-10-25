package gameclasses;

import java.awt.*;
import java.util.ArrayList;


/**
 * Object of type Enemy.
 */
public class Enemy extends Entity {
    // Parameters initialization.
    public final int healthBarYOffset = 8;
    public final int healthBarXOffset = 8;

    public HealthBar healthBar;
    public int damage = GameSettings.ENEMY_DAMAGE;

    private GamePanel gamePanel;
    private Player player;
    private int health;
    private int maxHealth;
    private double speed;
    private int gold = GameSettings.ENEMY_REWARD;

    /**
     * Creates a new object of type Enemy.
     * @param player the main Player.
     * @param health the health of the Enemy class.
     * @param gold the amount of gold Enemy class gives upon dying.
     * @param xPosition the x coordinate of Enemy position.
     * @param yPosition the y coordinate of Enemy position.
     */
    public Enemy(GamePanel gamePanel, Player player, int health, double speed,
                 double xPosition, double yPosition, ArrayList<Entity> entities,
                 double width, double height) {
                    
        super(xPosition, yPosition, entities, width, height);

        this.gamePanel = gamePanel;
        this.player = player;
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;

        this.healthBar = new HealthBar(this.gamePanel, this.maxHealth,
                                     (int) width - this.healthBarXOffset, 5);
        this.healthBar.setHealthBarPosition((int) this.getXPosition() + (this.healthBarXOffset / 2),
                                            (int) this.getYPosition() - this.healthBarYOffset);
    }

    /**
     * Method to award player gold.
     * @param player the player of type Player.
     */
    public void giveGold(Player player) {
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
     * Method to make Enemy take damage.
     * @param damage the amount of damage Enemy should take.
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.healthBar != null) {
            this.healthBar.updateHealthBar(this.health);
        }
    }

    /**
     * Method to check if the enemy health is below or 0.
     */
    public boolean checkForDeath() {
        if (this != null && this.health <= 0) {
            giveGold(player);

            this.healthBar.healthBarBackground.setVisible(false);
            this.healthBar.healthBarForeground.setVisible(false);

            return true;
        }
        
        return false;
    }

    /**
     * Calculates delta-x.
     * @param middleOfPanelX x-coordinate of the middle of the panel.
     * @param size size of the town hall.
     * @return double containing the direction in which the enemy should move, horizontally,
     *          to reach the town hall.
     */
    public double moveX(double middleOfPanelX, int size) {
        double distanceX = (middleOfPanelX - size) - this.getXPosition();

        if (distanceX > 0) {
            return this.speed;
        }

        if (distanceX < 0) {
            return -1 * this.speed;
        }

        return 0;
    }

    /**
     * Calculates delta-y.
     * @param middleOfPanelY y-coordinate of the middle of the panel.
     * @param size size of the town hall.
     * @return double containing the direction in which the enemy should move, vertically,
     *          to reach the town hall.
     */
    public double moveY(double middleOfPanelY, int size) {

        double distanceY = (middleOfPanelY - size) - this.getYPosition();

        if (distanceY > 0) {
            return this.speed;
        }

        if (distanceY < 0) {
            return -1 * this.speed;
        }

        return 0;
    }


    /**
     * Draws the enemy.
     * @param g the graphics of the game.
     */
    public void paintEnemy(Graphics g) {

        g.setColor(Color.red);
        g.fillRect((int) super.getXPosition(), (int) super.getYPosition(),
                    (int) super.getWidth(), (int) super.getHeight());
    }

    /**
     * Resets the enemy position to one movement before.
     */
    public void resetEnemyPosition() {

        super.setPosition(super.getOldX(), super.getOldY());
    }

}