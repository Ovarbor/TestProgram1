import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Report {

    FilesReader filesReader = new FilesReader();

    private final List<String> stringList = new ArrayList<>();
    private final List<String> stringList2 = new ArrayList<>();
    private final List<String> stringList3 = new ArrayList<>();
    private String[] array1;
    private int countFalse = 0;

    public void readReport(String path) {
        String line = filesReader.readFileContents(path);
        String[] array = line.split("\r\\d\r");
        array1 = array[0].trim().split("\r");
        String[] array2 = array[1].trim().split("\r");
        valueMatching(array1, array2, stringList2);
        valueMatching(array2, array1, stringList);
        System.out.println("Чтение завершено!");
    }

    public void writeReport(String path) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            for (int i = 0; i < array1.length; i++) {
                if (!stringList2.get(i).equals("?")) {
                    fileWriter.write(array1[i] + ":" + stringList2.get(i) + "\r");
                }
            }
            for (String s : stringList3) {
                fileWriter.write(s + ":?" + "\r");
            }
            System.out.println("Запись завершена!");
        } catch (IOException e) {
            System.out.println("Произошла ошибка во время записи файла.");
        }
    }

    private void valueMatching(String[] things3, String[] things4, List<String> stringList) {
        for (String s : things3) {
            countFalse = 0;
            for (String value : things4) {
                if (s.toLowerCase().contains(value.toLowerCase()) || value.toLowerCase().contains(s.toLowerCase())) {
                    stringList.add(value);
                } else {
                    if (compareStrings(s, value)) {
                        stringList.add(value);
                    }
                    if (countFalse == things4.length) {
                        stringList.add("?");
                    }
                }
            }
        }
        for (int i = 0; i < stringList.size(); i++) {
            if (stringList.get(i).equals("?")) {
                stringList3.add(things3[i]);
            }
        }
    }

    private Boolean compareStrings(String string1, String string2) {
        int countTrue = 0;
        int countFalse = 0;
        String[] strings1 = string1.split(" ");
        String[] strings2 = string2.split(" ");
        for (String s : strings1) {
            for (String value : strings2) {
                if (s.equalsIgnoreCase(value)) {
                    countTrue++;
                } else if (string1.equals("Бетон с присадкой") &&
                string2.equals("Цемент") || string1.equals("Цемент") && string2.equals("Бетон с присадкой")) {
                    countTrue++;
                } else {
                    if (compareWords(s, value)) {
                        countTrue++;
                    } else {
                        countFalse++;
                    }
                }
            }
        }
        if (countTrue >= 2) {
            return true;
        } else if (strings1.length == 1 && countTrue == 1) {
            return true;
        }
        if (countFalse > 0 && countTrue == 0) {
            this.countFalse++;
            return false;
        }
        return false;
    }

    private Boolean compareWords(String word1, String word2) {
        int count = 0;
        String[] strings1 = word1.split("");
        String[] strings2 = word2.split("");
        if (strings1.length >= strings2.length) {
            for (int i = 0; i < strings2.length; i++) {
                if (strings1[i].equalsIgnoreCase(strings2[i])) {
                    count++;
                }
            }
        } else {
            for (int i = 0; i < strings1.length; i++) {
                if (strings1[i].equalsIgnoreCase(strings2[i])) {
                    count++;
                }
            }
        }
        return count >= 5;
    }
}
