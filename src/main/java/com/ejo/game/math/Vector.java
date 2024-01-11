package com.ejo.game.math;

public class Vector {

    public static Vector NULL = new Vector(0,0,0);

    private final double x;
    private final double y;
    private final double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(double x, double y) {
        this(x,y,0);
    }


    public Vector getAdded(Vector vector) {
        return new Vector(getX() + vector.getX(), getY() + vector.getY(), getZ() + vector.getZ());
    }

    public Vector getSubtracted(Vector vector) {
        return new Vector(getX() - vector.getX(), getY() - vector.getY(), getZ() - vector.getZ());
    }

    public Vector getMultiplied(double multiplier) {
        return new Vector(getX() * multiplier, getY() * multiplier, getZ() * multiplier);
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(getX(),2) + Math.pow(getY(),2) + Math.pow(getZ(),2));
    }

    public Vector getUnitVector() {
        return getMultiplied(1 / getMagnitude());
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "<" + getX() + ", " + getY() + ", " + getZ() + ">";
    }
}
