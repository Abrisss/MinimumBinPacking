package algorithm;

import depository.Bin;
import depository.Depository;
import depository.Thing;
import sun.awt.geom.Crossings;

import java.util.List;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class LastBinAlgorithm {

    public boolean oneStep(Depository depo) {
        if (depo.isAbleToAddThingToBin()) {
            return addThingToBin(depo.getNextThing(), depo.getOpenBins(), depo);
        }
        return false;
    }

    private boolean addThingToBin(Thing nextThing, List<Bin> openBins, Depository depo) {
        for (Bin bin : openBins) {
            if (bin.addThingIfPossible(nextThing)) {
                return true;
            } else {
                bin.setClosed();
            }
        }
        depo.returnThing(0, nextThing);
        return false;
    }

}
