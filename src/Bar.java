import java.awt.*;

public abstract class Bar {
    private Point origin;
    private double width;
    private double height;
    private double originalHeight;
    protected Color color;
    private String label;

    public abstract void draw(Graphics g);

    public Bar(Point origin, double width, double height, double originalHeight, String label){

        this.origin = origin;
        this.width = width;
        this.height = height;
        this.originalHeight = originalHeight;
        color = new Color((int) (256*Math.random()), (int) (256*Math.random()), (int) (256*Math.random()));
        this.label = label;

    }

    public Point getOrigin() {
        return origin;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getOriginalHeight() {
        return originalHeight;
    }

    public String getLabel() {
        return label;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setOriginalHeight(double originalHeight) {
        this.originalHeight = originalHeight;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
