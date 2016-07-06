package bt.be.bddapplication.model;

/**
 * Created by rome03 on 6/07/2016.
 */
public class Fournisseur {
    private  int idFss;
    private String nomFss;
    private String emailFss;
    private String adresseFss;

    public Fournisseur() {}

    public Fournisseur(String nom, String mail) {
        this.emailFss=mail;
        this.nomFss=nom;
    }

    public Fournisseur(String nom, String mail, String adresse) {
        this.nomFss=nom;
        this.emailFss=mail;
        this.adresseFss=adresse;
    }

    public int getIdFss() {
        return idFss;
    }

    public void setIdFss(int idFss) {
        this.idFss = idFss;
    }

    public String getAdresseFss() {
        return adresseFss;
    }

    public void setAdresseFss(String adresseFss) {
        this.adresseFss = adresseFss;
    }

    public String getEmailFss() {
        return emailFss;
    }

    public void setEmailFss(String emailFss) {
        this.emailFss = emailFss;
    }

    public String getNomFss() {
        return nomFss;
    }

    public void setNomFss(String nomFss) {
        this.nomFss = nomFss;
    }

    public String toString() {

        return String.format("[%s] %s %s - %s", idFss, nomFss, emailFss, adresseFss);
    }
}
