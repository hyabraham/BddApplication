package bt.be.bddapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rome03 on 4/07/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="maDB";
    public static final int DB_VERSION=1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(GestionnaireDAO.CREATE_REQUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(GestionnaireDAO.UPGRADE_REQUEST);
        onCreate(db);
    }
}
