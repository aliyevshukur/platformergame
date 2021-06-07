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

    public Bullet(int x, int y, ID id, int direction) {
        super(x, y, id);
        velX = (direction == 1) ? -speed : speed;
        velY = speed / 5;
        width = 5;
        height = 5;

    }

    @Override
    public void tick() {
        velY += speed / 300;
        x += Math.round(velX);
        y += Math.round(velY);

    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.yellow);
//        g.drawOval((int) x, (int) y, width, height);
        g.drawImage(Animation.ground.get(0),  x,  y, width, height, null);
    }

}
