package gameclasses;

import java.awt.*;
import java.util.ArrayList;


public class Wave {

    private int numbOfEnemies;
    private Enemy[] enemies;
    private ArrayList<Entity> entities = new ArrayList<>();

    public Wave(int amountEnemies, Player player) {

        this.numbOfEnemies = amountEnemies;
        enemies = new Enemy[amountEnemies];

        for (int i = 0; i < numbOfEnemies; i++) {
            enemies[i] = new Enemy(player, 100, 10, 2, 50, 0, 0, entities, 30, 30);
        }
    }

    public void moveEnemies() {
        for (Enemy e : enemies) {

            e.setPosition(e.getXPosition() + e.moveX(700, 400, 30),
                         e.getYPosition() + e.moveY(700, 400, 30));
        }    
    }

    public void paintEnemies(Graphics g) {
        moveEnemies();
        for (Enemy e: enemies) {
            e.paintEnemy(g);
        }
    }
}
