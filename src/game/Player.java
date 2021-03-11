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
public class Player extends GameObject {

    private final Handler handler;
    private int point;
    private int health;
    private Color color;

    private boolean[] keyPressed = new boolean[5];

    public Player(int x, int y, ID id) {
        super(x, y, id);
        this.handler = SharedVariables.getHandler();
        health = 100;
        point = 0;
        width = 50;
        height = 50;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void tick() {
        regulateKeyboard();
        collision();
        x += velX;
        y += velY;
        health = clamp(health, 0, 100);
        x = clamp(x, 0, Game.WIDTH - width);
        y = clamp(y, 0, Game.HEIGHT - height);
    }

    private void regulateKeyboard() {
        int s = 8;
        if (keyPressed[1]) {
            velX = -s;
        } else if (keyPressed[2]) {
            velX = s;
        } else {
            velX = 0;
        }
        if (keyPressed[3]) {
            velY = -s;
        } else if (keyPressed[4]) {
            velY = s;
        } else {
            velY = 0;
        }
    }

    private void collision() {
        for (int i = 0; i < handler.getObjects().size(); i++) {
            GameObject temp = handler.getObjects().get(i);
            if (temp.getId() == ID.BASIC_ENEMY) {
                if (this.getBounds().intersects(temp.getBounds())) {
                    SharedVariables.getPlayerByID(this.id).health--;
                    color = Color.RED;
                } else {
                    color = Color.WHITE;
                }
            } else if (temp.getId() == ID.HEALTH) {
                if (this.getBounds().intersects(temp.getBounds())) {
                    SharedVariables.getPlayerByID(this.id).health += 10;
                    handler.removeObject(temp);
                }
            } else if (temp.getId() == ID.SMART_ENEMY) {
                if (this.getBounds().intersects(temp.getBounds())) {
                    SharedVariables.getPlayerByID(this.id).health -= 2;
                    color = Color.RED;
                }
            } else if (temp.getId() == ID.UNPREDICTABLE_ENEMY) {
                if (this.getBounds().intersects(temp.getBounds())) {
                    SharedVariables.getPlayerByID(this.id).health -= 3;
                    color = Color.RED;
                }
            } else if (temp.getId() == ID.COIN) {
                if (this.getBounds().intersects(temp.getBounds())) {
                    SharedVariables.getPlayerByID(this.id).point++;
                    handler.removeObject(temp);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean[] getKeyPressed() {
        return keyPressed;
    }

    public void setKeyPressed(boolean[] keyPressed) {
        this.keyPressed = keyPressed;
    }
}
