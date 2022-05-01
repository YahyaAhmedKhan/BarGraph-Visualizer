import java.util.ArrayList;
import java.awt.Point;

public class BarLocationDeterminer {

    /**
     * Determines the origin location of the all the Horizontal bars in a JavaGraphs
     * object <br>
     * <br/>
     * 
     * The points are found relative to the drawing area, not the whole Frame. The
     * drawing area is the area within the border s<br>
     * <br/>
     * 
     * The first bar is always at (0,0). This simplifes the visualizing where tha bars
     * draw from and if need be, the point can be changed according to situation, so
     * it simplifies calculations as well.
     * 
     * @param javaGraph the JavaGraphs object to whose bar Points are to be
     *                  determined
     * @return an ArrayList of Points with the correct locations of the bars
     */
    public static ArrayList<Point> getHorizontalPoints(JavaGraphs javaGraph) {

        ArrayList<Point> points = new ArrayList<Point>();

        double horiWidth = BarWidthDeterminer.getBarWidth(javaGraph);
        int gap = javaGraph.getGap(); // the gap b/w bars when graph is drawn

        for (int i = 0; i < javaGraph.getBarCount(); i++) {
            points.add(new Point(0, (int) (i * (horiWidth + gap))));
        }
        return points;
    }

    /**
     * Determines the origin location of the all the Vertical bars in a JavaGraphs
     * object <br>
     * <br/>
     * 
     * The points are found relative to the drawing area, not the whole Frame. The
     * drawing area is the area within the borders
     * 
     * @param javaGraph the JavaGraphs object to whose bar Points are to be
     *                  determined
     * @return an ArrayList of Points with the correct locations of the bars
     */
    public static ArrayList<Point> getVerticalPoints(JavaGraphs javaGraph) {

        ArrayList<Point> points = new ArrayList<Point>();

        int gap = javaGraph.getGap(); // the gap b/w bars when graph is drawn
        int drawHeight = javaGraph.getDrawHeight();
        int drawWidth = javaGraph.getDrawWidth();

        double horiWidth = BarWidthDeterminer.getBarWidth(javaGraph);
        double vertWidth = horiWidth * ((double) (drawHeight - 3 * gap) / (drawWidth - 3 * gap)); // width of bars on
                                                                                                  // Vertical Bar Graph

        for (int i = 0; i < javaGraph.getBarCount(); i++) {
            points.add(new Point((int) (i * (vertWidth + gap)), 0));
        }
        return points;
    }

}
