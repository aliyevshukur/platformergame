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
 * @author dejavu
 */
public class Decorations extends GameObject{
    private int index = 0;

    public Decorations(int x, int y, ID id, int width, int height, int index) {
        super(x, y, id);
        this.width = width;
        this.height = height;
        this.index = index;
    }

    @Override
    public void tick() {
 }

    @Override
    public void render(Graphics g) {
        g.drawImage(Animation.decorations.get(index), x, y, width, height, null);
 }
    
}
