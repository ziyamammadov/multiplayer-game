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
public class BasicEnemy extends GameObject {

    private final Color color;

    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);
        this.color = Color.BLUE;
        velX = 5;
        velY = 5;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void tick() {
        changeDirection();
        x += velX;
        y += velY;
        x = clamp(x, 0, Game.WIDTH - width);
        y = clamp(y, 0, Game.HEIGHT - height);
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
