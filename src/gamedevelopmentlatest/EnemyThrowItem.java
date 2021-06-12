/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

/**
 *
 * @author Shukur
 */
public class EnemyThrowItem extends GameObject {

    private int destroyCount = 0;
    private boolean reached = false;

    private float speed = 2f;
    private Direction playerDirection = GSpace.getPlayer().getDirection();
    private int playerX = GSpace.getPlayer().x;
    private int playerY = GSpace.getPlayer().y;

    public EnemyThrowItem(int x, int y, ID id, Direction direction, int width, int height) {
        super(x, y, id);

        velX = (direction == Direction.LEFT) ? -speed : speed;
        velY = speed / 15;

        this.width = height;
        this.height = height;

    }

    @Override
    public void tick() {
        if (destroyCount < 200) {
            destroyCount++;
        }
        if (destroyCount >= 200) {
            GSpace.getHandler().removeObject(this);
            destroyCount = 0;
        }

        int diffX = playerX - x;
        int diffY = playerY - y;

        float angle = (float) Math.atan2(diffY, diffX);

        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);

        x += Math.round(velX);
        y += Math.round(velY);

        collision();
    }

    public void collision() {
        Handler handler = GSpace.getHandler();
        for (int i = 0; i < handler.getObjects().size(); i++) {
            GameObject temp = handler.getObjects().get(i);

            if (getBounds().intersects(temp.getBounds())) {
                if (temp.getId() == ID.PLAYER || temp.getId() == ID.GROUND || temp.getId() == ID.BOX && temp.getId() == ID.WALL) {
                    GSpace.getPlayer().damagePlayer(5);
                    handler.removeObject(this);
                }
            }
        }
    }

    @Override
    public void render(Graphics g
    ) {
        Animation.animateObject(g, this);
    }
}
