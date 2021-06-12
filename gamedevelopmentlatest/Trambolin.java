/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.Graphics;

/**
 *
 * @author dejavu
 */
public class Trambolin extends GameObject {

    public Trambolin(int x, int y, ID id, int width, int height) {
        super(x, y, id);
        this.width = width;
        this.height = height;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Animation.trambolin.get(0), x, y, width, height, null);
    }

}
