/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * @author Shukur
 */
public class ImageLoader {

    public static BufferedImage loadImage(String path, Object obj) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(obj.getClass().getResource(path));
        } catch (Exception ex) {
            System.out.println("No pic found");
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
                } else if (red == 255 && green == 255 && blue == 0) {
                    handler.addObject(new Enemy(xx * 16, yy * 16, ID.ENEMY, 16 * size, 24 * size));
                } else if (red == 255 && green == 124 && blue == 53) {
                    handler.addObject(new Spawner(xx * 16, yy * 16, ID.SPAWNER, 20 * size, 25 * size));
                } else if (red == 255 && green == 200 && blue == 200) {
                    handler.addObject(new Wall(xx * 32, yy * 32, ID.WALL, 16 * size, 16 * size));
                }else if (red == 0 && green == 0 && blue == 255) {
                    player.setX(xx);
                    player.setY(yy);
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
