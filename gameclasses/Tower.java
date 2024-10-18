package gameclasses;

import java.awt.*;
import javax.swing.*;


/**
 * Object of type Tower.
 */
public class Tower extends GridCell {
    double radius = 200;
    int row;
    int column;
    int damage = 35;

    
    /**
     * Creates a new object of type Tower.
     */
    public Tower(Player player, int row, int column, int size, JPanel panel) {
        super(row, column, player, size, panel);
        super.occupied = true;
        super.empty = false;

        System.out.println("Tower placed at " + row + ", " + column);
    }

    /**
     * Hits enemy.
     * @param enemy enemy that is hit.
     */

    public void handleEnemy(Enemy enemy) {
        if (enemyInRange(enemy)) {        
            System.out.println("Attacking enemies in range");
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

    public void showRange(boolean on, Graphics g) {
        if (on) {
            Graphics2D g2d = (Graphics2D) g;

            // Set transparency: 0.5f means 50% transparency
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

            g2d.setColor(Color.lightGray);

            // Calculate the center of the tower
            int centerX = super.getX() + super.getSize() / 2;
            int centerY = super.getY() + super.getSize() / 2;

            System.out.println(super.getSize());

            System.out.println("Centre X: " + centerX);
            System.out.println("Centre Y: " + centerY);

            // Calculate the top-left corner of the oval
            int topLeftX = (int) (centerX - radius);
            int topLeftY = (int) (centerY - radius);

            // Draw the semi-transparent oval, centered on the tower
            g2d.fillOval(topLeftX, topLeftY, (int) (2 * radius), (int) (2 * radius));

            // Reset the composite to fully opaque after drawing the oval (for future drawing operations)
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        } else {
            //Call repaint for example
        }
    }


}