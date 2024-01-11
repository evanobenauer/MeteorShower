package com.ejo.game.entity;

import com.ejo.game.App;
import com.ejo.game.input.Key;
import com.ejo.game.math.Vector;

import java.awt.*;

public class Player extends PhysicsRect {

    private boolean spawned;

    private boolean onGround;

    public Player(Vector pos) {
        super(new Vector(10,10), Color.GREEN, 10, pos,Vector.NULL, Vector.NULL);
        this.onGround = false;
        this.spawned = false;
    }


    public void update() {
        updateGravity(9.8f * 10);

        if (onGround) updateFriction(1f);

        updateControl();

        updateKinematics();

        updateGroundCollision();

        capSpeed();
    }

    public void spawn() {
        Vector midPos = App.WINDOW.getSize().getMultiplied(.5);
        setPos(new Vector(midPos.getX(),450));
        this.spawned = true;
    }

    public void updateControl() {
        int hForce = 600;
        if (!onGround) hForce /= 10;

        //Jump
        if (onGround && (Key.KEY_SPACE.isKeyDown() || Key.KEY_W.isKeyDown() || Key.KEY_UP.isKeyDown()))
            addForce(new Vector(0, -3000));

        //Left
        if (Key.KEY_A.isKeyDown() || Key.KEY_LEFT.isKeyDown())
            addForce(new Vector(-hForce, 0));

        //Right
        if (Key.KEY_D.isKeyDown() || Key.KEY_RIGHT.isKeyDown())
            addForce(new Vector(hForce, 0));
    }

    private void updateGroundCollision() {
        int groundY = 500;
        if (getPos().getY() + getRectangle().getSize().getY() >= groundY) {
            onGround = true;
            setPos(new Vector(getPos().getX(),groundY - getRectangle().getSize().getY())); //Set Y on ground
            setVelocity(new Vector(getVelocity().getX(),0)); //Set velocity to 0
            addForce(new Vector(0,-getNetForce().getY())); //Add normal force from ground
        } else {
            onGround = false;
        }
    }


    private void updateFriction(float coefficient) {
        //Static Friction
        if (Math.abs(getVelocity().getX()) <= 0) {
            if (Math.abs(getNetForce().getX()) < coefficient * Math.abs(getNetForce().getY())) {
                return; //If static friction passes, do NOT apply any new friction forces
            }
        }

        //Kinetic Friction
        double kineticForce = coefficient * Math.abs(getNetForce().getY());
        if (getVelocity().getX() > 0) kineticForce *= -1;
        if (getVelocity().getX() == 0 && getNetForce().getX() > 0) kineticForce *= -1;
        addForce(new Vector(kineticForce,0));

    }

    private void updateGravity(float g) {
        addForce(new Vector(0,g));
    }


    private void capSpeed() {
        double bound = 25;
        if (getVelocity().getX() > bound) setVelocity(new Vector(bound, getVelocity().getY()));
        if (getVelocity().getX() < -bound) setVelocity(new Vector(-bound, getVelocity().getY()));
    }


    public boolean isOnGround() {
        return onGround;
    }

    public boolean hasSpawned() {
        return spawned;
    }
}
