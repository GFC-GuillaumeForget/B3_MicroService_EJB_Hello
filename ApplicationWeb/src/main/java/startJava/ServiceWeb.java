package startJava;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.time.LocalDate;

//1er cas de test SOA/WSDL :
// dans glassFish
//http://localhost:8080/ApplicationWeb-1.0-SNAPSHOT/startJavaEjb?tester

@WebService(serviceName = "startJavaEjb")

public class ServiceWeb {
    @WebMethod (operationName="test")
    @GET
    public String test(String prenom, String sexe, Integer anneeNaiss) {
        return prenom;
    }
}


