package com.ejo.game.entity;

import com.ejo.game.math.Vector;
import com.ejo.game.component.Rectangle;

import java.awt.*;

public abstract class PhysicsRectangle extends Rectangle {

    private double mass;

    private Vector pos;
    private Vector velocity;
    private Vector acceleration;
    private Vector netForce;

    private float deltaT;

    public PhysicsRectangle(Vector size, Color color, double mass, Vector position, Vector velocity, Vector netForce) {
        super(size,color);

        this.mass = mass;

        this.pos = position;
        this.velocity = velocity;
        this.acceleration = Vector.NULL;
        this.netForce = netForce;

        this.deltaT = .1f;
    }

    public PhysicsRectangle(Vector size, Color color, double mass, Vector position) {
        this(size,color,mass,position,Vector.NULL,Vector.NULL);
    }

    public void updateKinematics() {
        setAcceleration(getNetForce().getMultiplied(1 / getMass()));
        setVelocity(getVelocity().getAdded(getAcceleration().getMultiplied(getDeltaT())));
        setCenter(getCenter().getAdded(getVelocity().getMultiplied(getDeltaT())));
        setNetForce(Vector.NULL);
    }


    private boolean isColliding(PhysicsRectangle rect) {
        boolean isXColliding = (rect.getPos().getX() + rect.getSize().getX() >= getPos().getX() && rect.getPos().getX() <= getPos().getX() + getSize().getX());
        boolean isYColliding = (rect.getPos().getY() + rect.getSize().getY() >= getPos().getY() && rect.getPos().getY() <= getPos().getY() + getSize().getY());
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

    public void addForce(Vector force) {
        setNetForce(getNetForce().getAdded(force));
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
        return getPos().getAdded(getSize().getMultiplied(.5f));
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

}
