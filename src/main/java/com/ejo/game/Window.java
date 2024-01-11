package com.ejo.game;

import com.ejo.game.math.Vector;
import com.ejo.game.scenes.Scene;

import javax.swing.*;
import java.awt.*;

public class Window {

    private final JFrame frame;
    private final JPanel panel;
    private Scene scene;

    public Window(String title, Vector pos, Vector size) {
        this.frame = new JFrame(title);

        setTitle(title);
        setPos(pos);
        setSize(size);

        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                requestFocus(true);
                super.paintComponent(g);
                try {
                    getScene().draw((Graphics2D) g);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        };

        getPanel().addKeyListener(new com.ejo.game.input.KeyListener());
        getPanel().addMouseListener(new com.ejo.game.input.MouseListener());

        getFrame().add(panel);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().setResizable(false);
        getFrame().setVisible(true);
    }

    public void runRepaintLoop() {
        while (getFrame().isVisible()) {
            getPanel().repaint(); //Repaints the screen at 60 fps. Rendering is handled by the JPanel
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void setTitle(String title) {
        getFrame().setTitle(title);
    }

    public void setPos(Vector pos) {
        getFrame().setLocation((int) pos.getX(), (int) pos.getY());
    }

    public void setSize(Vector size) {
        getFrame().setSize((int) size.getX(), (int) size.getY());
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }


    public String getTitle() {
        return getFrame().getTitle();
    }

    public Vector getPos() {
        Point point = getFrame().getLocationOnScreen();
        return new Vector(point.getX(), point.getY());
    }

    public Vector getSize() {
        Dimension dimension = getFrame().getSize();
        return new Vector(dimension.getWidth(), dimension.getHeight());
    }

    public Scene getScene() {
        return scene;
    }


    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }
}
