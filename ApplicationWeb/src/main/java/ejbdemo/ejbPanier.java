package ejbdemo;

import ejbdemo.models.Article;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

import static com.sun.org.apache.xml.internal.utils.LocaleUtility.EMPTY_STRING;

//@Stateless -> un seul etat partagé entre tous les clients
//@Stateful -> un état 1 instance, détruite  après la response au client

@Stateless
//@Stateful
@LocalBean
@Path("/panier")
public class ejbPanier implements  ejbRemotePanierInterface {
    private List<Article> itemsJson=null;

    public ejbPanier(){
        itemsJson= new ArrayList<Article>();
    }

    @Override
    @GET
    @Path("/getArticles")
    public List<Article> getArticles() {
            Connection con = null;
            String url = "jdbc:mysql://localhost/formation";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root";
            String password = "";

            List<Article> articles = new ArrayList<Article>();
            try {

                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(url , userName, password);

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from article");

                Article article;
                while (rs.next()) {
                    article = new Article();
                    article.setId(rs.getInt(1));
                    article.setNom(rs.getString(2));
                    article.setPrix(rs.getInt(3));
                    articles.add(article);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            return articles;
     }

    @Override
    @POST
    @Path("/add")
    @Consumes("application/x-www-form-urlencoded")
    @Produces({MediaType.APPLICATION_JSON})
    /*
           Fonction ajouter au panier
           @return list JSON des articles du panier
     */
    public Response postNewArticle(@FormParam("nom") String nom, @FormParam("prix") Integer prix) {
        try{
            /*
            Methode Element JSON
            final Map<String, ?> config = Collections.emptyMap();
            JsonBuilderFactory factory = Json.createBuilderFactory(config);
            JsonObject value = factory.createObjectBuilder()
                    .add("nom", nom)
                    .add("prix", prix)
                    .build();*/
            //itemsJson.add(value);

            /* best MEthode :  via Model Article */
            Article a = new Article();
            a.setNom(nom);  a.setPrix(prix);
            itemsJson.add(a);

            // > old method Element JSON :
            // String message = itemsJson.toString();
            // > best method Serializable Model Article :
             List<Article> panier = getPanier();

            return Response.status(Response.Status.ACCEPTED)
                    .entity(panier.toString())
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
    @Produces({MediaType.APPLICATION_JSON})
    public List<Article> getPanier() {
        // > old method Element JSON :
        // return itemsJson.toString();
        // > best method Serializable Model Article :
        return itemsJson;
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