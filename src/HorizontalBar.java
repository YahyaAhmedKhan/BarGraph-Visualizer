import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.font.TextLayout;

public class HorizontalBar extends Bar {

    HorizontalBar(Point origin, double width, double height, String label) {
        super(origin, width, height, label);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(getOrigin().x, getOrigin().y, (int) getHeight(), (int) getWidth());

        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        float fontSize = ((float) getWidth() * 0.3f); // sets the font of the label to be 0.3 times the bar's width
        fontSize = (fontSize < 15 ? 15 : fontSize); // makes the font size 15 if font is too small
        if (getTextLayout() == null) { // making the textLayout object of the bar if it is null
            setTextLayout(new TextLayout(getLabel(), g.getFont().deriveFont(fontSize),
                    g2d.getFontRenderContext()));
        }
        Point loc = new Point((int) (getOrigin().x + getHeight() + 10), // the location of the TextLayout
                (int) (getOrigin().y + (getWidth() / 2) + getTextLayout().getBounds().getHeight() / 2));

        getTextLayout().draw(g2d, loc.x, loc.y);
    }
}
