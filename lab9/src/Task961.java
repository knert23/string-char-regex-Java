import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task961 {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<String> listInf = new ArrayList<>(); // конструкция
    static ArrayList<String> listDistrict = new ArrayList<>(); // районы
    static ArrayList<Integer> listDistrictInf = new ArrayList<>(); // данные по районам

    public static void main(String[] args) {
        String[] masList = {"Район города", "Номер/Название ветеринарной клиники", "Количество работников",
                "Количество врачей", "Количество врачей высшей категории",
                "Количество врачей прошедших повышение квалификации за последние 5 лет",
                "Общее число пациентов в базе", "Число пациентов собак", "Число пациентов кошек", "Наличие рентгена",
                "Наличие компьютеров", "Количество компьютеров", "Число клеток для передержки животных",
                "Средний суммарный доход клиники", "Общая стоимость оборудования клиники"};

        listInf.addAll(Arrays.asList(masList));

        String[] vetClinics = {"vetClinic1", "vetClinic2", "vetClinic3", "vetClinic4", "vetClinic5", "vetClinic6",
                "vetClinic7", "vetClinic8", "vetClinic9", "vetClinic10", "vetClinic11", "vetClinic12", "vetClinic13",
                "vetClinic14", "vetClinic15", "vetClinic16", "vetClinic17", "vetClinic18", "vetClinic19", "vetClinic20"};
        int n = 20;
        String[] infAboutVetClinic = new String[n];
        out.println("Введите данные о ветеринарных клиниках (всё в одну строку) в виде:");
        out.println("""
                Район города; Номер/Название ветеринарной клиники; Количество работников; Количество врачей;\s
                Количество врачей высшей категории;\s
                Количество врачей прошедших повышение квалификации за последние 5 лет;\s
                Общее число пациентов в базе; Число пациентов собак; Число пациентов кошек; Наличие рентгена;\s
                Наличие компьютеров; Количество компьютеров; Число клеток для передержки животных;\s
                Средний суммарный доход клиники; Общая стоимость оборудования клиники.""");

        for (int i = 0; i < n; i++) {
            infAboutVetClinic[i] = checkInfAboutVetClinics();
        }

        for (int i = 0; i < n; i++) {
            out.println(vetClinics[i] + ": " + infAboutVetClinic[i]);
        }
        analyzeInfAboutVetClinics(infAboutVetClinic, n);
    }

    // анализируем данные и выделяем то, что нужно по условию задачи
    public static void analyzeInfAboutVetClinics (String[] infAboutVetClinic, int n) {
        String[] splitInf;
        int[]  answer = new int[2];
        for (int i = 0; i < n; i++) {
            splitInf = infAboutVetClinic[i].split(";");
            if (splitInf[1].contains("/")) { // если строка содержит "/", то это автоматически значит, что есть номер
                answer[0]++;
            }

            int numberDocHighCategory = Integer.parseInt(splitInf[4]);
            int numberDocHighCategoryFive = Integer.parseInt(splitInf[5]);
            int totalNumberDoc = Integer.parseInt(splitInf[2]);
            int totalNumberPatient = Integer.parseInt(splitInf[6]);
            boolean conditions = numberDocHighCategory >= 2 & numberDocHighCategoryFive > totalNumberDoc * 3.0 / 10 &
                    totalNumberPatient > 100;
            if (i == 0) {
                listDistrict.add(splitInf[0]);
                if (conditions) {
                    listDistrictInf.add(1);
                }
            }
            else {
                if (!listDistrict.contains(splitInf[0])) {
                    listDistrict.add(splitInf[0]);
                    if (conditions) {
                        listDistrictInf.add(1);
                    }
                    else {
                        listDistrictInf.add(0);
                    }
                } else if (listDistrict.contains(splitInf[0])) {
                    if (conditions) {
                        int index = listDistrict.indexOf(splitInf[0]);
                        int newElement = listDistrictInf.get(index) + 1;
                        listDistrictInf.set(index, newElement);
                    }
                }
            }
            int totalCostEquipment = Integer.parseInt(splitInf[14]);
            if ((splitInf[9].equals("есть") | splitInf[9].equals("Есть")) & totalCostEquipment > 5000000) {
                answer[1]++;
            }
        }
        output(answer);
    }

    // проверка на структуру данных
    public static String checkInfAboutVetClinics() {
        String string = scanner.nextLine();
        String[] strMasVetClinic = string.split(";"); // создаем массив, в каждой ячейке - инф. в опр. виде

        while (cycleCheckInfAboutVetClinics(strMasVetClinic)) { // проверка праавильности ввода конструкции
            out.println("Пожалуйста, повторите ввод данных:");
            string = scanner.nextLine();
            strMasVetClinic = string.split(";");
        }

        return string;
    }

    // сам цикл по проверке
    public static boolean cycleCheckInfAboutVetClinics(String[] strMasVetClinic) {
        boolean marker = false;
        for (char ch : strMasVetClinic[0].toCharArray()) {
            if (Character.isDigit(ch)) {
                out.println("В позиции 1, где указывается " + listInf.get(0) +
                        ", вы ввели не буквы, там должна быть буквенная строка!");
                marker = true;
            }
        }

        String[] strMasVetClinicBoxTwo = strMasVetClinic[1].split("/");
        if (strMasVetClinic[1].contains("/")) {
            int countTwoFirst = 0;
            int countTwoSecond = 0;
            for (char ch : strMasVetClinicBoxTwo[0].toCharArray()) {
                if (!Character.isDigit(ch)) {
                    marker = true;
                    countTwoFirst++;
                }
            }
            for (char ch : strMasVetClinicBoxTwo[1].toCharArray()) {
                if (!Character.isLetter(ch)) {
                    marker = true;
                    countTwoSecond++;
                }
            }
            if (countTwoFirst > 0) {
                out.println("В позиции 2, где указывается номер клиники, вы ввели не число!");
            }
            if (countTwoSecond > 0) {
                out.println("В позиции 2, где указывается название клиники, вы ввели не буквы!");
            }
        }
        else if (!strMasVetClinic[1].contains("/")) {
            int countTwo = 0;
            for (char ch : strMasVetClinic[1].toCharArray()) {
                if (!Character.isLetter(ch)) {
                    countTwo++;
                }
            }
            if (countTwo > 0) {
                out.println("В позиции 2, где указывается название клиники, вы ввели не буквы!");
            }
        }

        for (int i = 2; i < 9; i++) {
            int countThreeToNine = 0;
            for (char ch : strMasVetClinic[i].toCharArray()) {
                if (Character.isLetter(ch)) {
                    countThreeToNine++;
                }
            }
            if(countThreeToNine > 0) {
                marker = true;
                out.println("В позиции " + (i + 1) + " (" + listInf.get(i) + ") " + "вы ввели не число!");
            }
        }

        for (int i = 9; i < 11; i++) {
            int countTenToEleven = 0;
            for (char ch : strMasVetClinic[i].toCharArray()) {
                if (!Character.isLetter(ch)) {
                    countTenToEleven++;
                }
            }
            if (countTenToEleven > 0) {
                marker = true;
                out.println("В позиции " + (i + 1) + " (" + listInf.get(i) + ") " + "вы ввели не буквы, " +
                        "укажите наличие (есть/нет)!");
            }
            if(countTenToEleven == 0 & !strMasVetClinic[i].equals("есть") & !strMasVetClinic[i].equals("Есть") &
                    !strMasVetClinic[i].equals("нет") & !strMasVetClinic[i].equals("Нет")) {
                out.println("В позиции " + (i + 1) + " (" + listInf.get(i) + ") " + "укажите наличие (есть/нет)");
            }
        }

        for (int i = 11; i < 15; i++) {
            int countTwelveToFifteen = 0;
            for (char ch : strMasVetClinic[i].toCharArray()) {
                if (!Character.isDigit(ch)) {
                    countTwelveToFifteen++;
                }
            }
            if (countTwelveToFifteen > 0) {
                marker = true;
                out.println("В позиции " + (i + 1) + " (" + listInf.get(i) + ") " + "вы ввели не число!");
            }
        }

        return marker;
    }

    public static void output(int[] answer) {
        out.println();
        out.println("Количество клиник с номером: " + answer[0]);
        out.println("------------------------------------------------------------------------------------------------");
        for (int i = 0; i < listDistrict.size(); i++) {
            out.println("Район: " + listDistrict.get(i) + ", количество клиник, удовлетворяющих условию: " +
                    listDistrictInf.get(i));
        }
        out.println("------------------------------------------------------------------------------------------------");
        out.println("Количество клиник, где есть рентген с общей стоимостью оборудования клиники более 5 млн. руб: " +
                answer[1]);
    }
}
