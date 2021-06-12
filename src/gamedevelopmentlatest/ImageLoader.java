/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author Shukur
 */
public class ImageLoader {

    public static BufferedImage loadImage(String path, Object obj) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(obj.getClass().getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

        return image;
    }

    public static void loadLevel(BufferedImage image, Handler handler, Player player) {

        handler.clear();
        int size = GSpace.multSize;
        int w = image.getWidth();
        int h = image.getHeight();
        for (int yy = 0; yy < h; yy++) {

            for (int xx = 0; xx < w; xx++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0x0ff;
                int green = (pixel >> 8) & 0x0ff;
                int blue = pixel & 0x0ff;

                if (red == 255 && green == 0 && blue == 0) {
                    handler.addObject(new Platform(xx * 32, yy * 32, ID.PLATFORM, 48 * size, 8 * size));
                } else if (red == 0 && green == 255 && blue == 0) {
                    handler.addObject(new Ground(xx * 32, yy * 32, ID.GROUND, 16 * size, 16 * size, 0));
                } else if (red == 100 && green == 100 && blue == 100) {
                    handler.addObject(new Ground(xx * 32, yy * 32, ID.GROUND, 16 * size, 16 * size, 4));
                } else if (red == 120 && green == 120 && blue == 120) {
                    handler.addObject(new Ground(xx * 32, yy * 32, ID.GROUND, 16 * size, 16 * size, 1));
                } else if (red == 140 && green == 140 & blue == 140) {
                    handler.addObject(new Ground(xx * 32, yy * 32, ID.GROUND, 16 * size, 16 * size, 2));
                } else if (red == 160 && green == 100 & blue == 100) {
                    handler.addObject(new Box(xx * 32, yy * 32, ID.BOX, 16 * size, 16 * size));
                } else if (red == 255 && green == 255 & blue == 10) {
                    handler.addObject(new Ladder(xx * 32, yy * 32, ID.LADDER, 16 * size, 16 * size));
                } else if (red == 255 && green == 255 && blue == 0) {
                    handler.addObject(new Enemy(xx * 16, yy * 16, ID.ENEMY, 16 * size, 24 * size));
                } else if (red == 255 && green == 124 && blue == 53) {
                    handler.addObject(new Spawner(xx * 32, yy * 30 + 12, ID.SPAWNER, 26 * size, 32 * size, new Enemy(xx * 32, yy * 28, ID.ENEMY, 16 * size, 24 * size)));
                } else if (red == 245 && green == 114 && blue == 43) {
                    handler.addObject(new Spawner(xx * 32, yy * 30, ID.SPAWNER2, 26 * size, 32 * size, new RockThrowerEnemy(xx * 32, yy * 28, ID.ROCK_THROWER_ENEMY, 20 * size, 32 * size)));
                } else if (red == 255 && green == 200 && blue == 200) {
                    handler.addObject(new Wall(xx * 32, yy * 32, ID.WALL, 16 * size, 16 * size));
                } else if (red == 0 && green == 0 && blue == 255) {
                } else if (red == 140 && green == 165 && blue == 255) {
                    handler.addObject(new RockThrowerEnemy(xx * 16, yy * 16, ID.ROCK_THROWER_ENEMY, 20 * size, 32 * size));
                } else if (red == 100 && green == 255 && blue == 200) {
                    handler.addObject(new Decorations(xx * 32, yy * 25, ID.DECORATION, 96 * size, 96 * size, 0));
                } else if (red == 200 && green == 255 && blue == 200) {
                    handler.addObject(new Decorations(xx * 32, yy * 25, ID.DECORATION, 96 * size, 96 * size, 1));
                } else if (red == 10 && green == 20 && blue == 100) {
                    handler.addObject(new Trambolin(xx * 32, yy * 32, ID.TRAMBOLIN, 48 * size, 16 * size));
                } else if (red == 0 && green == 0 && blue == 255) {
                    player.setX(300);
                    player.setY(500);
                    handler.addObject(player);
                }
            }
        }

//        handler.sort();
        handler.removeObject(player);
        handler.addObject(player);
//        handler.addObject(new Platform(100, 130, ID.PLATFORM));
    }
}
