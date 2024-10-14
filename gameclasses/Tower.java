package gameclasses;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JButton;

/**
 * Object of type Tower.
 */
public class Tower extends GridCell {
    double radius = 10;
    int size;
    int row;
    int column;
    int damage = 25;

    
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
            enemy.takeDamage(damage); 
        }
    }
    

    /**
     * Calculates whether an enemy is in range of this tower.
     * @param enemy enemy for which it is calculated if it is in range.
     * @return true if enemy is in range, false if enemy is not in range.
     */

    public boolean enemyInRange(Enemy enemy) {
        double distance = Math.pow(Math.pow(super.getX() - enemy.getXPosition(), 2) 
                        + Math.pow(super.getY() - enemy.getYPosition(), 2), 1 / 2);

        if (distance <= radius) {
            return true;
        }

        return false;
    }

}