import java.awt.*;

public class VerticalBar extends Bar {

    public VerticalBar(Point origin, double width, double height, double originalHeight, String label){
        super(origin, width, height, originalHeight, label);
    }

    public void draw(Graphics g) {

        g.setColor(color);
        g.fillRect((int) (getOrigin().x - getWidth()), getOrigin().y, (int) getHeight(), (int) getWidth());
    }
}
