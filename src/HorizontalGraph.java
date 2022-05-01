import java.awt.*;
public class HorizontalGraph extends Graph {

    @Override
    public void drawBars(JavaGraphs javaGraph, Graphics g) {
        //Graphics g = javaGraph.getGraphics();
        for (Bar bar : getBarList()) {
            bar.draw(g);
        }
    }

}
