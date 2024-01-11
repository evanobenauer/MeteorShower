package com.ejo.game.component;

import com.ejo.game.math.Vector;

import java.awt.*;

public class Rectangle implements Drawable {

    private Vector size;
    private Color color;
    private double angle;

    public Rectangle(Vector size, Color color, double angle) {
        this.size = size;
        this.color = color;
        this.angle = angle;
    }

    public Rectangle(Vector size, Color color) {
        this(size,color,0);
    }

    @Override
    public void draw(Graphics2D graphics, Vector pos) {
        int transX = (int)pos.getX();
        int transY = (int)pos.getY();
        graphics.translate(transX, transY);
        graphics.rotate(getAngle());
        graphics.setColor(getColor());
        graphics.fillRect(0, 0, (int) getSize().getX(), (int) getSize().getY());
        graphics.setColor(new Color(255, 255, 255, 255));
        graphics.rotate(-getAngle());
        graphics.translate(-transX, -transY);
    }

    public Vector getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public double getAngle() {
        return angle;
    }


    public void setSize(Vector size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
