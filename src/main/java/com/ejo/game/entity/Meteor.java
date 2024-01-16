package com.ejo.game.entity;

import com.ejo.game.math.Vector;

import java.awt.*;
import java.util.Random;

public class Meteor extends PhysicsRectangle {

    private double spin;
    private double spinRate;

    public Meteor(Vector position, int size, Vector velocity) {
        super(new Vector(size,size), new Color(134, 48, 22,255), 1, position, velocity, Vector.NULL);
        this.spin = 0;
        this.spinRate = 0;
    }

    @Override
    public void draw(Graphics2D graphics, Vector pos) {
        Vector centerOffset = getSize().getMultiplied(-.5);

        graphics.translate((int)pos.getX(), (int)pos.getY());
        graphics.rotate(getAngle());
        graphics.setColor(getColor());

        graphics.fillRect((int)centerOffset.getX(), (int)centerOffset.getY(), (int) getSize().getX(), (int) getSize().getY());

        graphics.setColor(new Color(255, 255, 255, 255));
        graphics.rotate(-getAngle());
        graphics.translate(-(int)pos.getX(), -(int)pos.getY());
    }

    public void update() {
        updateKinematics();
        setAngle(spin);
        spin += spinRate;
    }

    public void setSpinRate(double spinRate) {
        this.spinRate = spinRate;
    }
}
