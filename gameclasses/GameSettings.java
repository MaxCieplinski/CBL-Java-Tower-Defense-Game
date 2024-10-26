package gameclasses;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Contains constant values for the game.
 */
public class GameSettings {


    public static final int INITIAL_GOLD = 50;
    public static final int TOWN_HALL_HEALTH = 500;
    public static final int TOWN_HALL_HEAL_PRICE = 1000;
    public static final int PLAYER_DAMAGE = 15;
    public static final int TIME_BEFORE_WAVE_START = 10;
    public static final int BREAK_TIME_BETWEEN_WAVES = 20; //default 8

    public static final int ENEMY_BOSS_DAMAGE = 35;
    public static final int ENEMY_BOSS_HEALTH = 1200;
    public static final double ENEMY_BOSS_SPEED = 0.4;
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
        return (int) Math.round(0.85 * (INITIAL_ENEMY_COUNT + (int) (Math.pow(waveNumber + 1, 2))));
    }

    public static int getEnemyReward(int waveNumber) {
        if (waveNumber <= 3) {
            return ENEMY_REWARD;
        } else {
            return ENEMY_REWARD - 2;
        }
    }

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

    public static ArrayList<Tower> towers = new ArrayList<>();
}