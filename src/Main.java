import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static String trainingPath;
    private static int valueK;
    private static TrainingData trainingData = new TrainingData();

    public static void main(String[] args) {
        System.out.println("Witaj w programie.");
        System.out.println("Proszę podaj ścieżkę do programu:");

        Scanner scanner = new Scanner(System.in);
        trainingPath = scanner.nextLine();
        trainingData.loadData(trainingPath);
//        trainingData.printData();
        System.out.println("Podaj wartość K");
        valueK = scanner.nextInt();

        menu();
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        String wybor;

        do {
            System.out.println("Co chcesz zrobić?");
            System.out.println("a) Wybierz opcję A");
            System.out.println("b) Wybierz opcję B");
            System.out.println("c) Wybierz opcję C");
            System.out.println("d) Zmień wartość K");
            System.out.println("e) Wyjdź z programu");

            wybor = scanner.nextLine();

            switch (wybor) {
                case "a":
                    break;
                case "b":
                    break;
                case "c":
                    break;
                case "d":
                    changeK();
                    break;
                case "e":
                    System.out.println("Wychodzę z programu.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Niepoprawny wybór. Spróbuj ponownie.");
                    break;
            }
        } while (!wybor.equals("e"));
    }

    private static void changeK() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybrano opcję zmiany K. Aktualne K: " + valueK);
        System.out.println("Podaj nowe K");
        valueK = scanner.nextInt();
        System.out.println("Nowa wartość K wynosi: " + valueK);
    }

}