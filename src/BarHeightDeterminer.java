import java.util.Collections;

public class BarHeightDeterminer {
    public static double ratio;

    public static double getRatio(JavaGraphs javagraph) {

        int maxHeight = (int) (javagraph.getDrawHeight() * (0.85));
        ratio = maxHeight / Collections.max(javagraph.getDataReader().getValues());
        return ratio;
    }

}
