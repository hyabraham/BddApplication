package bt.be.bddapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rome03 on 4/07/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="maDBStock";
    public static final int DB_VERSION=1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(GestionnaireDAO.CREATE_REQUEST);
        db.execSQL(FournisseurDAO.CREATE_REQUEST);
        db.execSQL(FrigoDAO.CREATE_REQUEST_FRIGO);
        db.execSQL(ProduitDAO.CREATE_REQUEST);
        db.execSQL(FrigoDAO.CREATE_REQUEST_PRODUIT_FRIGO);
        db.execSQL(Mouvement_Stock_DAO.CREATE_REQUEST_MOUVEMENT_STOCK);
        db.execSQL(Mouvement_Stock_DAO.CREATE_REQUEST_PRODUIT_MOUVEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(GestionnaireDAO.UPGRADE_REQUEST);
        db.execSQL(FournisseurDAO.UPGRADE_REQUEST);
        db.execSQL(FrigoDAO.UPGRADE_REQUEST_FRIGO);
        db.execSQL(FrigoDAO.UPGRADE_REQUEST_PRODUIT_FRIGO);
        db.execSQL(ProduitDAO.UPGRADE_REQUEST);
        db.execSQL(Mouvement_Stock_DAO.UPGRADE_REQUEST_MOUVEMENT_STOCK);
        db.execSQL(Mouvement_Stock_DAO.UPGRADE_REQUEST_PRODUIT_MOUVEMENT);
        onCreate(db);
    }
}
