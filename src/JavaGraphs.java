// package Main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

// import Graph.GraphArea;

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
    private Font defaultFont = new Font("TimesRoman", Font.PLAIN, 20);
    private int barCount;
    private DataReader dataReader;
    private Graph horizontalGraph;
    private Graph verticalGraph;
    private JButton switchButton;
    private boolean showHorizontalGraph = true;
    private Font yAxisFont;

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
     * Sets up the JavaGraphs Object, by instantiating necessary lists setting up window size.
     */
    private void setup() {

        horizontalGraph = GraphFactory.getInstance().getGraph(true);
        verticalGraph = GraphFactory.getInstance().getGraph(false);

        barCount = dataReader.getValues().size();

        switchButton = new JButton("switch");
        switchButton.setLocation(width - 100, height - 60);
        switchButton.setSize(100, 30);
        switchButton.addActionListener(this);
        getContentPane().add(switchButton);

    }
    /**
     * Initalizes the bars by setting them up with their respective Labels and correspoonding 
     * Values as read from the DataReader
     */
    private void intializeBars() {
         // width of bars for Horizontal graph:
        double horiWidth = BarWidthDeterminer.getBarWidth(this);

         // width of bars for Vertical graph:
        double vertWidth = horiWidth
                * ((drawWidth - ((barCount + 3) * gap)) / (double) (drawHeight - ((barCount + 3) * gap)));
                
        double horiRatio = BarHeightDeterminer.getRatio(this);
        double vertRatio = horiRatio * ((double) drawHeight / drawWidth);

        ArrayList<Point> horiPoints = LocationDeterminer.getHorizontalPoints(this);
        ArrayList<Point> vertPoints = LocationDeterminer.getVerticalPoints(this);

        for (int i = 0; i < barCount; i++) {

            String label = dataReader.getLabels().get(i);
            double value = dataReader.getValues().get(i);
            Point horiOrigin = horiPoints.get(i);
            horiOrigin.setLocation(borderWidth, (borderWidth + 2 * gap) + horiOrigin.y);
            Point vertOrigin = vertPoints.get(i);
            vertOrigin.setLocation((borderWidth + 2 * gap) + vertOrigin.x, borderWidth + drawHeight + vertOrigin.y);

            Bar hBar = BarFactory.getInstance().getHorizontalBar(null, 0);
            Bar vBar = BarFactory.getInstance().getVerticalBar(null, 0);

            hBar.setOrigin(horiOrigin);
            hBar.setHeight(value * horiRatio);
            hBar.setOriginalHeight(value * horiRatio);

            hBar.setWidth(horiWidth);
            hBar.setLabel(label);

            vBar.setOrigin(vertOrigin);
            vBar.setHeight(value * vertRatio);
            vBar.setOriginalHeight(value * vertRatio);
            vBar.setWidth(vertWidth);
            vBar.setLabel(label);

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
    private void drawAxisLabels() { // Done by Tariq
        boolean horizontal = showHorizontalGraph;
        Graphics g = getGraphics();
        double maxValue = Collections.max(getDataReader().getValues());
        int stringHeight = (g.getFontMetrics().getAscent());

        if (horizontal) { // horizontal graph, labels on x-axis
            g.setColor(Color.BLACK);
            setFont(defaultFont);
            int markDistance = (int) (drawWidth * 0.85) / 10;
            int xAxisPosition = height - borderWidth; // the y value for the bottom of the graph (x-axis)
            int y1LineCoordinate = xAxisPosition - 10;
            int y2LineCoordinate = xAxisPosition + 10;
            String labelString;
            for (int i = 0; i < 11; i++) {

                g.drawLine(borderWidth + i * markDistance, y1LineCoordinate, borderWidth + i * markDistance,
                        y2LineCoordinate);
            }

        } else { // vertical graph, labels on y-axis

            g.setColor(Color.BLACK);
            int markDistance = (int) (drawHeight * 0.85) / 10;
            int yAxisPosition = borderWidth; // the x value for the left of the graph (y-axis)
            int x1LineCoordinate = yAxisPosition + 10;
            int x2LineCoordinate = yAxisPosition - 10;
            String labelString;
            int lowestMarkHeight = height - borderWidth;

            for (int i = 0; i < 11; i++) {

                g.drawLine(x1LineCoordinate, lowestMarkHeight - i * markDistance, x2LineCoordinate,
                        lowestMarkHeight - i * markDistance);

            }
        }
    }

    void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        // *****Add your code here*****
        g2d.clearRect(0, 0, getWidth(), getHeight());        
        drawBorder();
        drawAxisLabels();
        switchButton.repaint();

        // ****************************

    }

    public void paint(Graphics g) {
        draw(g);
        g.dispose();
    }

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JavaGraphs(new DataReader("data.txt")).setVisible(true);

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == switchButton) {
            showHorizontalGraph ^= true;
        }
        repaint();
    }

}
