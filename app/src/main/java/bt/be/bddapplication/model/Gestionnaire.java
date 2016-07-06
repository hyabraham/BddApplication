package bt.be.bddapplication.model;

/**
 * Created by rome03 on 4/07/2016.
 */
public class Gestionnaire {

    private  int id;
    private String prenom;
    private String nom;
    private String email;
    private String mdp;

    public Gestionnaire() {}
    public Gestionnaire(String mail, String password) {
        this.email=mail;
        this.mdp=password;
    }
    public Gestionnaire(String prenom, String nom, String mail, String password) {
        this.prenom=prenom;
        this.nom=nom;
        this.email=mail;
        this.mdp=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {

        return String.format("[%s] %s %s - %s", id, nom, prenom, email);
    }

}
