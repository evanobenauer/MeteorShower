package com.ejo.game.input;

import java.util.HashMap;

/**
 * This class has the purpose of utilizing an immediate keyDown detection. I don't know if I want to keep it, but it does work
 */
public class Key {

    public static final HashMap<Integer,Key> LIST_KEYS = new HashMap<>();

    public static final int ACTION_PRESS = 1;
    public static final int ACTION_RELEASE = 0;
    public static final int ACTION_HOLD = 2;

    public static final Key KEY_W = new Key(87);
    public static final Key KEY_A = new Key(65);
    public static final Key KEY_D = new Key(68);
    public static final Key KEY_S = new Key(83);

    public static final Key KEY_UP = new Key(38);
    public static final Key KEY_DOWN = new Key(40);
    public static final Key KEY_LEFT = new Key(37);
    public static final Key KEY_RIGHT = new Key(39);

    public static final Key KEY_SPACE = new Key(32);


    //------------------------------------------

    private final int id;
    private boolean isKeyDown;

    public Key(int id) {
        this.id = id;
        LIST_KEYS.put(id,this);
    }

    public void update(int key, int state) {
        if (getId() == key) {
            if (state == ACTION_PRESS || state == ACTION_HOLD) setKeyDown(true);
            if (state == ACTION_RELEASE) setKeyDown(false);
        }
    }

    public void setKeyDown(boolean keyDown) {
        isKeyDown = keyDown;
    }

    public boolean isKeyDown() {
        return isKeyDown;
    }

    public int getId() {
        return id;
    }

}
