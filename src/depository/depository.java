package depository;

import algorithm.FirstBinAlgorithm;
import algorithm.LastBinAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class Depository {

    private  List<Bin> bins;
    private  List<Thing> things;
    private final LastBinAlgorithm lastBinAlgorithm;
    private final FirstBinAlgorithm firstBinAlgorithm;

    public Depository() {
        firstBinAlgorithm = new FirstBinAlgorithm();
        lastBinAlgorithm = new LastBinAlgorithm();
    }

    public void init(final List<Thing> things, final int binsNumber) {
        this.things = things;
        bins = new ArrayList<>(binsNumber);
        for(int i = 0; i< binsNumber; i++){
            bins.add(i, new Bin());
        }
    }

    public void runLastBinAlgorithm(){
        lastBinAlgorithm.run(this);
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

    public List<Bin> getBins() {
        return bins;
    }

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
}
