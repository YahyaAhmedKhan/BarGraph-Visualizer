import java.awt.*;
import java.util.ArrayList;

public abstract class Graph {

    private ArrayList<Bar> barList;


    public Graph() {
        barList = new ArrayList<Bar>();
    }

    /**
     * draws the bars in the Graph on the javaGraphs object
     * @param javaGraph
     */
    public abstract void drawBars(JavaGraphs javaGraph);

    /**
     * gets the list of the bars in the Graph
     * @return
     */
    public ArrayList<Bar> getBarList() {
        return barList;
    }

}
