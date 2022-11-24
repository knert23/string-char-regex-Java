import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class Task911 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        out.println("Введите текст:");
        String text = scanner.nextLine();
        String[] splitText = text.split("\s");
        check(splitText);
    }

    public static void check(String[] masText) {
        String[] pattern = {"!.+!", "\\?.+\\?", "\\.\\.\\..+\\."};
        String[] replace = {"!", "?", "..."};
        Matcher matcher;
        Pattern patternNow;

        for (int i = 0; i < masText.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                patternNow = Pattern.compile(pattern[j]);
                matcher = patternNow.matcher(masText[i]);
                if (matcher.find()) {
                    masText[i] = matcher.replaceAll(replace[j]);
                }
            }
        }

        out.println();
        out.print("Отредактированный текст:");
        out.println();
        for (String string : masText) {
            out.print(string + "\s");
        }
    }
}
