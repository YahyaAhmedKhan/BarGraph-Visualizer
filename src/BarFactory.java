import java.awt.Point;
public class BarFactory {

    static BarFactory  instance = null;
    private BarFactory(){

    }

    public static BarFactory getInstance(){
        if(instance == null) {
            instance = new BarFactory();
        }
        return instance;
    }
    public HorizontalBar getHorizontalBar(){
        return new HorizontalBar(new Point(), 0, 0, "");
    }
    public VerticalBar getVerticalBar(){
        return new VerticalBar(new Point(), 0, 0, "");
    }
    
}
