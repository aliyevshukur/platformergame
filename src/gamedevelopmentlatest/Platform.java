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

    public Platform(int x, int y, ID id) {
        super(x, y, id);
        this.color = Color.MAGENTA;
        width = 128;
        height = 16;
        hasGravity = false;
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
        g.setColor(color);
        g.fill3DRect( x,  y, width, height, true);
//        g.drawImage(Animation.platform.get(0),  x,  y, width, height, null);
    }

}
