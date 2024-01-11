package com.ejo.game;

import com.ejo.game.math.Vector;
import com.ejo.game.scenes.TitleScene;

public class App {

    //TODO: Make a game where you are a player on the ground. Blocks randomly appear and begin to fall
    // and you have to dodge

    public static final Window WINDOW = new Window(
            "Meteor Shower",
            new Vector(100,100),
            new Vector(800,600));

    public static void main(String[] args) {
        WINDOW.setScene(new TitleScene());
        WINDOW.runRepaintLoop();
    }
}
