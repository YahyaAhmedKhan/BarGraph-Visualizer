import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JButton;

// import Graph.GraphArea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class JavaGraphs extends JFrame implements ActionListener {

    private static int width = 800;
    private static int height = 600;
    private Timer timer;
    private int delay = 14;
    // private GraphArea area;

    private static int borderWidth = 50;
    public static int gap = 10;
    private int progress = 0;
    private int progressTime = 300;
    private int drawHeight = height - 2 * borderWidth;
    private int drawWidth = width - 2 * borderWidth;
    private Font defaultFont = new Font("TimesRoman", Font.PLAIN, 20);;
    private int barCount;
    private DataReader dataReader;
    private Graph horizontalGraph;
    private Graph verticalGraph;
    private JButton switchButton;
    private boolean showHorizontalGraph = true;

    public static int getGap() {
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
        double HoriWidth = BarWidthDeterminer.getBarWidth(this);
        // double VertWidth = HoriWidth * ((double) (drawWidth - 3 * gap) / (drawHeight
        // - 3 * gap));
        double VertWidth = HoriWidth
                * ((drawWidth - ((barCount + 3) * gap)) / (double) (drawHeight - ((barCount + 3) * gap)));
        System.out.println("horiWidth " + HoriWidth + "\n");
        System.out.println("vertWidth " + VertWidth + "\n");
        System.out.println("vert bar draw width " + (drawWidth - 3 * gap) + "\n");
        System.out.println("hori bar draw height " + (drawHeight - 3 * gap) + "\n");

        double HoriRatio = BarHeightDeterminer.getBarHeight(this);
        double VertRatio = HoriRatio * ((double) drawHeight / drawWidth);

        ArrayList<Point> HoriPoints = LocationDeterminer.getHorizontalPoints(this);
        ArrayList<Point> VertPoints = LocationDeterminer.getVerticalPoints(this);

        for (int i = 0; i < barCount; i++) {

            String label = dataReader.getLabels().get(i);
            double value = dataReader.getValues().get(i);
            Point horiOrigin = HoriPoints.get(i);
            horiOrigin.setLocation(borderWidth, (borderWidth + 2 * gap) + horiOrigin.y);
            Point vertOrigin = VertPoints.get(i);
            vertOrigin.setLocation((borderWidth + 2 * gap) + vertOrigin.x, borderWidth + drawHeight + vertOrigin.y);

            Bar hBar = BarFactory.getInstance().getHorizontalBar();
            Bar vBar = BarFactory.getInstance().getVerticalBar();

            hBar.setOrigin(horiOrigin);
            hBar.setHeight(value * HoriRatio);
            hBar.setOriginalHeight(value * HoriRatio);

            hBar.setWidth(HoriWidth);
            hBar.setLabel(label);

            vBar.setOrigin(vertOrigin);
            vBar.setHeight(value * VertRatio);
            vBar.setOriginalHeight(value * VertRatio);
            vBar.setWidth(VertWidth);
            vBar.setLabel(label);

            horizontalGraph.getBarList().add(hBar);
            verticalGraph.getBarList().add(vBar);

            // System.out.println("vP\n" + VertPoints);
            // System.out.println();
            // System.out.println("hP\n"+ HoriPoints);

        }
    }

    private void drawBorder() {
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.drawRect(borderWidth, borderWidth, width - 2 * borderWidth, height - 2 * borderWidth);

    }

    private void drawAxisLabels(boolean horizontal) {

        Graphics2D g2d = (Graphics2D) getGraphics();
        double maxValue = Collections.max(getDataReader().getValues());

        if (horizontal) {
            g2d.setColor(Color.BLACK);
            double markDistance = (drawWidth * 0.85) / 10;
            int axisHeight = height - borderWidth;
            String labelString;

            for (int i = 0; i < 11; i++) {
                g2d.drawLine((int) (borderWidth + markDistance * i), axisHeight - 10,
                        (int) (borderWidth + markDistance * i),
                        axisHeight + 10);
                labelString = String.format("%.2f", maxValue * (i / 10.0));
                g2d.drawString(labelString,
                        (int) (borderWidth + markDistance * i) - (g2d.getFontMetrics().stringWidth(labelString)) / 2,
                        axisHeight + 20);

            }
        } else {
            g2d.setColor(Color.BLACK);
            int markDistance = (int) (drawHeight * 0.85) / 10;
            int axisDisplacement = borderWidth;
            String labelString;
            int initialMarkHeight = (int) (drawHeight * 0.15 + borderWidth);

            for (int i = 0; i < 11; i++) {
                int markOffset = i * markDistance;
                g2d.drawLine(axisDisplacement - 10, initialMarkHeight + markOffset, axisDisplacement + 10,
                        initialMarkHeight + markOffset);

                labelString = String.format("%.2f", maxValue * ((10 - i) / 10.0));

                int stringHeight = (g2d.getFontMetrics().getAscent() + g2d.getFontMetrics().getDescent()) / 2;
                g2d.drawString(labelString, axisDisplacement - 40,
                        (int) (borderWidth + 0.15 * drawHeight + i * markDistance) + stringHeight / 2);
            }
        }

    }

    void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        // *****Add your code here*****
        g2d.clearRect(0, 0, getWidth(), getHeight());
        drawBorder();
        drawAxisLabels(showHorizontalGraph);

        if (progress < progressTime) {
            for (int i = 0; i < barCount; i++) {
                Bar bar = horizontalGraph.getBarList().get(i);
                bar.setHeight((int) (bar.getOriginalHeight() * progress * progress / (progressTime * progressTime)));
                bar = verticalGraph.getBarList().get(i);
                bar.setHeight((int) (bar.getOriginalHeight() * progress * progress / (progressTime * progressTime)));
            }
        }
        progress++;

        if (showHorizontalGraph)
            horizontalGraph.drawBars(this);
        else
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
            // System.out.println(e.getSource());
            showHorizontalGraph ^= true;
        }

        repaint();
    }

}
