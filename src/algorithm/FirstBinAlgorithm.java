package algorithm;

import depository.Bin;
import depository.Depository;
import depository.Thing;

import java.util.List;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class FirstBinAlgorithm {

    public boolean oneStep(Depository depo) {
        if (depo.isAbleToAddThingToBin()) {
            return addThingToBin(depo.getNextThing(), depo.getNotFullBins(), depo);
        }
        return false;
    }

    private boolean addThingToBin(Thing nextThing, List<Bin> notFullBins, Depository depo) {
        for (Bin bin : notFullBins) {
            if (bin.addThingIfPossible(nextThing)) {
                return true;
            }
        }
        depo.returnThing(0, nextThing);
        return false;
    }
}
