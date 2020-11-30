import startJava.ServiceWeb;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class Main  {

    /* Objectif de ce pilote
  - integrer un jar
  - EJB stateful et stateless
   */

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--Application web --");

        List<JsonObject> itemsJson= new ArrayList<JsonObject>();
        final Map<String, ?> config = Collections.emptyMap();
        JsonBuilderFactory factory = Json.createBuilderFactory(config);
        JsonObject value = factory.createObjectBuilder()
                .add("nom", "guil")
                .add("prix", 1)
                .build();
        //itemsJson.add(value);
        itemsJson.add(value);

        System.out.println(itemsJson.toString());
    }
}