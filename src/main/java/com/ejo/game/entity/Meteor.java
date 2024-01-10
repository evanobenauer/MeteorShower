package com.ejo.game.entity;

import com.ejo.game.math.Vector;

import java.awt.*;

public class Meteor extends PhysicsRect {

    public Meteor(Vector size, Color color, double mass, Vector position, Vector velocity, Vector netForce) {
        super(size, color, mass, position, velocity, netForce);
    }

}
