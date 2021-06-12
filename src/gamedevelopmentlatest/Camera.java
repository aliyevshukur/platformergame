/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

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
        this.x += ((player.getX() + player.getBounds().width - x) - Game.WIDTH / 2) * 0.02f;
        this.y += ((player.getY() + player.getBounds().height - y) - Game.HEIGHT / 2) * 0.02f;
        if (x <= 0) {
            x = 0;
        }
        if (y <= 0) {
            y = 0;
        }
    }

    @Override
    public void render(Graphics g) {
    }

}
