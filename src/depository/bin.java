package depository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class Bin {

    private double weight;
    private final double totalWeight;
    private boolean isFull;
    private boolean isOpen;
    private final List<Thing> things;

    public Bin() {
        things = new ArrayList<>();
        weight = 0;
        totalWeight = 1;
        isFull = false;
        isOpen = true;
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
            if (this.weight + weight == totalWeight) {
                isFull = true;
            }
            return true;
        }
        return false;
    }

    public boolean isFull() {
        return isFull;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setClosed() {
        isOpen = false;
    }
}
