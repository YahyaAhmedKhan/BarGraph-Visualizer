import java.awt.*;

public class VerticalGraph extends Graph {

    @Override
    public void drawBars(JavaGraphs javaGraph) {
        Graphics g = javaGraph.getGraphics();

        for (Bar bar : getBarList()){
            bar.draw(g);
        }
    }
    
}
