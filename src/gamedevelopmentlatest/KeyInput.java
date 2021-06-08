/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Ziya
 */
public class KeyInput extends KeyAdapter {

    private final Player player;

    public KeyInput() {
        this.player = GSpace.getPlayer();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            player.getKeyPressed()[3] = true;
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            player.getKeyPressed()[1] = true;
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            player.getKeyPressed()[4] = true;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            player.getKeyPressed()[2] = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            player.getKeyPressed()[0] = true;
        }
        if (key == KeyEvent.VK_Q) {
            player.getKeyPressed()[5] = true;
        }
        if (key == KeyEvent.VK_E ) {
            player.getKeyPressed()[6] = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            player.getKeyPressed()[3] = false;
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            player.getKeyPressed()[1] = false;
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            player.getKeyPressed()[4] = false;
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            player.getKeyPressed()[2] = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            player.getKeyPressed()[0] = false;
        }
        if (key == KeyEvent.VK_Q) {
            player.getKeyPressed()[5] = false;
        }
        if (key == KeyEvent.VK_Q && key == KeyEvent.VK_S) {
            player.getKeyPressed()[6] = false;
        }
        if (key == KeyEvent.VK_E) {
            player.getKeyPressed()[6] = false;
        }
    }

}
