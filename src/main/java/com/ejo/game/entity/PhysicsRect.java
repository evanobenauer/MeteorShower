package com.ejo.game.entity;

import com.ejo.game.math.Vector;
import com.ejo.game.shape.Rectangle;

import java.awt.*;

public abstract class PhysicsRect {

    private final Rectangle rectangle;

    private double mass;

    private Vector pos;
    private Vector velocity;
    private Vector acceleration;
    private Vector netForce;

    private float deltaT;

    public PhysicsRect(Vector size, Color color, double mass, Vector position, Vector velocity, Vector netForce) {
        this.rectangle = new Rectangle(size,color);

        this.mass = mass;

        this.pos = position;
        this.velocity = velocity;
        this.acceleration = Vector.NULL;
        this.netForce = netForce;

        this.deltaT = .1f;
    }

    public PhysicsRect(Vector size, Color color, double mass, Vector position) {
        this(size,color,mass,position,Vector.NULL,Vector.NULL);
    }

    public void render() {
        getRectangle().render(getPos());
    }


    public void updateKinematics() {
        setAcceleration(getNetForce().getMultiplied(1 / getMass()));
        setVelocity(getVelocity().getAdded(getAcceleration().getMultiplied(getDeltaT())));
        setCenter(getCenter().getAdded(getVelocity().getMultiplied(getDeltaT())));
        setNetForce(Vector.NULL);
    }


    private boolean isColliding(PhysicsRect rect) {
        boolean isXColliding = (rect.getPos().getX() + rect.getRectangle().getSize().getX() >= getPos().getX() && rect.getPos().getX() <= getPos().getX() + getRectangle().getSize().getX());
        boolean isYColliding = (rect.getPos().getY() + rect.getRectangle().getSize().getY() >= getPos().getY() && rect.getPos().getY() <= getPos().getY() + getRectangle().getSize().getY());
        return isXColliding && isYColliding;
    }


    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    public void setCenter(Vector center) {
        setPos(getPos().getAdded(center.getSubtracted(getCenter())));
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    private void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public void setNetForce(Vector netForce) {
        this.netForce = netForce;
    }

    public void setDeltaT(float deltaT) {
        this.deltaT = deltaT;
    }


    public double getMass() {
        return mass;
    }

    public Vector getPos() {
        return pos;
    }

    public Vector getCenter() {
        return getPos().getAdded(getRectangle().getSize().getMultiplied(.5f));
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public Vector getNetForce() {
        return netForce;
    }

    public float getDeltaT() {
        return deltaT;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

}
