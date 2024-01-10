package com.ejo.game.math;

public class MathUtil {

    public double roundDouble(double number, int digits) {
        String num = String.format("%." + digits + "f", number);
        return Double.parseDouble(num);
    }

    public double getDistanceBetweenPoints(Vector vec1, Vector vec2) {
        return vec1.getSubtracted(vec2).getMagnitude();
    }

}
