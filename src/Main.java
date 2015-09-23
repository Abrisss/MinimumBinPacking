import algorithm.LastBinAlgorithm;
import depository.Depository;
import utility.ThingsReader;

public class Main {

    public static void main(String[] args) {
        ThingsReader thingsReader = new ThingsReader();

        Depository depo = new Depository(thingsReader.parse("things"), 10);

        LastBinAlgorithm lastBinAlgorithm = new LastBinAlgorithm();
        lastBinAlgorithm.runAlgorithm(depo);
    }
}
