/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Ziya
 */
public class Platform extends GameObject {

    private final Color color;
    private boolean playerOn = false;

    public Platform(int x, int y, ID id, int width, int height) {
        super(x, y, id);
        this.color = Color.MAGENTA;
        this.width = width;
        this.height = height;
        isFalling = false;
    }

    @Override
    public void tick() {
        if (playerOn) {
            x += Math.round(velX);
            GSpace.getPlayer().setX(GSpace.getPlayer().getX() + Math.round(velX));
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Animation.platform.get(0), x, y, width, height, null);
    }

}
