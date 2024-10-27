package gameclasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * Player class.
 */
public class Player extends Entity {
    private GamePanel panel;
    private int speed;
    private int gold = GameSettings.INITIAL_GOLD;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean shiftPressed = false;

    private ArrayList<Bullet> bullets;
    private ArrayList<Entity> entities;

    private Timer shootTimer;
    private static final int SHOOT_DELAY = 150;

    public boolean waveStarted = false;

    /**
     * Initialize player with basic starting values.
     * @param x the x position of the player.
     * @param y the y position of the player.
     * @param speed the speed of the player.
     * @param panel the panel the game is rendered on (not to be confused with menu panel).
     * @param entities an arraylist containing all the entities.
     * @param width the width of the player.
     * @param height the height of the player.
     * @param bullets an arraylist containing all the bullets.
     */
    public Player(double x, double y, int speed, GamePanel panel, 
                ArrayList<Entity> entities, double width, 
                double height, ArrayList<Bullet> bullets) {

        super(x, y, entities, width, height);
                
        this.panel = panel;
        this.speed = speed;
        this.bullets = bullets;
        this.entities = entities;
        
        this.panel.setFocusable(true);
        this.panel.requestFocusInWindow();

        shootTimer = new Timer(SHOOT_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shootBullet();
            }
        });
        
        this.panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (waveStarted) {
                    if (shiftPressed) {
                        if (getGold() >= GameSettings.PLAYER_BOMB_PRICE) {
                            panel.addBomb(new Bomb(e.getX(), e.getY(), panel.getEnemies()));
                            panel.repaint();
                            subtractGold(GameSettings.PLAYER_BOMB_PRICE);
                        }
                    } else {
                        shootBullet(e);
                        shootTimer.start();
                        panel.requestFocusInWindow();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                shootTimer.stop();
                
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


    /**
    * Sets up the key bindings.
    */
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

        // Shift
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, 1, false), "ShiftPressed");
        actionMap.put("ShiftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shiftPressed = true;
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, 0, true), "ShiftReleased");
        actionMap.put("ShiftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shiftPressed = false;
            }
        });
    }

    /**
     * Method to shoot bullets.
     * @param mouseEvent mouseEvenet of input handling.
     */
    private void shootBullet(MouseEvent mouseEvent) {
        int mouseX = mouseEvent.getX();
        int mouseY = mouseEvent.getY();
                    
        double angleRadians = Math.atan2(mouseY - super.getYPosition(), 
                                        mouseX - super.getXPosition());
        double directionX = Math.cos(angleRadians);
        double directionY = Math.sin(angleRadians);

        Bullet bullet = new Bullet(this.panel, (int) super.getXPosition(), 
                                (int) super.getYPosition(), 8, 10, 
                                directionX, directionY, bullets, entities);

        bullets.add(bullet);
    }

    /**
     * Same method as shootBullet but takes no parameters.
     * Used for overloading.
     */
    private void shootBullet() {
        int mouseX = (int) panel.getMousePosition().getX();
        int mouseY = (int) panel.getMousePosition().getY();
                    
        double angleRadians = Math.atan2(mouseY - super.getYPosition(), 
                                        mouseX - super.getXPosition());
        double directionX = Math.cos(angleRadians);
        double directionY = Math.sin(angleRadians);

        Bullet bullet = new Bullet(this.panel, (int) super.getXPosition(), 
                                (int) super.getYPosition(), 8, 10, 
                                directionX, directionY, bullets, entities);

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
     * Method to read the current player gold.
     * @return current player gold value.
     */
    public int getGold() {
        return gold;
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