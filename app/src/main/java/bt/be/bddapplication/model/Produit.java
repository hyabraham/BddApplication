package bt.be.bddapplication.model;

import java.util.Date;

/**
 * Created by rome03 on 7/07/2016.
 */
public class Produit {
    private int idProduit;
    private String libelleProduit;
    private String descriptionProduit;
    private Date datePeremptionProduit;
    private Date dateLivraisonProduit;
    private int qteSeuil;
    private int idFss;
    private Fournisseur fournisseurProduit;

    public Produit(){}

    public Produit(int id, String libelle, String description, Date dateperemption, int seuil){
        this.idProduit=id;
        this.libelleProduit=libelle;
        this.descriptionProduit=description;
        this.datePeremptionProduit=dateperemption;
        this.qteSeuil=seuil;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getLibelleProduit() {
        return libelleProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public Date getDatePeremptionProduit() {
        return datePeremptionProduit;
    }

    public void setDatePeremptionProduit(String datePeremptionProduit) {
        Date datePeremption = new Date(datePeremptionProduit);
        this.datePeremptionProduit = datePeremption;
    }

    public int getQteSeuil() {
        return qteSeuil;
    }

    public void setQteSeuil(int qteSeuil) {
        this.qteSeuil = qteSeuil;
    }

    public Date getDateLivraisonProduit() {
        return dateLivraisonProduit;
    }

    public void setDateLivraisonProduit(String dateLivraisonProduit) {
        Date datelivraison = new Date(dateLivraisonProduit);
        this.dateLivraisonProduit = datelivraison;
    }

    public Fournisseur getFournisseurProduit() {
        return fournisseurProduit;
    }

    public void setFournisseurProduit(Fournisseur fournisseurProduit) {
        this.fournisseurProduit = fournisseurProduit;
    }

    public int getIdFss() {
        return idFss;
    }

    public void setIdFss(int idFss) {

        this.idFss = getFournisseurProduit().getIdFss();
    }
}
