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
public class Bullet extends GameObject {

    float speed = 5;
    private Direction playerDirection = GSpace.getPlayer().getDirection();
    private Direction yDirection;
    private Sound bulletSound;

    public Bullet(int x, int y, ID id, Direction direction, Direction yDirection) {
        super(x, y, id);
        this.yDirection = yDirection;
        velX = (direction == Direction.LEFT) ? -speed : speed;
        if (yDirection == Direction.DOWN) {
            velY = speed;
            velX = 0;
        } else if (yDirection == Direction.UP) {
            velY = -speed;
            velX = 0;
        } else if (yDirection == Direction.NONE) {
            velY = 0;
        }
        width = 41;
        height = 20;
        bulletSound = new Sound("C:\\Users\\Ziya\\Downloads\\platformergame-main\\src\\gamedevelopmentlatest\\res\\sword.wav");
        bulletSound.startThread();
        bulletSound.play();
    }

    @Override
    public void tick() {

        x += Math.round(velX);
        y += Math.round(velY);

    }

    @Override
    public void render(Graphics g) {

        if (yDirection == Direction.DOWN || yDirection == Direction.UP) {
            g.drawImage(Animation.bullet.get(4), x, y, width, height, null);
        } else if (playerDirection == Direction.LEFT) {
            g.drawImage(Animation.bullet.get(0), x, y, width, height, null);
        } else {
            g.drawImage(Animation.bullet.get(2), x, y, width, height, null);
        }
    }

}
