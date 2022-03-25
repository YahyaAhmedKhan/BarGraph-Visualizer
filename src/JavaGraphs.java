import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.JButton;

// import Graph.GraphArea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JavaGraphs extends JFrame implements ActionListener {

    private static int width = 800;
    private static int height = 600;
    private Timer timer;
    private int delay = 8;
    // private GraphArea area;

    private static int borderWidth = 50;
    public static int gap = 10;
    private int progress;
    private int progressTime;
    private int drawHeight = height - 2 * borderWidth;
    private int drawWidth = width - 2 * borderWidth;
    private Font defaultFont = new Font("TimesRoman", Font.PLAIN, 20);;
    private int barCount;
    private DataReader dataReader;
    private Graph horizontalGraph;
    private Graph verticalGraph;
    private JButton switchButton;

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

    }

    private void intializeBars() {
        double HoriWidth = BarWidthDeterminer.getBarWidth(this);
        // double VertWidth = HoriWidth * ((double) (drawWidth - 3 * gap) / (drawHeight - 3 * gap));
        double VertWidth = HoriWidth * ((drawWidth-((barCount+3)*gap))/(double)(drawHeight -((barCount+3)*gap)));
        //CW*raio + gap  )) *barcount = drawwitdh-3gap
        //drawW-3gap / ((barC)) - gap
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
            hBar.setWidth(HoriWidth);
            hBar.setLabel(label);

            vBar.setOrigin(vertOrigin);
            vBar.setHeight(value * VertRatio);
            vBar.setWidth(VertWidth);
            System.out.println(VertWidth);
            vBar.setLabel(label);

            horizontalGraph.getBarList().add(hBar);
            verticalGraph.getBarList().add(vBar);

            // System.out.println("vP\n" + VertPoints);
            // System.out.println();
            // System.out.println("hP\n"+  HoriPoints);

        }
    }

    private void drawBorder() {
        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.drawRect(borderWidth, borderWidth, width - 2 * borderWidth, height - 2 * borderWidth);

    }

    void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        // *****Add your code here*****
        g2d.clearRect(0, 0, getWidth(), getHeight());
        drawBorder();
        // horizontalGraph.drawBars(this);
        verticalGraph.drawBars(this);

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

        repaint();
    }

}
