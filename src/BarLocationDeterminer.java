import java.util.ArrayList;
import java.awt.Point;

public class BarLocationDeterminer {

    public static ArrayList<Point> getHorizontalPoints(JavaGraphs javaGraph) {

        ArrayList<Point> points = new ArrayList<Point>();

        double horiWidth = BarWidthDeterminer.getBarWidth(javaGraph);
        int gap = javaGraph.getGap();

        for (int i = 0; i < javaGraph.getBarCount(); i++) {
            points.add(new Point(0, (int) (i * (horiWidth + gap))));
        }
        return points;
    }

    public static ArrayList<Point> getVerticalPoints(JavaGraphs javaGraph) {

        ArrayList<Point> points = new ArrayList<Point>();

        double horiWidth = BarWidthDeterminer.getBarWidth(javaGraph);
        int gap = javaGraph.getGap();
        int drawHeight = javaGraph.getDrawHeight();
        int drawWidth = javaGraph.getDrawWidth();

        double vertWidth = horiWidth * ((double) (drawHeight - 3 * gap) / (drawWidth - 3 * gap));

        for (int i = 0; i < javaGraph.getBarCount(); i++) {
            points.add(new Point((int) (i * (vertWidth + gap)), 0));
        }
        return points;
    }

}
