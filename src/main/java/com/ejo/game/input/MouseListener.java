package com.ejo.game.input;

import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {
            Mouse.LIST_BUTTONS.get(e.getButton()).update(e.getButton(), 1);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        try {
            Mouse.LIST_BUTTONS.get(e.getButton()).update(e.getButton(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
