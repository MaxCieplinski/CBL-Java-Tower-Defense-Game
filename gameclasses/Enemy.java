package gameclasses;

import java.awt.*;
import java.util.ArrayList;


/**
 * Object of type Enemy.
 */
public class Enemy extends Entity {
    // Parameters initialization.
    HealthBar healthBar;
    int healthBarYOffset = 8;
    int healthBarXOffset = 8;
    GamePanel gamePanel;
    Player player;
    TownHall townHall;
    Map map;
    int health;
    int maxHealth;
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
    public Enemy(GamePanel gamePanel, Player player, TownHall townHall, Map map, int health, int damage, 
                double speed, int gold, double xPosition, double yPosition, 
                ArrayList<Entity> entities, double width, double height) {
                    
        super(xPosition, yPosition, entities, width, height);

        this.gamePanel = gamePanel;
        this.player = player;
        this.townHall = townHall;
        this.map = map;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.speed = speed;
        this.gold = gold;

        this.healthBar = new HealthBar(this.gamePanel, this.health, this.maxHealth, (int) width - this.healthBarXOffset, 5);
        this.healthBar.setHealthBarPosition((int) this.getXPosition() + (this.healthBarXOffset / 2) , (int) this.getYPosition() - this.healthBarYOffset);
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
        if (this.healthBar != null) {
            this.healthBar.updateHealthBar(this.health);
        }

        checkForDeath();
    }

    /**
     * Method to check if the enemy health is below or 0.
     */
    public boolean checkForDeath() {
        if (this.health <= 0) {
            giveGold(player);
            this.healthBar.healthBarBackground.setVisible(false);
            this.healthBar.healthBarForeground.setVisible(false);
            try {
                if (this.healthBar != null) {
                    this.healthBar.healthBarBackground.setVisible(false);
                    this.healthBar.healthBarForeground.setVisible(false);
                    this.healthBar.healthBarBackground = null;
                    this.healthBar.healthBarForeground = null;
                    this.healthBar = null;
                }  
            } catch (Exception e) {
            
            }
            
            return true;
        }
        
        return false;
    }

    public void findPathToTownHall() {
        //GOAL this.townHall
    }

    public double moveX(double middleOfPanelX, double middleOfPanelY, int size) {
        double distanceX = (middleOfPanelX - size) - this.getXPosition();

        if (distanceX > 0) {
            return this.speed;
        }

        if (distanceX < 0) {
            return -1 * this.speed;
        }

        return 0;
    }

    public double  moveY(double middleOfPanelX, double middleOfPanelY, int size) {
        double distanceY = (middleOfPanelY - size) - this.getYPosition();

        if (distanceY > 0) {
            return this.speed;
        }

        if (distanceY < 0) {
            return -1 * this.speed;
        }

        return 0;
    }


    public void paintEnemy(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) super.getXPosition(), (int) super.getYPosition(),
                    (int) super.getWidth(), (int) super.getHeight());
    }

}