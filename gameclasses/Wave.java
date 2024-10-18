package gameclasses;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;


public class Wave implements Runnable {
    private Thread waveThread;
    Player player;
    private GamePanel gamePanel;
    private TownHall townHall;
    private Map map;
    private int numbOfEnemies;
    public ArrayList<Enemy> enemies;
    private ArrayList<Entity> entities = new ArrayList<>();
    public String waveStatus;
    public int waveNumber = 0;
    public boolean active = false;
    public boolean waveInProgress = true;

    public Wave(Player player, GamePanel gamePanel, TownHall townHall,
             Map map, int amountEnemies) {
        this.player = player;
        this.gamePanel = gamePanel;
        this.townHall = townHall;
        this.map = map;
        this.numbOfEnemies = amountEnemies;

    }

    public void startWaveThread() {
        waveThread = new Thread(this);
        waveThread.start();
    }

    @Override
    public void run() {
        waveInProgress = true;
        for (int i = 10; i > 0; i--) {
            waveStatus = "Starting in " + Integer.toString(i);
            this.gamePanel.updateMenu(Optional.of(waveStatus));

            try {
                // to sleep 10 seconds
                Thread.sleep(1000);
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
        this.startWave();
    }


    public void startWave() {
        System.out.println("Wave started");

        this.enemies = new ArrayList<>();

        for (int i = 0; i < this.numbOfEnemies; i++) {
            Random rand = new Random();
            int side = rand.nextInt(4);
            int xPos = 0;
            int yPos = 0;

            switch (side) {
                case 0:
                    //UP
                    xPos = rand.nextInt(this.gamePanel.getWidth());
                    yPos = 0;
                    break;
                case 1:
                    //DOWN
                    xPos = rand.nextInt(this.gamePanel.getWidth());
                    yPos = this.gamePanel.getHeight();
                    break;
                case 2:
                    //LEFT
                    xPos = 0;
                    yPos = rand.nextInt(this.gamePanel.getHeight());
                    break;
                case 3:
                    //RIGHT
                    xPos = this.gamePanel.getWidth();
                    yPos = rand.nextInt(this.gamePanel.getHeight());
                    break;
                default:
                    break;
            }

            enemies.add(new Enemy(this.gamePanel, this.player, this.townHall, this.map, 100,
                 10, 1, 50, xPos, yPos, entities, 30, 30));
        }

        active = true;
        System.out.println("Enemies spawned");
    }

    public void moveEnemies() {
        for (Enemy e : enemies) {
            e.setPosition(e.getXPosition() + e.moveX(700, 400, 30),
                         e.getYPosition() + e.moveY(700, 400, 30));

            e.healthBar.setHealthBarPosition((int) e.getXPosition() + (e.healthBarXOffset / 2),
                                             (int) e.getYPosition() - e.healthBarYOffset);
        }    
    }

    public void paintEnemies(Graphics g) {
        //moveEnemies();
        for (Enemy e: enemies) {
            e.paintEnemy(g);
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public void endWave() {
        active = false;
        waveInProgress = false;
        System.out.println("Wave ended!");
    }

    public void updateWaveState() {
        enemies.removeIf(Enemy::checkForDeath);

        if (enemies.isEmpty()) {
            endWave();
        }
    }
}
