import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class JavaGraphs extends JFrame implements ActionListener {

    private static int width = 800;
    private static int height = 600;
    private static int borderWidth = 70;
    private static int gap = 10;
    private Timer timer;
    private int delay = 14;
    private int progress = 0;
    private int progressTime = 300;
    private int drawHeight = height - 2 * borderWidth;
    private int drawWidth = width - 2 * borderWidth;
    private Font defaultFont = new Font("Sans Fransisco", Font.PLAIN, 15);
    private int barCount;
    private DataReader dataReader;
    private Graph horizontalGraph;
    private Graph verticalGraph;
    private JButton switchButton;
    private boolean showHorizontalGraph = true;

    public int getGap() {
        return gap;
    }

    public int getDrawWidth() {
        return drawWidth;
    }

    public int getDrawHeight() {
        return drawHeight;
    }

    public DataReader getDataReader() {
        return dataReader;
    }

    public int getBarCount() {
        return barCount;
    }

    public JavaGraphs(DataReader dataReader) {
        super("Graphs Program");

        getContentPane().setBackground(Color.WHITE);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        // *****Add your code here*****
        this.dataReader = dataReader;
        setup();
        intializeBars();

        // ****************************
        timer = new Timer(delay, this);
        timer.start();
    }

    /**
     * Initializes the javaGraph object, setting and making necessary fields
     */
    private void setup() {

        horizontalGraph = GraphFactory.getInstance().getGraph(true); // creating/getting a horizontalGraph
        verticalGraph = GraphFactory.getInstance().getGraph(false); // creating/getting a verticalGraph

        barCount = dataReader.getValues().size(); // setting the number of Bars

        switchButton = new JButton("switch");
        switchButton.setLocation(width - 100, height - 60);
        switchButton.setSize(100, 30);
        switchButton.addActionListener(this);
        getContentPane().add(switchButton);

        setFont(defaultFont);
    }

    /**
     * Sets up the bars by setting their fields to the correct values, e.g the
     * label, width, height etc.
     */
    private void intializeBars() {

        double horiWidth = BarWidthDeterminer.getBarWidth(this);
        double horiRatio = BarHeightDeterminer.getBarHeightRatio(this);

        // Deriving the vertical bar widths from the horizontal bar width:
        double vertWidth = horiWidth
                * ((drawWidth - ((barCount + 3) * gap)) / (double) (drawHeight - ((barCount + 3) * gap)));

        // Deriving the vertical bar Height Ratio from the horizontal bar height Ratio:
        double vertRatio = horiRatio * ((double) drawHeight / drawWidth);

        ArrayList<Point> horiPoints = LocationDeterminer.getHorizontalPoints(this);
        ArrayList<Point> vertPoints = LocationDeterminer.getVerticalPoints(this);

        for (int i = 0; i < barCount; i++) {

            // extract the label, and value/height for the bar in vairables
            String label = dataReader.getLabels().get(i);
            double value = dataReader.getValues().get(i);

            // setting the bar's origin point
            Point horiOrigin = horiPoints.get(i);
            horiOrigin.setLocation(borderWidth, (borderWidth + 2 * gap) + horiOrigin.y);
            Point vertOrigin = vertPoints.get(i);
            vertOrigin.setLocation((borderWidth + 2 * gap) + vertOrigin.x, borderWidth + drawHeight + vertOrigin.y);

            // creating new bars
            Bar hBar = BarFactory.getInstance().getHorizontalBar();
            Bar vBar = BarFactory.getInstance().getVerticalBar();

            // setting the origin, height, width, and label for the horizontal bars:
            hBar.setOrigin(horiOrigin);
            hBar.setHeight(value * horiRatio);
            hBar.setOriginalHeight(value * horiRatio);
            hBar.setWidth(horiWidth);
            hBar.setLabel(label);

            // setting the origin, height, width, and label for the vertical bars:
            vBar.setOrigin(vertOrigin);
            vBar.setHeight(value * vertRatio);
            vBar.setOriginalHeight(value * vertRatio);
            vBar.setWidth(vertWidth);
            vBar.setLabel(label);

            // add the correctly setup bar to its respective Graph
            horizontalGraph.getBarList().add(hBar);
            verticalGraph.getBarList().add(vBar);

        }
    }

    /**
     * Draws the rectangular border within which the bars are drawn.
     */
    private void drawBorder(Graphics g) { // Done by Tariq

        g.drawRect(borderWidth, borderWidth, drawWidth, drawHeight);

    }

    /**
     * Draws the axis marks (on every 10% percent of largest bar),
     * also draws the number labels on these marks.
     */
    private void drawAxisLabels(Graphics g) {
        boolean horizontal = showHorizontalGraph;
        // Graphics g = getGraphics();
        double maxValue = Collections.max(getDataReader().getValues());
        int stringHeight = (g.getFontMetrics().getAscent());

        if (horizontal) { // horizontal graph, labels and marks on x-axis

            g.setColor(Color.BLACK);
            int markDistance = (int) (drawWidth * 0.85) / 10; // the x distance b/w 2 adjacent marks
            int xAxisPosition = height - borderWidth; // the y-coord for the bottom of the graph (at the x-axis)
            int markTopY = xAxisPosition - 10; // y-coord for top of the marks
            int markBottomY = xAxisPosition + 10; // y-coord for bottom of the marks
            int stringY = xAxisPosition + 13 + stringHeight; // y-coord for the (bottom of) label
            String label;

            for (int i = 0; i < 11; i++) {

                int markX = borderWidth + i * markDistance; // x-coord for the marks
                g.drawLine(markX, markTopY, markX, markBottomY); // drawing the mark line
                label = String.format("%.2f", maxValue * (i / 10.0)); // getting the label value to decimals
                int stringX = markX - g.getFontMetrics().stringWidth(label) / 2; // x-coord for label (centered at mark)
                g.drawString(label, stringX, stringY); // drawing the number label

            }

        } else { // vertical graph, labels on y-axis

            g.setColor(Color.BLACK);
            int markDistance = (int) (drawHeight * 0.85) / 10; // the y distance b/w 2 adjacent marks
            int yAxisPosition = borderWidth; // the x-coord for the left of the graph (at the y-axis)
            int lowestMarkHeight = height - borderWidth; // y-coord for lowest mark
            int markRightX = yAxisPosition + 10; // x-coord for the mark's right end
            int markLeftX = yAxisPosition - 10; // x-coord for the mark's left end
            String label;

            for (int i = 0; i < 11; i++) {

                int markY = lowestMarkHeight - i * markDistance; // y-coord for the marks
                g.drawLine(markRightX, markY, markLeftX, markY); // drawing the mark line
                label = String.format("%.2f", maxValue * (i / 10.0)); // getting the label value to decimals
                int labelX = yAxisPosition - (13 + g.getFontMetrics().stringWidth(label)); // x-coord for label (with
                                                                                           // gap away from mark)
                int labelY = markY + stringHeight / 2; // y-coord for label
                g.drawString(label, labelX, labelY); // drawing the number label
                // Toolkit.getDefaultToolkit().sync();

            }
        }

    }

    /**
     * draws the visuals on the javaGraph's graphics
     * 
     * @param g the graphics object on which to draw
     */
    void draw(Graphics g) {

        // *****Add your code here*****

        g.clearRect(0, 0, getWidth(), getHeight()); // clears screen before redrawing
        drawBorder(g);
        drawAxisLabels(g);

        Bar bar;

        // Animating the bars growing at the start:
        if (progress < progressTime) { // keep growing until at 100$ final length
            progress++; // increment the progress/ the lenght of the bar

            // for all bars:
            // sets the height of the bar to (progress/progressTime)^2 of actual height
            // the fraction ( progress / progressTime ) ^ 2 appraoches 1 as progress grows
            // fraction grows quadratically, simulating acceleration
            for (int i = 0; i < barCount; i++) {

                bar = horizontalGraph.getBarList().get(i);
                bar.setHeight((int) (bar.getOriginalHeight() * progress * progress / (progressTime * progressTime)));
                bar = verticalGraph.getBarList().get(i);
                bar.setHeight((int) (bar.getOriginalHeight() * progress * progress / (progressTime * progressTime)));
            }
        }

        // Draws either of the 2 graphs depending on selection:
        if (showHorizontalGraph)
            horizontalGraph.drawBars(this, g);
        else
            verticalGraph.drawBars(this, g);

        // switchButton.repaint();

        // ****************************
    }

    @Override
    public void paint(Graphics g) {
        // super.paint(g);
        draw(g);

        Toolkit.getDefaultToolkit().sync();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == switchButton) {
            showHorizontalGraph ^= true; // switches the Graph Selection if Button is pressed
        }
        repaint();
    }

    public static void main(String[] args) throws Exception {
        new JavaGraphs(new DataReader("data.txt")).setVisible(true);
    }

}
