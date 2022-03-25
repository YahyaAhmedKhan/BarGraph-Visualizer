public class BarFactory {

    private static BarFactory instance = null;

    private BarFactory(){

    }

    public HorizontalBar getHorizontalBar(String label, double value){
        return new HorizontalBar(null,0,0,0,"");
    }

    public VerticalBar getVerticalBar(String label, double value){
        return new VerticalBar(null, 0,0,0,"");
    }


    public static BarFactory getInstance() {
        if (instance == null) instance = new BarFactory();
        return instance;

    }
}
