package com.ejo.game.component;

import com.ejo.game.math.Vector;

import java.awt.*;

public class Text implements Drawable {

    private String string;
    private Font font;
    private Color color;
    private Vector size;

    public Text(String string, Font font, Color color) {
        this.string = string;
        this.font = font;
        this.color = color;
        this.size = Vector.NULL;
    }

    @Override
    public void draw(Graphics2D graphics, Vector pos) {
        graphics.setColor(getColor());
        graphics.setFont(new Font("Arial",Font.PLAIN,50));
        graphics.drawString(getString(),(int)pos.getX(),(int)pos.getY());
        updateTextSize(graphics);
        graphics.setColor(new Color(255,255,255,255));
    }

    private void updateTextSize(Graphics2D graphics) {
        this.size = new Vector(graphics.getFontMetrics().stringWidth(getString()),graphics.getFontMetrics().getHeight());
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getString() {
        return string;
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }

    public Vector getSize() {
        return size;
    }
}
