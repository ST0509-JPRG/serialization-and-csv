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
import java.util.ArrayList;

public class App {

    public static ArrayList<StudentModule> fromCsvFile(String pathname) {
        File inputFile = new File(pathname);
        ArrayList<StudentModule> studentModules = new ArrayList<>();
        try {
            BufferedReader inStream = new BufferedReader(new FileReader(inputFile));
            String s = inStream.readLine(); // skip first line which is the header
            while ((s = inStream.readLine()) != null) {
                studentModules.add(StudentModule.fromCsv(s));
            }
            inStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception!");
            e.printStackTrace();
        }
        return studentModules;

    }

    public static void toCsvFile(ArrayList<StudentModule> studentModules, String pathname) {
        File outputFile = new File(pathname);
        try {

            PrintWriter outStream = new PrintWriter(new FileOutputStream(outputFile));
            outStream.println(StudentModule.getStringCsvHeader());
            for (StudentModule studentModule : studentModules) {
                outStream.println(studentModule.toCsv());
            }
            outStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    public static ArrayList<StudentModule> deserialize(String filepath) {
        try {
            ObjectInputStream fin = new ObjectInputStream(new FileInputStream(filepath));
            @SuppressWarnings("unchecked")
            ArrayList<StudentModule> deserializedStudentModules = (ArrayList<StudentModule>) fin.readObject();
            fin.close();
            return deserializedStudentModules;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found!");
            e.printStackTrace();
        }

        return null;
    }

    public static void serialize(ArrayList<StudentModule> studentModules, String filepath) {
        FileOutputStream fout;
        ObjectOutputStream oos;
        try {
            fout = new FileOutputStream(filepath);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(studentModules);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Module jprg = new Module("ST0503", "JPRG", 4);
        StudentModule john = new StudentModule(jprg, 50);
        StudentModule peter = new StudentModule(jprg, 70);
        StudentModule mary = new StudentModule(jprg, 90);
        ArrayList<StudentModule> studentModules = new ArrayList<>();
        studentModules.add(john);
        studentModules.add(peter);
        studentModules.add(mary);

        String csvPathname = "./studentModule.csv";
        String serializedPathname = "./studentModules.out";

        toCsvFile(studentModules, csvPathname);
        serialize(studentModules, serializedPathname);

        System.out.println("From CSV: ");
        ArrayList<StudentModule> loadedStudentModules = fromCsvFile(csvPathname);
        for (StudentModule studentModule : loadedStudentModules) {
            System.out.println(studentModule.toString());
        }
        System.out.println();
        System.out.println("Deserialized: ");
        ArrayList<StudentModule> deserializedStudentModules = deserialize(serializedPathname);
        for (StudentModule studentModule : deserializedStudentModules) {
            System.out.println(studentModule.toString());
        }
    }

}
