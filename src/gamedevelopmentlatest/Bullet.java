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
    private int yDirection;

    public Bullet(int x, int y, ID id, int direction, int yDirection) {
        super(x, y, id);
        this.yDirection = yDirection;
        velX = (direction == 1) ? -speed : speed;
        if (yDirection == 1) {
            velY = speed;
            velX = 0;
        } else if (yDirection == 2) {
            velY = -speed;
            velX = 0;
        } else if (yDirection == 0) {
            velY = 0;
        }
        System.out.println("VELY" + velY + " " + velX);
        width = 41;
        height = 20;

    }

    @Override
    public void tick() {

        x += Math.round(velX);
        y += Math.round(velY);

    }

    @Override
    public void render(Graphics g) {

        if (yDirection == 1 || yDirection == 2) {
            g.drawImage(Animation.bullet.get(4), x, y, width, height, null);
        } else if (playerDirection == 1) {
            g.drawImage(Animation.bullet.get(0), x, y, width, height, null);
        } else {
            g.drawImage(Animation.bullet.get(2), x, y, width, height, null);
        }
    }

}
