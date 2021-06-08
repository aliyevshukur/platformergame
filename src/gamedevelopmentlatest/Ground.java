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
        this.type = type;

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        // 0 - 5 0-2 surface 3-5 ground
//        switch (type) {
//            case "surface":
//                order = 0;
//            case "underground":
//                order = 4;
//            case "edge1":
//                order = 1;
//            case "edge2":
//                order = 2;
//        }
//        System.out.println("TYPE " + type + " " + order);

        g.drawImage(Animation.ground.get(order), x, y, width, height, null);

    }

}
