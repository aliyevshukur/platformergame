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
 * @author Shukur
 */
public class Bullet extends GameObject {

    float speed = 5;
    private int playerDirection = GSpace.getPlayer().getDirection();
    
    public Bullet(int x, int y, ID id, int direction) {
        super(x, y, id);
        velX = (direction == 1) ? -speed : speed;
        velY = speed;
        width = 21;
        height = 10;

    }

    @Override
    public void tick() {

        x += Math.round(velX);

    }

    @Override
    public void render(Graphics g) {

        if (playerDirection == 1) {
            g.drawImage(Animation.bullet.get(0), x, y, width, height, null);
        } else {
            g.drawImage(Animation.bullet.get(2), x, y, width, height, null);
        }
    }

}
