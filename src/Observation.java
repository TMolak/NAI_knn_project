import java.util.Arrays;

public class Observation {
    private String label;
    private double[] features;

    public Observation(String label, double[] features) {
        this.label = label;
        this.features = features;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double[] getFeatures() {
        return features;
    }

    public void setFeatures(double[] features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "Observation{" +
                "label='" + label + '\'' +
                ", features=" + Arrays.toString(features) +
                '}';
    }
}
