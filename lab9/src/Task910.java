import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.out;

public class Task910 {
    static Scanner scanner = new Scanner(System.in);

    static ArrayList<String[]> listOfCompanies = new ArrayList<>();
    static ArrayList<String> listNameOfCompaniesCondition2 = new ArrayList<>();
    static ArrayList<Integer> listNumbOfEmployee = new ArrayList<>();
    static ArrayList<String> listNameOfCompaniesCondition1 = new ArrayList<>();

    public static void main(String[] args) {
        out.println("Введите текст:");
        String text = scanner.nextLine();
        String[] masSplitText = text.split("\\. ");
        for (String str : masSplitText) {
            out.println(str);
        }
        String[] masSentence;
        for (String s : masSplitText) {
            masSentence = s.split(";\s");
            listOfCompanies.addAll(Collections.singleton(masSentence));
        }

        int count = 0;
        int numbOfEmployees = 10000;
        String firstFivePhoneNumb = "";
        String firstFiveFaxNumb = "";
        String lastDigitPhoneNumb = "";
        String[] masAddress;
        String lastDigitNumbBuilding = "";
        int numbOfEmployeesInCompany = 0;
        for (int i = 0; i < listOfCompanies.size(); i++) {
            for (int j = 0; j < listOfCompanies.get(i).length; j++) {
                lastDigitPhoneNumb = listOfCompanies.get(i)[2].substring(11);
                masAddress = listOfCompanies.get(i)[4].split(",\s");
                lastDigitNumbBuilding = masAddress[6].substring(masAddress[6].length() - 1);
                firstFivePhoneNumb = listOfCompanies.get(i)[2].substring(2, 7);
                firstFiveFaxNumb = listOfCompanies.get(i)[3].substring(2, 7);
                numbOfEmployeesInCompany = Integer.parseInt(listOfCompanies.get(i)[6]);
            }
            if (firstFivePhoneNumb.equals(firstFiveFaxNumb) & lastDigitPhoneNumb.equals(lastDigitNumbBuilding)) {
                count++;
                listNameOfCompaniesCondition1.add(listOfCompanies.get(i)[0]);
            }
            if (numbOfEmployeesInCompany > numbOfEmployees) {
                listNameOfCompaniesCondition2.add(listOfCompanies.get(i)[0]);
                listNumbOfEmployee.add(numbOfEmployeesInCompany);
            }
        }
        output(count);
    }

    public static void output(int count) {
        out.println();
        out.printf("Количество компаний, в которых первые 5 цифр факса совпадает с номером телефона, " +
                "\nа последняя цифра телефона совпадает с последней цифрой номера дома: %d", count);
        out.println();
        out.println("Это компании:");
        for (int i = 0; i < listNameOfCompaniesCondition1.size(); i++) {
            out.println(listNameOfCompaniesCondition1.get(i));
        }
        out.println();
        out.println("Международные компании, количество сотрудников у которых больше 10000, представлены ниже:");
        for (int i = 0; i < listNameOfCompaniesCondition2.size(); i++) {
            out.printf(listNameOfCompaniesCondition2.get(i) + ",  количество сотрудников: %d", listNumbOfEmployee.get(i));
            out.println();
        }
    }
}