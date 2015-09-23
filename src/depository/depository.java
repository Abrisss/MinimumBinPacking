package depository;

import java.util.List;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class Depository {

    private List<Bin> bins;
    private final List<Thing> things;

    public Depository(List<Thing> things) {
        this.things = things;
    }
}
