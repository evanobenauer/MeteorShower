package com.ejo.game.input;

import java.util.HashMap;

/**
 * This class has the purpose of utilizing an immediate keyDown detection. I don't know if I want to keep it, but it does work
 */
public class Mouse {

    public static final HashMap<Integer, Mouse> LIST_BUTTONS = new HashMap<>();

    public static final int ACTION_CLICK = 1;
    public static final int ACTION_RELEASE = 0;


    public static final Mouse BUTTON_LEFT = new Mouse(1);
    public static final Mouse BUTTON_RIGHT = new Mouse(3);
    public static final Mouse BUTTON_MIDDLE = new Mouse(2);


    //------------------------------------------

    private final int id;
    private boolean isButtonDown;

    public Mouse(int id) {
        this.id = id;
        LIST_BUTTONS.put(id,this);
    }

    public void update(int key, int action) {
        if (getId() == key) {
            if (action == ACTION_CLICK) setButtonDown(true);
            if (action == ACTION_RELEASE) setButtonDown(false);
        }
    }

    public void setButtonDown(boolean buttonDown) {
        isButtonDown = buttonDown;
    }

    public boolean isButtonDown() {
        return isButtonDown;
    }

    public int getId() {
        return id;
    }

}
