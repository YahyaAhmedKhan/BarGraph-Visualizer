import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
    private static ArrayList<String> labels = new ArrayList<String>();
    private static ArrayList<Double> values = new ArrayList<Double>();

    public DataReader(String FilePathName) {
        File dataFile = new File(FilePathName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(dataFile);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        while (scanner.hasNext()) {
            labels.add(scanner.next());
            values.add(Double.parseDouble(scanner.next()));
            if (scanner.hasNextLine())
                scanner.nextLine();
        }

    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public ArrayList<String> getlabels() {
        return labels;
    }

    public int getBarCount() {
        return values.size();
    }

}
