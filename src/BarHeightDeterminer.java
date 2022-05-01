import java.util.Collections;

public class BarHeightDeterminer {

    /**
     * Derives the ratio by which
     * 
     * @param javaGraph
     * @return
     */
    public static double getBarHeightRatio(JavaGraphs javaGraph) {

        int drawWidth = javaGraph.getDrawWidth();

        double maxValue = Collections.max(javaGraph.getDataReader().getValues());

        return ((drawWidth * 0.85) / maxValue);

    }
}
