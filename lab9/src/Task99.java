import static java.lang.System.out;

import java.util.Scanner;
import java.util.regex.*;

public class Task99 {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        out.println("Введите любой текст:");
        String text = scanner.nextLine();
        String[] masSplitText = text.split("\\.\s");
        int count = 0;

        for (int i = 0; i < masSplitText.length; i++) {
            Pattern patternFirst = Pattern.compile("%.+?%");
            Pattern patternSecond = Pattern.compile("%.+?\\$");
            Pattern patternThird = Pattern.compile("\\$.+?%");
            Matcher matcherFirst = patternFirst.matcher(masSplitText[i]);
            Matcher matcherSecond = patternSecond.matcher(masSplitText[i]);
            Matcher matcherThird = patternThird.matcher(masSplitText[i]);
            if (matcherFirst.find()) {
                count++;
                masSplitText[i] = matcherFirst.replaceAll("");
            }
            if (matcherSecond.find()) {
                count++;
                masSplitText[i] = matcherSecond.replaceAll("");
            }
            if (matcherThird.find()) {
                count++;
                masSplitText[i] = matcherThird.replaceAll("");
            }
        }

        out.println();
        if (count >= 1) {
            for (String str : masSplitText) {
                out.print(str + ". ");
            }
        }
        if (count == 0) {
            out.println("Комментариев в тексте не найдено.");
        }
    }
}
