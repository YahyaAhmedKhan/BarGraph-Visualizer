import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;


public class VerticalBar extends Bar {

    VerticalBar(Point origin, double width, double height, String label) {
        super(origin, width, height, label);
        
    }

    /*
       Graphics2D g = ...;
   Point2D loc = ...;
   Font font = Font.getFont("Helvetica-bold-italic");
   FontRenderContext frc = g.getFontRenderContext();
   TextLayout layout = new TextLayout("This is a string", font, frc);
   layout.draw(g, (float)loc.getX(), (float)loc.getY());
   */


    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(getOrigin().x, (int) (getOrigin().y - getHeight()), (int) getWidth(), (int) getHeight());

        int stringWidth = g.getFontMetrics().stringWidth(getLabel());

        g.setColor(Color.BLACK);
        g.drawString(getLabel(), (int) (getOrigin().x + (getWidth() / 2.0) - (stringWidth/2.0) ),(int) (getOrigin().y - getHeight() - 10));

    }


}
