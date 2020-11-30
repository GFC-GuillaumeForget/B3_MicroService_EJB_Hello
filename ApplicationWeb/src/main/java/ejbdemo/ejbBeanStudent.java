package ejbdemo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.sun.org.apache.xml.internal.utils.LocaleUtility.EMPTY_STRING;

//@Stateless -> un seul etat partagé entre tous les clients
//@Stateful -> un état 1 instance, détruite  après la response au client

@Stateless
//@Stateful
@LocalBean
@Path("/student")
public class ejbBeanStudent implements  ejbRemoteStudentInterface {
    String prenoms ="";

    public ejbBeanStudent(){

    }


    @GET
    @Produces("text/html")
    public String sayName() {
        return "prenom GF";
    }

    public void setPrenomsFromEjbBean(String prenoms ){
        prenoms = prenoms;
    }





}