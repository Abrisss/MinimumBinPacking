package depository;

import algorithm.FirstBinAlgorithm;
import algorithm.LastBinAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class Depository {

    private  List<Bin> bins;
    private  List<Thing> things;
    private final LastBinAlgorithm lastBinAlgorithm;
    private final FirstBinAlgorithm firstBinAlgorithm;
    private boolean isInit;

    public Depository() {
        firstBinAlgorithm = new FirstBinAlgorithm();
        lastBinAlgorithm = new LastBinAlgorithm();
        isInit = false;
    }

    public void init(final List<Thing> things, final int binsNumber) {
        this.things = things;
        bins = new ArrayList<>(binsNumber);
        for(int i = 0; i< binsNumber; i++){
            bins.add(i, new Bin());
        }
        isInit = true;
    }

    public void sort(){
        Collections.sort(things);
        Collections.reverse(things);
    }

    public boolean runLastBinAlgorithmOneStep() {
        return lastBinAlgorithm.oneStep(this);
    }

    public boolean runFirstBinAlgorithmOneStep() {
        return firstBinAlgorithm.oneStep(this);
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

    public List<Thing> getThings() {
        return things;
    }

    public void returnThing(int index, Thing nextThing) {
        things.add(index,nextThing);
    }

    public List<Bin> getBins() {
        return bins;
    }

    /**
     * If depo has at least one thing, and has at least one open and not full bin.
     * @return
     */
    public boolean isAbleToAddThingToBin() {
        boolean binCondition = false;
        boolean thingCondition = false;
        for (Bin bin : bins) {
            if (bin.isOpen() && !bin.isFull()) {
                binCondition = true;
            }
        }
        if(!things.isEmpty()){
            thingCondition = true;
        }
        return (binCondition && thingCondition);
    }


    public boolean isInit() {
        return isInit;
    }
}
