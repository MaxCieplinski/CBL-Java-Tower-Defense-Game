package gameclasses;

import java.util.ArrayList;
import java.awt.*;

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
        //If tower or enemies empty do nothing!!!
        //else
        if ((this.towers != null) && (this.enemies != null)) {    
            for (Tower tower : towers) {
                for (Enemy enemy : enemies) {
                    tower.handleEnemy(enemy);
                    System.out.println(" Hit enemy");
                }
            }
        }

        try {
            // to sleep 10 seconds
            Thread.sleep(1000);
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
            t.showRange(true, g);
        }
    }
}
