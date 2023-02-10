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

        String message = "Вам необходимо прописать сообщение в следубщем формате в одну строку с пробелами:\n" +
                "1. Режим сортировки (-a или -d), необязательный, по умолчанию сортируем по возрастанию;\n" +
                "2. Тип данных (-s или -i), обязательный;\n" +
                "3. Имя выходного файла, обязательное (прописываем полностью путь, где мы хотим создать файл и его формат);\n" +
                "4. Остальные параметры – имена входных файлов, не менее одного( перечисляем через пробел).\n" +
                "Пример: \"-d -s C:\\Program Files\\out.txt C:\\Program Files\\files\"";

        Scanner scanner = new Scanner(System.in);

        /**
         * Создаем приемник строки, в которой будут прописаны все элементы, по заданию. Проверяем все ли корректно.
         */
        while (scanner.hasNext()){
            String str = scanner.nextLine();
            if (str.equals("end")){
                break;
            }
            String [] text = str.split(" ");


            String mode = SortSetting.sortMode(text[0]);
            if (text.length < 3 && mode.equals("Ascending(default)") || text.length < 4 && !mode.equals("Ascending(default)")){
                System.out.println("Некорректный формат сообщения, попробуйте еще раз.\n" + message);
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
                System.out.println("Неверный формат типа данных, необходимо указать корректный.\n" + message);
                continue;
            }

            if (fileOut.equals("")){
                fileOut = text[2];
                type = SortSetting.dataType(text[1]);
                for (int i = 3; i < text.length; i++) {
                    filesIn.add(text[i]);
                }
            }
            new File(fileOut); // создаем out файл

            for (String path: filesIn) {
                FindFile.findFile(new File(path));
            }
            List<String> paths = FindFile.getPaths(); // получили список всех путей, который пользователю необходимо сортировать слиянием


            List<String> allElements = new ArrayList<>();
            for (String path: paths) {
                try {
                    List<String> elements = Files.readAllLines(Paths.get(path));
                    allElements.addAll(elements);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            int number = 0;
            String[] list = new String[allElements.size()];
            for (String string: allElements) {
                list[number] = string;
                number++;
            }

            List<String> correctList = new ArrayList<>();
            if (type.equals("Integer")){
                int numberStr = 0;
                int [] listInt = new int[allElements.size()];
                for (String string: allElements) {
                    if (!SortSetting.isDigit(string)){
                        numberStr++;
                    }
                    listInt[numberStr] = Integer.parseInt(string);
                    numberStr++;
                }
                int [] result = SortSetting.sortArrayInt(listInt,mode);
                for (int i = 0; i < result.length; i++) {
                    correctList.add(String.valueOf(result[i]));
                }
            } else if (type.equals("String")){

                String [] resultStr = SortSetting.sortArrayStr(list,mode);
                for (int i = 0; i < resultStr.length; i++) {
                    correctList.add(String.valueOf(resultStr[i]));
                }
           }

            try {
                Files.write(Paths.get(fileOut),correctList);
                System.out.println("Файл с названием " + Paths.get(fileOut).getFileName() + " записан.");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
