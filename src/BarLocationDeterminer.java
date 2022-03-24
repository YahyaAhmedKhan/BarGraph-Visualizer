import java.util.ArrayList;
import java.awt.Point;

public class BarLocationDeterminer {

    public static ArrayList<Point> getHorizontalPoints(JavaGraphs javaGraph) {

        ArrayList<Point> points = new ArrayList<Point>();

        double HoriWidth = BarWidthDeterminer.getBarWidth(javaGraph);
        int gap = javaGraph.getGap();

        for (int i = 0; i < javaGraph.getBarCount(); i++) {
            points.add(new Point(0, (int) (i * (HoriWidth + gap))));
        }
        return points;
    }

    public static ArrayList<Point> getVerticalPoints(JavaGraphs javaGraph) {

        ArrayList<Point> points = new ArrayList<Point>();

        double HoriWidth = BarWidthDeterminer.getBarWidth(javaGraph);
        int gap = javaGraph.getGap();
        int drawHeight = javaGraph.getDrawHeight();
        double VertWidth = HoriWidth * ((double) (drawHeight - 3 * gap) / (drawHeight - 3 * gap));

        for (int i = 0; i < javaGraph.getBarCount(); i++) {
            points.add(new Point((int) (i * (VertWidth + gap)), 0));
        }
        return points;
    }

}
