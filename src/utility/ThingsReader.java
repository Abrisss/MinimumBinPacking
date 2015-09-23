package utility;

import depository.Thing;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Abraham on 2015. 09. 23..
 */
public class ThingsReader {

    JSONParser parser;

    public ThingsReader() {
        parser = new JSONParser();
    }

    public List<Thing> parseThings(String fileName) {
        List<Thing> returnThings = null;
        try {
            File file = loadFile(fileName);
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj;
            returnThings = new ArrayList<>();

            JSONArray thingsArray = (JSONArray) jsonObject.get("things");
            Iterator<Double> thingsIterator = thingsArray.iterator();
            while (thingsIterator.hasNext()) {
                returnThings.add(new Thing(thingsIterator.next()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnThings;
    }

    public Integer parseBinsNumber(String fileName) {
        int binsNumber = 0;
        try {
            File file = loadFile(fileName);
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj;

            Long temp = (Long) jsonObject.get("binsNumber");
            binsNumber = temp.intValue();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return binsNumber;
    }

    private File loadFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource("resources/" + fileName + ".json").getFile());
    }
}
