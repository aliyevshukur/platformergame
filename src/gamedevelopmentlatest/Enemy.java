/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.Graphics;

/**
 *
 * @author Shukur
 */
public class Enemy extends Character {

    public int hitCount = 0;

    public Enemy(int x, int y, ID id, int width, int height) {
        super(x, y, id);
        speed = 0.6f;
        velX = speed;
        direction = Direction.RIGHT;
        this.width = width;
        this.height = height;
    }

    @Override
    public void tick() {
        if (!alive) {
            hitCount++;

            if (hitCount > 100) {
                handler.removeObject(this);
            }

        } else {
            if (onAir) {
                velY += gravity;
            } else {
                if (velX >= 0) {
                    velX -= friction;
                } else {
                    velX += friction;
                }
            }

            if (Math.abs(x - GSpace.getPlayer().getX()) > 24) {
                if ((x - GSpace.getPlayer().getX()) > 0) {
                    direction = Direction.LEFT;
                    velX = -1 * speed;
                    x += Math.round(velX);
                } else if ((x - GSpace.getPlayer().getX()) < 0) {
                    direction = Direction.RIGHT;
                    velX = 1 * speed;
                    x += Math.round(velX);
                }
            }
        }

        y += Math.round(velY);
        collision();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        Animation.animateCharacter(g, this);
    }

}
