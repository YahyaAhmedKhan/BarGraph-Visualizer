import java.awt.Point;
import java.text.CollationElementIterator;
import java.awt.Color;
import java.util.Random;
import java.awt.font.TextLayout;
import java.awt.Graphics;

public abstract class Bar {
    private Point origin;
    private double height;
    private double width;
    private double originalHeight;
    protected Color color;
    private String label;
    protected TextLayout tl;

    Bar(Point origin, double width, double height, String label) {
        this.origin = origin;
        this.width = width;
        this.height = height;
        this.label = label;
        Random r = new Random();
        color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));


    }

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

}
