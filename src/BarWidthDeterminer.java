public class BarWidthDeterminer {

    /**
     * Derives the width of the bars for a given JavaGraphs object. <br>
     * <br/>
     * The width found is for the Horizontal bars.
     * 
     * @param javaGraphs the javaGraph for which the barWidth is to be found
     * @return the width of the bars
     */
    public static double getBarWidth(JavaGraphs javaGraphs) {
        int gap = javaGraphs.getGap();
        int drawHeight = javaGraphs.getDrawHeight();
        int barCount = javaGraphs.getBarCount();

        // The calulation for barWidth is slightly simplified.
        // it works by:
        // Taking the drawHeight, removing the gap at the top, then removine half the
        // gap at the bottom, and then dividing it by barcount, them removing gap from
        // this value.
        // Basically: (length occupied by the bars and the gap after each bar) / barCount - gap
        double barWidth = (double) (drawHeight - (barCount + 3) * gap) / barCount;

        return barWidth;
    }

}
