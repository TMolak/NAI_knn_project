import java.util.Comparator;

public class Compare implements Comparator<Observation> {

    @Override
    public int compare(Observation o1, Observation o2) {
        double[] o1Features = o1.getFeatures();
        double[] o2Features = o2.getFeatures();



        return 0;
    }
}
