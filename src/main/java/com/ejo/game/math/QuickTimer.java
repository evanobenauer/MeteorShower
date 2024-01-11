package com.ejo.game.math;

public class QuickTimer {

    private long startTime;

    public QuickTimer() {
        this.startTime = -1;
    }

    public void start() {
        if (!isStarted()) restart();
    }

    public void restart() {
        this.startTime = System.currentTimeMillis();
    }

    public boolean isStarted() {
        return getStartTimeMillis() > -1;
    }

    public boolean hasTimePassed(double ms) {
        return isStarted() && ms < getTimeMillis();
    }

    public double getTimeMillis() {
        return System.currentTimeMillis() - getStartTimeMillis();
    }


    private long getStartTimeMillis() {
        return startTime;
    }

}
