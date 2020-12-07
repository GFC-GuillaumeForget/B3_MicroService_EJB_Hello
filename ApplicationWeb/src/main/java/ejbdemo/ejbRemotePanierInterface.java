package ejbdemo;
import ejbdemo.models.Article;

import javax.ejb.Remote;
import javax.ws.rs.core.Response;
import java.util.List;

@Remote
public interface ejbRemotePanierInterface {
    /* json format : list */
    public List<Article> getArticles();

    public Response postNewArticle(String nom, Integer prix);

    /* json format : list */
    //old version
    // public String getPanier();

    /* best version
    list of Articles */
    public   List<Article> getPanier();
}
