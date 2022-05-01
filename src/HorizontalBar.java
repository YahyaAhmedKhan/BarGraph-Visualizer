import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class HorizontalBar extends Bar {
    HorizontalBar(Point origin, double width, double height, String label) {
        super(origin, width, height, label);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(getOrigin().x, getOrigin().y, (int) getHeight(), (int) getWidth());
        // g.setColor(Color.BLACK);
        // g.drawString(str, x, y);
        int stringHeight = (g.getFontMetrics().getAscent());

        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        if (tl == null) {
            tl = new TextLayout(getLabel(), g.getFont().deriveFont((float) getWidth() * 0.3f),
                    g2d.getFontRenderContext());

        }
        Point loc = new Point((int) (getOrigin().x + getHeight() + 10),
                (int) (getOrigin().y + (getWidth() / 2) + tl.getBounds().getHeight() / 2));

        tl.draw(g2d, loc.x, loc.y);

        g.setColor(Color.BLACK);

        // g.drawString(getLabel(), (int) (getOrigin().x + getHeight() + 10),
        // (int) (getOrigin().y + (getWidth() / 2) + stringHeight / 2));
    }
}
