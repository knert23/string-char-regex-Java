import static java.lang.System.out;

import java.util.Scanner;
import java.util.ArrayList;

public class Task94 {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        out.println("Введите размерность n квадратной матрицы:");
        int n = scanner.nextInt();
        if (n < 10) {
            throw new IllegalArgumentException("n не может быть меньше 10, пожалуйста повторите ввод");
        }
        out.println("Введите данные:");
        char[][] masChar = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                masChar[i][j] = inputMasChar();
            }
        }
        printMasChar(masChar, n);
        checkConstruction(masChar, n);
    }

    public static char inputMasChar() {
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

    // Ищет наличие конструкции вида: сos(«число»* «число») = «число»
    public static void checkConstruction (char[][] masChar, int n) {
        int countForMethodAnswer = 0; // для того, чтобы countLines, countColumns записывались в list 1 раз
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 9; j++) {
                // проверка по строкам на присутствие конструкции
                if (masChar[i][j] == 'c' & masChar[i][j+1] == 'o' & masChar[i][j+2] == 's' & masChar[i][j+3] == '(' &
                masChar[i][j+7] == ')' ) {
                    if (Character.isDigit(masChar[i][j+4]) & Character.isDigit(masChar[i][j+6]) &
                            Character.isDigit(masChar[i][j+9])) {
                        if (masChar[i][j+5] == '*' & masChar[i][j+8] == '=') {
                            countForMethodAnswer++;
                            printLineColumn(masChar, i, j, 1, 0);
                            checkTheCorrectnessOfTheConstruction(Character.digit(masChar[i][j + 4], 10),
                                    Character.digit(masChar[i][j + 6], 10),
                                    Character.digit(masChar[i][j + 9], 10), 1, 0,
                                    countForMethodAnswer);
                        }
                    }
                }
                // проверка по столбцам на присутствие конструкции
                if (masChar[j][i] == 'c' & masChar[j+1][i] == 'o' & masChar[j+2][i] == 's' & masChar[j+3][i] == '(' &
                        masChar[j+7][i] == ')' ) {
                    if (Character.isDigit(masChar[j+4][i]) & Character.isDigit(masChar[j+6][i]) &
                            Character.isDigit(masChar[j+9][i])) {
                        if (masChar[j+5][i] == '*' & masChar[j+8][i] == '=') {
                            printLineColumn(masChar, i, j, 0, 1);
                            countForMethodAnswer = 2;
                            checkTheCorrectnessOfTheConstruction(Character.digit(masChar[j + 4][i], 10),
                                    Character.digit(masChar[j + 6][i], 10),
                                    Character.digit(masChar[j + 9][i], 10),0, 1,
                                    countForMethodAnswer);
                        }
                    }
                }
            }
        }
        answer();
    }

    // Проверяет правильность вычисления выражения сos(«число»* «число») = «число»
    public static void checkTheCorrectnessOfTheConstruction (int k, int m, int c, int isLine,
                                                             int isColumn, int countForMethodAnswer) {
        int countLines = 0, countColumns = 0;
        if (countForMethodAnswer == 1) {
            list.add(countLines);
            list.add(countColumns);
        }
        if (isLine == 1) {
            if (Math.cos(k * m) == c) {
                countLines++;
                list.set(0, countLines);
            } else {
                list.add(k);
                list.add(m);
                list.add(c);
            }
        }
        if (isColumn == 1) {
            if (Math.cos(k * m) == c) {
                countColumns++;
                list.set(1, countColumns);
            } else {
                list.add(k);
                list.add(m);
                list.add(c);
            }
        }
    }

    // Выводит выражения данного вида по строкам и по столбцам.
    public static void printLineColumn (char[][] array, int k, int m, int isLine, int isColumn) {
        out.println();
        if (isLine == 1) {
            out.println("В строчке " + k + " содержится конструкция:");
            for (int i = m; i <= m + 9; i++) {
                out.print(array[k][i] + "\t");
            }
            out.println();
        }
        else if (isColumn == 1) {
            out.println("В столбце " + k + " содержится конструкция:");
            for (int i = m; i <= m + 9; i++) {
                out.print(array[i][k] + "\t");
            }
        }
    }

    public static void printMasChar (char[][] masChar, int n) {
        out.println("Матрица имеет вид:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(masChar[i][j] + "\t");
            }
            out.println();
        }
    }

    public static void answer() {
        out.println();
        out.println();
        out.println("Всего по строкам содержится " + list.get(0) + " правильно вычисленных конструкций.");
        out.println("Всего по столбцам содержится " + list.get(1) + " правильно вычисленных конструкций.");
        if (list.get(0) == 0| list.get(1) == 0) {
            out.println("Выражения, которые были вычислены не правильно с корректировкой ответа:");
            for (int i = 2; i < list.size() - 2; i = i + 3) {
                out.println("cos(" + list.get(i) + " * " + list.get(i + 1) + ")" + " = " + list.get(i + 2));
                out.println("cos(" + list.get(i) + " * " + list.get(i + 1) + ")" + " = " +
                        Math.cos(list.get(i) * list.get(i + 1)));
                out.println();
            }
        }
    }
}