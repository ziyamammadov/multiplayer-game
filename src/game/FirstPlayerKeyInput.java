/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Ziya
 */
public class FirstPlayerKeyInput extends KeyAdapter {

    private final Player player;

    public FirstPlayerKeyInput() {
        this.player = SharedVariables.getPlayer();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (key == KeyEvent.VK_W) {
            player.getKeyPressed()[3] = true;
        }
        if (key == KeyEvent.VK_A) {
            player.getKeyPressed()[1] = true;
        }
        if (key == KeyEvent.VK_S) {
            player.getKeyPressed()[4] = true;
        }
        if (key == KeyEvent.VK_D) {
            player.getKeyPressed()[2] = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            player.getKeyPressed()[3] = false;
        }
        if (key == KeyEvent.VK_A) {
            player.getKeyPressed()[1] = false;
        }
        if (key == KeyEvent.VK_S) {
            player.getKeyPressed()[4] = false;
        }
        if (key == KeyEvent.VK_D) {
            player.getKeyPressed()[2] = false;
        }
    }

}
