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

                    if (getBounds(boundsType.underBounds).intersects(temp.getBounds())) {
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
                    if (getBounds(boundsType.underBounds).intersects(temp.getBounds())) {
                        onAirTemp = false;
                        if (!onAir) {
                            isJumping = false;
                            climbing = false;
                        }
                    }

                    if (getBounds().intersects(temp.getBounds())) {

                        if (velY > 0) {
                            if (getBounds(boundsType.downBounds).intersects(temp.getBounds(boundsType.upBounds))) {
                                y = temp.getY() - height;
//                                velY = 0;
                                isJumping = false;
                            }
                        }
                    }
                    break;
                case ENEMY:

                    if (getBounds(boundsType.underBounds).intersects(temp.getBounds())) {
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

                    if (getBounds(boundsType.underBounds).intersects(temp.getBounds())) {
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
                    if (getBounds(boundsType.underBounds).intersects(temp.getBounds())) {
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
//                    if (getBounds(boundsType.underBounds).intersects(temp.getBounds())) {
//                        onAirTemp = false;
//                        if (!onAir) {
//                            isJumping = false;
//                            climbing = false;
//                        }
//                    }
//
                    if (getBounds().intersects(temp.getBounds())) {
//                        if (getBounds(boundsType.downBounds).intersects(temp.getBounds(boundsType.upSurface))) {
//                            GSpace.getPlayer().setClimbing(false);
//                            y = temp.getY() - height;
                        y -= 4.5;
//                        } else {
//                        GSpace.getPlayer().setClimbing(true);

//                        }
                    }

                    break;
                case TRAMBOLIN:
//                    if (getBounds(boundsType.underBounds).intersects(temp.getBounds())) {
//                        onAirTemp = false;
//                        if (!onAir) {
//                            isJumping = false;
//                            climbing = false;
//                        }
//                    }
//
                    if (getBounds().intersects(temp.getBounds())) {
//                        if (getBounds(boundsType.downBounds).intersects(temp.getBounds(boundsType.upSurface))) {
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
            if (getBounds(boundsType.downBounds).intersects(collapsedObj.getBounds(boundsType.upBounds))) {
                y = collapsedObj.getY() - height;
                velY = 0;
                isJumping = false;
                onAir = false;
                climbing = false;

            }
        } else if (velY < 0) {
            if (getBounds(boundsType.upBounds).intersects(collapsedObj.getBounds(boundsType.downBounds))) {
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
            if (getBounds(boundsType.rightBounds).intersects(collapsedObj.getBounds())) {
                velX = 0;
                x = collapsedObj.getX() - width;

                if (id == ID.ROCK_THROWER_ENEMY) {
                    direction = direction == 1 ? 2 : 1;
                }
            }
        } else if (velX < 0) {
            if (getBounds(boundsType.leftBounds).intersects(collapsedObj.getBounds())) {
                velX = 0;
                x = collapsedObj.getX() + collapsedObj.getWidth();

                if (id == ID.ROCK_THROWER_ENEMY) {
                    direction = direction == 1 ? 2 : 1;
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
//        System.out.println("WIDTH"+ health);
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
