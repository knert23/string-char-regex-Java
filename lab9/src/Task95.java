import static java.lang.System.out;

import java.util.Scanner;

public class Task95 {

    static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args) {
        out.println("Введите любую строку:");
        String string = scanner.nextLine();
        int count = 0;
        char[] masString = string.toCharArray();
        for (char s : masString) {
            if (Character.isDigit(s)) {
                count++;
            }
        }
        out.println("В ведённой строчке содержатся числа в количестве: " + count);
    }
}
