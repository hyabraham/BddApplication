package bt.be.bddapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bt.be.bddapplication.model.Frigo;

/**
 * Created by rome03 on 7/07/2016.
 */
public class FrigoDAO {

    public static final String TABLE_FRIGO="FRIGO";
    public static final String TABLE_EST_STOCKER="FRIGO_PRODUIT";

    public static final String COLUMN_ID="IDFrigo";
    public static final String COLUMN_ID_Gestionnaire="IDGestionnaire";
    public static final String COLUMN_NOM_FRIGO ="NomFrigo";
    public static final String COLUMN_LOCALISATION_FRIGO="Localisation";
    public static final String COLUMN_TEMP_FRIGO="TemperatureFrigo";
    public static final String COLUMN_QTE_STOCK="Qte_en_Stock";
    public static final String COLUMN_DATE_CREATION="dateCreation";

    public static final String CREATE_REQUEST_FRIGO=" CREATE TABLE  " + FrigoDAO.TABLE_FRIGO
            + "("  + FrigoDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FrigoDAO.COLUMN_NOM_FRIGO + " TEXT NOT NULL, "
            + FrigoDAO.COLUMN_LOCALISATION_FRIGO + " TEXT NOT NULL,"
            + FrigoDAO.COLUMN_TEMP_FRIGO + " FLOAT NOT NULL,"
            + FrigoDAO.COLUMN_DATE_CREATION + " TEXT NOT NULL,"
            + FrigoDAO.COLUMN_ID_Gestionnaire + " INTEGER NOT NULL," +
            "FOREIGN KEY("+ FrigoDAO.COLUMN_ID_Gestionnaire+ ") REFERENCES "+ GestionnaireDAO.TABLE_USER+ "(IDGestionnaire)" +");";

    public static final String CREATE_REQUEST_PRODUIT_FRIGO = "CREATE TABLE  "+ FrigoDAO.TABLE_EST_STOCKER
            + "("  + ProduitDAO.COLUMN_ID +  " INTEGER NOT NULL, "
            + FrigoDAO.COLUMN_QTE_STOCK + " INTEGER DEFAULT 0 ,"
            + FrigoDAO.COLUMN_ID + " INTEGER NOT NULL,"
            + " FOREIGN KEY ("+ FrigoDAO.COLUMN_ID + ") REFERENCES "+ FrigoDAO.TABLE_FRIGO +"("+ FrigoDAO.COLUMN_ID+ "),"
            + " FOREIGN KEY ("+ProduitDAO.COLUMN_ID + ") REFERENCES "+ ProduitDAO.TABLE_PRODUIT + "("+ProduitDAO.COLUMN_ID+ "),"
            + "PRIMARY KEY ("+ ProduitDAO.COLUMN_ID +" , "+ FrigoDAO.COLUMN_ID+ ")" + ");";

    public  static final String UPGRADE_REQUEST_FRIGO="DROP TABLE " + FrigoDAO.TABLE_FRIGO;
    public  static final String UPGRADE_REQUEST_PRODUIT_FRIGO="DROP TABLE " + FrigoDAO.TABLE_EST_STOCKER;

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public FrigoDAO(Context context){this.context=context;}

    public FrigoDAO openWritable(){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
        //dbHelper.onUpgrade(db,1,2);
        return this;
    }

    public FrigoDAO openReadable(){
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        db.close();
        dbHelper.close();
    }

    public long createFrigo(Frigo f){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NOM_FRIGO,f.getNomFrigo());
        cv.put(COLUMN_LOCALISATION_FRIGO,f.getLocalisationFrigo());
        cv.put(COLUMN_TEMP_FRIGO,f.getTemperature());
        cv.put(COLUMN_DATE_CREATION,f.getDateCreationFrigo().toString());
        cv.put(COLUMN_ID_Gestionnaire,f.getIdGestionnaire());
        return db.insert(TABLE_FRIGO,null,cv);
    }

    public long createEstStocker(int IDProduit, int IDFrigo, int qte){
        ContentValues cv = new ContentValues();
        cv.put(FrigoDAO.COLUMN_ID,IDFrigo);
        cv.put(ProduitDAO.COLUMN_ID,IDProduit);
        cv.put(COLUMN_QTE_STOCK,qte);
        return db.insert(TABLE_EST_STOCKER,null,cv);
    }

    public Cursor getFrigoCursorById(int frigoID) {
        Cursor c = db.query(TABLE_FRIGO, null, COLUMN_ID + "=" + frigoID, null, null, null, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            return c;
        }
        else
            return null;
    }

    public Cursor getFrigo(){
        Cursor c = db.rawQuery("SELECT " + FrigoDAO.COLUMN_ID + " , " + COLUMN_NOM_FRIGO + " FROM " + FrigoDAO.TABLE_FRIGO,null);
        if(c.getCount()>0){
            c.moveToFirst();
            return c;
        }else
            return null;
    }

    public static Frigo cursorToFrigo(Cursor c){
        Frigo f =new Frigo();
        f.setIdFrigo(c.getInt(c.getColumnIndex(COLUMN_ID)));
        f.setNomFrigo(c.getString(c.getColumnIndex(COLUMN_NOM_FRIGO)));
        f.setLocalisationFrigo(c.getString(c.getColumnIndex(COLUMN_LOCALISATION_FRIGO)));
        f.setTemperature(c.getFloat(c.getColumnIndex(COLUMN_TEMP_FRIGO)));
        f.setDateCreationFrigo(c.getString(c.getColumnIndex(COLUMN_DATE_CREATION)));
        f.setIdGestionnaire(c.getInt(c.getColumnIndex(COLUMN_ID_Gestionnaire)));
        return f;
    }

    public Frigo getFrigoById(int id){

        Cursor c =getFrigoCursorById(id);

        if(c!=null){
            return cursorToFrigo(c);
        }else {
            return null;
        }
    }

    public int update(Frigo f){

        ContentValues cv =new ContentValues();
        cv.put(COLUMN_NOM_FRIGO,f.getNomFrigo());
        cv.put(COLUMN_LOCALISATION_FRIGO,f.getLocalisationFrigo());
        cv.put(COLUMN_TEMP_FRIGO,f.getTemperature());
        cv.put(COLUMN_DATE_CREATION,f.getDateCreationFrigo().toString());
        cv.put(COLUMN_ID_Gestionnaire,f.getIdGestionnaire());
        return db.update(TABLE_FRIGO,cv, COLUMN_ID + "=" + f.getIdFrigo(),null);
    }
    public void deleteProduit(Frigo f) {
        db.delete(TABLE_FRIGO, COLUMN_ID + "=" + f.getIdFrigo(), null);
    }

    public void misAjourStock(int idfrgo,int idproduit, int qte){
        //Cursor c = db.rawQuery("UPDATE " + FrigoDAO.TABLE_EST_STOCKER + " SET " + FrigoDAO.COLUMN_QTE_STOCK + " = " + qte +" WHERE "+ FrigoDAO.COLUMN_ID +"= ? and " + ProduitDAO.COLUMN_ID +"= ?" , new String[]{idfrgo,idproduit});
        ContentValues cv = new ContentValues();
        cv.put(FrigoDAO.COLUMN_QTE_STOCK, qte);
        String where = Integer.toString(idfrgo)+ " = "+ Integer.toString(idproduit);
        db.update(FrigoDAO.TABLE_EST_STOCKER,cv, where, new String[]{});
    }
    public int getQteStock(int IDFrigo, int IDProduit){
        Cursor c = db.rawQuery("SELECT " + FrigoDAO.COLUMN_QTE_STOCK + " FROM " + FrigoDAO.TABLE_EST_STOCKER +
                " WHERE " + FrigoDAO.COLUMN_ID + " = ? AND " + ProduitDAO.COLUMN_ID + " = ?", new String[]{Integer.toString(IDFrigo),Integer.toString(IDProduit)});
        if(c!=null) {
            return c.getInt(c.getColumnIndex(FrigoDAO.COLUMN_QTE_STOCK));
        }else
            return 0;
    }

    public Boolean checkEstStocker(int IDFrigo, int IDProduit){

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_EST_STOCKER + " WHERE "+ COLUMN_ID +"= ? AND "
                + ProduitDAO.COLUMN_ID + " = ? ", new String[]{Integer.toString(IDFrigo),Integer.toString(IDProduit)});
        if(c.getCount()>0){
            return true;
        }else{return false;}
    }
}
