// package Main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

// import Graph.GraphArea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class JavaGraphs extends JFrame implements ActionListener {

    private static int width = 800;
    private static int height = 600;
    private Timer timer;
    private int delay = 14;
    // private GraphArea area;

    private static int borderWidth = 50;
    private static int gap = 10;
    private int progress = 0;
    private int progressTime = 300;
    private int drawHeight = height - 2 * borderWidth;
    private int drawWidth = width - 2 * borderWidth;
    private Font defaultFont = new Font("TimesRoman", Font.PLAIN, 20);
    private int barCount;
    private DataReader dataReader;
    private boolean showHorizontalGraph = false;
    private Graph horizontalGraph;
    private Graph verticalGraph;
    // private JButton switchButton;

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

    public JavaGraphs() {
        super("Graphs Program");

        getContentPane().setBackground(Color.WHITE);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // *****Add your code here*****

        // ****************************
        timer = new Timer(delay, this);
        timer.start();
    }

    private void drawBorder() {

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
                new JavaGraphs().setVisible(true);

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }

}
