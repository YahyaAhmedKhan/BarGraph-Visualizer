public class GraphFactory {
    private static GraphFactory instance;

    private GraphFactory() {

    }

    public static GraphFactory getInstance() {
        if (instance == null) {
            instance = new GraphFactory();
        }
        return instance;
    }

    public Graph getGraph(boolean horizontal) {
        if (horizontal) {
            return new HorizontalGraph();

        } else
            return new VerticalGraph();
    }
}
