package ejbdemo;
import javax.ejb.Remote;
import javax.ws.rs.QueryParam;

@Remote
public interface ejbRemoteInterface {

    public String sayHello();
    public String putName(String yourName);
}
