package gameclasses;

import java.awt.*;

/**
 * TownHall class that handles all the code for the town hall in the game.
 * Game ends when town hall health is 0.
 */
public class TownHall implements Runnable {

    private Thread townHallThread;
    private HealthBar healthBar;
    private GamePanel gamePanel;
    private Wave wave;

    private int health = GameSettings.TOWN_HALL_HEALTH;
    private int maxHealth = GameSettings.TOWN_HALL_HEALTH;
    private int posX;
    private int posY;
    private int size;
    private int radius = 50;

    /**
     * Constructs the townhall object.
     * @param gamePanel the panel the game is run on.
     * @param health health of the town hall.
     * @param x the x-coordinate of the town hall.
     * @param y the y-coordinate of the town hall.
     * @param size the size of the town hall.
     * @param wave the wave object that handles waves.
     */
    public TownHall(GamePanel gamePanel, int x, int y, int size, Wave wave) {
        this.gamePanel = gamePanel;
        this.posX = x;
        this.posY = y;
        this.size = size;
        this.wave = wave;

        this.healthBar = new HealthBar(gamePanel, size, 5);
        this.healthBar.setHealthBarPosition(x - size, y - size - 10);

        townHallThread = new Thread(this);
        townHallThread.start();
    }

    @Override
    public void run() {

        if (this.wave.getEnemies() != null && !this.wave.getEnemies().isEmpty()) {

            for (Enemy enemy : this.wave.getEnemies()) {
                double deltaXSquared = Math.pow(posX - enemy.getXPosition(), 2);
                double deltaYSquared = Math.pow(posY - enemy.getYPosition(), 2);
                double distance = Math.sqrt(deltaXSquared + deltaYSquared);
                
                if (distance <= radius) {
                    takeDamage(enemy.damage);
                }
            }

        }

        try {
            // to sleep 1.5 seconds
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // recommended because catching InterruptedException clears interrupt flag
            townHallThread.interrupt();
            // you probably want to quit if the thread is interrupted
            return;
        }

        run();
    }

    /**
     * Removes health from the town hall.
     * @param damage damage of the attacking enemies.
     */
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
        this.healthBar.updateHealthBar(health, maxHealth);

        if (health == 0) {
            //GAME OVER
            gamePanel.endGame();
        }
    }

    /**
     * Draws the town hall.
     * @param g the main game graphics.
     */
    public void paintTownHall(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(posX - size, posY - size, size, size);
    }


}
