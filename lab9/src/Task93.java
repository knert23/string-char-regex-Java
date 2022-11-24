import static java.lang.System.out;

import java.util.Scanner;

public class Task93 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        out.println("Введите размерность массива:");
        int n = scanner.nextInt();
        out.println("Введите любой массив символов по одному в строку:");
        char[] masChar = new char[n];
        for (int i = 0; i < n; i++) {
            masChar[i] = input();
        }
        check(masChar, n);
    }

    public static char input() {
        char ch = ' ';
        String string = scanner.next();
        if (string.length() == 1) {
            ch = string.charAt(0);
        }
        else {
            while (string.length() != 1) {
                out.println("Вы ввели не один символ, повторите ввод");
                string = scanner.next();
                if (string.length() == 1) {
                    ch = string.charAt(0);
                }
            }
        }
        return ch;
    }
    public static void check(char[] masChar, int n) {
        int countLat = 0, countCommas = 0, parameterI = 0, parameterJ = 0;
        boolean checkU = false;
        boolean checkBo = false;
        boolean checkOb = false;
        boolean checkDigit = false;
        boolean checkSiSi1 = false;
        boolean checkSjSj1 = false;
        for (int i = 0; i < masChar.length; i++) { // по длине всего массива
            for (int k = 97; k <= 122; k++) { // вхождение прописных латинских букв
                if (masChar[i] == k) {
                    countLat++;
                }
            }
            if (masChar[i] == 'ю' | masChar[i] == 'Ю') { // вхождение буквы "ю"
                checkU = true;
            }
            if (masChar[i] == ',') { // вхождение запятой
                countCommas++;
            }
        }
        for (int i = 0; i < n - 1; i++) { // идёт до предпоследнего символа
            if (masChar[i] == 'в' & masChar[i + 1] == 'о') { // вхождение 'во'
                checkBo = true;
            }
            if (masChar[i] == 'о' & masChar[i + 1] == 'в') {// вхождение 'ов'
                checkOb = true;
            }
            if (Character.isDigit(masChar[i]) & Character.isDigit(masChar[i + 1])) { // пара соседних одинаковых чисел
                if (masChar[i] == masChar[i + 1]) {
                    checkDigit = true;
                }
            }
        }
        for (int k = 2; k < n - 1; k++) { // идёт до предпоследнего символа
            if ((Character.isLowerCase(masChar[k]) & Character.isUpperCase(masChar[k + 1])) |
                    (Character.isUpperCase(masChar[k]) & Character.isLowerCase(masChar[k + 1]))) {
                // si, si+1 это одинаковые буквы отличающиеся регистром
                checkSiSi1 = true;
                parameterI = k;
                break;
            }
        }
        for (int c = parameterI + 1; c < n - 1; c++) { // идёт до предпоследнего символа
            if (Character.isDigit(masChar[c])) {
                if ((Character.digit(masChar[c], 10) == 0) & (Character.digit(masChar[c + 1], 10) == 0)) {
                    // sj, sj+1 это 0
                    checkSjSj1 = true;
                    parameterJ = c;
                    break;
                }
            }
        }
        printAns(countLat, checkU, countCommas, checkBo, checkOb, checkDigit,
                checkSiSi1, checkSjSj1, parameterI, parameterJ);
    }

    public static void printAns(int countLat, boolean checkU, int countCommas, boolean checkBo, boolean checkOb,
                                boolean checkDigit, boolean checkSiSi1, boolean checkSjSj1,
                                int parameterI, int parameterJ) {
        out.println("Количество латинских прописных букв: " + countLat);
        if (checkU) {
            out.println("В массив входит буква 'ю'");
        }
        if (countCommas >= 2) {
            out.println("В массиве имеются две или более запятые");
        }
        if (checkBo) {
            out.println("В массив входит последовательность 'во'");
        }
        if (checkOb) {
            out.println("В массив входит последовательность 'ов'");
        }
        if (checkDigit) {
            out.println("В массив входит пара соседствующих одинаковых цифр");
        }
        if (checkSiSi1 & checkSjSj1) {
            out.println("В массиве есть такие натуральные i и j, что 1 < i < j < n и что si, si+1 " +
                    "это одинаковые буквы отличающиеся регистром, a sj, sj+1 это 0");
            out.println("Первое вхождение i = " + parameterI);
            out.println("Первое вхождение i = " + parameterJ);
        }
    }
}