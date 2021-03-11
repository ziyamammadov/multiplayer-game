/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Ziya
 */
public abstract class GameObject implements Serializable {

    protected int x, y;
    protected ID id;
    protected double velX, velY;
    protected int height = 50;
    protected int width = 50;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int setGetX(int velX) {
        return velX;
    }

    public int setGetY(int velY) {
        return velY;
    }

    public abstract Rectangle getBounds();

    public abstract void tick();

    public abstract void render(Graphics g);

    public static int clamp(int number, int min, int max) {
        if (number >= max) {
            return max;
        } else if (number <= min) {
            return min;
        }
        return number;
    }

}
