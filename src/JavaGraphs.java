import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.font.TextLayout;
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
        // timer.addActionListener(this);
        timer.start();
        System.out.println(getFont());

    }

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

    private void intializeBars() {
        double horiWidth = BarWidthDeterminer.getBarWidth(this);
        double vertWidth = horiWidth
                * ((drawWidth - ((barCount + 3) * gap)) / (double) (drawHeight - ((barCount + 3) * gap)));

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

        }
    }

    private void drawBorder(Graphics g) {

        g.drawRect(borderWidth, borderWidth, width - 2 * borderWidth, height - 2 * borderWidth);

    }

    /**
     * Draws the axis marks (on every 10% percent of largest bar),
     * also draws the number labels on these marks.
     */
    private void drawAxisLabels() {
        boolean horizontal = showHorizontalGraph;
        Graphics2D g = (Graphics2D) getGraphics();
        double maxValue = Collections.max(getDataReader().getValues());
        int stringHeight = (g.getFontMetrics().getAscent());
        if (horizontal) { // horizontal graph, labels on x-axis

            g.setColor(Color.BLACK);
            double markDistance = (drawWidth * 0.85) / 10;
            int xAxisPosition = height - borderWidth; // the y value for the bottom of the graph (x-axis)
            String labelString;

            for (int i = 0; i < 11; i++) {
                g.drawLine((int) (borderWidth + markDistance * i), xAxisPosition - 10,
                        (int) (borderWidth + markDistance * i),
                        xAxisPosition + 10);
                labelString = String.format("%.2f", maxValue * (i / 10.0));

                g.drawString(labelString,
                        (int) (borderWidth + markDistance * i) - (g.getFontMetrics().stringWidth(labelString)) / 2,
                        xAxisPosition + 10 + 3 + stringHeight);

            }

        } else { // vertical graph, labels on y-axis

            g.setColor(Color.BLACK);
            int markDistance = (int) (drawHeight * 0.85) / 10;
            int yAxisPosition = borderWidth; // the x value for the left of the graph (y-axis)
            String labelString;
            int lowestMarkHeight = height - borderWidth;

            for (int i = 0; i < 11; i++) {
                int markOffset = i * markDistance;

                g.drawLine(yAxisPosition - 10, lowestMarkHeight - markOffset, yAxisPosition + 10,
                        lowestMarkHeight - markOffset);

                labelString = String.format("%.2f", maxValue * (i / 10.0));
                int stringWidth = g.getFontMetrics().stringWidth(labelString);

                g.drawString(labelString, yAxisPosition - 13 - stringWidth,
                        (lowestMarkHeight - markOffset) + stringHeight / 2);

            }
        }

    }

    void draw(Graphics g) {

        // *****Add your code here*****
        g.clearRect(0, 0, getWidth(), getHeight());
        drawBorder(g);
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
