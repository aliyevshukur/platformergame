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

    public Character(int x, int y, ID id) {
        super(x, y, id);
        this.handler = GSpace.getHandler();
    }

    protected void collision() {
        boolean floating = true;
        for (int i = 0; i < handler.getObjects().size(); i++) {
            GameObject temp = handler.getObjects().get(i);

            switch (temp.getId()) {
                case GROUND:

                    if (getBounds(boundsType.underBounds).intersects(temp.getBounds())) {
                        floating = false;
                        if (!onAir) {
                            isJumping = false;
                        }
                    }

                    if (getBounds().intersects(temp.getBounds())) {

                        if (velY > 0) {
                            if (getBounds(boundsType.downBounds).intersects(temp.getBounds(boundsType.upBounds))) {
                                y = temp.getY() - height;
                                velY = 0;
                                isJumping = false;
                                onAir = false;
                            }
                        } else if (velY < 0) {
                            if (getBounds(boundsType.upBounds).intersects(temp.getBounds(boundsType.downBounds))) {
                                y = temp.getY() + temp.getBounds().height;
                                velY *= -1.2;
                                isJumping = false;
                                onAir = false;
                            }
                        }

                        if (velX < 0) {
                            if (getBounds(boundsType.leftBounds).intersects(temp.getBounds())) {
//                                x = temp.getX() + temp.getBounds().width - width;
//                                y = temp.getY() - height;
//
                                velX = 0;
                                isJumping = false;
                                onAir = false;
                            }
                        }

                        if (velX > 0) {
                            if (getBounds(boundsType.rightBounds).intersects(temp.getBounds())) {
//                                x = temp.getX() + width;
//                                y = temp.getY() - height;
//
                                velX = 0;
                                isJumping = false;
                                onAir = false;
                            }
                        }
                    }
                    break;
                case SURPRIZE_BOX:
                    if (getBounds(boundsType.underBounds).intersects(temp.getBounds())) {
                        floating = false;
                        if (!onAir) {
                            isJumping = false;
                        }
                    }

                    if (getBounds().intersects(temp.getBounds())) {

                        if (velY > 0) {
                            if (getBounds(boundsType.downBounds).intersects(temp.getBounds(boundsType.upBounds))) {
                                y = temp.getY() - height;
                                velY = 0;
                                isJumping = false;
                            }
                        } else if (velY < 0) {
                            if (getBounds(boundsType.upBounds).intersects(temp.getBounds(boundsType.downBounds))) {
                                y = temp.getY() + temp.getBounds().height;
                                velY += -1.2;
                                health -= 0.2;
//                                ((SurprizeBox).bump(-3));
                                velY = 0.5f;
                            }
                        }

                        if (velX < 0) {
                            if (getBounds(boundsType.leftBounds).intersects(temp.getBounds())) {
                                x = temp.getX() - temp.getBounds().width;
                                velX = 0;
                            }
                        }

                        if (velX > 0) {
                            if (getBounds(boundsType.rightBounds).intersects(temp.getBounds())) {
                                x = temp.getX() - width;
                                velX = 0;
                            }
                        }
                    }
                    break;
                case PLATFORM:
                    if (getBounds(boundsType.underBounds).intersects(temp.getBounds())) {
                        floating = false;
                        if (!onAir) {
                            isJumping = false;
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
                    if (id == ID.ENEMY && getBounds().intersects(temp.getBounds())) {

                        health -= 15;
                        if (health <= 0) {
                            alive = false;
                        }

                        handler.removeObject(temp);
                    }

            }

        }
        onAir = floating;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
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
}
