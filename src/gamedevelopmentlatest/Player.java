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

    private boolean[] keyPressed = new boolean[7];
    private final Color color;
    private int health;
    private final float jumpingSpeed = 3;

    public Player(int x, int y, ID id) {
        super(x, y, id);

        direction = 2;
        color = Color.white;
        velY = 0;
        velX = 0;
        width = 16 * GSpace.multSize;
        height = 32 * GSpace.multSize;

        keyPressed[0] = false;
        keyPressed[1] = false;
        keyPressed[2] = false;
        keyPressed[3] = false;
        keyPressed[4] = false;
        keyPressed[5] = false;
        keyPressed[6] = false; // shoot

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
            direction = 1;
        } else if (velX > 0.5) {
            direction = 2;
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

        collision();

    }

    private void keyInput() {
        if (!isFalling) {
            if (keyPressed[1]) {
                velX += -speed;
            }
            if (keyPressed[2]) {
                velX += speed;
            }
            if (keyPressed[0]) {
                if (!isFalling) {
                    velY -= jumpingSpeed;
                    isFalling = true;
                }
            }
        } else {
            if (keyPressed[1]) {
                velX += -speed / 2;
            }
            if (keyPressed[2]) {
                velX += speed / 2;
            }
        }

        if (keyPressed[6]) {
            handler.addObject(new Bullet(x + 5, y + 5, ID.BULLET, direction));
        }
    }

    @Override
    public void render(Graphics g) {

        Animation.animateObject(g, this);

//        g.setColor(color);
//        g.fillOval((int) x, (int) y, width, height);
        g.setColor(Color.green);
        g.fillRect(x - 20, y - 30, 2 * width * health / 100, 20);

        g.setColor(Color.black);
        g.drawRect(x - 20, y - 30, 2 * width, 20);
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
