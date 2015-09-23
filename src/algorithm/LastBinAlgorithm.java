package algorithm;

import depository.Bin;
import depository.Depository;
import depository.Thing;

import javax.management.Query;
import java.util.List;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class LastBinAlgorithm {
    private Depository depo;

    public Depository runAlgorithm(Depository depo) {
        this.depo = depo;
        addThingToBin(depo.getNextThing(), depo.getOpenBins());
        return this.depo;
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
