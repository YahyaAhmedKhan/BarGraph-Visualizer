import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.font.TextLayout;

public class VerticalBar extends Bar {

    VerticalBar(Point origin, double width, double height, String label) {
        super(origin, width, height, label);

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(getOrigin().x, (int) (getOrigin().y - getHeight()), (int) getWidth(), (int) getHeight());

        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        if (getTl() == null) {
            float fontSize = (float) getWidth() * 0.15f;
            fontSize = (fontSize < 15 ? 15 : fontSize);
            setTL(new TextLayout(getLabel(), g.getFont().deriveFont(fontSize),
                    g2d.getFontRenderContext()));
        }
        Point loc = new Point((int) (getOrigin().x + (getWidth() / 2.0) - (getTl().getBounds().getWidth() / 2.0)),
                (int) (getOrigin().y - getHeight() - 10));

        getTl().draw(g2d, loc.x, loc.y);

    }

}
