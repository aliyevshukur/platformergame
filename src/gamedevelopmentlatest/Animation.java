/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * @author Shukur
 */
public class Animation {

    private static int animationClock = 0;

    static ArrayList<BufferedImage> wall;
    static ArrayList<BufferedImage> ground;
    static ArrayList<BufferedImage> player;
    static ArrayList<BufferedImage> surprizeBox;
    static ArrayList<BufferedImage> platform;
    static ArrayList<BufferedImage> enemy1;
    static ArrayList<BufferedImage> bullet;
    static ArrayList<BufferedImage> rockThrower;
    static ArrayList<BufferedImage> spawner;
    static ArrayList<BufferedImage> enemyThrowItem;

    private static BufferedImage spreadSheet;

    public Animation() {
        ground = new ArrayList();
        wall = new ArrayList();
        player = new ArrayList();
        surprizeBox = new ArrayList();
        platform = new ArrayList();
        enemy1 = new ArrayList();
        bullet = new ArrayList();
        rockThrower = new ArrayList();
        spawner = new ArrayList();
        enemyThrowItem = new ArrayList();

        try {
            spreadSheet = ImageIO.read(getClass().getResource("res/tileset.png"));
        } catch (Exception e) {
            System.out.println("Found no spreadsheet" + e);
        }

        loadSpreadSheet();

    }

    public static int getClock() {
        return animationClock;
    }

    public static void tick() {
        ++animationClock;
        if (animationClock > 60) {
            animationClock = 0;
        }
    }

    private void loadSpreadSheet() {
        loadSheet(ID.PLAYER, 16, 24);
        loadSheet(ID.GROUND, 16, 16);
        loadSheet(ID.PLATFORM, 48, 16);
        loadSheet(ID.SURPRIZE_BOX, 16, 16);
        loadSheet(ID.WALL, 16, 16);
        loadSheet(ID.ENEMY, 16, 24);
        loadSheet(ID.BULLET, 21, 10);
        loadSheet(ID.ROCK_THROWER_ENEMY, 20, 32);
        loadSheet(ID.SPAWNER, 24, 32);
        loadSheet(ID.ENEMY_THROW_ITEM, 16, 16);

    }

    private ArrayList<BufferedImage> loadSheet(ID id, int bitWidth, int bitHeight) {
        int count = 1;
        int row = 0;
        int begin = 0;

        ArrayList<BufferedImage> list;

        if (null == id) {
            list = new ArrayList();
        } else {
            switch (id) {

                case PLAYER:
                    begin = 0; //col
                    count = 24; //row
                    row = 17; //range
                    list = player;
                    break;
                case GROUND:
                    begin = 0;
                    count = 0;
                    row = 6;
                    list = ground;
                    break;
                case PLATFORM:
                    begin = 5;
                    count = 20;
                    row = 0;
                    list = platform;
                    break;
                case WALL:
                    begin = 14;
                    count = 1;
                    row = 1;
                    list = wall;
                    break;
                case ENEMY:
                    begin = 0;
                    count = 26;
                    row = 15;
                    list = enemy1;
                    break;
                case BULLET:
                    begin = 10;
                    count = 0;
                    row = 5;
                    list = bullet;
                    break;
                case ROCK_THROWER_ENEMY:
                    begin = 0;
                    count = 28;
                    row = 9;
                    list = rockThrower;
                    break;
                case SPAWNER:
                    begin = 5;
                    count = 1;
                    row = 3;
                    list = spawner;
                    break;
                case ENEMY_THROW_ITEM:
                    begin = 10;
                    count = 3;
                    row = 8;
                    list = enemyThrowItem;
                    break;
                default:
                    list = new ArrayList();
                    break;
            }

        }
        int step = 16;
        if (id == ID.SPAWNER || id == ID.ROCK_THROWER_ENEMY) {
            step = 32;
        }
        for (int i = begin; i <= (row + begin); ++i) {
            list.add(spreadSheet.getSubimage(step * i, 16 * count, bitWidth, bitHeight));
        }

        return list;
    }

    public static void animateObject(Graphics g, GameObject obj) {
        int index = 0;

        int leftEnd = 0;
        int rightEnd = 0;

        ArrayList<BufferedImage> array = new ArrayList<>();

        if (null != obj.getId()) {
            switch (obj.getId()) {
                case ENEMY_THROW_ITEM:
                    leftEnd = 3;
                    rightEnd = 7;
                    array = enemyThrowItem;
                    break;
                default:
                    break;
            }
        }

        if (obj.direction == 1) {
            index = leftEnd - obj.getX() % ((leftEnd + 1) * 10) / 10;
            g.drawImage(array.get(index), obj.getX(), obj.getY(), obj.width, obj.height, null);
        } else if (obj.direction == 2) {
            index = rightEnd - obj.getX() % ((leftEnd + 1) * 10) / 10;
            g.drawImage(array.get(index), obj.getX(), obj.getY(), obj.width, obj.height, null);

        }

    }

    public static void animateCharacter(Graphics g, Character obj) {
        int index = 0;

        int leftEnd = 0;
        int rightEnd = 0;
        int jumpLeft = 0;
        int jumpRight = 0;
        int idle = 1;
        ArrayList<BufferedImage> array = new ArrayList<>();

        if (null != obj.getId()) {
            switch (obj.getId()) {
                case PLAYER:
                    leftEnd = 8;
                    rightEnd = 17;
                    jumpLeft = 2;
                    jumpRight = 13;
                    idle = 10;
                    array = player;
                    break;
                case ENEMY:
                    leftEnd = 7;
                    rightEnd = 15;
                    jumpLeft = 3;
                    jumpRight = 14;
                    idle = 5;
                    array = enemy1;
                    break;
                case ROCK_THROWER_ENEMY:
                    leftEnd = 4;
                    rightEnd = 9;
                    jumpRight = 7;
                    jumpLeft = 2;
                    idle = obj.direction == 1 ? 4 : 6;
                    array = rockThrower;
                    break;
                case SPAWNER:
                    leftEnd = 1;
                    rightEnd = 0;
                    jumpRight = 0;
                    idle = 1;
                    jumpLeft = 7;
                    array = spawner;
                    break;
                case ENEMY_THROW_ITEM:
                    leftEnd = 3;
                    rightEnd = 3;
                    jumpRight = 0;
                    idle = 0;
                    jumpLeft = 0;
                    array = enemyThrowItem;
                    break;
                default:
                    break;
            }
        }

        if (obj.alive) {
            if (obj.onAir) {
                if (obj.direction == 1) {
                    g.drawImage(array.get(jumpLeft), obj.getX(), obj.getY(), obj.width, obj.height, null);
                } else {
                    g.drawImage(array.get(jumpRight), obj.getX(), obj.getY(), obj.width, obj.height, null);
                }
            } else if (obj.direction == 1) {

                index = obj.velX < -0.5 ? leftEnd - obj.getX() % ((leftEnd + 1) * 10) / 10 : idle - animationClock % 20 / 10;

                g.drawImage(array.get(index), obj.getX(), obj.getY(), obj.width, obj.height, null);

            } else if (obj.direction == 2) {

                index = obj.velX > 0.5 ? rightEnd - obj.getX() % ((leftEnd + 1) * 10) / 10 : idle - animationClock % 20 / 10;

                g.drawImage(array.get(index), obj.getX(), obj.getY(), obj.width, obj.height, null);

            }
        } else {
            index = idle - animationClock % 20 / 10;
            g.drawImage(array.get(index), obj.getX(), obj.getY(), obj.width, obj.height, null);
        }
    }

    public static int getAnimationClock() {
        return animationClock;
    }
}
