package bt.be.bddapplication.model;

import java.util.Date;

/**
 * Created by rome03 on 6/07/2016.
 */
public class Frigo {
    private int idFrigo;
    private String nomFrigo;
    private String localisationFrigo;
    private double temperature;
    private Date dateCreationFrigo;
    private Gestionnaire gestionnaireFrigo;

    public Frigo(){}
    public Frigo(int id,String nomF,String localite, double temp,Date dateCreation,Gestionnaire gestF){
        this.idFrigo=id;
        this.nomFrigo=nomF;
        this.localisationFrigo=localite;
        this.temperature=temp;
        this.dateCreationFrigo=dateCreation;
        this.gestionnaireFrigo=gestF;
    }

    public int getIdFrigo() {
        return idFrigo;
    }

    public void setIdFrigo(int idFrigo) {
        this.idFrigo = idFrigo;
    }

    public String getNomFrigo() {
        return nomFrigo;
    }

    public void setNomFrigo(String nomFrigo) {
        this.nomFrigo = nomFrigo;
    }

    public String getLocalisationFrigo() {
        return localisationFrigo;
    }

    public void setLocalisationFrigo(String localisationFrigo) {
        this.localisationFrigo = localisationFrigo;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Date getDateCreationFrigo() {
        return dateCreationFrigo;
    }

    public void setDateCreationFrigo(Date dateCreationFrigo) {
        this.dateCreationFrigo = dateCreationFrigo;
    }

    public Gestionnaire getGestionnaireFrigo() {
        return gestionnaireFrigo;
    }

    public void setGestionnaireFrigo(Gestionnaire gestionnaireFrigo) {
        this.gestionnaireFrigo = gestionnaireFrigo;
    }
}
