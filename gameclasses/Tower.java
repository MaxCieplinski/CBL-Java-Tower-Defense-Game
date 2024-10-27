package gameclasses;

import java.awt.*;
import javax.swing.*;


/**
 * Object of type Tower.
 */
public class Tower extends GridCell {

    private double radius = GameSettings.TOWER_RANGE;
    private int damage = GameSettings.TOWER_DAMAGE;
    
    /**
     * Creates a new object of type Tower.
     */
    public Tower(Player player, int row, int column, int size, JPanel panel) {
        super(row, column, player, size, panel, GameSettings.getTowerSprite());
        super.health = GameSettings.TOWER_HEALTH;
        super.maxHealth = GameSettings.TOWER_HEALTH;

        super.occupied = true;
        super.empty = false;
        super.healthBar = new HealthBar(panel, 25, 2);

        super.towerRangeOn = true;

        //Compensate for cellSize = 25.
        this.healthBar.setHealthBarPosition(row, column - 5);
    }

    /**
     * Hits enemy.
     * @param enemy enemy that is hit.
     */
    public void handleEnemy(Enemy enemy) {
        if (enemyInRange(enemy)) {        
            enemy.takeDamage(damage); 
        }
    }
    

    /**
     * Calculates whether an enemy is in range of this tower.
     * @param enemy enemy for which it is calculated if it is in range.
     * @return true if enemy is in range, false if enemy is not in range.
     */
    public boolean enemyInRange(Enemy enemy) {
        double deltaXSquared = Math.pow(super.getX() - enemy.getXPosition(), 2);
        double deltaYSquared = Math.pow(super.getY() - enemy.getYPosition(), 2);

        double distance = Math.sqrt(deltaXSquared + deltaYSquared);

        if (distance <= radius) {
            return true;
        }

        return false;
    }

    /**
     * Creates a circle that shows the range.
     * @param g the graphics of the game.
     */
    public void showRange(Graphics g) {
        if (super.towerRangeOn) {
            //Cast Graphics to Graphics2D to be able to use setComposite.
            Graphics2D g2d = (Graphics2D) g;

            // Set transparency: 0.2f means 20% transparency
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));

            g2d.setColor(Color.lightGray);

            // Calculate the center of the tower
            int centerX = super.getX() + super.getSize() / 2;
            int centerY = super.getY() + super.getSize() / 2;

            // Calculate the top-left corner of the oval
            int topLeftX = (int) (centerX - radius);
            int topLeftY = (int) (centerY - radius);

            // Draw the semi-transparent oval, centered on the tower
            g2d.fillOval(topLeftX, topLeftY, (int) (2 * radius), (int) (2 * radius));

            //Reset transparency for future drawing purposes.
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        } 
    }


}