package bt.be.bddapplication.model;

/**
 * Created by rome03 on 6/07/2016.
 */
public class Fournisseur {
    private  int idFournisseur;
    private String nomFournisseur;
    private String emailFournisseur;
    private String adresseFournisseur;

    public Fournisseur() {}

    public Fournisseur(String nom, String mail) {
        this.emailFournisseur =mail;
        this.nomFournisseur =nom;
    }

    public Fournisseur(String nom, String mail, String adresse) {
        this.nomFournisseur =nom;
        this.emailFournisseur =mail;
        this.adresseFournisseur =adresse;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getAdresseFournisseur() {
        return adresseFournisseur;
    }

    public void setAdresseFournisseur(String adresseFournisseur) {
        this.adresseFournisseur = adresseFournisseur;
    }

    public String getEmailFournisseur() {
        return emailFournisseur;
    }

    public void setEmailFournisseur(String emailFournisseur) {
        this.emailFournisseur = emailFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String toString() {

        return String.format("[%s] %s %s - %s", idFournisseur, nomFournisseur, emailFournisseur, adresseFournisseur);
    }
}
