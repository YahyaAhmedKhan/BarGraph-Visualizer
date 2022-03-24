import java.awt.Point;
import java.awt.Color;
import java.util.Random;

import javax.lang.model.util.Elements.Origin;

import java.awt.Graphics;

public class HorizontalBar extends Bar {
    HorizontalBar(Point origin, double width, double height, String label) {
        super(origin, width, height, label);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(getOrigin().x, getOrigin().y, (int) getHeight(), (int) getWidth());
    }
}
