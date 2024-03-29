package com.ejo.game.entity;

import com.ejo.game.App;
import com.ejo.game.math.QuickTimer;
import com.ejo.game.math.Vector;

import java.util.ArrayList;
import java.util.Random;

public class MeteorSpawner {

    private final Random random;

    private final ArrayList<Meteor> meteorList;

    private final QuickTimer spawnTimer;

    public MeteorSpawner(ArrayList<Meteor> meteorList) {
        this.random = new Random();
        this.spawnTimer = new QuickTimer();
        this.meteorList = meteorList;
    }

    public void spawnMeteors(int minCount, int maxCount, int ms) {
        spawnTimer.start();
        if (spawnTimer.hasTimePassed(ms)) {
            for (int i = 0; i < random.nextInt(maxCount - minCount) + minCount; i++) {
                spawnMeteor();
            }
            spawnTimer.restart();
        }
    }

    public void spawnMeteor() {
        Vector position = new Vector(random.nextInt((int) App.WINDOW.getSize().getX()), -50);
        int size = random.nextInt(30 - 10) + 10;
        Vector velocity = new Vector(random.nextInt(10) * (random.nextBoolean() ? 1 : -1), 20);
        Meteor meteor = new Meteor(position, size, velocity);
        meteor.setSpinRate(random.nextFloat() / 10 * (random.nextBoolean() ? 1 : -1));
        meteorList.add(meteor);
    }

}
