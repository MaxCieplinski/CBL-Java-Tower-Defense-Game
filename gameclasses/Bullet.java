package gameclasses;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Bullet class, which handles code for a bullet that player shoots.
 * Inherits from class Entity.
 */
public class Bullet extends Entity {

    private double directionX;
    private double directionY;

    private int speed;
    private int damage = GameSettings.PLAYER_DAMAGE;

    private JLabel bulletLabel;
    
    private JPanel gamePanel;

    private ArrayList<Bullet> bullets;

    /**
     * Constructs a bullet object.
     * @param gamePanel the main game panel.
     * @param startX starting x-coordinate of bullet.
     * @param startY starting y-coordinate of bullet.
     * @param speed speed of the bullet.
     * @param size size of the bullet.
     * @param directionX the direction in terms of x the bullet is going to.
     * @param directionY the direction in terms of y the bullet is going to.
     * @param bullets arraylist of all bullets in the game.
     * @param entities arraylist of all entities in the game.
     * @param damage the damage of the bullet.
     */
    public Bullet(JPanel gamePanel, int startX, int startY, int speed, int size, double directionX, 
                double directionY, ArrayList<Bullet> bullets, ArrayList<Entity> entities) {

        super(startX, startY, entities, size, size);

        this.gamePanel = gamePanel;
        this.speed = speed;
        this.directionX = directionX;
        this.directionY = directionY;
        this.bullets = bullets;

        this.bulletLabel = new JLabel();
        this.bulletLabel.setOpaque(true);
        this.bulletLabel.setBackground(Color.yellow);
        this.bulletLabel.setSize(size, size);
        this.bulletLabel.setLocation((int) this.x, (int) this.y);

        this.gamePanel.setLayout(null);
        this.gamePanel.add(this.bulletLabel);
    }

    /**
     * Updates bullet position.
     */
    public void updatePosition() {
        x += this.directionX * speed;
        y += this.directionY * speed;

        this.bulletLabel.setLocation((int) x, (int) y);

        if (x < 0 || x > gamePanel.getWidth() || y < 0 || y > gamePanel.getHeight()) {
            destroy();
        }
    }

    /**
     * Destroys the bullet.
     */
    public void destroy() {
        bullets.remove(this);
        gamePanel.remove(this.bulletLabel); 
    }

    /**
     * Handles when the bullet hits an enemy.
     * @param e the enemy that is
     */
    public void handleHit(Enemy e) {
        e.takeDamage(damage);
    }
}
