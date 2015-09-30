package depository;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class Thing implements Comparable {
    private final double weight;

    public Thing(final double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Object o) {
        Thing thing = (Thing) o;
        if (this.weight < thing.weight) {
            return -1;
        } else if (this.weight == thing.weight) {
            return 0;
        } else {
            return 1;
        }
    }
}
