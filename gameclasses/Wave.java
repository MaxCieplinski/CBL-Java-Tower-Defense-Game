package gameclasses;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;


public class Wave implements Runnable {
    private Thread waveThread;
    public GamePanel gamePanel;
    private int numbOfEnemies;
    private double enemyIncrementPercentage;
    private Enemy[] enemies;
    private ArrayList<Entity> entities = new ArrayList<>();
    public String waveStatus;
    public int waveNumber = 0;

    public Wave(GamePanel gamePanel, int amountEnemies, double enemyIncrementPercentage, Player player) {
        this.gamePanel = gamePanel;
        this.numbOfEnemies = amountEnemies;
        this.enemyIncrementPercentage = enemyIncrementPercentage;
        enemies = new Enemy[amountEnemies];

        for (int i = 0; i < numbOfEnemies; i++) {
            enemies[i] = new Enemy(player, 100, 10, 2, 50, 0, 0, entities, 30, 30);
        }
    }

    public void startWaveThread() {
        waveThread = new Thread(this);
        waveThread.start();
    }

    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            waveStatus = "Starting in " + Integer.toString(i);
            this.gamePanel.updateMenu(Optional.of(waveStatus));

            try {
                // to sleep 10 seconds
                waveThread.sleep(1000);
            } catch (InterruptedException e) {
                // recommended because catching InterruptedException clears interrupt flag
                waveThread.interrupt();
                // you probably want to quit if the thread is interrupted
                return;
            }
        }

        waveStatus = Integer.toString(waveNumber);
        waveNumber += 1;

        this.gamePanel.updateMenu(Optional.of(Integer.toString(waveNumber)));
        startWave();
    }

    public void startWave() {
        System.out.print("Wave started");

        //START THE WAVE, AFTER ALL ENEMIES ARE DEAD CALL run()
        moveEnemies();


        /*
        try {
            // to sleep 10 seconds
            waveThread.sleep(5000);
            run();
        } catch (InterruptedException e) {
            // recommended because catching InterruptedException clears interrupt flag
            waveThread.interrupt();
            // you probably want to quit if the thread is interrupted
            return;
        }
            */
    }

    public void moveEnemies() {
        for (Enemy enemy : enemies) {
            // enemy.FindPath()

            /*
            e.setPosition(e.getXPosition() + e.moveX(700, 400, 30),
                         e.getYPosition() + e.moveY(700, 400, 30));
             */
        }    
    }

    public void paintEnemies(Graphics g) {
        moveEnemies();
        for (Enemy e: enemies) {
            e.paintEnemy(g);
        }
    }
}
