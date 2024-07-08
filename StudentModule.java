import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class StudentModule implements Serializable {
    private Module module;
    private double score;

    public StudentModule(Module module, double score) {
        this.module = module;
        this.score = score;
    }

    public String toCsv() {
        return String.format("%s,%.2f", module.toCsv(), score);
    }

    public String toString() {
        return String.format("%s - %.2f/100", module.toString(), score);
    }

    public static StudentModule fromCsv(String csv) {
        String[] values = csv.split(",");
        return new StudentModule(new Module(values[0], values[1], Integer.parseInt(values[2])),
                Double.parseDouble(values[3]));
    }

    public static String getStringCsvHeader() {
        return String.format("%s,%s", Module.getStringCsvHeader(), "score");
    }

}
