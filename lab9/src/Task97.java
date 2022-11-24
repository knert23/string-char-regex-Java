import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Scanner;

public class Task97 {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Integer> listIndex = new ArrayList<>();
    static ArrayList<String> listString = new ArrayList<>();
    static ArrayList<Integer> listInteger = new ArrayList<>();

    public static void main(String[] args) {
        out.println("Введите любую строку:");
        String string = scanner.nextLine();
        String[] masString = string.split("");

        int count = 0;
        for (int i = 0; i < masString.length; i++) {
            listString.add(masString[i]);
            if (masString[i].equals("*")) {
                count++;
                listIndex.add(count);
                listInteger.add(i);
            }
        }

        for (int i = 0; i < listIndex.size(); i++) {
            listString.set(listInteger.get(i), listIndex.get(i).toString());
        }

        out.println("Преобразованная строка имеет вид:");
        for (String str :listString) {
            out.print(str);
        }
    }
}