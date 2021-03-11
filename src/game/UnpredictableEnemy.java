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
public class UnpredictableEnemy extends GameObject {

    private Color color;

    public UnpredictableEnemy(int x, int y, ID id) {
        super(x, y, id);
        velX = 5;
        velY = 5;
        this.color = Color.cyan;

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
    }

    private void changeDirection() {
        if (x >= Game.WIDTH - width || x <= 0) {
            if (velX > 0) {
                velX = -(2 + Math.random() * 15);
            } else {
                velX = (2 + Math.random() * 15);
            }
        }
        if (y >= Game.HEIGHT - height || y <= 0) {
            if (velY > 0) {
                velY = -(2 + Math.random() * 15);
            } else {
                velY = 2 + Math.random() * 15;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

}
