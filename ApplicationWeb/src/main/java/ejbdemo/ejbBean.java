package ejbdemo;

import java.rmi.RemoteException;
import javax.ejb.*;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.ws.rs.*;

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

    @PUT
    @Consumes("text/plain")
    @Produces("text/html")
    public String putName(@QueryParam("yourName") String yourName) {
        if(yourName.isEmpty()) return "Erreur Request";

        userNames = userNames.concat(yourName).concat(";");
        return "Done for ".concat(yourName)
               .concat("<br/> saved :").concat(sayHello());
    }


}