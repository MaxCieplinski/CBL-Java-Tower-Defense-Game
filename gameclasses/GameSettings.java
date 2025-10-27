package gameclasses;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Contains constant values for the game.
 */
public class GameSettings {
    public static final int INITIAL_GOLD = 100;
    public static final int TOWN_HALL_HEALTH = 500;
    public static final int TOWN_HALL_HEAL_PRICE = 100;
    public static final int TOWN_HALL_HEAL_PERCENTAGE = 10;
    public static final int PLAYER_DAMAGE = 15;
    public static final int PLAYER_BOMB_DAMAGE = 20;
    public static final int PLAYER_BOMB_RADIUS = 75;
    public static final int PLAYER_BOMB_PRICE = 300;
    public static final int TIME_BEFORE_WAVE_START = 10;
    public static final int BREAK_TIME_BETWEEN_WAVES = 20; //default 8

    public static final int ENEMY_BOSS_DAMAGE = 35;
    public static final int ENEMY_BOSS_HEALTH = 1300;
    public static final double ENEMY_BOSS_SPEED = 0.32;
    public static final int ENEMY_BOSS_REWARD = 150;
    public static final int ENEMY_BOSS_WIDTH = 90;
    public static final int ENEMY_BOSS_HEIGHT = 90;
    public static final Color ENEMY_BOSS_COLOR = Color.CYAN;
    public static final int ENEMY_BOSS_BUILDING_DAMAGE = 400;

    public static final int ENEMY_DAMAGE = 10;
    public static final int ENEMY_HEALTH = 50;
    public static final double ENEMY_SPEED = 1; // default 1
    public static final int ENEMY_REWARD = 10;
    public static final int INITIAL_ENEMY_COUNT = 5;
    public static final int ENEMY_WIDTH = 30;
    public static final int ENEMY_HEIGHT = 30;
    public static final Color ENEMY_COLOR = Color.RED;
    public static final int ENEMY_BUILDING_DAMAGE = 20;

    public static final int TOWER_DAMAGE = 10;
    public static final int TOWER_PRICE = 100;
    public static final int TOWER_RANGE = 100;
    public static final int TOWER_HEALTH = 400;

    public static final int WALL_PRICE = 20;
    public static final int WALL_HEALTH = 100;

    public static int getEnemyCount(int waveNumber) {
        return INITIAL_ENEMY_COUNT + (int) (Math.pow(waveNumber + 1, 2));
    }

    /**
     * Method that returns the enemy reward, reduces enemy reward in later waves.
     * @return the enemy reward.
     */
    public static int getEnemyReward(int waveNumber) {
        if (waveNumber <= 3) {
            return ENEMY_REWARD;
        } else {
            return ENEMY_REWARD - 2;
        }
    }

    /**
     * Method that returns the price of healing town hall, the price is higher in later waves.
     * @return the price it takes for the town hall to get +10% health.
     */
    public static int getTownHallHealthPrice(int waveNumber) {
        if (waveNumber > 5) {
            if (waveNumber < 3) {
                return TOWN_HALL_HEAL_PRICE * 3;
            } else if (waveNumber < 6) {
                return TOWN_HALL_HEAL_PRICE * 5;
            } else if (waveNumber < 10) {
                return TOWN_HALL_HEAL_PRICE * 7;
            } else {
                return TOWN_HALL_HEAL_PRICE * 10;
            }
        }

        return TOWN_HALL_HEAL_PRICE;
    }

    /**
     * Method that tries to return the tower sprite image.
     * @return the tower sprite image.
     */
    public static Image getTowerSprite() {
        try {
            Image sprite = ImageIO.read(new File("sprites/TowerSprite.png"));
            return sprite;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Method that tries to return the wall sprite image.
     * @return the wall sprite image.
     */
    public static Image getWallSprite() {
        try {
            Image sprite = ImageIO.read(new File("sprites/WallSprite.png"));
            return sprite;
        } catch (IOException e) {
            return null;
        }
    }

    public static ArrayList<Tower> towers = new ArrayList<>();
}