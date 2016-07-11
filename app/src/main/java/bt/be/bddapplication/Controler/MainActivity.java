package bt.be.bddapplication.Controler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.DBHelper;
import bt.be.bddapplication.db.GestionnaireDAO;
import bt.be.bddapplication.model.Gestionnaire;

public class MainActivity extends AppCompatActivity {


    Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        extra = intent.getExtras();
        if(extra!=null){
            String leMail= extra.getString("Mail");
            TextView t=(TextView)findViewById(R.id.txt_userName);
            t.setText(leMail);
        }
    }

    public void userRegister(View v) {

        Intent registerIntent = new Intent(MainActivity.this, SubscribtionActivity.class);
        startActivity(registerIntent);

    }

    public void logUser(View v) {
        String mdpToCompare=""; //Mot de passe à comparer avec celui de la base de donnée.
        EditText email = (EditText) findViewById(R.id.txt_userName);
        EditText password = (EditText) findViewById(R.id.txt_userPassword);
        String leMail = email.getText().toString();
        String leMdp = password.getText().toString();
        int idgestionnaire=0;
        try {
            Md5 mdpToSave=new Md5(leMdp);
            mdpToCompare=mdpToSave.getCode();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Gestionnaire u = new Gestionnaire(leMail, mdpToCompare);

        GestionnaireDAO dao = new GestionnaireDAO(this);
        dao = dao.openWritable();
        if (dao.checkUserByMail(leMail, mdpToCompare)) {
            Intent homeActivityIntent = new Intent(MainActivity.this, HomeActivity.class);
            Cursor c =dao.getGestionaire(leMail);
            Gestionnaire g =new Gestionnaire(c.getInt(c.getColumnIndex(GestionnaireDAO.COLUMN_ID)),
                    c.getString(c.getColumnIndex(GestionnaireDAO.COLUMN_FIRST_NAME)),
                    c.getString(c.getColumnIndex(GestionnaireDAO.COLUMN_LAST_NAME)),
                    c.getString(c.getColumnIndex(GestionnaireDAO.COLUMN_EMAIL)),
                    c.getString(c.getColumnIndex(GestionnaireDAO.COLUMN_PASSWORD)));
            SharedPreferences pref_Id_Gestionnaire= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor=pref_Id_Gestionnaire.edit();
            editor.putInt("id",g.getId());
            editor.apply();
            startActivity(homeActivityIntent);
        } else {
            Toast.makeText(MainActivity.this, "E-mail and Password not matching", Toast.LENGTH_SHORT).show();

        }
        dao.close();
    }
}
