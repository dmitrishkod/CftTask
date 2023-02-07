package src.main.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindFile {
    static List<String> paths = new ArrayList<>();

    public static List<String> getPaths() {
        return paths;
    }

    public static void findFile(File path){
        if (path.isFile() && isTxt(path.getName())){
            paths.add(path.getPath());
        }
        try {
            File[] files = path.listFiles();
            for (File f : files) {
                FindFile.findFile(f);
            }
        } catch (NullPointerException ignored){
        }
    }

    private static boolean isTxt(String name){
        char ch = name.charAt(0);
        if (!Character.isDigit(ch) && !Character.isLetter(ch)){
            return false;
        }
        int txt = name.lastIndexOf(".txt");
        if ( txt < 0){
            return false;
        }
        return true;
    }
}
