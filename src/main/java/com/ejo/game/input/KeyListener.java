package com.ejo.game.input;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            Key.LIST_KEYS.get(e.getKeyCode()).update(e.getKeyCode(),1);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            Key.LIST_KEYS.get(e.getKeyCode()).update(e.getKeyCode(),0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
