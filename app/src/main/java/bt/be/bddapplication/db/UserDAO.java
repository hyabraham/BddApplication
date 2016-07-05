package bt.be.bddapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import bt.be.bddapplication.model.User;

/**
 * Created by rome03 on 4/07/2016.
 */
public class UserDAO {

    public static final String TABLE_USER="GESTIONNAIRE";

    public static final String COLUMN_ID="IDGestionnaire";
    public static final String COLUMN_LAST_NAME="prenom";
    public static final String COLUMN_FIRST_NAME="nom";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_PASSWORD="mdp";

    public static final String CREATE_REQUEST="CREATE TABLE "+ UserDAO.TABLE_USER
            +"("+UserDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UserDAO.COLUMN_FIRST_NAME + " TEXT NOT NULL, "
            + UserDAO.COLUMN_LAST_NAME + " TEXT NOT NULL,"
            + UserDAO.COLUMN_EMAIL + " TEXT NOT NULL,"
            + UserDAO.COLUMN_PASSWORD + " TEXT NOT NULL" +");";

    public  static final String UPGRADE_REQUEST="DROP TABLE " + UserDAO.TABLE_USER;

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public UserDAO(Context context){
        this.context=context;
    }

    public UserDAO openWritable(){
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
        //dbHelper.onCreate(db);
       //dbHelper.onUpgrade(db,1,2);
        return this;
    }

    public UserDAO openReadable(){
        dbHelper=new DBHelper(context);
        db=dbHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        db.close();
        dbHelper.close();
    }

    public long createUser(User u){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_FIRST_NAME,u.getPrenom());
        cv.put(COLUMN_LAST_NAME,u.getNom());
        cv.put(COLUMN_EMAIL,u.getEmail());
        cv.put(COLUMN_PASSWORD,u.getMdp());
        return db.insert(TABLE_USER,null,cv);
    }

    public void deleteUser(User u) {
        db.delete(TABLE_USER, COLUMN_ID + "=" + u.getId(), null);
    }

    public Cursor getUserCursorById(int userId) {
        Cursor c = db.query(TABLE_USER, null, COLUMN_ID + "=" + userId, null, null, null, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            return c;
        }
        else
            return null;
    }
//Permet de savoir si un user de ce mail existe deja dans la base de donnée.
    public Boolean getUserByMail(String leMail){
        //Cursor c = db.query(TABLE_USER, null, COLUMN_EMAIL + "=" + leMail, null, null, null, null);
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE "+ COLUMN_EMAIL +"= ?", new String[]{leMail});
        if(c.getCount()>0){
        return false;
        }else{return true;}
    }
// cette péthode permet d'authentifier le user
    public Boolean checkUserByMail(String monMail,String monPassword){
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE "+ COLUMN_EMAIL +"= ? and " + COLUMN_PASSWORD +"= ?" , new String[]{monMail,monPassword});
        if(c.getCount()>0){
            return true;
        }else{return false;}
    }

    public static User cursorToUser(Cursor c){
        User u =new User();
        u.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        u.setPrenom(c.getString(c.getColumnIndex(COLUMN_FIRST_NAME)));
        u.setNom(c.getString(c.getColumnIndex(COLUMN_LAST_NAME)));
        u.setEmail(c.getString(c.getColumnIndex(COLUMN_EMAIL)));
        u.setMdp(c.getString(c.getColumnIndex(COLUMN_PASSWORD)));
        return u;
    }

    public User getUserById(int id){

        Cursor c =getUserCursorById(id);

        if(c!=null){
            return cursorToUser(c);
        }else {
            return null;
        }
    }

    public int update(User u){

        ContentValues cv =new ContentValues();
        cv.put(COLUMN_FIRST_NAME,u.getPrenom());
        cv.put(COLUMN_LAST_NAME,u.getNom());
        cv.put(COLUMN_EMAIL,u.getEmail());
        cv.put(COLUMN_PASSWORD,u.getMdp());
        return db.update(TABLE_USER,cv, COLUMN_ID + "=" + u.getId(),null);
    }

    public void delete(User u){

        db.delete(TABLE_USER,COLUMN_ID + "=" + u.getId(),null);
    }

}
