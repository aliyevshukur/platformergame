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
public class Camera extends GameObject {

    private final Player player;

    public Camera() {
        super(0, 0, ID.CAMERA);
        this.player = GSpace.getPlayer();
        this.height = GSpace.getPlayer().getHeight();
        this.width = GSpace.getPlayer().getWidth();

    }

    @Override
    public void tick() {
        this.x += ((player.getX() + player.getWidth() - x) - Game.WIDTH / 2) * 0.05f;
        this.y += ((player.getY() + player.getHeight() - y) - Game.HEIGHT / 2) * 0.05f;

        if (x <= 0) {
            x = 0;
        }
        if (y <= 0) {
            y = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fill3DRect( x,  y, 10, 10, true);
    }

}
