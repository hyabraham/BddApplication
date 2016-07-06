package bt.be.bddapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
    public static final String COLUMN_QTE_STOCK="qteSeuil";
    public static final String COLUMN_DATE_LIVRAISON="dateLivraison";
    public static final String COLUMN_ID_FOURNISSEUR="IDFournisseur";

    public static final String CREATE_REQUEST="CREATE TABLE "+ ProduitDAO.TABLE_PRODUIT
            +"("+ ProduitDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ProduitDAO.COLUMN_LIBELLE + " TEXT NOT NULL, "
            + ProduitDAO.COLUMN_DESCRIPTION + " TEXT NOT NULL,"
            + ProduitDAO.COLUMN_QTE_SEUIL + " INTEGER NOT NULL,"
            + ProduitDAO.COLUMN_DATE_LIVRAISON + " TEXT NOT NULL,"
            + ProduitDAO.COLUMN_ID_FOURNISSEUR + " INTEGER NOT NULL FOREIGN KEY REFERENCES FOURNISSEUR (IDFournisseur)" +");";

    public  static final String UPGRADE_REQUEST="DROP TABLE " + ProduitDAO.TABLE_PRODUIT;


}
