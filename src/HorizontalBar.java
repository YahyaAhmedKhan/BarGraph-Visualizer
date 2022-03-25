import java.awt.*;

public class HorizontalBar extends Bar{

    public HorizontalBar(Point origin, double width, double height, double originalHeight, String label){
        super(origin, width, height, originalHeight, label);
    }

    public void draw(Graphics g) {

        g.setColor(color);
        g.fillRect(getOrigin().x, (int) (getOrigin().y - getHeight()), (int) getWidth(), (int) getHeight());
    }
}
