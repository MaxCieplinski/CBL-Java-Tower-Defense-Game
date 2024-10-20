package gameclasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

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

    ArrayList<Bullet> bullets;
    ArrayList<Entity> entities;

    public boolean waveStarted = false;

    /**
     * Initialize player with basic starting values.
     */
    public Player(double x, double y, int speed, JPanel panel, 
                ArrayList<Entity> entities, double width, 
                double height, ArrayList<Bullet> bullets) {

        super(x, y, entities, width, height);
                
        this.panel = panel;
        this.speed = speed;
        this.health = 100;
        this.gold = 1000;
        this.bullets = bullets;
        this.entities = entities;

        this.panel.setFocusable(true);
        this.panel.requestFocusInWindow();
        
        this.panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (waveStarted) {
                    shootBullet(e);
                    panel.requestFocusInWindow();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });

        setupKeyBindings();
    }

        private void setupKeyBindings() {
        // Define input maps for the panel
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        // Up movement (W key)
        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up = true;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("released W"), "stopUp");
        actionMap.put("stopUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up = false;
            }
        });

        // Down movement (S key)
        inputMap.put(KeyStroke.getKeyStroke("S"), "moveDown");
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                down = true;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("released S"), "stopDown");
        actionMap.put("stopDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                down = false;
            }
        });

        // Left movement (A key)
        inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                left = true;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("released A"), "stopLeft");
        actionMap.put("stopLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                left = false;
            }
        });

        // Right movement (D key)
        inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                right = true;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("released D"), "stopRight");
        actionMap.put("stopRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                right = false;
            }
        });
    }

    private void shootBullet(MouseEvent mouseEvent) {
        int mouseX = mouseEvent.getX();
        int mouseY = mouseEvent.getY();
                    
        double angleRadians = Math.atan2(mouseY - super.getYPosition(), 
                                        mouseX - super.getXPosition());
        double directionX = Math.cos(angleRadians);
        double directionY = Math.sin(angleRadians);

        Bullet bullet = new Bullet(this.panel, (int) super.getXPosition(), 
                                (int) super.getYPosition(), 8, 10, 
                                directionX, directionY, bullets, entities, 30);

        bullets.add(bullet);
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