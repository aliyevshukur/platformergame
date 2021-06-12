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
public class Ground extends GameObject {

    private int order;
    private String type;

    public Ground(int x, int y, ID id) {
        super(x, y, id);
    }

    public Ground(int x, int y, ID id, int width, int height, int order) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.order = order;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Animation.ground.get(order), x, y, width, height, null);
    }

}
