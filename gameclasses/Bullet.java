package gameclasses;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bullet extends Entity {
    int x;
    int y;
    double directionX;
    double directionY;
    int speed;
    int size;

    JLabel bulletLabel;
    JPanel gamePanel;
    ArrayList<Bullet> bullets;

    public Bullet(JPanel gamePanel, int startX, int startY, int speed, int size, double directionX, double directionY, ArrayList<Bullet> bullets, ArrayList<Entity> entities) {
        super(startX, startY, entities, size, size);

        this.gamePanel = gamePanel;
        this.x = startX;
        this.y = startY;
        this.speed = speed;
        this.size = size;
        this.directionX = directionX;
        this.directionY = directionY;
        this.bullets = bullets;

        this.bulletLabel = new JLabel();
        this.bulletLabel.setOpaque(true);
        this.bulletLabel.setBackground(Color.yellow);
        this.bulletLabel.setSize(size, size);
        this.bulletLabel.setLocation(this.x, this.y);

        this.gamePanel.setLayout(null);
        this.gamePanel.add(this.bulletLabel);
    }

    public void updatePosition() {
        x += this.directionX * speed;
        y += this.directionY * speed;

        this.bulletLabel.setLocation(x, y);

        if (x < 0 || x > gamePanel.getWidth() || y < 0 || y > gamePanel.getHeight()) {
            destroy();
        }
    }

    public void destroy() {
        bullets.remove(this);
        gamePanel.remove(this.bulletLabel); 
    }
}
