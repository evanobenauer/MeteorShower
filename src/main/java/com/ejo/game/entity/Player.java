package com.ejo.game.entity;

import com.ejo.game.App;
import com.ejo.game.render.Ground;
import com.ejo.game.input.Key;
import com.ejo.game.math.Vector;

import java.awt.*;

public class Player extends PhysicsRectangle {

    private boolean spawned;

    private boolean onGround;

    public Player(Vector pos) {
        super(new Vector(10,10), Color.GREEN, 10, pos);
        this.onGround = false;
        this.spawned = false;
    }

    public void spawn() {
        Vector midPos = App.WINDOW.getSize().getMultiplied(.5);
        setPos(new Vector(midPos.getX(),450));
        this.spawned = true;
    }

    public void update() {
        updateGravity(9.8f * 10);

        if (onGround) updateFriction(1f);

        updateControl();

        updateKinematics();

        updateWallBounds();

        capSpeed();
    }

    public void updateGroundCollision(Ground ground) {
        if (getPos().getY() + getSize().getY() >= ground.getY()) {
            onGround = true;
            setPos(new Vector(getPos().getX(),ground.getY() - getSize().getY())); //Set Y on ground
            setVelocity(new Vector(getVelocity().getX(),0)); //Set velocity to 0
            addForce(new Vector(0,-getNetForce().getY())); //Add normal force from ground
        } else {
            onGround = false;
        }
    }

    public void updateWallBounds() {
        //Left Wall
        if (getPos().getX() < 0) {
            setVelocity(new Vector(0,getVelocity().getY()));
            setPos(new Vector(0, getPos().getY()));
        }

        //Right Wall
        if (getPos().getX() + getSize().getX() + 16 > App.WINDOW.getSize().getX()) {
            setVelocity(new Vector(0,getVelocity().getY()));
            setPos(new Vector(App.WINDOW.getSize().getX() - getSize().getX() - 16,getPos().getY()));
        }
    }

    public void updateControl() {
        int hForce = 300;
        if (!onGround) hForce /= 5;

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

    private void updateFriction(float coefficient) {
        //Static Friction
        if (Math.abs(getVelocity().getX()) <= 1) {
            //if the X force is less than the static friction force
            if (Math.abs(getNetForce().getX()) < coefficient * Math.abs(getNetForce().getY())) {
                setVelocity(new Vector(0,getVelocity().getY())); //Stops player if static friction is active
                return; //If static friction passes, do NOT apply any new friction forces
            }
        }

        //Kinetic Friction
        double kineticForce = coefficient * Math.abs(getNetForce().getY());
        boolean velocityPositive = getVelocity().getX() > 0;
        boolean forcePositive = getVelocity().getX() == 0 && getNetForce().getX() > 0;
        if (velocityPositive || forcePositive) //Decides the sign of the friction force
            kineticForce *= -1;
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
