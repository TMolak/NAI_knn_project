import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static String trainingPath;
    private static int valueK;
    private static TrainingData trainingData = new TrainingData();

    public static void main(String[] args) {
        System.out.println("Witaj w programie.");
        System.out.println("Proszę podaj ścieżkę do pliku treningowego:");

        Scanner scanner = new Scanner(System.in);
        trainingPath = scanner.nextLine();
        trainingData.loadData(trainingPath);
        System.out.println("Podaj wartość K");
        valueK = scanner.nextInt();

        menu();
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        String wybor;
        do {
            System.out.println("Co chcesz zrobić?");
            System.out.println("a) Klasyfikacja obserwacji z pliku");
            System.out.println("b) Klasyfikacja własnej obserwacji podanej przez użytkownika");
            System.out.println("c) Zmień wartość K");
            System.out.println("d) Wyjdź z programu");

            wybor = scanner.nextLine();

            switch (wybor) {
                case "a":
                    loadDataToClassify();
                    break;
                case "b":
                    singleObservationToClassify();
                    break;
                case "c":
                    changeK();
                    break;
                case "d":
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

    private static void loadDataToClassify() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ścieżkę do pliku z danymi do klasyfikacji.");
        String path = scanner.nextLine();

    }

    private static void singleObservationToClassify() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj dane jakie chcesz porównać");
        String line = scanner.nextLine();
        String[] features = line.split(",");
        double[] parsedFeatures = new double[features.length];
        for (int i = 0; i < features.length; i++) {
            parsedFeatures[i] = Double.parseDouble(features[i]);
        }
        Observation observation = new Observation(parsedFeatures);
        System.out.println(observation.toString());

        String label = classifyData(observation);
        observation.setLabel(label);
        System.out.println("Twoje dane dopasowano do "+label);
    }

    public static String classifyData(Observation observation) {
        List<Map.Entry<String, Double>> distanceMap = new ArrayList<>();

        trainingData.getObservationList().forEach(x -> {
            double[] xFeatures = x.getFeatures();
            double[] oFeatures = observation.getFeatures();
            double sum = 0;
            for (int i = 0; i < xFeatures.length; i++) {
                double val = (oFeatures[i] - xFeatures[i]);
                sum += Math.pow(val, 2);
            }
            double distance = Math.sqrt(sum);
//            System.out.println(x.getLabel() + " " + Arrays.toString(x.getFeatures()) + " " + distance);
            distanceMap.add(new AbstractMap.SimpleEntry<>(x.getLabel(), distance));
        });

//        System.out.println("///////////////////////////////");
//        for (Map.Entry<String, Double> entry : distanceMap) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//        System.out.println("///////////////////////////////");

        List<Map.Entry<String, Double>> sortedList = distanceMap.stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
//
//        System.out.println("///////////////////////////////");
//        for (Map.Entry<String, Double> entry : sortedList) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//        System.out.println("///////////////////////////////");

        Map<String, Integer> labelCounts = new HashMap<>();
        for (Map.Entry<String, Double> entry : sortedList) {
            String label = entry.getKey();
            labelCounts.put(label, labelCounts.getOrDefault(label, 0) + 1);
        }

        String mostFrequentLabel = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> m : labelCounts.entrySet()) {
            if (m.getValue() > maxCount) {
                maxCount = m.getValue();
                mostFrequentLabel = m.getKey();
            }
        }

        return mostFrequentLabel;
    }
}