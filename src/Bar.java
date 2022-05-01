import java.awt.Point;
import java.awt.Color;
import java.util.Random;
import java.awt.font.TextLayout;
import java.awt.Graphics;

public abstract class Bar {

    /**
     * The origin point of Bar <br>
     * <br/>
     * For Vertical bars: the bottom left corner of the rectangle <br>
     * <br/>
     * For Horizontal bars: the top left corner of the rectangle
     */
    private Point origin;
    private double height;
    private double width;
    private double originalHeight;
    protected Color color;
    private String label;
    /**
     * The textLayout object of the bar, used to draw the label for the bars. <br>
     * <br/>
     * Helps in centering the label with the bar's width and setting the right font
     * size
     */
    private TextLayout textLayout;

    /**
     * makes a new bar a the Point origin, with given width and height and label
     * 
     * @param origin the origin point of the bar
     * @param width  the width of the bar
     * @param height the height(length) of the bar
     * @param label  the label for the bar
     */
    Bar(Point origin, double width, double height, String label) {
        this.origin = origin;
        this.width = width;
        this.height = height;
        this.label = label;
        Random r = new Random();
        color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    /**
     * draws the bar
     * 
     * @param g the graphics object on which to draw the bar
     */
    public abstract void draw(Graphics g);

    public Point getOrigin() {
        return this.origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getOriginalHeight() {
        return this.originalHeight;
    }

    public void setOriginalHeight(double originalHeight) {
        this.originalHeight = originalHeight;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TextLayout getTextLayout() {
        return this.textLayout;
    }

    public void setTextLayout(TextLayout tl) {
        this.textLayout = tl;
    }

}
