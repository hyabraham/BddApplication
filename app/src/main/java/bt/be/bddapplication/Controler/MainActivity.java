package bt.be.bddapplication.Controler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.UserDAO;
import bt.be.bddapplication.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        try {
            Md5 mdpToSave=new Md5(leMdp);
            mdpToCompare=mdpToSave.getCode();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User u = new User(leMail, mdpToCompare);
        UserDAO dao = new UserDAO(this);
        dao = dao.openWritable();
        if (dao.checkUserByMail(leMail, mdpToCompare)) {
            Intent homeActivityIntent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeActivityIntent);
        } else {
            Toast.makeText(MainActivity.this, "E-mail and Password not matching", Toast.LENGTH_SHORT).show();

        }
    }
}
