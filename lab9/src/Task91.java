import static java.lang.System.out;

import java.util.Scanner;

public class Task91 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        out.println("Введите размерность массива:");
        int n = scanner.nextInt();
        char[] masChar = new char[n];
        out.println("Вводите строку по одному символу:");
        for (int i = 0; i < n; i++) {
            masChar[i] = input();
        }
        check(masChar);
        for (int i = 0; i < masChar.length; i++) {
            out.print(masChar[i] + " ");
        }
    }

    public static char input() {
        char ch = ' ';
        String string = scanner.next();
        if (string.length() == 1) {
            ch = string.charAt(0);
        }
        else {
            while (string.length() != 0) {
                out.println("Вы ввели не 1 символ, повторите ввод");
                string = scanner.next();
                if (string.length() == 1)
                    ch = string.charAt(0);
            }
        }
        return ch;
    }

    public static void check(char[] masChar) {
        for (int i = 0; i < masChar.length; i++){
            if (Character.isLowerCase((masChar[i]))) {
                masChar[i] = Character.toUpperCase(masChar[i]);
            }
            else {
                masChar[i] = Character.toLowerCase(masChar[i]);
            }
        }
    }
}
