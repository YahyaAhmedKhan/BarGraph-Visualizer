import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.xml.crypto.Data;
import javax.swing.JButton;

// import Graph.GraphArea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaGraphs extends JFrame implements ActionListener {

    private static int width = 800;
    private static int height = 600;
    private Timer timer;
    private int delay = 8;
    // private GraphArea area;

    private static int borderWidth = 60;
    public static int gap = 10;
    private int progress;
    private int progressTime;
    public static int drawHeight = height - 2 *borderWidth;
    public static int drawWidth = width - 2 * borderWidth;
    private Font defaultFont = new Font("TimesRoman", Font.PLAIN, 20);;
    private int barCount;
    private DataReader dataReader;

    public DataReader getDataReader() {
        return dataReader;
    }
    

    public int getBarCount(){
        return barCount;
    }
    private Graph horizontalGraph;
    private Graph verticalGraph;   
    private JButton switchButton;

    public JavaGraphs(DataReader dataReader) {
        super("Graphs Program");

        getContentPane().setBackground(Color.WHITE);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // *****Add your code here*****
        this.dataReader = dataReader;

        horizontalGraph = GraphFactory.getInstance().getGraph(true);
        verticalGraph = GraphFactory.getInstance().getGraph(false);

        barCount = dataReader.getValues().size();

        for (int i = 0; i < barCount; i++) {
            Bar hBar;
            Bar vBar;

            String label = dataReader.getLabels().get(i);
            double value = dataReader.getValues().get(i);

            hBar = BarFactory.getInstance().getHorizontalBar();
            vBar = BarFactory.getInstance().getHorizontalBar();

            hBar.setLabel(label);
            hBar.setHeight(value);
            vBar.setLabel(label);
            vBar.setHeight(value);

            horizontalGraph.getBarList().add(hBar);
            verticalGraph.getBarList().add(vBar);

        }

        // ****************************
        timer = new Timer(delay, this);
        timer.start();
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
                new JavaGraphs(new DataReader("data.txt")).setVisible(true);

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }

}
