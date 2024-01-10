package com.ejo.game.shape;

import com.ejo.game.math.Vector;

import java.awt.*;

public class Rectangle {

    private Vector size;
    private Color color;

    public Rectangle(Vector size, Color color) {
        this.size = size;
        this.color = color;
    }

    public void render(Vector pos) {

    }

    public Vector getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setSize(Vector size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
