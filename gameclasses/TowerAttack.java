package gameclasses;

import java.awt.*;
import java.util.ArrayList;

public class TowerAttack implements Runnable {
    
    private Thread towerAttackThread;
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;

    public TowerAttack (ArrayList<Tower> towers, ArrayList<Enemy> enemies) {
        this.towers = towers;
        this.enemies = enemies;
    }

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

    public void updateLists(ArrayList<Tower> towers, ArrayList<Enemy> enemies) {
        this.towers = towers;
        this.enemies = enemies;
    }

    public void showTowerRanges(Graphics g) {
        for (Tower t : towers) {
            t.showRange(g);
        }
    }

    public void stopTowerThread() {
        this.towers = null;
        this.enemies = null;
    }
}
