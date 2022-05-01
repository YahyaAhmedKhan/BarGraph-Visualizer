import java.awt.Point;
public class BarFactory {

    static BarFactory  instance = null;
    private BarFactory(){

    }

    /**
     * gets the singleton instance of the BarFactory
     * @return the Barfactory instance
     */
    public static BarFactory getInstance(){
        if(instance == null) {
            instance = new BarFactory();
        }
        return instance;
    }
    /**
     * Gets a new horizontal Bar
     * @return the new horizontal bar
     */
    public HorizontalBar getHorizontalBar(){
        return new HorizontalBar(new Point(), 0, 0, "");
    }
    /**
     * Gets a new horizontal Bar
     * @return the new vertical bar
     */
    public VerticalBar getVerticalBar(){
        return new VerticalBar(new Point(), 0, 0, "");
    }
    
}
