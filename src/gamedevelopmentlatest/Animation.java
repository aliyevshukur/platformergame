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


        try {
            spreadSheet = ImageIO.read(getClass().getResource("res/tileset2.png"));
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
//                case SURPRIZE_BOX:
//                    begin = 9;
//                    count = 0;
//                    row = 0;
//                    list = surprizeBox;
//                    break;
//                case WALL:
//                    begin = 8;
//                    count = 1;
//                    row = 4;
//                    list = wall;
//                    break;
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
                    count = 12;
                    row = 10;
                    list = rockThrower;
                    break;
                default:
                    list = new ArrayList();
                    break;
            }

        }
        for (int i = begin; i <= (row + begin); ++i) {
            list.add(spreadSheet.getSubimage(16 * i, 16 * count, bitWidth, bitHeight));
        }


        return list;
    }

    public static void animateObject(Graphics g, GameObject obj) {
        if (obj.getId() == ID.PLAYER) {
            animatePlayer((Player) obj, g);
        } else if (obj.getId() == ID.ENEMY) {
            animateObject(g, obj, 3);
        } else if (obj.getId() == ID.SPAWNER) animateObject(g, obj, 12);
        else if (obj.getId() == ID.ROCK_THROWER_ENEMY) animateObject(g, obj, 5);
    }

    public static void animateObject(Graphics g, GameObject obj, int size) {
        int index = 0;

        int leftEnd = 0;
        int rightEnd = 0;
        int jumpLeft = 0;
        int jumpRight = 0;
        int idle = 0;
        ArrayList<BufferedImage> array = new ArrayList<>();
        if (obj.getId() == ID.ENEMY) {
            leftEnd = 7;
            rightEnd = 15;
            jumpLeft = 2;
            jumpRight = 13;
            idle = 5;
            array = enemy1;
        } else if (obj.getId() == ID.ROCK_THROWER_ENEMY) {
            leftEnd = 4;
            rightEnd = 9;
            jumpRight = 7;
            idle = 5;
            jumpLeft = 2;
            array = rockThrower;
            System.out.println("Animation" + array.toString());
        } else if (obj.getId() == ID.SPAWNER) {
            leftEnd = 7;
            rightEnd = 15;
            jumpRight = 13;
            idle = 5;
            jumpLeft = 2;
            array = enemy1;
        }


        if (obj.alive) {
            if (obj.onAir) {
                if (obj.direction == 1) {
                    g.drawImage(array.get(jumpLeft), obj.getX(), obj.getY(), obj.width, obj.height, null);
                } else {
                    System.out.println(jumpRight);
                    System.out.println("size" + array.size());
                    System.out.println(obj.getId());
                    g.drawImage(array.get(jumpRight), obj.getX(), obj.getY(), obj.width, obj.height, null);
                }
            } else if (obj.direction == 1) {
                index = obj.velX < -0.5 ? leftEnd - obj.getX() % ((leftEnd + 1) * 10) / 10 : idle - animationClock % 20 / 10;
                g.drawImage(array.get(index), obj.getX(), obj.getY(), obj.width, obj.height, null);
            } else if (obj.direction == 2) {
                index = obj.velX > 0.5 ? rightEnd - obj.getX() % ((leftEnd + 1) * 10) / 10 : idle - animationClock % 20 / 10;
            }
            g.drawImage(array.get(index), obj.getX(), obj.getY(), obj.width, obj.height, null);
        } else {
            index = idle - animationClock % 20 / 10;
            g.drawImage(array.get(index), obj.getX(), obj.getY(), obj.width, obj.height, null);
        }

        //      15 tile 0-7 left 8-15 right
    }


    public static void animatePlayer(Player obj, Graphics g) {
        int index = 0;
        // 0-18
        // left 0-8 right 9-17 jump 6 -8 down 9

        if (obj.onAir) {
            if (obj.direction == 1) {
                g.drawImage(player.get(3), obj.getX(), obj.getY(), obj.width, obj.height, null);
            } else {
                g.drawImage(player.get(13), obj.getX(), obj.getY(), obj.width, obj.height, null);
            }
        } else if (obj.direction == 1) {
            if (obj.velX < -0.5) {
                index = 8 - obj.getX() % 90 / 10;
                g.drawImage(player.get(index), obj.getX(), obj.getY(), obj.width, obj.height, null);
            } else {
                g.drawImage(player.get(8), obj.getX(), obj.getY(), obj.width, obj.height, null);
            }
        } else if (obj.direction == 2) {
            if (obj.velX > 0.5) {
                index = 17 - obj.getX() % 90 / 10;
                g.drawImage(player.get(index), obj.getX(), obj.getY(), obj.width, obj.height, null);
            } else {

                g.drawImage(player.get(10), obj.getX(), obj.getY(), obj.width, obj.height, null);
            }
        }
    }

    public static int getAnimationClock() {
        return animationClock;
    }
}
