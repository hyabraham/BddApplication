package bt.be.bddapplication.model;

import android.util.ArrayMap;

import java.util.Date;

/**
 * Created by rome03 on 7/07/2016.
 */
public class Mouvement_Stock {

    private int idMvt;
    private String typeMvt;
    private String descriptionMvt;
    private Date dateMvt;
    private ArrayMap<Integer, Integer> listMvt;
    private int qtt;
    public Mouvement_Stock(){}

    public  Mouvement_Stock(int id, String type, String description, Date date){
        this.idMvt=id;
        this.typeMvt=type;
        this.descriptionMvt=description;
        this.dateMvt=date;
    }

    public int getIdMvt() {
        return idMvt;
    }

    public void setIdMvt(int idMvt) {
        this.idMvt = idMvt;
    }

    public String getTypeMvt() {
        return typeMvt;
    }

    public void setTypeMvt(String typeMvt) {
        this.typeMvt = typeMvt;
    }

    public String getDescriptionMvt() {
        return descriptionMvt;
    }

    public void setDescriptionMvt(String descriptionMvt) {
        this.descriptionMvt = descriptionMvt;
    }

    public Date getDateMvt() {
        return dateMvt;
    }

    public void setDateMvt(String dateMvt) {
        Date datemvt=new Date(dateMvt);
        this.dateMvt = datemvt;
    }

    public ArrayMap<Integer, Integer> getListMvt() {
        return listMvt;
    }

    public void setListMvt(ArrayMap<Integer, Integer> listMvt) {

        this.listMvt.put(idMvt,qtt);
    }
}
