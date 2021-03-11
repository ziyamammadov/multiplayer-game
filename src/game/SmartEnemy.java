/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Ziya
 */
public class SmartEnemy extends GameObject {

    private ID trackedID;
    private Color color;

    public SmartEnemy(int x, int y, ID id, ID trackedID) {
        super(x, y, id);
        this.color = Color.MAGENTA;
        this.trackedID = trackedID;
        width = 30;
        height = 30;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        x = clamp(x, 0, Game.WIDTH - width);
        y = clamp(y, 0, Game.HEIGHT - height);
        changeDirection();
        trackPlayer();
    }

    private void trackPlayer() {
        int s = 2;
        Player player = SharedVariables.getPlayerByID(trackedID);
        if (player.getX() > x) {
            velX = +s;
        }
        if (player.getX() < x) {
            velX = -s;
        }
        if (player.getY() > y) {
            velY = +s;
        }
        if (player.getY() < y) {
            velY = -s;
        }
    }

    private void changeDirection() {
        if (x == Game.WIDTH - width || x == 0) {
            velX = -velX;
        }
        if (y == Game.HEIGHT - height || y == 0) {
            velY = -velY;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

}
