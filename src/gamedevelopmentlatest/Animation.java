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
 *
 * @author Shukur
 */
public class Animation {

    private static int animationClock = 0;

    static ArrayList<BufferedImage> wall;
    static ArrayList<BufferedImage> ground;
    private static ArrayList<BufferedImage> player;
    static ArrayList<BufferedImage> surprizeBox;
    static ArrayList<BufferedImage> platform;
    static ArrayList<BufferedImage> enemy1;
    private static BufferedImage spreadSheet;

    public Animation() {
        ground = new ArrayList();
        wall = new ArrayList();
        player = new ArrayList();
        surprizeBox = new ArrayList();
        platform = new ArrayList();
        enemy1 = new ArrayList();

        try {
            spreadSheet = ImageIO.read(getClass().getResource("res/spreadSheet.png"));
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
        loadSheet(ID.PLAYER, 16, 32);
        loadSheet(ID.GROUND, 16, 16);
        loadSheet(ID.PLATFORM, 48, 8);
        loadSheet(ID.SURPRIZE_BOX, 16, 16);
        loadSheet(ID.WALL, 16, 16);
        loadSheet(ID.ENEMY, 16, 16);

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
                    begin = 0;
                    count = 14;
                    row = 12;
                    list = player;
                    break;
                case GROUND:
                    begin = 0;
                    count = 1;
                    row = 17;
                    list = player;
                    break;
                case PLATFORM:
                    begin = 20;
                    count = 1;
                    row = 0;
                    list = platform;
                    break;
                case SURPRIZE_BOX:
                    begin = 0;
                    count = 14;
                    row = 4;
                    list = surprizeBox;
                    break;
                case WALL:
                    begin = 8;
                    count = 1;
                    row = 4;
                    list = wall;
                    break;
                case ENEMY:
                    begin = 0;
                    count = 3;
                    row = 5;
                    list = enemy1;
                    break;
                default:
                    list = new ArrayList();
                    break;
            }

        }
        for (int i = begin; 1 < (count + begin); ++i) {
            list.add(spreadSheet.getSubimage(i * 33, row * 33, bitWidth * 33, bitHeight * 33));
        }
        return list;
    }

    public static void animateObject(Graphics g, GameObject obj) {
        if (obj.getId() == ID.PLAYER) {
            animatePlayer((Player) obj, g);
        } else if (obj.getId() == ID.ENEMY) {
            animateObject(g, obj, 3);
        }
    }

    public static void animateObject(Graphics g, GameObject obj, int size) {
        int index = 0;
        
        if (obj.alive) {
            index = 5 -  obj.getX() % 30 / 15;
            g.drawImage(player.get(index + 1),  obj.getX(),  obj.getY(), obj.width, obj.height, null);
        } else {
            g.drawImage(player.get(0),  obj.getX(),  obj.getY(), obj.width, obj.height, null);
        }
    }

    public static void animatePlayer(Player obj, Graphics g) {
        int index = 0;
        // 0-13
        // left 0-6 right 7-13

//        if (obj.onAir) {
//            if (obj.direction == 1) {
//                g.drawImage(player.get(1),  obj.getX(),  obj.getY(), obj.width, obj.height, null);
//            } else {
//                g.drawImage(player.get(12), obj.getX(), obj.getY(), obj.width, obj.height, null);
//            }
//        } else if (obj.direction == 1) {
//            if (obj.velX < -0.5) {
//                index = 5 -  obj.getX() % 30 / 10;
//                g.drawImage(player.get(index),  obj.getX(),  obj.getY(), obj.width, obj.height, null);
//            } else {
//                g.drawImage(player.get(6),  obj.getX(),  obj.getY(), obj.width, obj.height, null);
//            }
//        } else if (obj.direction == 2) {
//            if (obj.velX > 0.5) {
//                index = 10 -  obj.getX() % 30 / 10;
//                g.drawImage(player.get(index),  obj.getX(),  obj.getY(), obj.width, obj.height, null);
//            } else {
//                g.drawImage(player.get(7),  obj.getX(),  obj.getY(), obj.width, obj.height, null);
//            }
//        }
    }

    public static int getAnimationClock() {
        return animationClock;
    }
}
