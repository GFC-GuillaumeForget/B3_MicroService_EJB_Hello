package ejbdemo.models;

import javax.json.JsonObject;
import javax.json.JsonValue;
import java.io.Serializable;
import java.util.Map;

public class Article implements Serializable {
    private int id;
    private String nom;
    private Integer prix;

    public Article(){}

    public Article(int id, String nom, Integer prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
    @Override
    public String toString() {
        return this.toJSON();
    }

    public String toJSON() {
        return "{ \"id\":" + this.id
                + ", \"nom\":\"" + this.nom + "\""
                + ", \"prix\":\"" + this.prix + "\""
                + "}";


    }

}
