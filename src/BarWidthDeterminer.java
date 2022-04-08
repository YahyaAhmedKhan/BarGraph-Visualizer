public class BarWidthDeterminer {
    public static double getBarWidth(JavaGraphs javaGraphs)
    {
        int gap = javaGraphs.getGap();
        int drawingHeight = javaGraphs.getDrawHeight();
        int barCount = javaGraphs.getBarCount();
        double barWidth = (double) (drawingHeight - (barCount + 3) * gap) / barCount;

        return barWidth;
    } 
}
