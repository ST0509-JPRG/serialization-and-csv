import java.io.Serializable;

public class Module implements Serializable {
    private String code;
    private String name;
    private int credit;

    public Module(String code, String name, int credit) {
        this.code = code;
        this.name = name;
        this.credit = credit;
    }

    public String toString() {
        return String.format("%s - %s (Credit: %d)", code, name, credit);
    }

    public String toCsv() {
        return String.format("%s,%s,%d", code, name, credit);
    }

    public static String getStringCsvHeader() {
        return String.format("%s,%s,%s", "code", "name", "credit");
    }

}
