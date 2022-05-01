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
    private int delay = 20;
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
        double horiRatio = BarHeightDeterminer.getBarHeight(this);

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

            // setting the bars origin point
            Point horiOrigin = horiPoints.get(i);
            horiOrigin.setLocation(borderWidth, (borderWidth + 2 * gap) + horiOrigin.y);
            Point vertOrigin = vertPoints.get(i);
            vertOrigin.setLocation((borderWidth + 2 * gap) + vertOrigin.x, borderWidth + drawHeight + vertOrigin.y);

            // create a new bar
            Bar hBar = BarFactory.getInstance().getHorizontalBar();
            Bar vBar = BarFactory.getInstance().getVerticalBar();

            // setting the origin, height, width, and label for the horizontal bars:
            hBar.setOrigin(horiOrigin);
            hBar.setHeight(value * horiRatio);
            hBar.setOriginalHeight(value * horiRatio);
            hBar.setWidth(horiWidth);
            hBar.setLabel(label);

            // setting the origin, height, width, and label for the horizontal bars:
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
    private void drawBorder() { // Done by Tariq

        getGraphics().drawRect(borderWidth, borderWidth, drawWidth, drawHeight);

    }

    /**
     * Draws the axis marks (on every 10% percent of largest bar),
     * also draws the number labels on these marks.
     */
    private void drawAxisLabels() {
        boolean horizontal = showHorizontalGraph;
        Graphics g = getGraphics();
        double maxValue = Collections.max(getDataReader().getValues());
        int stringHeight = (g.getFontMetrics().getAscent());

        if (horizontal) { // horizontal graph, labels on x-axis
            g.setColor(Color.BLACK);
            int markDistance = (int) (drawWidth * 0.85) / 10;
            int xAxisPosition = height - borderWidth; // the y value for the bottom of the graph (x-axis)
            int y1MarkCoordinate = xAxisPosition - 10;
            int y2MarkCoordinate = xAxisPosition + 10;
            int yStringCoordinate = xAxisPosition + 13 + stringHeight;
            String labelString;

            for (int i = 0; i < 11; i++) {

                int xMarkCoordinate = borderWidth + i * markDistance;
                g.drawLine(xMarkCoordinate, y1MarkCoordinate, xMarkCoordinate, y2MarkCoordinate);
                labelString = String.format("%.2f", maxValue * (i / 10.0));
                int xStringCoordinate = xMarkCoordinate - g.getFontMetrics().stringWidth(labelString) / 2;
                g.drawString(labelString, xStringCoordinate, yStringCoordinate);

            }

        } else { // vertical graph, labels on y-axis

            g.setColor(Color.BLACK);
            int markDistance = (int) (drawHeight * 0.85) / 10;
            int yAxisPosition = borderWidth; // the x value for the left of the graph (y-axis)
            int lowestMarkHeight = height - borderWidth;
            int x1MarkCoordinate = yAxisPosition + 10;
            int x2MarkCoordinate = yAxisPosition - 10;
            String labelString;

            for (int i = 0; i < 11; i++) {

                int yMarkCoordinate = lowestMarkHeight - i * markDistance;
                g.drawLine(x1MarkCoordinate, yMarkCoordinate, x2MarkCoordinate, yMarkCoordinate);
                labelString = String.format("%.2f", maxValue * (i / 10.0));
                int xStringCoordinate = yAxisPosition - (13 + g.getFontMetrics().stringWidth(labelString));
                int yStringCoordinate = yMarkCoordinate + stringHeight / 2;
                g.drawString(labelString, xStringCoordinate, yStringCoordinate);

            }
        }

    }

    void draw(Graphics g) {

        // *****Add your code here*****
        g.clearRect(0, 0, getWidth(), getHeight());
        drawBorder();
        drawAxisLabels();

        Bar bar;
        if (progress < progressTime) {
            progress++;
            for (int i = 0; i < barCount; i++) {
                bar = horizontalGraph.getBarList().get(i);
                bar.setHeight((int) (bar.getOriginalHeight() * progress * progress / (progressTime * progressTime)));
                bar = verticalGraph.getBarList().get(i);
                bar.setHeight((int) (bar.getOriginalHeight() * progress * progress / (progressTime * progressTime)));
            }
        }

        // setFont(defaultFont);

        if (showHorizontalGraph) {

            horizontalGraph.drawBars(this);
        } else
            verticalGraph.drawBars(this);

        // Toolkit.getDefaultToolkit().sync();
        // ****************************
    }

    public void paint(Graphics g) {
        // super.paint(g);
        draw(g);
        // g.dispose();
        // Toolkit.getDefaultToolkit().sync();
        // switchButton.revalidate();
        switchButton.repaint();

    }

    public static void main(String[] args) throws Exception {
        new JavaGraphs(new DataReader("data.txt")).setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == switchButton) {
            showHorizontalGraph ^= true;
        }

        repaint();
    }

}
