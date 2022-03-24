public class HorizontalGraph extends Graph {

    @Override
    public void drawBars(JavaGraphs javaGraph) {
        for (Bar bar : getBarList()) {
            bar.draw(javaGraph.getGraphics());
        }
    }

}
