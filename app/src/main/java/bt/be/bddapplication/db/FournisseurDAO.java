package bt.be.bddapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bt.be.bddapplication.model.Fournisseur;


/**
 * Created by rome03 on 6/07/2016.
 */
public class FournisseurDAO {

    public static final String TABLE_FOURNISSEUR="FOURNISSEUR";

    public static final String COLUMN_ID="IDFournisseur";
    public static final String COLUMN_NAME="nomFss";
    public static final String COLUMN_EMAIL="emailFss";
    public static final String COLUMN_ADRESSE="adresseFss";

    public static final String CREATE_REQUEST="CREATE TABLE "+ FournisseurDAO.TABLE_FOURNISSEUR
            +"("+ FournisseurDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FournisseurDAO.COLUMN_NAME + " TEXT NOT NULL, "
            + FournisseurDAO.COLUMN_EMAIL + " TEXT NOT NULL,"
            + FournisseurDAO.COLUMN_ADRESSE + " TEXT NOT NULL" +");";

    public  static final String UPGRADE_REQUEST="DROP TABLE " + FournisseurDAO.TABLE_FOURNISSEUR;

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public FournisseurDAO(Context context){
        this.context=context;
    }

    public FournisseurDAO openWritable(){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
        return this;
    }

    public FournisseurDAO openReadable(){
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        db.close();
        dbHelper.close();
    }

    public long createFournisser(Fournisseur f){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,f.getNomFss());
        cv.put(COLUMN_EMAIL,f.getEmailFss());
        cv.put(COLUMN_ADRESSE,f.getAdresseFss());
        return db.insert(TABLE_FOURNISSEUR,null,cv);
    }
    public void deleteFournisseur(Fournisseur f) {
        db.delete(TABLE_FOURNISSEUR, COLUMN_ID + "=" + f.getIdFss(), null);
    }

    public Cursor getFssCursorById(int fssId) {
        Cursor c = db.query(TABLE_FOURNISSEUR, null, COLUMN_ID + "=" + fssId, null, null, null, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            return c;
        }
        else
            return null;
    }
    public Cursor getFournisseur(){
        Cursor c = db.rawQuery("SELECT " + GestionnaireDAO.COLUMN_ID + " , " + COLUMN_NAME + " FROM " + FournisseurDAO.TABLE_FOURNISSEUR,null);
        if(c.getCount()>0){
            c.moveToFirst();
            return c;
        }else
            return null;
    }

    public Boolean getFssByMail(String leMail){
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FOURNISSEUR + " WHERE "+ COLUMN_EMAIL +"= ?", new String[]{leMail});
        if(c.getCount()>0){
            return false;
        }else{return true;}
    }


    public static Fournisseur cursorToUser(Cursor c){
        Fournisseur f =new Fournisseur();
        f.setIdFss(c.getInt(c.getColumnIndex(COLUMN_ID)));
        f.setNomFss(c.getString(c.getColumnIndex(COLUMN_NAME)));
        f.setAdresseFss(c.getString(c.getColumnIndex(COLUMN_ADRESSE)));
        f.setEmailFss(c.getString(c.getColumnIndex(COLUMN_EMAIL)));
        return f;
    }

    public Fournisseur getFournisseurById(int id){

        Cursor c =getFssCursorById(id);

        if(c!=null){
            return cursorToUser(c);
        }else {
            return null;
        }
    }

    public int update(Fournisseur f){

        ContentValues cv =new ContentValues();
        cv.put(COLUMN_NAME,f.getNomFss());
        cv.put(COLUMN_ADRESSE,f.getAdresseFss());
        cv.put(COLUMN_EMAIL,f.getEmailFss());

        return db.update(TABLE_FOURNISSEUR,cv, COLUMN_ID + "=" + f.getIdFss(),null);
    }

    public void delete(Fournisseur f){

        db.delete(TABLE_FOURNISSEUR,COLUMN_ID + "=" + f.getIdFss(),null);
    }

}
