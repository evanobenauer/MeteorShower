package com.ejo.game.render;

import com.ejo.game.App;
import com.ejo.game.math.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ground implements Drawable {

    private final int yPos;

    private final int rockCount;

    private final ArrayList<Vector> rockPosList = new ArrayList<>();

    public Ground(int yPos, int rockCount) {
        this.yPos = yPos;
        this.rockCount = rockCount;
        initRockPositions();
    }

    @Override
    public void draw(Graphics2D graphics, Vector pos) {
        new Rectangle(new Vector(App.WINDOW.getSize().getX(),100),new Color(76, 36, 1,255)).draw(graphics,new Vector(0,yPos));

        for (int i = 0; i < rockCount; i++) {
            Rectangle rock = new Rectangle(new Vector(6,6), new Color(58, 26, 0,255),Math.PI / 4);
            rock.draw(graphics, rockPosList.get(i));
        }
    }

    private void initRockPositions() {
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            rockPosList.add(new Vector(random.nextInt((int)App.WINDOW.getSize().getX()), random.nextInt((int)App.WINDOW.getSize().getY() - 500) + 500));
        }
    }

    public int getY() {
        return yPos;
    }
}
