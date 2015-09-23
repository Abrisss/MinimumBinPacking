package algorithm;

import depository.Bin;
import depository.Depository;
import depository.Thing;

import java.util.List;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class LastBinAlgorithm {
    private Depository depo;

    public void run(Depository depo) {
        this.depo = depo;
        while(depo.isAbleToAddThingToBin()){
            addThingToBin(depo.getNextThing(), depo.getOpenBins());
        }

    }

    private boolean addThingToBin(Thing nextThing, List<Bin> openBins) {
        for (Bin bin : openBins) {
            if (bin.addThingIfPossible(nextThing)) {
                return true;
            }
            else{
                bin.setClosed();
            }
        }
        depo.returnThing(0, nextThing);
        return false;
    }
}
