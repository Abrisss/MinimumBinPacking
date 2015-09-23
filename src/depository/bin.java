package depository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class Bin {

    private double weight;
    private final double totalWeight;
    private final List<Thing> things;

    public Bin() {
        things = new ArrayList<>();
        weight = 0;
        totalWeight = 0;
    }

    public boolean addThingIfPossible(Thing thing) {
        if (isAbleToAddThing(thing.getWeight())) {
            addThing(thing);
            return true;
        }
        return false;
    }

    private void addThing(Thing thing) {
        weight += thing.getWeight();
        things.add(thing);
    }

    private boolean isAbleToAddThing(double weight) {
        if (this.weight + weight <= totalWeight) {
            return true;
        }
        return false;
    }
}
