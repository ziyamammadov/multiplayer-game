/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Ziya
 */
public class HUD {

    private final Player player;
    private final Player secondPlayer;

    public HUD() {
        this.player = SharedVariables.getPlayer();
        this.secondPlayer = SharedVariables.getSecondPlayer();

    }

    public void render(Graphics g) {
        drawFirstPlayerHealthBar(g);
        drawSecondPlayerHealthBar(g);
        gameOver(g);
        playerWon(g);
    }

    private void drawFirstPlayerHealthBar(Graphics g) {
        g.setColor(Color.green);
        g.fill3DRect(30, 10, 2 * Player.clamp(player.getHealth(), 0, 100), 50, true);
        g.setColor(Color.red);
        g.fill3DRect(30 + 2 * Player.clamp(player.getHealth(), 0, 100), 10, 2 * (100 - Player.clamp(player.getHealth(), 0, 100)), 50, true);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.orange);
        g.drawString("1st Player's health: " + player.getHealth(), 270, 30);
        g.drawString("Score : " + player.getPoint(), 270, 55);
    }

    private void drawSecondPlayerHealthBar(Graphics g) {
        g.setColor(Color.green);
        g.fill3DRect(1060, 10, 2 * Player.clamp(secondPlayer.getHealth(), 0, 100), 50, true);
        g.setColor(Color.red);
        g.fill3DRect(1060 + 2 * Player.clamp(secondPlayer.getHealth(), 0, 100), 10, 2 * (100 - Player.clamp(secondPlayer.getHealth(), 0, 100)), 50, true);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.orange);
        g.drawString("2nd Player's health: " + secondPlayer.getHealth(), 800, 30);
        g.drawString("Score : " + secondPlayer.getPoint(), 800, 55);
    }

    private void gameOver(Graphics g) {
        if (player.getHealth() <= 0 || secondPlayer.getHealth() <= 0) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.red);
            g.drawString("GAME IS OVER", (Game.WIDTH - 300) / 2, (Game.HEIGHT - 50) / 2);
            g.drawString((player.getHealth() <= 0 ? " SECOND PLAYER WON" : " FIRST PLAYER WON"), (Game.WIDTH - 300) / 2 - 90, (Game.HEIGHT - 50) / 2 + 50);

            SharedVariables.getHandler().terminate();
//            SharedVariables.getGame().stop();
//            Why does SharedVariables.getGame().stop() method work before drawing "GAME IS OVER" even if I put it later than g.drawSring() method?
        }
    }

    private void playerWon(Graphics g) {
        if (player.getPoint() == 50 || secondPlayer.getPoint() == 50) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.red);
            g.drawString((player.getHealth() == 50 ? " FIRST PLAYER WON" : " SECOND PLAYER WON"), (Game.WIDTH - 300) / 2 - 90, (Game.HEIGHT - 50) / 2 + 50);

            SharedVariables.getHandler().terminate();
        }
    }
}
