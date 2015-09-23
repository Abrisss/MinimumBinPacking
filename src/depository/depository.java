package depository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class Depository {

    private final List<Bin> bins;
    private final List<Thing> things;

    public Depository(final List<Thing> things, final int binsNumber) {
        this.things = things;
        bins = new ArrayList<>(binsNumber);
        for(int i = 0; i< binsNumber; i++){
            bins.add(i, new Bin());
        }
    }

    public List<Bin> getBins() {
        return bins;
    }

    public List<Bin> getNotFullBins() {
        List<Bin> notFullBins = new ArrayList<>();
        for (Bin bin : bins) {
            if (!bin.isFull()) {
                notFullBins.add(bin);
            }
        }
        return notFullBins;
    }

    public List<Bin> getOpenBins() {
        List<Bin> openBins = new ArrayList<>();
        for (Bin bin : bins) {
            if (bin.isOpen()) {
                openBins.add(bin);
            }
        }
        return openBins;
    }

    public Thing getNextThing(){
        return things.remove(0);
    }

    public void returnThing(int index, Thing nextThing) {
        things.add(index,nextThing);
    }
}
