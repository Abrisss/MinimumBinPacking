import depository.Depository;
import gui.MainFrame;
import utility.ThingsReader;

public class Main {

    public static void main(String[] args) {
        ThingsReader thingsReader = new ThingsReader();
        Depository depo = new Depository();
        MainFrame mainFrame = new MainFrame(depo, thingsReader);
    }
}
