package src.main.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public static LinkedList<Integer> mergeInteger(String mode, List<List<String>> filesIn){
        LinkedList <Integer> correctList = new LinkedList();
        int [] groupA = new int[0];
        int [] groupB = new int[0];
        int [] groupC = new int[0];
        for (List<String> listElements:filesIn) {
            if (groupA.length != 0 && groupB.length != 0){
                groupC = new int[listElements.size()];
                int c = 0;
                for (String element:listElements) {
                    if (isNumeric(element)){
                        groupC[c] = Integer.parseInt(element);
                        c++;
                    }
                }
            }
            else if (groupA.length != 0){
                groupB = new int[listElements.size()];
                int b = 0;
                for (String element:listElements) {
                    if (isNumeric(element)) {
                        groupB[b] = Integer.parseInt(element);
                        b++;
                    }
                }
            }
            else {
                groupA = new int[listElements.size()];
                int a = 0;
                for (String element:listElements) {
                    if (isNumeric(element)){
                        groupA[a] = Integer.parseInt(element);
                        a++;
                    }
                }
            }
        }
        if (groupC.length == 0){
            correctList = mergeArrayInt(groupA,groupB,mode);
            return correctList;
        }
        List<Integer> correctListAB = mergeArrayInt(groupA,groupB,mode);

        int[] groupAB = new int[correctListAB.size()];
        int a = 0;
        for (Integer element:correctListAB) {
            groupAB[a] = element;
            a++;
        }
        correctList = mergeArrayInt(groupAB,groupC,mode);
        return correctList;
    }


    public static LinkedList<String> mergeString(String mode, List<List<String>> filesIn){
        LinkedList <String> correctList = new LinkedList();
        String [] groupA = new String[0];
        String [] groupB = new String[0];
        String [] groupC = new String[0];

        for (List<String> listElements:filesIn) {
            if (groupA.length != 0 && groupB.length != 0){
                groupC = new String[listElements.size()];
                int c = 0;
                for (String element:listElements) {
                    groupC[c] = element;
                    c++;

                }
            }
            else if (groupA.length != 0){
                groupB = new String[listElements.size()];
                int b = 0;
                for (String element:listElements) {
                    groupB[b] = element;
                    b++;
                }
            }
            else {
                groupA = new String[listElements.size()];
                int a = 0;
                for (String element:listElements) {
                    groupA[a] = element;
                    a++;
                }
            }
        }
        if (groupC.length == 0){
            correctList = mergeArrayStr(groupA,groupB,mode);
            return correctList;
        }
        List<String> correctListAB = mergeArrayStr(groupA,groupB,mode);

        String[] groupAB = new String[correctListAB.size()];
        int a = 0;
        for (String element:correctListAB) {
            groupAB[a] = element;
            a++;
        }
        correctList = mergeArrayStr(groupAB,groupC,mode);
        return correctList;
    }

    public static LinkedList<Integer> mergeArrayInt(int [] groupA, int [] groupB, String mode){
        int positionA = 0;
        int positionB = 0;
        LinkedList<Integer> correctList = new LinkedList<>();

        for (int i = 0; i < groupA.length + groupB.length; i++) {
            if (positionA == groupA.length) {
                correctList.add(groupB[i - positionB - 1]);
                positionB++;
            } else if (positionB == groupB.length) {
                correctList.add(groupA[i - positionA - 1]);
                positionA++;
            } else if (mode.equals("Descending")) {
                if (groupA[i - positionA] > groupB[i - positionB]) {
                    correctList.add(groupA[i - positionA]);
                    positionB++;
                } else {
                    correctList.add(groupB[i - positionB]);
                    positionA++;
                }
            } else {
                if (groupA[i - positionA] < groupB[i - positionB]) {
                    correctList.add(groupA[i - positionA]);
                    positionB++;
                } else {
                    correctList.add(groupB[i - positionB]);
                    positionA++;
                }
            }
        }
        return correctList;
    }

    public static LinkedList<String> mergeArrayStr(String [] groupA, String [] groupB, String mode){
        int positionA = 0;
        int positionB = 0;
        LinkedList<String> correctList = new LinkedList<>();
        for (int i = 0; i < groupA.length + groupB.length; i++) {
            if (positionA == groupA.length) {
                correctList.add(groupB[i - positionB]);
                positionB++;
            } else if (positionB == groupB.length) {
                correctList.add(groupA[i - positionA]);
                positionA++;
            } else if (mode.equals("Descending")) {
                if (groupA[i - positionA].length() > groupB[i - positionB].length()) {
                    correctList.add(groupA[i - positionA]);
                    positionB++;
                } else {
                    correctList.add(groupB[i - positionB]);
                    positionA++;
                }
            } else {
                if (groupA[i - positionA].length() < groupB[i - positionB].length()) {
                    correctList.add(groupA[i - positionA]);
                    positionB++;
                } else {
                    correctList.add(groupB[i - positionB]);
                    positionA++;
                }
            }
        }
        return correctList;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
