package com.ejo.game.scenes;

import com.ejo.game.component.Background;
import com.ejo.game.math.Vector;
import com.ejo.game.component.Text;

import javax.swing.*;
import java.awt.*;

public class TitleScene extends Scene {

    private final JButton mainButton;
    private final Text title;

    private float sinStep = 0;

    private final Background background = new Background(100,10);

    public TitleScene() {
        super("Title Scene");
        this.mainButton = new JButton("Start!");
        this.title = new Text("Meteor Shower",new Font("Arial",Font.BOLD,50),Color.YELLOW);
        initStartButton();
        addSwingComponents(mainButton);
        background.initStarPositions();
    }

    @Override
    public void draw(Graphics2D graphics) {
        setMainButtonLocation();
        background.draw(graphics,Vector.NULL);
        drawSineTitle(graphics);
    }

    private void drawSineTitle(Graphics2D graphics) {
        title.draw(graphics,new Vector(getWindow().getSize().getX() / 2 - title.getSize().getX() / 2,getWindow().getSize().getY() / 2 + - title.getSize().getY() / 2 + 5 * Math.sin(sinStep)));
        sinStep += .075;
    }

    private void setMainButtonLocation() {
        mainButton.setLocation((int)(getWindow().getSize().getX() / 2 - mainButton.getWidth() / 2), (int)(getWindow().getSize().getY() / 2 - mainButton.getHeight() / 2) + 10);
    }

    private void initStartButton() {
        mainButton.setSize(200, 50);
        setMainButtonLocation();
        mainButton.setFont(new Font("Arial",Font.PLAIN,30));
        mainButton.addActionListener(action -> getWindow().setScene(new GameScene()));
    }

}
