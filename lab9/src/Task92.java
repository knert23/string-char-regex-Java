import static java.lang.System.out;

import java.util.Scanner;

public class Task92 {
    public static void main(String[] args) {
        out.println("------------------------------------------------------------------------------------------------");
        out.println("Условия ввода:");
        out.println("В одну строку записывается только один символ");
        out.println("Символы, которые могут использоваться при записи 16-го числа: 0, 1, 2,…, 9, A, B, C, D, E, F");
        out.println("------------------------------------------------------------------------------------------------");
        input();
    }

    public static void input() {
        Scanner scanner = new Scanner(System.in);
        out.println("Введите размерность массива:");
        int n = scanner.nextInt();
        if (n == 0) {
            throw new IllegalArgumentException("Размерность массива не может быть ровна 0, введите n >= 1:");
        }
        char[] masChar = new char[n];
        out.println("Введите шестнадцатеричное число:");
        for (int i = 0; i < n; i++) {
            masChar[i] = inputMasChar(i, n);
        }
        conversion(masChar, n);
    }
    
    public static char inputMasChar(int i, int n ) {
        Scanner scannerMas = new Scanner(System.in);
        out.println("masChar[ " + i + " ] = ");
        char ch = ' ';
        String string = scannerMas.next();
        if (string.length() == 1) {
            ch = string.charAt(0);
            if (ch == '0' & (i == 0 || i == n-1)) { // первый и последний символ не может быть равен 0
                while (ch == '0') {
                    out.println("Вы ввели не верно A[0], оно не может быть равно 0");
                    out.println("A[ " + i + " ] = ");
                    string = scannerMas.next();
                    ch = string.charAt(0);
                }
            }
            while (check(ch)) { // проверка на правильность ввода
                out.println("Пожалуйста посмотрите условия ввода и введите только разрешенные символы:");
                out.println("masChar[ " + i + " ] = ");
                string = scannerMas.next();
                ch = string.charAt(0);
                while (string.length() != 1) { // проверка на ввод 1 символа
                    out.println("Вы ввели не 1 символ, повторите ввод:");
                    out.println("masChar[ " + i + " ] = ");
                    string = scannerMas.next();
                }
                if (ch == '0' & (i == 0 || i == n-1)) { // первый и последний символ не может быть равен 0
                    while (ch == '0') {
                        out.println("Вы ввели не верно A[0], оно не может быть равно 0");
                        out.println("A[ " + i + " ] = ");
                        string = scannerMas.next();
                        ch = string.charAt(0);
                    }
                }
            }
        }
        else {
            while (string.length() != 1) { // проверка на ввод 1 символа
                out.println("Вы ввели не 1 символ, повторите ввод:");
                out.println("masChar[ " + i + " ] = ");
                string = scannerMas.next();
                if (string.length() == 1) {
                    ch = string.charAt(0);
                    if (ch == '0' & (i == 0 || i == n-1)) { // первый и последний символ не может быть равен 0
                        while (ch == '0') {
                            out.println("Вы ввели не верно A[0], оно не может быть равно 0");
                            out.println("A[ " + i + " ] = ");
                            string = scannerMas.next();
                            ch = string.charAt(0);
                        }
                    }
                    while (check(ch)) { // проверка на правильность ввода
                        out.println("Пожалуйста посмотрите условия ввода и введите только разрешенные символы:");
                        out.println("masChar[ " + i + " ] = ");
                        string = scannerMas.next();
                        ch = string.charAt(0);
                    }
                }
            }
        }
        return ch;
    }

    public static boolean check(char ch) { // проверка на правильность ввода посредством
        int count = 0;                     // перебирания кода символа Unicode в десятичном исчислении
        for (int k = 0; k <= 47; k++) {
            if (ch == k) {
                count++;
            }
        }
        for (int k = 58; k <= 64; k++) {
            if (ch == k) {
                count++;
            }
        }
        for (int k = 71; k <= 1000; k++) {
            if (ch == k) {
                count++;
            }
        }
        return count >= 1; // если символ совпал с неразрешенным, то возвращает true
    }

    public static void conversion(char[] masChar, int n) {
        int count = 0;
        int temp = 0;
        for (int i = n - 1, j = 0; i >= 0; i--, j++) { // перевод в 10-ю систему счисления
            temp += Character.digit(masChar[i], 16) * Math.pow(16, j);
        }
        out.println("Число в 16-ой системе в 10-ой имеет вид: " + temp);
        int k = temp;
        while (k > 0) { // считаем размер массива masTwo
            k /= 2;
            count++;
        }
        int[] masTwo = new int[count];
        for (int i = count - 1; i >= 0 ; i--) { // перевод в 2-ю систему счисления
            masTwo[i] = temp % 2;
            temp /= 2;
        }
        printMasTwo(masTwo);
    }

    public static void printMasTwo(int[] masTwo) {
        out.print("Введенное вами число в 2-ой системе счисления имеет вид: ");
        for (int i = 0; i < masTwo.length; i++) {
            out.print(masTwo[i]);
        }
    }
}