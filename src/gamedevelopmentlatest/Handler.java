/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Ziya
 */
public class Handler {

    private final ArrayList<GameObject> objects = new ArrayList();

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            if (tempObject != null) {
                tempObject.tick();
            }
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            if (tempObject != null) {
                tempObject.render(g);
            }
        }
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }

    public void terminate() {
        this.objects.clear();
    }

    public void clear() {
        this.objects.removeAll(objects);
    }
}
