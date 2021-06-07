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
public class SurprizeBox extends GameObject {

    int counter = 0;

    public SurprizeBox(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {
        counter++;
        if (counter > 90) {
            counter = 0;
        }

        y += Math.round(velY);
        if (y < stateY) {
            velY += gravity;
        } else {
            y = stateY;
            velY = 0;
        }

    }

    public void bump(float bump) {
        velY = bump;
    }

    @Override
    public void render(Graphics g) {
//        g.drawImage(Animation.surprizeBox.get(counter / 30),  x,  y, width, height, null);
    }

}
