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
        if (!alive) {
            handler.removeObject(this);
        }
        if (tickCounter >= 300 && countEnemies() <= 3) {
            if (ID.SPAWNER == id) {
                handler.addObject(new Enemy(obj.getX(), obj.getY(), obj.getId(), obj.getWidth(), obj.getHeight()));
            } else {
                handler.addObject(new RockThrowerEnemy(obj.getX(), obj.getY(), obj.getId(), obj.getWidth(), obj.getHeight()));
            }
            tickCounter = 0;
        }
        collision();
    }

    private long countEnemies() {
        return handler.getObjects().stream().filter(obj -> obj.id == ID.ENEMY).count();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        Animation.animateCharacter(g, this);
    }
}
