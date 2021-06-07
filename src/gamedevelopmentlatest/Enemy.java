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

    public Enemy(int x, int y, ID id) {
        super( x,  y, id);
        speed = 1f;
        velX = speed;
        direction = 2;
    }

    @Override
    public void tick() {

        if (!alive) {
            hitCount++;
            if (hitCount > 100) {
                handler.removeObject(this);
            } else {
                if (onAir) {
                    velY += gravity;
                }

                if ((x - GSpace.getPlayer().getX() > 0) & direction == 2) {
                    direction = 1;
                    velX = -1 * speed;
                } else if ((x - GSpace.getPlayer().getX() < 0) & direction == 1) {
                    direction = 2;
                    velX = 1 * speed;
                }
            }
        }

        collision();
    }

    public void hit(GameObject obj) {
        // spesific behaviour of enemy when it hist the player
        alive = false;
        System.out.println("Eshedu en la liahe illelah");
    }

    @Override
    public void render(Graphics g) {
        if (alive) {
            g.drawImage(Animation.enemy1.get(1), width, width, null);
        } else {
            g.drawImage(Animation.enemy1.get(0), width, width, null);
        }
    }

}
