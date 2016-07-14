package bt.be.bddapplication.model;

import android.util.ArrayMap;

import java.util.Date;

/**
 * Created by rome03 on 7/07/2016.
 */
public class Mouvement_Stock {

    private int idMouvement;
    private String typeMouvement;
    private String justificationMouvement;
    private Date dateMouvement;
    private ArrayMap<Integer, Integer> listMvt;
    private int qtt;
    public Mouvement_Stock(){}

    public  Mouvement_Stock(int id, String type, String justification, Date date){
        this.idMouvement =id;
        this.typeMouvement =type;
        this.justificationMouvement = justification;
        this.dateMouvement =date;
    }
    public  Mouvement_Stock(String type, String justification, Date date){

        this.typeMouvement =type;
        this.justificationMouvement = justification;
        this.dateMouvement =date;
    }

    public int getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(int idMouvement) {
        this.idMouvement = idMouvement;
    }

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public String getJustificationMouvement() {
        return justificationMouvement;
    }

    public void setJustificationMouvement(String justificationMouvement) {
        this.justificationMouvement = justificationMouvement;
    }

    public Date getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(String dateMouvement) {
        Date datemvt=new Date(dateMouvement);
        this.dateMouvement = datemvt;
    }

    public ArrayMap<Integer, Integer> getListMvt() {
        return listMvt;
    }

    public void setListMvt(ArrayMap<Integer, Integer> listMvt) {

        this.listMvt.put(idMouvement,qtt);
    }
}
