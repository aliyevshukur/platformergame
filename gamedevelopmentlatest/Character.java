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
public abstract class Character extends GameObject {

    Handler handler;
    int health = 100;
    int point = 0;
    protected float speed = 0.3f;
    protected float friction = 0.15f;
    protected boolean isJumping = true;
    protected boolean climbing;

    public Character(int x, int y, ID id) {
        super(x, y, id);
        this.handler = GSpace.getHandler();
    }

    protected void collision() {

        boolean onAirTemp = true;
        for (int i = 0; i < handler.getObjects().size(); i++) {
            GameObject temp = handler.getObjects().get(i);

            switch (temp.getId()) {
                case GROUND:
                    if (getBounds(BoundType.UNDER_BOUND).intersects(temp.getBounds())) {
                        onAirTemp = false;
                        if (!onAir) {
                            isJumping = false;
                            climbing = false;
                        }
                    }

                    if (getBounds().intersects(temp.getBounds())) {
                        collideDownAndUp(temp);
                        collideLeftAndRight(temp);
                    }
                    break;

                case PLATFORM:
                    if (getBounds(BoundType.UNDER_BOUND).intersects(temp.getBounds())) {
                        onAirTemp = false;
                        if (!onAir) {
                            isJumping = false;
                            climbing = false;
                        }
                    }

                    if (getBounds().intersects(temp.getBounds())) {

                        if (velY > 0) {
                            if (getBounds(BoundType.DOWN_BOUND).intersects(temp.getBounds(BoundType.UPPER_BOUND))) {
                                y = temp.getY() - height;
//                                velY = 0;
                                isJumping = false;
                            }
                        }
                    }
                    break;

                case ENEMY:
                    if (getBounds(BoundType.UNDER_BOUND).intersects(temp.getBounds())) {
                        if (id == ID.PLAYER) {
                            GSpace.getPlayer().damagePlayer(5);
                        }
                    }
                    break;

                case BULLET:
                    if ((id == ID.ENEMY || id == ID.SPAWNER) && getBounds().intersects(temp.getBounds())) {
                        health -= health >= 15 ? 15 : health;
                        if (health <= 0) {
                            alive = false;
                            // decreaseSpawnHealth();
                        }
                        handler.removeObject(temp);
                    }
                    break;

                case ENEMY_THROW_ITEM:
                    if (getBounds().intersects(temp.getBounds())) {
                        if (id == ID.PLAYER) {
                            health -= health >= 10 ? 10 : health;
                            if (health <= 0) {
                                alive = false;
                            }
                        }
                    }
                    break;

                case WALL:
                    if (getBounds(BoundType.UNDER_BOUND).intersects(temp.getBounds())) {
                        onAirTemp = false;
                        if (!onAir) {
                            isJumping = false;
                            climbing = false;
                        }
                    }
                    collideDownAndUp(temp);
                    collideLeftAndRight(temp);
                    break;

                case BOX:
                    if (getBounds(BoundType.UNDER_BOUND).intersects(temp.getBounds())) {
                        onAirTemp = false;
                        if (!onAir) {
                            isJumping = false;
                            climbing = false;
                        }
                    }

                    if (getBounds().intersects(temp.getBounds())) {
                        collideDownAndUp(temp);
                        collideLeftAndRight(temp);

                    }
                    break;

                case LADDER:
//                    if (getBounds(BoundType.UNDER_BOUND).intersects(temp.getBounds())) {
//                        onAirTemp = false;
//                        if (!onAir) {
//                            isJumping = false;
//                            climbing = false;
//                        }
//                    }
//
                    if (getBounds().intersects(temp.getBounds())) {
//                        if (getBounds(BoundType.DOWN_BOUND).intersects(temp.getBounds(BoundType.UP_SURFACE))) {
//                            GSpace.getPlayer().setClimbing(false);
//                            y = temp.getY() - height;
                        y -= 4.5;
//                        } else {
//                        GSpace.getPlayer().setClimbing(true);

//                        }
                    }
                    break;

                case TRAMBOLIN:
//                    if (getBounds(BoundType.UNDER_BOUND).intersects(temp.getBounds())) {
//                        onAirTemp = false;
//                        if (!onAir) {
//                            isJumping = false;
//                            climbing = false;
//                        }
//                    }
//
                    if (getBounds().intersects(temp.getBounds())) {
//                        if (getBounds(BoundType.DOWN_BOUND).intersects(temp.getBounds(BoundType.UP_SURFACE))) {
//                            GSpace.getPlayer().setClimbing(false);
//                            y = temp.getY() - height;
                        y -= 30;
                        onAir = true;
                        onAirTemp = true;
                        isJumping = true;
//                        } else {
//                        GSpace.getPlayer().setClimbing(true);

//                        }
                    }

                    break;

            }

        }
        onAir = onAirTemp;
    }

    public void collideDownAndUp(GameObject collapsedObj) {
        if (velY > 0) {
            if (getBounds(BoundType.DOWN_BOUND).intersects(collapsedObj.getBounds(BoundType.UPPER_BOUND))) {
                y = collapsedObj.getY() - height;
                velY = 0;
                isJumping = false;
                onAir = false;
                climbing = false;

            }
        } else if (velY < 0) {
            if (getBounds(BoundType.UPPER_BOUND).intersects(collapsedObj.getBounds(BoundType.DOWN_BOUND))) {
                y = collapsedObj.getY() + collapsedObj.getBounds().height;
                velY *= -1.2;
                isJumping = false;
                onAir = false;
                climbing = false;

            }
        }
    }

    public void collideLeftAndRight(GameObject collapsedObj) {

        if (velX > 0) {
            if (getBounds(BoundType.RIGHT_BOUND).intersects(collapsedObj.getBounds())) {
                velX = 0;
                x = collapsedObj.getX() - width;

                if (id == ID.ROCK_THROWER_ENEMY) {
                    direction = direction == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
                }
            }
        } else if (velX < 0) {
            if (getBounds(BoundType.LEFT_BOUND).intersects(collapsedObj.getBounds())) {
                velX = 0;
                x = collapsedObj.getX() + collapsedObj.getWidth();

                if (id == ID.ROCK_THROWER_ENEMY) {
                    direction = direction == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
                }
            }
        }

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g
    ) {
        Color healthColor = Color.green;
        if (id == ID.ENEMY) {
            healthColor = Color.red;
        }
        g.setColor(healthColor);
        g.fillRect(x - 3, y - 10, width * health / 100, 10);
        g.setColor(Color.black);
        g.drawRect(x - 3, y - 10, width, 10);
    }

    public boolean isClimbing() {
        return climbing;
    }

    public void setClimbing(boolean climbing) {
        this.climbing = climbing;
    }
}
