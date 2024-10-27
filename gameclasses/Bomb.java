package gameclasses;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Bomb {
    private int damage = GameSettings.PLAYER_BOMB_DAMAGE;
    private int radius = GameSettings.PLAYER_BOMB_RADIUS;
    private boolean isDrawing = false;
    private int bombX, bombY;
    private ArrayList<Enemy> enemies;
    private Timer drawTimer;
    private float alpha = 0.8f;
    private float alphaDecrement = 0.2f;

    public Bomb(int x, int y, ArrayList<Enemy> enemies) {
        this.bombX = x;
        this.bombY = y;
        this.isDrawing = true;
        this.enemies = enemies;

        for (Enemy enemy : enemies) {
            if (enemyInRange(enemy)) {
                enemy.takeDamage(damage);
            }
        }

        drawTimer = new Timer(100, e -> updateTransparency()); // Update every 100ms
        drawTimer.setRepeats(true);
        drawTimer.start();
    }

    private boolean enemyInRange(Enemy enemy) {
        double deltaXSquared = Math.pow(bombX - enemy.getXPosition(), 2);
        double deltaYSquared = Math.pow(bombY - enemy.getYPosition(), 2);

        double distance = Math.sqrt(deltaXSquared + deltaYSquared);

        if (distance <= radius) {
            return true;
        }

        return false;
    }

    private void updateTransparency() {
        alpha -= alphaDecrement;
        if (alpha <= 0) {
            alpha = 0;
            stopDrawing();
        }
    }

    public void stopDrawing() {
        isDrawing = false;
        drawTimer.stop();
    }

    public void paint(Graphics g) {
        if (isDrawing) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.setColor(Color.lightGray);
            g2d.fillOval(bombX - radius, bombY - radius, 2 * radius, 2 * radius);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }

    public boolean isVisible() {
        return isDrawing;
    }

    public int getX() {
        return bombX;
    }

    public int getY() {
        return bombY;
    }
}
