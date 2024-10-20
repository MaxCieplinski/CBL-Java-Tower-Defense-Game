package gameclasses;

import java.awt.*;

public class TownHall {
    private HealthBar healthBar;
    private GamePanel gamePanel;

    private int health;
    private int posX;
    private int posY;
    private int size;

    public TownHall(GamePanel gamePanel, int health, int x, int y, int size) {
        this.gamePanel = gamePanel;
        this.health = health;
        this.posX = x;
        this.posY = y;
        this.size = size;

        this.healthBar = new HealthBar(gamePanel, this.health, this.health, size, 5);
        this.healthBar.setHealthBarPosition(x - size, y - size - 10);
    }

    public void paintTownHall(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(posX - size, posY - size, size, size);
    }
}
