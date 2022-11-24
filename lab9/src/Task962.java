import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Scanner;

public class Task962 {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        String[] vetClinics = {"vetClinic1", "vetClinic2", "vetClinic3", "vetClinic4", "vetClinic5", "vetClinic6",
                "vetClinic7", "vetClinic8", "vetClinic9", "vetClinic10", "vetClinic11", "vetClinic12", "vetClinic13",
                "vetClinic14", "vetClinic15", "vetClinic16", "vetClinic17", "vetClinic18", "vetClinic19", "vetClinic20"};
        int n = 20;
        String[] infAboutVetClinic = new String[n];
        out.println("Введите данные о ветеринарных клиниках (всё в одну строку):");
        out.println("""
                Район города; Номер/Название ветеринарной клиники; Количество работников; Количество врачей;\s
                Количество врачей высшей категории;\s
                Количество врачей прошедших повышение квалификации за последние 5 лет;\s
                Общее число пациентов в базе; Число пациентов собак; Число пациентов кошек; Наличие рентгена;\s
                Наличие компьютеров; Количество компьютеров; Число клеток для передержки животных;\s
                Средний суммарный доход клиники; Общая стоимость оборудования клиники.""");

        for (int i = 0; i < n; i++) {
            infAboutVetClinic[i] = scanner.nextLine();
        }

        for (int i = 0; i < n; i++) {
            out.println(vetClinics[i] + ": " + infAboutVetClinic[i]);
        }
        checkInfAboutVetClinic(infAboutVetClinic, n);
    }

    public static void checkInfAboutVetClinic (String[] infAboutVetClinic, int n) {
        int c = 15;
        int[] answer = new int[3];
        String[] splitInf;
        String[] masDistrict = {"Рай.", "рай.", "р.", "Р.", "Р-н", "р-н"};
        String[] masCostEquipment = {"стоимость", "Стоимость", "Стоимость оборудования", "стоимость оборудования"};
        for (int i = 0; i < n; i++) {
            splitInf = infAboutVetClinic[i].split(";");
            for (int j = 0; j < masDistrict.length; j++) {
                for (int k = 0; k < c; k++) {
                    if (splitInf[k].contains(masDistrict[j])) {
                       if (i == 0) {
                           list.add(splitInf[k]);
                       }
                       else if (!list.contains(splitInf[k])) {
                           list.add(splitInf[k]);
                           answer[1]++;
                       }
                    }
                }
            }

            int countNameVetClinic = 0;
            for (int j = 0; j < c; j++) {
                if (splitInf[j].contains("/")) {
                    answer[0]++;
                }
                for (char ch : splitInf[j].toCharArray()) {
                    if (Character.isDigit(ch)) {
                        countNameVetClinic++;
                    }
                }
                if (countNameVetClinic > 1) {
                    answer[0]++;
                }
            }

            for (int j = 0; j < masCostEquipment.length; j++) {
                for (int k = 0; k < c; k++) {
                    if ((splitInf[k].contains("Есть") | splitInf[k].contains("есть")) &
                            splitInf[k].contains(masCostEquipment[j])) {
                        answer[2]++;
                    }
                }
            }
        }
        output(answer);
    }

    public static void output(int[] answer) {
        out.println("Количество клиник с номером: " + answer[0]);
        for (int i = 0; i < list.size(); i++) {
            out.println("Район: " + list.get(i) + ", количество клиник, удовлетворяющих условию: " + answer[1]);
        }
        out.println("Количество клиник, где есть рентген с общей стоимостью оборудования клиники более 5 млн. руб: " +
                answer[2]);
    }

}
