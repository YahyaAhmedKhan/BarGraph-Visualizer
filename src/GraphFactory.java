import javax.print.attribute.standard.DateTimeAtCompleted;

import org.w3c.dom.html.HTMLAnchorElement;

public class GraphFactory {
    private GraphFactory instance;

    private GraphFactory() {

    }

    public GraphFactory getInstance() {
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
