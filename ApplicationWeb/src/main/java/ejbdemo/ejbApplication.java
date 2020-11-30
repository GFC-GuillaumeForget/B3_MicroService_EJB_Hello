package ejbdemo;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//2z cas de test WebService REST Full Webservice:
// dans glassFish
// http://forgetg-pc:8080/ApplicationWeb-1.0-SNAPSHOT/rest

@ApplicationPath("/rest")
public class ejbApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(ejbBean.class);
        classes.add(ejbPanier.class);
        return classes;
    }
}