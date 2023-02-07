package src.main.java;

public class SortSetting {

    public static String sortMode(String mode) {
        if (mode.equals("-a") || mode.equals("-d")) {
            if (mode.equals("-a")) {
                return "Ascending";
            } else {
                return "Descending";
            }
        }
        return "Ascending(default)";
    }

    public static String dataType(String type) {
        if (type.equals("-s") || type.equals("-i")) {
            if (type.equals("-s")) {
                return "String";
            } else {
                return "Integer";
            }
        }
        return "bag";
    }
}
