import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

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
        g.drawString(getLabel(),(int) (getOrigin().x + getHeight() + 10), (int) (getOrigin().y + (getWidth() / 2) + stringHeight/2));
    }
}
