package gameclasses;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bullet extends Entity {
    double directionX;
    double directionY;
    int speed;


    JLabel bulletLabel;
    JPanel gamePanel;
    ArrayList<Bullet> bullets;
    int damage;

    public Bullet(JPanel gamePanel, int startX, int startY, int speed, int size, double directionX, 
                double directionY, ArrayList<Bullet> bullets, ArrayList<Entity> entities, int damage) {
        super(startX, startY, entities, size, size);

        this.gamePanel = gamePanel;
        this.speed = speed;
        this.directionX = directionX;
        this.directionY = directionY;
        this.bullets = bullets;
        this.damage = damage;

        this.bulletLabel = new JLabel();
        this.bulletLabel.setOpaque(true);
        this.bulletLabel.setBackground(Color.yellow);
        this.bulletLabel.setSize(size, size);
        this.bulletLabel.setLocation( (int) this.x, (int) this.y);

        this.gamePanel.setLayout(null);
        this.gamePanel.add(this.bulletLabel);
    }

    public void updatePosition() {
        x += this.directionX * speed;
        y += this.directionY * speed;

        this.bulletLabel.setLocation((int) x, (int) y);

        if (x < 0 || x > gamePanel.getWidth() || y < 0 || y > gamePanel.getHeight()) {
            destroy();
        }
    }

    public void destroy() {
        bullets.remove(this);
        gamePanel.remove(this.bulletLabel); 
    }

    public void handleHit(Enemy e) {
        e.takeDamage(damage);
    }
}
