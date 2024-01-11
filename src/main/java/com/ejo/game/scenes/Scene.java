package com.ejo.game.scenes;

import com.ejo.game.App;
import com.ejo.game.Window;

import javax.swing.*;
import java.awt.*;

/**
 * The Scene class acts as a "joint" between java swing and the game. I am not the biggest fan on swing
 * so we now have the scene :)
 */
public abstract class Scene {

    private final String title;

    public Scene(String title) {
        this.title = title;
        initClearPanel();
    }

    public abstract void draw(Graphics2D graphics);

    private void initClearPanel() {
        for (Component component : getWindow().getPanel().getComponents())
            getWindow().getPanel().remove(component);
    }

    public void addSwingComponents(JComponent... components) {
        for (JComponent component : components)
            getWindow().getPanel().add(component);
    }

    public Component[] getSwingComponents() {
        return getWindow().getPanel().getComponents();
    }

    public String getTitle() {
        return title;
    }

    public Window getWindow() {
        return App.WINDOW;
    }

}
