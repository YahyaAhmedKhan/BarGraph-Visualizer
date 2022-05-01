import java.util.Collections;

public class BarHeightDeterminer {

    /**
     * Derives the ratio of the bar's value to the length it should have on the
     * graph <br>
     * <br/>
     * For example, if the value for CARS is 12, but the length on the screen
     * should be 66, then the ratio is 66/12 = 5.5. Multiplying the current value by
     * the ratio will get the correct length. <br>
     * <br/>
     * 
     * This calculation only needs the greatest valued Bar's value as it will
     * determine the ratio. (As only it's length has to be a certain length, the
     * rest will just be scaled accordingly) <br>
     * <br/>
     * 
     * This ratio is for the Horizontal Bars
     * 
     * @param javaGraph the JavaGraphs for which to determine the ratio
     * @return the ratio of the values to desired lengths
     */
    public static double getBarHeightRatio(JavaGraphs javaGraph) {

        int drawWidth = javaGraph.getDrawWidth();

        double maxValue = Collections.max(javaGraph.getDataReader().getValues());

        return ((drawWidth * 0.85) / maxValue);

    }
}
