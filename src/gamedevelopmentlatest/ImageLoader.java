/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
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
        for (int xx = 0; xx < w; xx++) {
            for (int yy = 0; yy < h; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0x0ff;
                int green = (pixel >> 8) & 0x0ff;
                int blue = pixel & 0x0ff;

                if (red == 255 && green == 0 && blue == 0) {
                    handler.addObject(new Platform(xx * 16, yy * 16, ID.PLATFORM, 48 * size, 8 * size));
                } else if (red == 0 && green == 255 && blue == 0) {
                    handler.addObject(new Ground(xx * 16, yy * 16, ID.GROUND, 16 * size, 16 * size));
                } else if (red == 255 && green == 255 && blue == 0) {
                    handler.addObject(new Enemy(xx * 16, yy * 16, ID.ENEMY, 16 * size, 24 * size));
                } else if (red == 0 && green == 0 && blue == 255) {
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
