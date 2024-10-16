package gameclasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Player class.
 */
public class Player extends Entity {
    JPanel panel;

    int health;
    int gold;

    int speed;

    boolean up;
    boolean down;
    boolean left;
    boolean right;



    /**
     * Initialize player with basic starting values.
     */
    public Player(double x, double y, int speed, JPanel panel, 
                ArrayList<Entity> entities, double width, double height) {

        super(x, y, entities, width, height);
                
        this.panel = panel;
        this.speed = speed;
        this.health = 100;
        this.gold = 1000;

        this.panel.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP) {
                    up = true;
                }

                if (key == KeyEvent.VK_DOWN) {
                    System.out.println("DOWN");
                    down = true;
                }

                if (key == KeyEvent.VK_LEFT) {
                    left = true;
                }

                if (key == KeyEvent.VK_RIGHT) {
                    right = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_UP) {
                    up = false;
                }

                if (key == KeyEvent.VK_DOWN) {
                    down = false;
                }

                if (key == KeyEvent.VK_LEFT) {
                    left = false;
                }

                if (key == KeyEvent.VK_RIGHT) {
                    right = false;
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
    }

    /**
     * Updates player position based on boolean values of: up, down, left, right.
     */
    public void updatePlayerPosition() {
        double posY = super.getYPosition();
        double posX = super.getXPosition();
        if (up) {
            posY = Math.max(posY -= speed, 0);
        }

        if (down) {
            posY = Math.min(posY += speed, panel.getHeight() - super.getHeight());
        }

        if (left) {
            posX = Math.max(posX - speed, 0);
        }

        if (right) {
            posX = Math.min(posX + speed, panel.getWidth() - super.getWidth());
        }

        //Update entity position for later purposes.
        super.setPosition(posX, posY);

        //System.out.println("X: " + super.getXPosition());
        //System.out.println("Y: " + super.getYPosition());
    }

    /**
     * Resets the player position to one movement before.
     */
    public void resetPlayerPosition() {
        super.setPosition(super.getOldX(), super.getOldY());

    }

    /**
     * Draws the player.
     * @param g graphics of the player.
     */
    public void paintPlayer(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int) super.getXPosition(), (int) super.getYPosition(),
                    (int) super.getWidth(), (int) super.getHeight());

    }

    /**
     * Method to get the current player health.
     * @return current player health value.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Method to set the player health.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Method to read the current player gold.
     * @return current player gold value.
     */
    public int getGold() {
        return gold;
    }

    /**
     * Method to set the player gold.
     * @param gold the amount of gold.
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * Method to add gold to player.
     * @param gold the amount of gold.
     */
    public void addGold(int gold) {
        this.gold += gold;
    }

    /**
     * Method to subtract gold from player.
     * @param gold the amount of gold.
     */
    public void subtractGold(int gold) {
        this.gold -= gold;
    }

}