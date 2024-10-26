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
    public int buildingDamage = GameSettings.ENEMY_BUILDING_DAMAGE;

    private GamePanel gamePanel;
    private Player player;
    private int health = GameSettings.ENEMY_HEALTH;
    private int maxHealth = GameSettings.ENEMY_HEALTH;
    private double speed = GameSettings.ENEMY_SPEED;
    private int reward;
    Color color = GameSettings.ENEMY_COLOR;

    /**
     * Creates a new object of type Enemy.
     * @param player the main Player.
     * @param xPosition the x coordinate of Enemy position.
     * @param yPosition the y coordinate of Enemy position.
     */
    public Enemy(GamePanel gamePanel, Player player,
                 double xPosition, double yPosition, ArrayList<Entity> entities,
                 double width, double height, int reward) {
                    
        super(xPosition, yPosition, entities, width, height);

        this.gamePanel = gamePanel;
        this.player = player;
        this.reward = reward;

        this.healthBar = new HealthBar(this.gamePanel,
                                     (int) width - this.healthBarXOffset, 5);
        this.healthBar.setHealthBarPosition((int) this.getXPosition() + (this.healthBarXOffset / 2),
                                            (int) this.getYPosition() - this.healthBarYOffset);
    }

    public double getSpeed() {
        return this.speed;
    }

    public Color getColor() {
        return this.color;
    }

    public int getReward() {
        return this.reward;
    }

    /**
     * Method to award player gold.
     * @param player the player of type Player.
     */
    public void giveGold(Player player) {
        player.addGold(getReward());
    }

    /**
     * Method to get the current Enemy health.
     * @return Enemy health
     */
    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method to make Enemy take damage.
     * @param damage the amount of damage Enemy should take.
     */
    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        if (this.healthBar != null) {
            this.healthBar.updateHealthBar(getHealth(), getMaxHealth());
        }

        if (checkForDeath()) {
            giveGold(player);
        }
    }

    /**
     * Method to check if the enemy health is below or 0.
     */
    public boolean checkForDeath() {
        if (this != null && getHealth() <= 0) {
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
            return getSpeed();
        }

        if (distanceX < 0) {
            return -1 * getSpeed();
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
            return getSpeed();
        }

        if (distanceY < 0) {
            return -1 * getSpeed();
        }

        return 0;
    }


    /**
     * Draws the enemy.
     * @param g the graphics of the game.
     */
    public void paintEnemy(Graphics g) {

        g.setColor(getColor());
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