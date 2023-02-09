package src.main.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String message = "��� ���������� ��������� ��������� � ��������� ������� � ���� ������ � ���������:\n" +
                "1. ����� ���������� (-a ��� -d), ��������������, �� ��������� ��������� �� �����������;\n" +
                "2. ��� ������ (-s ��� -i), ������������;\n" +
                "3. ��� ��������� �����, ������������ (����������� ��������� ����, ��� �� ����� ������� ���� � ��� ������);\n" +
                "4. ��������� ��������� � ����� ������� ������, �� ����� ������.����� ����� ������� �����,\n" +
                "� ������� �������� ������ ����� ��� �������(����������� ��������� ����, ����� ���� ��� ����� � ������� �� ����� �����).\n" +
                "������: \"-d -s C:\\Program Files\\out.txt C:\\Program Files\\files\"";

        Scanner scanner = new Scanner(System.in);

        /**
         * ������� �������� ������, � ������� ����� ��������� ��� ��������, �� �������. ��������� ��� �� ���������.
         */
        while (scanner.hasNext()){
            String str = scanner.nextLine();
            if (str.equals("end")){
                break;
            }
            String [] text = str.split(" ");


            String mode = SortSetting.sortMode(text[0]);
            if (text.length < 3 && mode.equals("Ascending(default)") || text.length < 4 && !mode.equals("Ascending(default)")){
                System.out.println("������������ ������ ���������, ���������� ��� ���.\n" + message);
                continue;
            }
            String type = "";
            String fileOut = "";
            List <String> filesIn = new ArrayList<>();


            if (mode.equals("Ascending(default)")){
                type = SortSetting.dataType(text[0]);
                fileOut = text[1];
                for (int i = 2; i < text.length; i++) {
                    filesIn.add(text[i]);
                }
            }



            if (type.equals("bug")){
                System.out.println("�������� ������ ���� ������, ���������� ������� ����������.\n" + message);
                continue;
            }

            if (fileOut.equals("")){
                fileOut = text[2];
                type = SortSetting.dataType(text[1]);
                for (int i = 3; i < text.length; i++) {
                    filesIn.add(text[i]);
                }
            }
            new File(fileOut); // ������� out ����

            for (String path: filesIn) {
                FindFile.findFile(new File(path));
            }
            List<String> paths = FindFile.getPaths(); // �������� ������ ���� �����, ������� ������������ ���������� ����������� ��������
            List<List<String>> listOfLists = new ArrayList<>();

            for (String path: paths) {
                try {
                    List<String> elements = Files.readAllLines(Paths.get(path));
                    listOfLists.add(elements);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            LinkedList<String> list = new LinkedList<>();

            if (type.equals("Integer")){
                LinkedList<Integer> listInt = SortSetting.mergeInteger(mode,listOfLists);
                for (int i :listInt) {
                    list.add(String.valueOf(i));
                }
            }else if (type.equals("String")){
                list = SortSetting.mergeString(mode,listOfLists);
            }

            try {
                Files.write(Paths.get(fileOut),list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
