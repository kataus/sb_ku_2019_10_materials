package ru.itvitality.sbrf.cu.l08serialization.xjson;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;

public class JavaxJsonDemo {
    public static void main(String[] args) throws FileNotFoundException {
   //     readFromFile();
  //      create();

//        navigateTree(create());
    }

    private static void readFromFile() throws FileNotFoundException {
        JsonReader reader = Json.createReader(new FileReader("jsondata.txt"));

        JsonStructure jsonIn = reader.read();
        System.out.println("jsonIn:" + jsonIn);
    }


    private static JsonObject create() {
        JsonObject jsonCreated = Json.createObjectBuilder()
                .add("firstName", "Duke")
                .add("age", 28)
                .add("streetAddress", "100 Internet Dr")
                .add("phoneNumbers", Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                    .add("type", "home")
                                    .add("number", "222-222-2222")))
                .build();
        System.out.println("jsonCreated:" + jsonCreated);

        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(jsonCreated);
        }

        System.out.println(stWriter);
        return jsonCreated;
    }

    private static void navigateTree(JsonValue tree) {
        switch (tree.getValueType()) {
            case OBJECT:
                System.out.println("OBJECT");
                JsonObject object = (JsonObject) tree;
                for (String name : object.keySet()) {
                    navigateTree(object.get(name));
                }
                break;
            case ARRAY:
                System.out.println("ARRAY");
                JsonArray array = (JsonArray) tree;
                for (JsonValue val : array) {
                    navigateTree(val);
                }
                break;
            case STRING:
                JsonString st = (JsonString) tree;
                System.out.println("STRING " + st.getString());
                break;
            case NUMBER:
                JsonNumber num = (JsonNumber) tree;
                System.out.println("NUMBER " + num.toString());
                break;
            case TRUE:
            case FALSE:
            case NULL:
                System.out.println(tree.getValueType().toString());
                break;
        }
    }
}
