public class BarWidthDeterminer {

    public static double getBarWidth(JavaGraphs javaGraph){

        int gap = javaGraph.getGap();

        int drawHeight = javaGraph.getDrawHeight();
        int barCount = javaGraph.getBarCount();

        return ((((double) drawHeight-3*gap)/barCount)-gap);
    } //mine

    // public static double getBarWidth(JavaGraphs javaGraphs)
    // {
    //     int gap = javaGraphs.getGap();
    //     int drawingHeight = javaGraphs.getDrawHeight();
    //     int barCount = javaGraphs.getBarCount();
    //     double barWidth = (double) (drawingHeight - (barCount + 3) * gap) / barCount;

    //     return barWidth;
    // } //tariqs
    
}
