package gameclasses;

import com.sun.tools.javac.Main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import javax.swing.*;

/**
 * The panel that the game is run on.
 */
public class GamePanel extends JPanel implements Runnable {

    private final int fps = 60;

    private Thread gameThread;
    private MenuPanel menuPanel;

    //Initializing arraylists.
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Bullet> playerBullets = new ArrayList<>();
    private ArrayList<Bomb> bombs = new ArrayList<>();

    //Initializing game classes.
    private Player player = new Player(0, 0, 4, this, entities, 20, 20, playerBullets);

    private Map map = new Map(25, this, player);

    private Wave wave = new Wave(player, this, map, GameSettings.INITIAL_ENEMY_COUNT, entities);

    private TowerAttack towerAttack = new TowerAttack(wave.getEnemies());

    private TownHall townHall = new TownHall(this, 700, 400, 75, wave, player);

    private EnemyAttack enemyAttack = new EnemyAttack(map.getMap(), wave.getEnemies());

    /**
     * Constructs a gamepanel object.
     */
    public GamePanel(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        //Set panel settings.
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1400, 800));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);

        // Add action listener to handle button click
        menuPanel.townHallAddHealthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                townHall.heal();
            }
        });
    }

    public void addBomb(Bomb b) {
        bombs.add(b);
    }

    public void setTownHallHealPrice(int price) {
        this.menuPanel.setTownHallHealPrice(price);
    }

    /**
     * Starts the game.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            map.initializeGrid();
            wave.startWaveThread();
            towerAttack.startTowerThread();
            enemyAttack.startEnemyThread();
        });

        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            if (timer > 1000000000) {
                timer = 0;
            }
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return wave.getEnemies();
    }

    /**
     * Update method, which is called every time after a set interval.
     */
    public void update() {
        if (map.getMap() != null) {
            if (!player.getCollider().checkForCollision(map.getMap())) {
                player.updatePlayerPosition();
            } else {
                player.resetPlayerPosition();
            }
        }

        if (wave.active) {
            wave.updateWaveState();  // Check wave progress and end if necessary
            wave.moveEnemies();

            towerAttack.updateLists(wave.getEnemies());
            enemyAttack.updateLists(map.getMap(), wave.getEnemies());
        }

        if (!wave.active && !wave.waveInProgress) {
            wave.startWaveThread(); 
        }

        try {
            synchronized (playerBullets) {

                Iterator<Bullet> iterator = playerBullets.iterator();
    
                while (iterator.hasNext()) {

                    Bullet bullet = iterator.next();

                    if (!bullet.getCollider().checkForCollision(wave.getEnemies(), player)) {
                        bullet.updatePosition();
                    }
                }
            }   
        } catch (Exception e) {
            return;
        }

    }

    /**
     * Paints the gamepanel. So it draws the playing field and player.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.drawGrid(g);
        player.paintPlayer(g);
        cleanUpBombs();
        
        for (Bomb bomb : bombs) {
            bomb.paint(g);
        }
        
        if (wave.active) {
            wave.paintEnemies(g);
        }

        towerAttack.showTowerRanges(g);
        updateMenu(Optional.empty());
        townHall.paintTownHall(g); 
    }

    public void cleanUpBombs() {
        bombs.removeIf(bomb -> !bomb.isVisible());
    }

    /**
     * Updates the menu.
     * @param waveStatus status of the wave.
     */
    public void updateMenu(Optional<String> waveStatus) {
        SwingUtilities.invokeLater(() -> {
            menuPanel.setGold(player.getGold());
    
            // Only update wave status if present
            waveStatus.ifPresent(menuPanel::setWave);
    
            // Repaint the menu panel to reflect updates
            menuPanel.repaint();
        });
    }

    /**
     * Ends the game.
     */
    public void endGame() {
        //Stop the game loop
        gameThread = null;
        
        //Stop wave and tower attack threads
        wave.stopWaveThread();
        towerAttack.stopTowerThread();
        enemyAttack.stopEnemyThread();
    
        //Display an end game message.
        JOptionPane.showMessageDialog(this, "Game Over! Thanks for playing!\n"
                                    + "Waves survived: " + wave.waveNumber 
                                    + "\nEnemies destroyed: " + wave.enemiesDestroyedStat);

        System.exit(0);
    }
}
