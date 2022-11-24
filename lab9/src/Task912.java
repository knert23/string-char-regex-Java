import static java.lang.System.out;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task912 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        out.println("Введите количество участников математического клуба:");
        int n = scanner.nextInt();
        out.println("Введите данные об учениках в виде:");
        out.println("возраст, пол, IQ, средний балл, место учебы, место жительства, численность семьи");
        String[] infAboutStudents = new String[n];
        scanner.nextLine(); // для того чтобы считать пустую строку (\n) после считывания объекта типа Integer
        for (int i = 0; i < n; i++) {
            infAboutStudents[i] = scanner.nextLine();
        }
        check(infAboutStudents);
    }

    public static void check(String[] infAboutStudents) {
        out.println();
        String[][] masPatternAll = {{"Мужской", "мужской", "мужск", "Мужск", "Муж", "муж"},
                {"Женский", "женский", "женск", "Женск", "Жен", "жен"}, {"Город", "город", "Гор", "гор"},
                {"Область", "область", "Обл", "обл"}, {"Край", "край", "Кр", "кр"}, {"\\.\\.\\."}};
        String[] replaceAll = {"м\\.", "ж\\.", "г\\.", "обл\\.", "кр\\.", "Заполнить"};
        Pattern pattern;
        Matcher matcher;
        for (int k = 0; k < infAboutStudents.length; k++) {
            out.println((k + 1) + " ученик:");
            out.println(infAboutStudents[k]);
            for (int i = 0; i < masPatternAll.length; i++) {
                for ( int j = 0; j < masPatternAll[i].length; j++) {
                    pattern = Pattern.compile(masPatternAll[i][j]);
                    matcher = pattern.matcher(infAboutStudents[k]);
                    if (matcher.find()) {
                        infAboutStudents[k] = matcher.replaceAll(replaceAll[i]);
                        break;
                    }
                }
            }
            out.println(infAboutStudents[k]);
            out.println("----------------------------------------------------------------------------------");
        }
    }
}