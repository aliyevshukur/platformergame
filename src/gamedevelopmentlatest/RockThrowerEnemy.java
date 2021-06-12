package gamedevelopmentlatest;

import java.awt.*;

public class RockThrowerEnemy extends Character {

    public int hitCount = 0;
    public int throwCooldown = 0;

    public RockThrowerEnemy(int x, int y, ID id, int width, int height) {
        super(x, y, id);
        speed = 0.6f;
        velX = speed;
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

            if (throwCooldown <= 400) {
                throwCooldown++;
            }

            if (onAir) {
                velY += gravity;
            } else {
                if (velX >= 0) {
                    velX -= friction;
                } else {
                    velX += friction;
                }
            }

            if (Math.abs(x - GSpace.getPlayer().getX()) > 300) {
                velX = speed;

                if (direction == Direction.LEFT) {
                    velX = -1 * speed;
                } else if (direction == Direction.RIGHT) {
                    velX = speed;
                }

                x += Math.round(velX);
            } else {
                if (GSpace.getPlayer().getX() - x > 0) {
                    direction = Direction.RIGHT;
                } else {
                    direction = Direction.LEFT;
                }

                if (throwCooldown >= 400) {
                    throwRock();
                    throwCooldown = 0;
                }
            }

        }

        y += Math.round(velY);

        collision();
//    }
    }

    public void hit(GameObject obj) {
        // spesific behaviour of enemy when it hist the player
        alive = false;
    }

    public void throwRock() {
        handler.addObject(new EnemyThrowItem(x, y + 20, ID.ENEMY_THROW_ITEM, direction, 16 * GSpace.multSize, 16 * GSpace.multSize));
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        Animation.animateCharacter(g, this);
    }

}
