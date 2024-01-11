package com.ejo.game.component;

import com.ejo.game.App;
import com.ejo.game.math.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Background implements Drawable {

    private final int starCount;
    private final int streakCount;

    private final ArrayList<Vector> starPosList = new ArrayList<>();
    private final ArrayList<Vector> streakPosList = new ArrayList<>();

    public Background(int starCount, int streakCount) {
        this.starCount = starCount;
        this.streakCount = streakCount;
        initStarPositions();
    }

    @Override
    public void draw(Graphics2D graphics, Vector pos) {
        Rectangle background = new Rectangle(App.WINDOW.getSize(), Color.BLACK);
        background.draw(graphics, Vector.NULL);

        for (int i = 0; i < starCount; i++) {
            Rectangle star = new Rectangle(new Vector(2,2), Color.WHITE,0);
            star.draw(graphics, starPosList.get(i));
        }

        for (int i = 0; i < streakCount; i++) {
            Rectangle star = new Rectangle(new Vector(20,2), Color.WHITE,Math.PI / 4);
            star.draw(graphics, streakPosList.get(i));
        }
    }

    private void initStarPositions() {
        Random random = new Random();
        for (int i = 0; i < starCount; i++) {
            starPosList.add(new Vector(random.nextInt((int)App.WINDOW.getSize().getX()), random.nextInt((int)App.WINDOW.getSize().getY())));
        }
        for (int i = 0; i < starCount; i++) {
            streakPosList.add(new Vector(random.nextInt((int)App.WINDOW.getSize().getX()), random.nextInt((int)App.WINDOW.getSize().getY())));
        }
    }

}
