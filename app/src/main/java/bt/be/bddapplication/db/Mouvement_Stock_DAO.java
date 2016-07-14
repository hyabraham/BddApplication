package bt.be.bddapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bt.be.bddapplication.model.Mouvement_Stock;
import bt.be.bddapplication.model.Produit;

/**
 * Created by rome03 on 7/07/2016.
 */
public class Mouvement_Stock_DAO {

    public static final String TABLE_MOUVEMENT_STOCK="MOUVEMENT_STOCK";
    public static final String TABLE_PORTE_SUR="PRODUIT_MOUVEMENT_STOCK";

    public static final String COLUMN_ID_MOUVEMENT="IDMouvement";
    public static final String COLUMN_TYPE="TypeMouvement";
    public static final String COLUMN_DESCRIPTION="DescriptionMouvement";
    public static final String COLUMN_DATE_MOUVEMENT="DateMouvement";
    public static final String COLUMN_QTE_MOUVEMENT="QteMvt";

    public static final String CREATE_REQUEST_MOUVEMENT_STOCK="CREATE TABLE "+ Mouvement_Stock_DAO.TABLE_MOUVEMENT_STOCK
            +"("+ Mouvement_Stock_DAO.COLUMN_ID_MOUVEMENT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Mouvement_Stock_DAO.COLUMN_TYPE + " TEXT NOT NULL, "
            + Mouvement_Stock_DAO.COLUMN_DESCRIPTION + " TEXT NOT NULL,"
            + Mouvement_Stock_DAO.COLUMN_DATE_MOUVEMENT + " TEXT NOT NULL);";

    public static final String CREATE_REQUEST_PRODUIT_MOUVEMENT="CREATE TABLE "+ Mouvement_Stock_DAO.TABLE_PORTE_SUR
            + "(" + Mouvement_Stock_DAO.COLUMN_QTE_MOUVEMENT + " INTEGER NOT NULL ,"
            + ProduitDAO.COLUMN_ID +" INTEGER, "
            + Mouvement_Stock_DAO.COLUMN_ID_MOUVEMENT + " INTEGER,"
            + "FOREIGN KEY ("+ ProduitDAO.COLUMN_ID +") REFERENCES  "+ ProduitDAO.TABLE_PRODUIT + "(" +ProduitDAO.COLUMN_ID +"),"
            +" FOREIGN KEY ("+ Mouvement_Stock_DAO.COLUMN_ID_MOUVEMENT +") REFERENCES "+ Mouvement_Stock_DAO.TABLE_MOUVEMENT_STOCK + "(" + Mouvement_Stock_DAO.COLUMN_ID_MOUVEMENT + ")"
            + "PRIMARY KEY ("+ ProduitDAO.COLUMN_ID +" ,"+ Mouvement_Stock_DAO.COLUMN_ID_MOUVEMENT+ ")" + ");";

    public  static final String UPGRADE_REQUEST_MOUVEMENT_STOCK="DROP TABLE " + Mouvement_Stock_DAO.TABLE_MOUVEMENT_STOCK;
    public  static final String UPGRADE_REQUEST_PRODUIT_MOUVEMENT="DROP TABLE " + Mouvement_Stock_DAO.TABLE_PORTE_SUR;

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public Mouvement_Stock_DAO(Context context){this.context=context;}

    public Mouvement_Stock_DAO openWritable(){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();

        //dbHelper.onUpgrade(db, 3, 4);
        return this;
    }

    public Mouvement_Stock_DAO openReadable(){
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        db.close();
        dbHelper.close();
    }

    // La méthode "createMouvementOnProduit" permet de créer un mouvement de stock en insérant les données dans les tables Mouvement_Stock et Porte_Sur

    public long createMouvementOnProduit(Mouvement_Stock mouvementStock, Produit produit,int qte){
        long res1 = this.createMouvement(mouvementStock);
        long res2 = this.createPorteSur(mouvementStock, produit,qte);

        return res1+res2;
    }
    // La méthode "createMouvement" permet d'inserer les donnée dans la table "Mouvement_Stock
    public long createMouvement(Mouvement_Stock mouvementStock){
        ContentValues cv1 = new ContentValues();

        cv1.put(COLUMN_TYPE,mouvementStock.getTypeMouvement());
        cv1.put(COLUMN_DESCRIPTION,mouvementStock.getJustificationMouvement());
        cv1.put(COLUMN_DATE_MOUVEMENT,mouvementStock.getDateMouvement().toString());
        return db.insert(TABLE_MOUVEMENT_STOCK, null, cv1);

    }
    // La méthode "createPorteSur" permet d'inserer les donnée dans la table "Porte_Sur"
    public long createPorteSur(Mouvement_Stock mouvementStock, Produit produit,int qte){
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_MOUVEMENT,mouvementStock.getIdMouvement());
        cv.put(ProduitDAO.COLUMN_ID,produit.getIdProduit());
        cv.put(COLUMN_QTE_MOUVEMENT,qte);
        return db.insert(TABLE_PORTE_SUR,null,cv);
    }

    public static Mouvement_Stock cursorToMovement_Stock(Cursor c){
        Mouvement_Stock ms =new Mouvement_Stock();
        ms.setIdMouvement(c.getInt(c.getColumnIndex(COLUMN_ID_MOUVEMENT)));
        ms.setTypeMouvement(c.getString(c.getColumnIndex(COLUMN_TYPE)));
        ms.setJustificationMouvement(c.getString(c.getColumnIndex(COLUMN_DESCRIPTION)));
        ms.setDateMouvement(c.getString(c.getColumnIndex(COLUMN_DATE_MOUVEMENT)));

        return ms;
    }


}
