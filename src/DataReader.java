import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {

    private ArrayList<String> labels;
    private ArrayList<Double> values;

    public ArrayList<String> getLabels() {
        return this.labels;
    }

    public ArrayList<Double> getValues() {
        return this.values;
    }

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
