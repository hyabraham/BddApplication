package bt.be.bddapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bt.be.bddapplication.model.Produit;

/**
 * Created by rome03 on 6/07/2016.
 */
public class ProduitDAO {
    public static final String TABLE_PRODUIT="PRODUIT";
    public static final String TABLE_EST_STOCKER="FRIGO_PRODUIT";

    public static final String COLUMN_ID="IDProduit";
    public static final String COLUMN_ID_FRIGO="IDFrigo";
    public static final String COLUMN_LIBELLE="libelle";
    public static final String COLUMN_DESCRIPTION="description";
    public static final String COLUMN_QTE_SEUIL="qteSeuil";
    public static final String COLUMN_DATE_PEREMPTION="datePeremption";
    public static final String COLUMN_DATE_LIVRAISON="dateLivraison";
    public static final String COLUMN_ID_FOURNISSEUR="IDFournisseur";

    public static final String CREATE_REQUEST="CREATE TABLE "+ ProduitDAO.TABLE_PRODUIT
            +"("+ ProduitDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ProduitDAO.COLUMN_LIBELLE + " TEXT NOT NULL, "
            + ProduitDAO.COLUMN_DESCRIPTION + " TEXT NOT NULL,"
            + ProduitDAO.COLUMN_QTE_SEUIL + " INTEGER NOT NULL,"
            + ProduitDAO.COLUMN_DATE_LIVRAISON + " TEXT NOT NULL,"
            + ProduitDAO.COLUMN_DATE_PEREMPTION + " TEXT NOT NULL, "
            + ProduitDAO.COLUMN_ID_FOURNISSEUR + " INTEGER NOT NULL," +
            " FOREIGN KEY ("+ ProduitDAO.COLUMN_ID_FOURNISSEUR + ")REFERENCES  "+ FournisseurDAO.COLUMN_ID + "(IDFournisseur)" +");";

    public  static final String UPGRADE_REQUEST="DROP TABLE " + ProduitDAO.TABLE_PRODUIT;
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public ProduitDAO(Context context){this.context=context;}

    public ProduitDAO openWritable(){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
        //dbHelper.onUpgrade(db,1,2);
        return this;
    }

    public ProduitDAO openReadable(){
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        db.close();
        dbHelper.close();
    }

    public long createProduit(Produit p){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_LIBELLE,p.getLibelleProduit());
        cv.put(COLUMN_DESCRIPTION,p.getDescriptionProduit());
        cv.put(COLUMN_DATE_PEREMPTION,p.getDatePeremptionProduit().toString());
        cv.put(COLUMN_DATE_LIVRAISON,p.getDateLivraisonProduit().toString());
        cv.put(COLUMN_QTE_SEUIL,p.getQteSeuil());
        cv.put(COLUMN_ID_FOURNISSEUR,p.getIdFournisseur());
       // cv.put(COLUMN_ID_FOURNISSEUR,p.getFournisseurProduit().getIdFournisseur());
        return db.insert(TABLE_PRODUIT,null,cv);
    }



    public Cursor getProduitCursorById(int produitID) {
        Cursor c = db.query(TABLE_PRODUIT, null, COLUMN_ID + "=" + produitID, null, null, null, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            return c;
        }
        else
            return null;
    }

    public Cursor getProduit(){
        Cursor c = db.rawQuery("SELECT " + ProduitDAO.COLUMN_ID + " , " + COLUMN_LIBELLE + " FROM " + ProduitDAO.TABLE_PRODUIT,null);
        if(c.getCount()>0){
            c.moveToFirst();
            return c;
        }else
            return null;
    }

    public static Produit cursorToProduit(Cursor c){
        Produit p =new Produit();
        p.setIdProduit(c.getInt(c.getColumnIndex(COLUMN_ID)));
        p.setLibelleProduit(c.getString(c.getColumnIndex(COLUMN_LIBELLE)));
        p.setDescriptionProduit(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
        p.setQteSeuil(c.getInt(c.getColumnIndex(COLUMN_QTE_SEUIL)));
        p.setDateLivraisonProduit(c.getString(c.getColumnIndex(COLUMN_DATE_LIVRAISON)));
        p.setDatePeremptionProduit(c.getString(c.getColumnIndex(COLUMN_DATE_PEREMPTION)));
        p.setIdFournisseur(c.getInt(c.getColumnIndex(COLUMN_ID_FOURNISSEUR)));
        return p;
    }

    public Produit getProduitById(int id){

        Cursor c =getProduitCursorById(id);

        if(c!=null){
            return cursorToProduit(c);
        }else {
            return null;
        }
    }

    public int update(Produit p){

        ContentValues cv =new ContentValues();
        cv.put(COLUMN_LIBELLE,p.getLibelleProduit());
        cv.put(COLUMN_DESCRIPTION,p.getDescriptionProduit());
        cv.put(COLUMN_QTE_SEUIL,p.getQteSeuil());
        cv.put(COLUMN_DATE_LIVRAISON,p.getDateLivraisonProduit().toString());
        cv.put(COLUMN_DATE_PEREMPTION,p.getDatePeremptionProduit().toString());
        cv.put(COLUMN_ID_FOURNISSEUR,p.getIdFournisseur());
        return db.update(TABLE_PRODUIT,cv, COLUMN_ID + "=" + p.getIdProduit(),null);
    }
    public void deleteProduit(Produit p) {
        db.delete(TABLE_PRODUIT, COLUMN_ID + "=" + p.getIdProduit(), null);
    }


}
