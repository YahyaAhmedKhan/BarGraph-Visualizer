import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {

    private ArrayList<String> labels;
    private ArrayList<Double> values;

    /**
     * gets the list of labels of the bars, as read from the file
     * 
     * @return the list of the labels
     */
    public ArrayList<String> getLabels() {
        return this.labels;
    }

    /**
     * gets the list of labels of the bars, as read from the file
     * 
     * @return the list of the labels
     */
    public ArrayList<Double> getValues() {
        return this.values;
    }

    /**
     * makes a new DataReader object, that take the file's name as a string and
     * fills up the labels and values list accordingly
     * 
     * @param fileName the name of the file as a string
     */
    public DataReader(String fileName) {

        labels = new ArrayList<String>();
        values = new ArrayList<Double>();

        String label;
        double value;
        Scanner scanner;
        File file = new File(fileName);

        try {
            scanner = new Scanner(file);

            while (scanner.hasNext()) {

                label = scanner.next();
                value = Double.parseDouble(scanner.next());

                labels.add(label);
                values.add(value);
                if (scanner.hasNextLine()) 
                    scanner.nextLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("an error has occured");
            e.printStackTrace();
        }

    }

}
