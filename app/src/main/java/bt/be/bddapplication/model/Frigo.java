package bt.be.bddapplication.model;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by rome03 on 6/07/2016.
 */
public class Frigo {
    private int idFrigo;
    private String nomFrigo;
    private String localisationFrigo;
    private float temperature;
    private Date dateCreationFrigo;
    private int idGestionnaire;
    private Gestionnaire gestionnaireFrigo;
    private ArrayMap<String, Integer> listProduit;

    public Frigo(){}
    public Frigo(String nomF,String localite, float temp,Date dateCreation,int idGest){

        this.nomFrigo=nomF;
        this.localisationFrigo=localite;
        this.temperature=temp;
        this.dateCreationFrigo=dateCreation;
        this.idGestionnaire=idGest;
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
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }



    public Date getDateCreationFrigo() {
        return dateCreationFrigo;
    }

    public void setDateCreationFrigo(String dateCreationFrigo) {
        Date datecreation=new Date(dateCreationFrigo);
        this.dateCreationFrigo = datecreation;
    }

    public Gestionnaire getGestionnaireFrigo() {
        return gestionnaireFrigo;
    }

    public void setGestionnaireFrigo(Gestionnaire gestionnaireFrigo) {
        this.gestionnaireFrigo = gestionnaireFrigo;
    }

    public int getIdGestionnaire() {
        return idGestionnaire;
    }

    public void setIdGestionnaire(int idGestionnaire) {
        this.idGestionnaire = getGestionnaireFrigo().getId();
    }
}
