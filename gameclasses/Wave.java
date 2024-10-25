package gameclasses;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * Wave class that contains all the code for a wave.
 * When a wave starts enemies start spawning from all directions.
 */
public class Wave implements Runnable {
    private Thread waveThread;
    private Player player;
    private GamePanel gamePanel;
    private Map map;    
    private ArrayList<Entity> entities;
    private int numbOfEnemies;
    public int enemiesDestroyedStat = 0;

    public ArrayList<Enemy> enemies;
    public int waveNumber = 0;
    public String waveStatus;
    public boolean active = false;
    public boolean waveInProgress = true;

    /**
     * Constructs a wave object.
     * @param player the player of the game.
     * @param gamePanel the panel on which the game is run.
     * @param map the map of the game.
     * @param amountEnemies amount of enemies in the wave.
     * @param entities arraylist containing all the entities in the game.
     */
    public Wave(Player player, GamePanel gamePanel,
             Map map, int amountEnemies, ArrayList<Entity> entities) {
                
        this.entities = entities;
        this.player = player;
        this.gamePanel = gamePanel;
        this.map = map;
        this.numbOfEnemies = amountEnemies;

    }

    /**
     * Starts wave thread.
     */
    public void startWaveThread() {
        waveThread = new Thread(this);
        waveThread.start();
    }

    @Override
    public void run() {
        waveInProgress = true;
        int wave_cooldown;
        if (waveNumber == 0) {
            wave_cooldown = GameSettings.TIME_BEFORE_WAVE_START;
        } else {
            wave_cooldown = GameSettings.BREAK_TIME_BETWEEN_WAVES;
        }

        for (int i = wave_cooldown; i > 0; i--) {
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

    /**
     * Starts the wave.
     */
    public void startWave() {

        //So that the player can start shooting
        player.waveStarted = true;

        this.enemies = new ArrayList<>();

        if (waveNumber != 1) {
            this.numbOfEnemies = GameSettings.getEnemyCount(waveNumber);
        }

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

            enemies.add(new Enemy(this.gamePanel, this.player, 100,
                1, xPos, yPos, entities, 30, 30));
        }

        active = true;
    }

    /**
     * Moves all enemies.
     */
    public void moveEnemies() {
        for (Enemy e : enemies) {

            if (!e.getCollider().checkForCollision(map.getMap())) {
                e.setPosition(e.getXPosition() + e.moveX(700, 30),
                            e.getYPosition() + e.moveY(400, 30));

                e.healthBar.setHealthBarPosition((int) e.getXPosition() + (e.healthBarXOffset / 2),
                                                (int) e.getYPosition() - e.healthBarYOffset);
            } else {
                e.resetEnemyPosition();
            }

        }    
    }

    /**
     * Paints all enemies.
     * @param g the graphics.
     */
    public void paintEnemies(Graphics g) {
        //moveEnemies();
        for (Enemy e: enemies) {
            e.paintEnemy(g);
        }
    }

    /**
     * Gets all enemies.
     * @return arraylist containing all the enemies.
     */
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    /**
     * Ends the wave.
     */
    public void endWave() {
        active = false;
        waveInProgress = false;

        //So that the player can build towers and walls instead of shooting.
        player.waveStarted = false;
    }

    /**
     * Updates wave state.
     */
    public void updateWaveState() {
        enemies.removeIf(enemy -> {
            if (enemy.checkForDeath()) {
                this.enemiesDestroyedStat += 1;
                enemy = null;
                return true;
            }

            return false;
        });

        if (enemies.isEmpty()) {
            endWave();
        }
    }

    /**
     * Stops the wave thread when the game has ended.
     */
    public void stopWaveThread() {
        this.active = false;
        this.waveInProgress = false;
    }
}
