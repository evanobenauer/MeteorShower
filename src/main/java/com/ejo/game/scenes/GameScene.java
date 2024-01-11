package com.ejo.game.scenes;

import com.ejo.game.component.Background;
import com.ejo.game.component.Ground;
import com.ejo.game.entity.Meteor;
import com.ejo.game.entity.Player;
import com.ejo.game.math.QuickTimer;
import com.ejo.game.math.Vector;
import com.ejo.game.component.Rectangle;

import java.awt.*;
import java.util.ArrayList;

public class GameScene extends Scene {

    private final ArrayList<Meteor> meteorList;

    private final QuickTimer countdownTimer;

    private int countdown = 3;

    private boolean started;

    private final Background background;
    private final Ground ground;
    private final Player player;

    public GameScene() {
        super("Game Scene");
        this.countdownTimer = new QuickTimer();
        this.started = false;

        this.meteorList = new ArrayList<>();

        this.background = new Background(100,10);
        this.ground = new Ground(500,30);
        this.player = new Player(new Vector(-100,-100));
    }

    @Override
    public void draw(Graphics2D graphics) {
        background.draw(graphics,Vector.NULL);
        ground.draw(graphics,Vector.NULL);

        countdownStart(graphics);

        if (!started) return;

        if (!player.hasSpawned()) player.spawn();

        player.update();
        player.updateGroundCollision(ground);

        player.draw(graphics,player.getPos());
    }


    private void countdownStart(Graphics2D graphics) {
        countdownTimer.start();
        if (!started) {
            graphics.setFont(new Font("Arial", Font.PLAIN, 50));
            graphics.drawString(String.valueOf(countdown), (int) getWindow().getSize().getX() / 2, (int) getWindow().getSize().getY() / 2);
        }
        if (countdownTimer.hasTimePassed(1000) && countdown >= 0) {
            if (countdown == 0) {
                started = true;
            } else {
                countdown--;
                countdownTimer.restart();
            }
        }
    }

}
