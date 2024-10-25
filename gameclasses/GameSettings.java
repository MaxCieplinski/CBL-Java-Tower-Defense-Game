package gameclasses;

import java.awt.Color;

public class GameSettings {
    public static final int INITIAL_GOLD = 0;
    public static final int TOWN_HALL_HEALTH = 500;
    public static final int PLAYER_DAMAGE = 5;
    public static final int TIME_BEFORE_WAVE_START = 10;
    public static final int BREAK_TIME_BETWEEN_WAVES = 20; //default 8

    public static final int ENEMY_BOSS_DAMAGE = 25;
    public static final int ENEMY_BOSS_HEALTH = 600;
    public static final double ENEMY_BOSS_SPEED = 0.4;
    public static final int ENEMY_BOSS_REWARD = 75;
    public static final int ENEMY_BOSS_WIDTH = 90;
    public static final int ENEMY_BOSS_HEIGHT = 90;
    public static final Color ENEMY_BOSS_COLOR = Color.CYAN;

    public static final int ENEMY_DAMAGE = 10;
    public static final int ENEMY_HEALTH = 50;
    public static final double ENEMY_SPEED = 1; // default 1
    public static final int ENEMY_REWARD = 20;
    public static final int INITIAL_ENEMY_COUNT = 5;
    public static final int ENEMY_WIDTH = 30;
    public static final int ENEMY_HEIGHT = 30;
    public static final Color ENEMY_COLOR = Color.RED;

    public static final int TOWER_DAMAGE = 10;
    public static final int TOWER_PRICE = 50;
    public static final int TOWER_RANGE = 100;

    public static final int WALL_PRICE = 10;

    public static int getEnemyCount(int waveNumber) {
        return INITIAL_ENEMY_COUNT + (int)(Math.pow(waveNumber + 1, 2));
    }
}