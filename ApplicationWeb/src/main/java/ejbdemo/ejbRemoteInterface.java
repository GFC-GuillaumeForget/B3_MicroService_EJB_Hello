package ejbdemo;
import javax.ejb.Remote;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Remote
public interface ejbRemoteInterface {
    public String sayHello();
    public String sayHello2();
    public String putName(String yourName);
    public Response postName(String yourName) throws URISyntaxException;


}
