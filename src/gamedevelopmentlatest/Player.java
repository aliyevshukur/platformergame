/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Ziya
 */
public class Player extends Character {

    private boolean[] keyPressed = new boolean[8];
    private final Color color;
    private final float jumpingSpeed = 2;
    private int bulletCount = 0;
    private int damageCooldown = 0;
    private Sound jumpingSound;

    public Player(int x, int y, ID id) {
        super(x, y, id);

        direction = Direction.RIGHT;
        color = Color.white;
        velY = 0;
        velX = 0;
        width = 16 * GSpace.multSize;
        height = 24 * GSpace.multSize;

        keyPressed[0] = false;
        keyPressed[1] = false;
        keyPressed[2] = false;
        keyPressed[3] = false;
        keyPressed[4] = false;
        keyPressed[5] = false;
        keyPressed[6] = false; // shoot
        this.jumpingSound = new Sound("res/jump.wav");
        jumpingSound.startThread();

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Rectangle getgetHeadArea() {
        return new Rectangle(x, y, 16, 16);
    }

    @Override
    public void tick() {
        if (velX < -0.5) {
            direction = Direction.LEFT;
        } else if (velX > 0.5) {
            direction = Direction.RIGHT;
        }

        keyInput();

        if (onAir) {
            velY += gravity;
        } else {
            if (velX >= 0) {
                velX -= friction;
            } else {
                velX += friction;
            }
        }
        x += Math.round(velX);
        y += Math.round(velY);

        if (bulletCount < 35) {
            bulletCount++;
        }

        if (damageCooldown < 40) {
            damageCooldown++;
        }

        collision();
    }

    private void keyInput() {

        if (!isJumping) {

            if (Math.abs(velX) < 3) {
                if (keyPressed[1]) {
                    velX += -speed;
                }
                if (keyPressed[2]) {
                    velX += speed;
                }

                if (keyPressed[3]) {
                    if (climbing) {
                        y -= 6;
                    }
                }

                if (keyPressed[0]) {
                    if (!isJumping) {
                        velY -= jumpingSpeed;
                        isJumping = true;
                        jumpingSound.play();
                    }
                }
            }
        } else {
            if (Math.abs(velX) < 3) {
                if (keyPressed[1]) {
                    velX += -speed / 7;
                }
                if (keyPressed[2]) {
                    velX += speed / 7;
                }
            }
        }

        if (keyPressed[5] && bulletCount > 30) {
            bulletCount = 0;
            handler.addObject(new Bullet(x + 15, y + 25, ID.BULLET, direction, Direction.NONE));
        }

        if (keyPressed[6] && bulletCount > 30) {
            bulletCount = 0;
            handler.addObject(new Bullet(x + 15, y + 25, ID.BULLET, direction, Direction.DOWN));
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        Animation.animateCharacter(g, this);
    }

    public void damagePlayer(int point) {

        if (damageCooldown >= 40) {
            health -= point;
            damageCooldown = 0;
        }
        if (health <= 0) {
            handler.removeObject(this);
        }
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean[] getKeyPressed() {
        return keyPressed;
    }

    public void setKeyPressed(boolean[] keyPressed) {
        this.keyPressed = keyPressed;
    }

}
