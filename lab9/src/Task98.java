import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.out;


public class Task98 {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<String[]> listSentence = new ArrayList<>();
    static ArrayList<String> listCommonWords = new ArrayList<>();

    public static void main(String[] args) {
        out.println("Введите любой текст:");
        String text = scanner.nextLine();
        String[] masSplitText = text.split("\\. ");
        String[] masSentence;
        for (String s : masSplitText) {
            masSentence = s.split("\s");
            listSentence.addAll(Collections.singleton(masSentence));
        }

        for (int i = 0; i < listSentence.size() - 1; i++) {
            int countMin = 0;
            int countMax = 1;
            int a;
            for (int j = 0; j < listSentence.get(i).length; j++) {
                for (int k = i + 1; k < listSentence.size(); k++) {
                    for (int m = 0; m < listSentence.get(k).length; m++) {
                        if (listSentence.get(i)[j].equalsIgnoreCase(listSentence.get(k)[m])) {
                            countMin++;
                            if (countMin > countMax) { // выбираем слово, которое чаще всего встречается
                                listCommonWords.add(listSentence.get(i)[j]);
                                a = countMax;
                                countMax = countMin;
                                countMin = a;
                            }
                        }
                    }
                }
            }
        }

        out.println();
        for (int i = 0; i < listSentence.size(); i++) {
            for (int j = 0; j < listSentence.get(i).length; j++) { // если слово есть в предложении, то выводим
                if (listSentence.get(i)[j].equalsIgnoreCase(listCommonWords.get(listCommonWords.size() - 1))) {
                    output(i);
                }
            }
        }
        out.println();
        out.println("Общее слово: " + listCommonWords.get(listCommonWords.size() - 1));
    }

    public static void output(int i) {
        for (int j = 0; j < listSentence.get(i).length; j++) {
            if (j != listSentence.get(i).length - 1) {
                out.print(listSentence.get(i)[j] + "\s");
            }
            else {
                out.print(listSentence.get(i)[j]);
            }
        }
        out.print(".\s");
    }
}