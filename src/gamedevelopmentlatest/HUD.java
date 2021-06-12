/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/**
 *
 * @author Ziya
 */
public class HUD {
    private final Player player;
    public HUD() {
        this.player = GSpace.getPlayer();
    }
    public void render(Graphics g) {
        gameOver(g);
    }
    private void gameOver(Graphics g) {
        if (player.getHealth() <= 0) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.red);
            g.drawString("GAME IS OVER", (Game.WIDTH - 300) / 2, (Game.HEIGHT - 50) / 2);
            GSpace.getHandler().terminate();
            Game.gameRun = false;
        }
    }
}