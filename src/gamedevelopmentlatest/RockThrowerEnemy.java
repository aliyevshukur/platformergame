package gamedevelopmentlatest;

import java.awt.*;

public class RockThrowerEnemy extends Character {

    public int hitCount = 0;

    public RockThrowerEnemy(int x, int y, ID id, int width, int height) {
        super(x, y, id);
        speed = 0.0f;
        velX = speed;
        direction = 2;
        this.width = width;
        this.height = height;
    }

    @Override
    public void tick() {
//
//        if (!alive) {
//            hitCount++;
//
//            if (hitCount > 100) {
//                handler.removeObject(this);
//            }
//
//        } else {
//            if (onAir) {
//                velY += gravity;
//            } else {
//                if (velX >= 0) {
//                    velX -= friction;
//                } else {
//                    velX += friction;
//                }
//            }
//
//            if (Math.abs(x - GSpace.getPlayer().getX()) > 24) {
//                if ((x - GSpace.getPlayer().getX()) > 0) {
//
//                    direction = 1;
//                    velX = -1 * speed;
//                    x += Math.round(velX);
//                } else if ((x - GSpace.getPlayer().getX()) < 0) {
//                    direction = 2;
//                    velX = 1 * speed;
//                    x += Math.round(velX);
//                }
//            }
//        }
//
//        y += Math.round(velY);
//        collision();
//    }
    }

    public void hit(GameObject obj) {
        // spesific behaviour of enemy when it hist the player
        alive = false;
        System.out.println("Eshedu en la liahe illelah");
    }

    @Override
    public void render(Graphics g) {

        super.render(g);

        Animation.animateObject(g, this);

    }

}

