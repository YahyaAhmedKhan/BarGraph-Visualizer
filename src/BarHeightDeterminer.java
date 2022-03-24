import java.util.Collection;
import java.util.Collections;

import javax.tools.JavaFileObject;

public class BarHeightDeterminer {

    public static double getBarHeight(JavaGraphs javaGraph) {

        int drawWidth = javaGraph.getDrawWidth();

        double maxValue = Collections.max(javaGraph.getDataReader().getValues());

        return ((drawWidth*0.85)/maxValue);

    }
}
