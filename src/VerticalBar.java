import java.awt.Point;
import java.awt.Graphics;

public class VerticalBar extends Bar {

    VerticalBar(Point origin, double width, double height, String label) {
        super(origin, width, height, label);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(getOrigin().x, (int) (getOrigin().y - getHeight()), (int) getWidth(), (int) getHeight());
    }

}
