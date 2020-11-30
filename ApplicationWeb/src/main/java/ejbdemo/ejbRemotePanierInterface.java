package ejbdemo;
import javax.ejb.Remote;
import javax.ws.rs.core.Response;

@Remote
public interface ejbRemotePanierInterface {
    /* json format : list */
    public String getArticles();

    public Response postNewArticle(String nom, Integer prix);

    /* json format : list */
    public String getPanier();



}
