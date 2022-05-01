import java.awt.*;
import java.util.ArrayList;

public abstract class Graph {

    private ArrayList<Bar> barList;
    public Graph(){
        barList = new ArrayList<Bar>();
    }
    
    public abstract void drawBars(JavaGraphs javaGraph);

    public ArrayList<Bar> getBarList() {
        return barList;
    }
    

}
