package ejbdemo;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static com.sun.org.apache.xml.internal.utils.LocaleUtility.EMPTY_STRING;

//@Stateless -> un seul etat partagé entre tous les clients
//@Stateful -> un état 1 instance, détruite  après la response au client

@Stateless
//@Stateful
@LocalBean
@Path("/hellows")
public class ejbBean implements  ejbRemoteInterface {
    private String userNames="";

    public ejbBean(){

    }


    @GET
    @Produces("text/html")
    public String sayHello() {
        return "<h1>Hello !<h1><p>Current user list = ".concat(userNames).concat("</h1>");
    }

    @GET
    @Path("/test")
    @Produces("text/html")
    public String sayHello2() {
        return "<h1>TEST !<h1>";
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public Response postName(@FormParam("typedName") String yourName) throws URISyntaxException {

        if(null!=yourName){
            userNames = userNames.concat(yourName).concat(";");

            //REDIRECTION avec message
            String msgQuery = null;
            try {
                msgQuery = "?message=" + encodeValue("çà va tu as gagné : ".concat(yourName));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            URI targetURIForRedirection = new URI("../main.html".concat(msgQuery));
            return Response.seeOther(targetURIForRedirection).build();
            //return "çà va tu as gagné : ".concat(yourName);
        } else {
            //return "///fatalo ERROR : envoi vide///";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(EMPTY_STRING).build();
        }
    }

    @PUT
    @Consumes("text/plain")
    @Produces("text/html")
    public String putName(@QueryParam("yourName") String yourName) {

        if(yourName.isEmpty()) return "Erreur Request : pas de prénom !";

        /*
        genrere une erreur :
        yourName = null;
        yourName = yourName.concat("toto");*/

        userNames = userNames.concat(yourName).concat(";");
        return "<span>Prénom réceptionné dans l'URI : ".concat(yourName).concat("</span><br/>")
                .concat("<br/> Prénoms sauvés :").concat(sayHello());
    }


    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }




}