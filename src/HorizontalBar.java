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
        float fontSize = ((float) getWidth() * 0.3f);
        fontSize = (fontSize < 15 ? 15 : fontSize);
        if (getTl() == null) {
            setTL(new TextLayout(getLabel(), g.getFont().deriveFont(fontSize),
                    g2d.getFontRenderContext()));
        }
        Point loc = new Point((int) (getOrigin().x + getHeight() + 10),
                (int) (getOrigin().y + (getWidth() / 2) + getTl().getBounds().getHeight() / 2));

        getTl().draw(g2d, loc.x, loc.y);
    }
}
