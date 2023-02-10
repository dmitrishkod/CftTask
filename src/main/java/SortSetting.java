package src.main.java;

import java.util.LinkedList;

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
        return "bug";
    }

    private static int[] mergeArrayInt(int [] groupA, int [] groupB, String mode){
        int[] res = new int[groupA.length + groupB.length];
        int n = groupA.length;
        int m = groupB.length;
        int i = 0, j = 0, k = 0;
        if (mode.equals("Descending")){
        while (i < n && j < m) {
            if (groupA[i] >= groupB[j]) {
                res[k] = groupA[i];
                i++; }
            else {
                res[k] = groupB[j];
                j++; }
            k++; } }
        else {
            while (i < n && j < m) {
                if (groupA[i] <= groupB[j]) {
                    res[k] = groupA[i];
                    i++; }
                else {
                    res[k] = groupB[j];
                    j++; }
                k++; }
        }
        while (i < n) {
            res[k] = groupA[i];
            i++;
            k++; }
        while (j < m) {
            res[k] = groupB[j];
            j++;
            k++; }
        return res;
    }

    public static int [] sortArrayInt(int[] arrayA, String mode){
        if (arrayA == null) {
            return null;
        }
        if (arrayA.length < 2) {
            return arrayA;
        }
        int [] arrayB = new int[arrayA.length / 2];
        System.arraycopy(arrayA, 0, arrayB, 0, arrayA.length / 2);

        int [] arrayC = new int[arrayA.length - arrayA.length / 2];
        System.arraycopy(arrayA, arrayA.length / 2, arrayC, 0, arrayA.length - arrayA.length / 2);

        arrayB = sortArrayInt(arrayB,mode);
        arrayC = sortArrayInt(arrayC, mode);


        return mergeArrayInt(arrayB, arrayC, mode);
    }

    public static String [] sortArrayStr(String[] arrayA, String mode){
        if (arrayA == null) {
            return null;
        }
        if (arrayA.length < 2) {
            return arrayA;
        }
        String [] arrayB = new String[arrayA.length / 2];
        System.arraycopy(arrayA, 0, arrayB, 0, arrayA.length / 2);

        String [] arrayC = new String[arrayA.length - arrayA.length / 2];
        System.arraycopy(arrayA, arrayA.length / 2, arrayC, 0, arrayA.length - arrayA.length / 2);

        arrayB = sortArrayStr(arrayB,mode);
        arrayC = sortArrayStr(arrayC, mode);


        return mergeArrayStr(arrayB, arrayC, mode);
    }


    private static String[] mergeArrayStr(String [] groupA, String [] groupB, String mode){
        String[] res = new String[groupA.length + groupB.length];
        int n = groupA.length;
        int m = groupB.length;
        int i = 0, j = 0, k = 0;
        if (mode.equals("Descending")){
            while (i < n && j < m) {
                if (groupA[i].length() >= groupB[j].length()) {
                    res[k] = groupA[i];
                    i++; }
                else {
                    res[k] = groupB[j];
                    j++; }
                k++; } }
        else {
            while (i < n && j < m) {
                if (groupA[i].length() <= groupB[j].length()) {
                    res[k] = groupA[i];
                    i++; }
                else {
                    res[k] = groupB[j];
                    j++; }
                k++; }
        }
        while (i < n) {
            res[k] = groupA[i];
            i++;
            k++; }
        while (j < m) {
            res[k] = groupB[j];
            j++;
            k++; }
        return res;
    }
    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
