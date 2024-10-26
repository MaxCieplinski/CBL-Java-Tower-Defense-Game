package gameclasses;

import java.util.ArrayList;

/**
 * Code which handles the enemy attacking the towers or walls.
 */
public class EnemyAttack implements Runnable{

    private Thread enemyAttackThread;
    private GridCell[][] grid;
    private ArrayList<Enemy> enemies;

    /**
     * Constructor object, which initializes the lists.
     */
    public EnemyAttack(GridCell[][] gridCells, ArrayList<Enemy> enemies) {
        this.grid = gridCells;
        this.enemies = enemies;
    }

    /**
     * Starts the thread.
     */
    public void startEnemyThread() {
        enemyAttackThread = new Thread(this);
        enemyAttackThread.start();
    }

    @Override
    public void run() {

        if ((this.grid != null) && (this.enemies != null)) {

            for (Enemy e : enemies) {

                if (e != null) {
                    if (e.getCollider().checkForCollision(grid)) {
                        e.getCollider().destroyBuildings(grid, e);
                    }
                }
            }

        }

        try {
            // to sleep 0.75 second
            Thread.sleep(750);
        } catch (InterruptedException e) {
            // recommended because catching InterruptedException clears interrupt flag
            enemyAttackThread.interrupt();
            // you probably want to quit if the thread is interrupted
            return;
        }
        
        run();
    }

    /**
     * Updates important arraylists for the thread.
     * @param gridCells the map. which is a two-dimensional array of gridcells.
     * @param enemies arraylist containing all enemies in the game.
     */
    public void updateLists(GridCell[][] gridCells, ArrayList<Enemy> enemies) {
        this.grid = gridCells;
        this.enemies = enemies;
    }

    /**
     * Stops the thread when the game is over.
     */
    public void stopEnemyThread() {
        this.enemies = null;
        this.grid = null;
    }
}
