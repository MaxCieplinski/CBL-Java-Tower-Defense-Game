package gameclasses;

import javax.swing.*;


/**
 * Object of type Tower.
 */
public class Tower extends GridCell implements Runnable {
    private Thread towerThread;
    double radius = 5;
    int size;
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

    public void startTowerThread() {
        towerThread = new Thread(this);
        towerThread.start();
    }

    @Override
    public void run() {
        try {
            // to sleep 10 seconds
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // recommended because catching InterruptedException clears interrupt flag
            towerThread.interrupt();
            // you probably want to quit if the thread is interrupted
            return;
        }
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
        double distance = Math.pow(Math.pow(super.getX() - enemy.getXPosition(), 2) 
                        + Math.pow(super.getY() - enemy.getYPosition(), 2), 1 / 2);

        if (distance <= radius) {
            return true;
        }

        return false;
    }

}