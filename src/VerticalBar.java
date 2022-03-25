import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;


public class VerticalBar extends Bar {

    VerticalBar(Point origin, double width, double height, String label) {
        super(origin, width, height, label);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(getOrigin().x, (int) (getOrigin().y - getHeight()), (int) getWidth(), (int) getHeight());

        int stringWidth = g.getFontMetrics().stringWidth(getLabel());

        g.setColor(Color.BLACK);
        g.drawString(getLabel(), (int) (getOrigin().x + (getWidth() / 2.0) - (stringWidth/2.0) ),(int) (getOrigin().y - getHeight() - 10));

    }

}
