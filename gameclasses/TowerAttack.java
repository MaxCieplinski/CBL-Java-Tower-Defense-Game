package gameclasses;

import java.awt.*;
import java.util.ArrayList;

/**
 * Handles everything to do with towers attacking, including the thread.
 */
public class TowerAttack implements Runnable {
    
    private Thread towerAttackThread;
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;

    /**
     * Creates an object of Class TowerAttack.
     * @param towers arraylist of all towers in the game.
     * @param enemies arraylist of all enemies in the game.
     */
    public TowerAttack(ArrayList<Tower> towers, ArrayList<Enemy> enemies) {
        this.towers = towers;
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

        if ((this.towers != null) && (this.enemies != null)) {    
            try {
                synchronized (towers) {
                    for (Tower tower : towers) {
                        synchronized (enemies) {
                            for (Enemy enemy : enemies) {
                                tower.handleEnemy(enemy);
                            }   
                        }
                    }   
                }    
            } catch (Exception e) {
            
            }
        }

        try {
            // to sleep 0.75 second
            Thread.sleep(750);
        } catch (InterruptedException e) {
            // recommended because catching InterruptedException clears interrupt flag
            towerAttackThread.interrupt();
            // you probably want to quit if the thread is interrupted
            return;
        }
        
        run();
    }

    /**
     * Updates important main arraylists for the thread.
     * @param towers arraylist containing all towers.
     * @param enemies arraylist containing all enemies.
     */
    public void updateLists(ArrayList<Tower> towers, ArrayList<Enemy> enemies) {
        this.towers = towers;
        this.enemies = enemies;
    }

    /**
     * Shows the tower ranges.
     * @param g the main graphics of the game.
     */
    public void showTowerRanges(Graphics g) {
        for (Tower t : towers) {
            t.showRange(g);
        }
    }

    /**
     * Stops the tower attack thread, called after game ends.
     */
    public void stopTowerThread() {
        this.towers = null;
        this.enemies = null;
    }
}
