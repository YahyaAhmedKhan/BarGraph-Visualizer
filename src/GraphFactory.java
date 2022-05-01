public class GraphFactory {
    private static GraphFactory instance;

    private GraphFactory() {

    }

    /**
     * get the singelton instance of the GraphFactory
     * 
     * @return the singelton instance
     */
    public static GraphFactory getInstance() {
        if (instance == null) {
            instance = new GraphFactory();
        }
        return instance;
    }

    /**
     * gets a new Horizontal or Vertical Graphs object
     * 
     * @param horizontal the boolean checking whether a horizontal or vertical graph
     *                   is to be returned
     * @return
     */
    public Graph getGraph(boolean horizontal) {
        if (horizontal) {
            return new HorizontalGraph();

        } else
            return new VerticalGraph();
    }
}
