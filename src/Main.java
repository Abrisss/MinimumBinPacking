import utility.ThingsReader;

public class Main {

    public static void main(String[] args) {
        ThingsReader thingsReader = new ThingsReader();
        System.out.println(thingsReader.parse("things").get(0).getWeight());
    }
}
