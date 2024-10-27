package gameclasses;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles everything to do with towers attacking, including the thread.
 */
public class TowerAttack implements Runnable {
    private Thread towerAttackThread;
    private ArrayList<Enemy> enemies;

    /**
     * Creates an object of Class TowerAttack.
     * @param enemies arraylist of all enemies in the game.
     */
    public TowerAttack(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * Starts the tower attack thread.
     */
    public void startTowerThread() {
        towerAttackThread = new Thread(this);
        towerAttackThread.start();
    }

    @Override
    public void run() {
        if ((GameSettings.towers != null) && (this.enemies != null)) { 
            List<Tower> towersCopy = new ArrayList<>(GameSettings.towers);
            List<Enemy> enemiesCopy = new ArrayList<>(enemies);

            for (Tower tower : towersCopy) {
                for (Enemy enemy : enemiesCopy) {
                    tower.handleEnemy(enemy);
                }
            }
        }

        try {
            // to sleep 0.75 second
            Thread.sleep(750);
        } catch (InterruptedException e) {
            towerAttackThread.interrupt();
            return;
        }
        
        run();
    }

    /**
     * Updates important main arraylists for the thread.
     * @param enemies arraylist containing all enemies.
     */
    public void updateLists(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * Shows the tower ranges.
     * @param g the main graphics of the game.
     */
    public void showTowerRanges(Graphics g) {
        for (Tower t : GameSettings.towers) {
            t.showRange(g);
        }
    }

    /**
     * Stops the tower attack thread, called after game ends.
     */
    public void stopTowerThread() {
        this.enemies = null;
    }
}
