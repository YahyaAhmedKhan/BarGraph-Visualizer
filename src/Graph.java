import java.util.ArrayList;

public abstract class Graph {

    private ArrayList<Bar> barList;
    public Graph(){

    }
    
    public abstract void drawBars(JavaGraphs javaGraph);

    public ArrayList<Bar> getBarList() {
        return barList;
    }
    

}
