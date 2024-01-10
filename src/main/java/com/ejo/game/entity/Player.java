package com.ejo.game.entity;

import com.ejo.game.math.Vector;

import java.awt.*;

public class Player extends PhysicsRect {

    private boolean onGround;

    public Player(Vector size, Color color, double mass, Vector position, Vector velocity, Vector netForce) {
        super(size, color, mass, position, velocity, netForce);
        this.onGround = false;
    }


    public void control() {

    }


    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean isOnGround() {
        return onGround;
    }

}
