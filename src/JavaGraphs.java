import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import javax.swing.JButton;
import java.awt.geom.Rectangle2D;

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

    private void setup() {

        horizontalGraph = GraphFactory.getInstance().getGraph(true);
        verticalGraph = GraphFactory.getInstance().getGraph(false);

        barCount = dataReader.getValues().size();

        switchButton = new JButton("switch");
        switchButton.setLocation(width - 100, height - 60);
        switchButton.setSize(100, 30);
        switchButton.addActionListener(this);
        // switchButton.setPreferredSize(new Dimension(width,height));
        getContentPane().add(switchButton);

    }

    private void intializeBars() {
        double horiWidth = BarWidthDeterminer.getBarWidth(this);
        // double vertWidth = horiWidth * ((double) (drawWidth - 3 * gap) / (drawHeight
        // - 3 * gap));
        double vertWidth = horiWidth
                * ((drawWidth - ((barCount + 3) * gap)) / (double) (drawHeight - ((barCount + 3) * gap)));
        // System.out.println("horiWidth " + horiWidth + "\n");
        // System.out.println("vertWidth " + vertWidth + "\n");
        // System.out.println("vert bar draw width " + (drawWidth - 3 * gap) + "\n");
        // System.out.println("hori bar draw height " + (drawHeight - 3 * gap) + "\n");

        double horiRatio = BarHeightDeterminer.getBarHeight(this);
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

            Bar hBar = BarFactory.getInstance().getHorizontalBar();
            Bar vBar = BarFactory.getInstance().getVerticalBar();

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

            // System.out.println("vP\n" + vertPoints);
            // System.out.println();
            // System.out.println("hP\n"+ horiPoints);

        }
    }

    private void drawBorder() {
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.drawRect(borderWidth, borderWidth, width - 2 * borderWidth, height - 2 * borderWidth);

    }

    /**
     * Draws the axis marks (on every 10% percent of largest bar),
     * also draws the number labels on these marks.
     */
    private void drawAxisLabels() {
        boolean horizontal = showHorizontalGraph;
        Graphics2D g2d = (Graphics2D) getGraphics();
        double maxValue = Collections.max(getDataReader().getValues());
        int stringHeight = (g2d.getFontMetrics().getAscent());
        // int stringHeight = (g2d.getFontMetrics().getAscent() +
        // g2d.getFontMetrics().getDescent()) / 2; //upper definiton is better
        // 

        if (horizontal) { // horizontal graph, labels on x-axis
            g2d.setColor(Color.BLACK);
            setFont(defaultFont);
            double markDistance = (drawWidth * 0.85) / 10;
            int xAxisPosition = height - borderWidth; // the y value for the bottom of the graph (x-axis)
            String labelString;

            // DRAWING THE HORIZONTAL DASHES
            // dash is drawLine at (x1,y1) to (x2,y2)
            // y1 = ten pixels down of x-axis | y2 = ten pixels up of x-axis
            // x1 = x2 = (x coord of 0 mark) + (markDistance * i) {i = iteration}
            // x1 = x2 obviously, mark is at same x-level

            // DRAWING THE NUMBERS ON AXIS
            // Bottom left of string is at (x, y) in drawString function
            // Top of label should be at: (x-axis level),
            // Then further 10 pixels down for dash-mark,
            // Then further down 3 pixels to give gap bw dash and label
            // So then drawString is at this height, plus [string's height]

            for (int i = 0; i < 11; i++) {
                g2d.drawLine((int) (borderWidth + markDistance * i), xAxisPosition - 10,
                        (int) (borderWidth + markDistance * i),
                        xAxisPosition + 10);
                labelString = String.format("%.2f", maxValue * (i / 10.0));

                g2d.drawString(labelString,
                        (int) (borderWidth + markDistance * i) - (g2d.getFontMetrics().stringWidth(labelString)) / 2,
                        xAxisPosition + 10 + 3 + stringHeight);

            }

        } else { // vertical graph, labels on y-axis

            g2d.setColor(Color.BLACK);
            int markDistance = (int) (drawHeight * 0.85) / 10;
            int yAxisPosition = borderWidth; // the x value for the left of the graph (y-axis)
            String labelString;
            int lowestMarkHeight = height - borderWidth;

            for (int i = 0; i < 11; i++) {
                int markOffset = i * markDistance;

                // DRAWING THE VERTICAL DASHES
                // dash at (x1,y1) to (x2,y2)
                // x1 = ten pixels left of y-axis | x2 = ten pixels right of y-axis
                // y1 = y2 = (y coord of 0 mark) - (markDistance * i) {i = iteration}
                // y1 = y2 obviously, mark is at same y-level
                // y is MINUS markDistance because y-value decreases as you go up the axis

                // DRAWING THE NUMBERS ON AXIS
                // Bottom left of string is at (x, y) in drawString function
                // Top of label should be at: (x-axis level),
                // Then further 10 pixels down for dash-mark,
                // Then further down 3 pixels to give gap bw dash and label
                // So then drawString is at this height, plus [string's height]

                g2d.drawLine(yAxisPosition - 10, lowestMarkHeight - markOffset, yAxisPosition + 10,
                        lowestMarkHeight - markOffset);

                // labelString = maxValue != (int) maxValue ? String.format("%.2f", maxValue *
                // ((10 - i) / 10.0)) : Integer.toString((int) (maxValue * ((10 - i) / 10.0)) );
                // System.out.println(g2d.getFontMetrics().stringWidth(labelString));
                labelString = String.format("%.2f", maxValue * (i / 10.0));
                int stringWidth = g2d.getFontMetrics().stringWidth(labelString);

                g2d.drawString(labelString, yAxisPosition - 13 - stringWidth,
                        (lowestMarkHeight - markOffset) + stringHeight / 2);

                // //draw rects
                // Rectangle2D a = g2d.getFontMetrics().getStringBounds((labelString), g2d);
                // a.setFrame(axis - borderWidth/2 -
                // g2d.getFontMetrics().stringWidth(labelString)/2, (int) ((borderWidth + 0.15 *
                // drawHeight + i * markDistance) + stringHeight / 2) - stringHeight,
                // a.getWidth(), stringHeight);
                // g2d.draw(a);
                // }
                // g2d.drawRect((axis - 13 - stringWidth), ((lowestMarkHeight - markOffset) +
                // stringHeight / 2)-stringHeight, stringWidth,
                // stringHeight);
                ;
            }
        }

    }

    void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        // *****Add your code here*****
        // setFont(defaultFont);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        drawBorder();
        drawAxisLabels();
        if (progress < progressTime) {
            for (int i = 0; i < barCount; i++) {
                Bar bar = horizontalGraph.getBarList().get(i);
                bar.setHeight((int) (bar.getOriginalHeight() * progress * progress / (progressTime * progressTime)));
                bar = verticalGraph.getBarList().get(i);
                bar.setHeight((int) (bar.getOriginalHeight() * progress * progress / (progressTime * progressTime)));
            }
        }
        progress++;

        if (showHorizontalGraph) {
            horizontalGraph.drawBars(this);
        } else
            verticalGraph.drawBars(this);

        // ****************************
    }

    public void paint(Graphics g) {
        draw(g);
        g.dispose();

        // switchButton.revalidate();
        switchButton.repaint();

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
