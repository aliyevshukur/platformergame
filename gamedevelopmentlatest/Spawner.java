/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Spawner extends Character {

    int tickCounter;
    long countEnemies = countEnemies();
    GameObject obj;

    public Spawner(int x, int y, ID id, int width, int height, GameObject obj) {
        super(x, y, id);
        speed = 0.0f;
        velX = 0;
        direction = Direction.RIGHT;
        this.width = width;
        this.height = height;
        tickCounter = 0;
        onAir = false;
        this.obj = obj;
    }

    @Override
    public void tick() {
        tickCounter++;
        int size = GSpace.multSize;
//         if(countEnemies<countEnemies()){
//                 health -=15;
//                System.out.println("health + "  +health);
//                System.out.println("count enemies : " + countEnemies);
//            countEnemies = countEnemies();
//            if (health <= 0) {
//            handler.removeObject(this);
//        }
        //   }
        if (tickCounter == 500) {
            handler.addObject(obj);
            tickCounter = 0;

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
        }
    }

    private long countEnemies() {
        return handler.getObjects().stream().filter(obj -> obj.id == ID.ENEMY).count();
    }

    //    public void hit(GameObject obj) {
//        // spesific behaviour of enemy when it hist the player
//       // alive = false;
//        System.out.println("it is a spawner");
//    }
    @Override
    public void render(Graphics g) {
        super.render(g);
        Animation.animateCharacter(g, this);

    }

}
