import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainingData {
    List<Observation> observationList;

    public TrainingData() {
        this.observationList = new ArrayList<>();
    }

    public void loadData(String path){
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while((line = br.readLine()) != null){
                String[] spliitedLine = line.split(",");
                double[] features = new double[spliitedLine.length-1];
                for (int i = 0; i < features.length; i++) {
                    features[i] = Double.parseDouble(spliitedLine[i]);
                }
                String label = spliitedLine[spliitedLine.length-1];
                Observation observation = new Observation(label, features);
                observationList.add(observation);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
//    public void printData() {
//        for (Observation observation : observationList) {
//            double[] features = observation.getFeatures();
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < features.length; i++) {
//                sb.append(features[i]);
//                if (i < features.length - 1) {
//                    sb.append(", ");
//                }
//            }
//            System.out.println(sb.toString() + ", " + observation.getLabel());
//        }
//    }
    public List<Observation> getObservationList() {
        return observationList;
    }

    public void setObservationList(List<Observation> observationList) {
        this.observationList = observationList;
    }
}
