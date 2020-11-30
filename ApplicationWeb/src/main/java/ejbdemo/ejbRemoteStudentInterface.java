package ejbdemo;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Remote
public interface ejbRemoteStudentInterface {
    public String sayName();
}
