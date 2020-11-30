package ejbdemo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.sun.org.apache.xml.internal.utils.LocaleUtility.EMPTY_STRING;

//@Stateless -> un seul etat partagé entre tous les clients
//@Stateful -> un état 1 instance, détruite  après la response au client

@Stateless
//@Stateful
@LocalBean
@Path("/panier")
public class ejbPanier implements  ejbRemotePanierInterface {
    private List<JsonObject> itemsJson=null;

    public ejbPanier(){
        itemsJson= new ArrayList<JsonObject>();
    }

    @Override
    public String getArticles() {
        return "";
    }

    @Override
    @POST
    @Path("/add")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public Response postNewArticle(@FormParam("nom") String nom, @FormParam("prix") Integer prix) {
        try{
            final Map<String, ?> config = Collections.emptyMap();
            JsonBuilderFactory factory = Json.createBuilderFactory(config);
            JsonObject value = factory.createObjectBuilder()
                    .add("nom", nom)
                    .add("prix", prix)
                    .build();
            //itemsJson.add(value);
            itemsJson.add(value);

            // System.out.println(itemsJson.toString());
            String message = itemsJson.toString();
            return Response.status(Response.Status.ACCEPTED)
                    .entity(message)
                    .build();
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }

    }

    @Override
    @GET
    @Path("/get")
    public String getPanier() {
        return itemsJson.toString();
    }

    @GET
    @Produces("text/html")
    public String sayHello() {
        return "";
    }

    @GET
    @Path("/test")
    @Produces("text/html")
    public String sayHello2() {
        return "<h1>TEST !<h1>";
    }



    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }




}