package ru.itvitality.sbrf.cu.l08serialization.simpleJson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class DemoSimpleJson {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("current dir: " + System.getProperty("user.dir"));

        //read();

        //create();


        navigateTree(create());
    }

    private static void read() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObjectIn = (JSONObject) jsonParser.parse((new FileReader("jsondata.txt")));
        System.out.println("jsonObjectIn:" + jsonObjectIn);
    }

    private static JSONObject create() {
        JSONObject root = new JSONObject();
        root.put("Key23", "Value42");
        JSONObject address = new JSONObject();
        address.put("Street", "Test");
        root.put("address", address);

        System.out.println("root:" + root);
        return root;
    }


    private static void navigateTree(Object jsonDoc) {
        String awareClassName = jsonDoc.getClass().getSimpleName();
        switch (awareClassName) {
            case "JSONArray":
                System.out.println("JSONArray");
                JSONArray array = (JSONArray) jsonDoc;
                array.forEach(element -> navigateTree(element));
                break;
            case "JSONObject":
                System.out.println("JSONObject");
                JSONObject jsonObject = (JSONObject) jsonDoc;
                jsonObject.entrySet().forEach(element -> navigateTree(element));
                break;
            case "Node":
                System.out.println("Node");
                Map.Entry entry = (Map.Entry) jsonDoc;
                navigateTree(entry.getValue());
                break;
            default:
                System.out.println(jsonDoc);

        }
    }
}
