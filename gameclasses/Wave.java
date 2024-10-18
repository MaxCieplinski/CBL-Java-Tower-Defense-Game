package gameclasses;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;


public class Wave implements Runnable {
    private Thread waveThread;
    Player player;
    private GamePanel gamePanel;
    private TownHall townHall;
    private Map map;
    private int numbOfEnemies;
    private double enemyIncrementPercentage;
    private Enemy[] enemies;
    private ArrayList<Entity> entities = new ArrayList<>();
    public String waveStatus;
    public int waveNumber = 0;
    public Boolean active = false;

    public Wave(Player player, GamePanel gamePanel, TownHall townHall, Map map, int amountEnemies, double enemyIncrementPercentage) {
        this.player = player;
        this.gamePanel = gamePanel;
        this.townHall = townHall;
        this.map = map;
        this.numbOfEnemies = amountEnemies;
        this.enemyIncrementPercentage = enemyIncrementPercentage;
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

        this.enemies = new Enemy[this.numbOfEnemies];

        for (int i = 0; i < this.numbOfEnemies; i++) {
            enemies[i] = new Enemy(this.player, this.townHall, this.map, 100,
             10, 2, 50, 100, 100, entities, 30, 30);
        }
        
        this.active = true;
    }

    public void moveEnemies() {
        for (Enemy e : enemies) {
            e.setPosition(e.getXPosition() + e.moveX(700, 400, 30),
                         e.getYPosition() + e.moveY(700, 400, 30));
             
        }    
    }

    public void paintEnemies(Graphics g) {
        //moveEnemies();
        for (Enemy e: enemies) {
            e.paintEnemy(g);
        }
    }
}
