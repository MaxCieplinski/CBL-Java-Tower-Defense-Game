package gameclasses;

import java.awt.*;

public class TownHall implements Runnable {
    Thread townHallThread;
    private HealthBar healthBar;
    private GamePanel gamePanel;
    Wave wave;

    private int health;
    private int posX;
    private int posY;
    private int size;
    private int radius = 50;

    public TownHall(GamePanel gamePanel, int health, int x, int y, int size, Wave wave) {
        this.gamePanel = gamePanel;
        this.health = health;
        this.posX = x;
        this.posY = y;
        this.size = size;
        this.wave = wave;

        this.healthBar = new HealthBar(gamePanel, this.health, this.health, size, 5);
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
            // to sleep 10 seconds
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // recommended because catching InterruptedException clears interrupt flag
            townHallThread.interrupt();
            // you probably want to quit if the thread is interrupted
            return;
        }

        run();
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
        this.healthBar.updateHealthBar(health);

        if (health == 0) {
            //GAME OVER
            gamePanel.endGame();
        }
    }

    public void paintTownHall(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(posX - size, posY - size, size, size);
    }


}
