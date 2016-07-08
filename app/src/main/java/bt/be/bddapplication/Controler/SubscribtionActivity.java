package bt.be.bddapplication.Controler;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.GestionnaireDAO;
import bt.be.bddapplication.model.Gestionnaire;


public class SubscribtionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribtion);
    }

    public void userCancelRegister(View v){

        Intent cancelIntent=new Intent(SubscribtionActivity.this,MainActivity.class);
        startActivity(cancelIntent);
    }

    // Fonction verifMail permet de vérifier si l'adresse mail contient le caractère @
    public boolean verifMail(String sMail){
        boolean result= false;
        for (int i=1; i<=sMail.length()-1;i++){
            if (sMail.charAt(i)=='@'){
                result=true;
            }
        }
        return result;
    }


    public void userRegister(View v){

        String messageMailFail="Please enter a valid adress mail";
        String messagePasswordFailled="Password 1 is different to password 2";

        EditText fname=(EditText)findViewById(R.id.txt_userFirstName);
        EditText lname=(EditText)findViewById(R.id.txt_userLastName);
        EditText email=(EditText)findViewById(R.id.txt_userMail);
        EditText password1=(EditText)findViewById(R.id.txt_userPassword);
        EditText password2=(EditText)findViewById(R.id.txt_userRePassword);

        String firstName,lastName,mail,mdp1,mdp2;
        int id;
        String mdpSaved=""; // cette variable recupère le mot de passe en MD5 qui sera stocké en BDD
        firstName=fname.getText().toString();
        lastName=lname.getText().toString();
        mail=email.getText().toString();
        mdp1=password1.getText().toString();
        mdp2=password2.getText().toString();

        if (verifMail(mail)) {
            if(mdp1.equals(mdp2)) {
                try {
                    Md5 mdpToSave=new Md5(mdp1);
                    mdpSaved=mdpToSave.getCode();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                Gestionnaire u = new Gestionnaire(firstName, lastName, mail, mdpSaved);
                GestionnaireDAO dao = new GestionnaireDAO(this);
                dao = dao.openWritable();
                //Log.i("AVANT LE TEST", "SUIS OK AVANT TON TEST");
                if(dao.getUserByMail(mail)) {
                    dao.createUser(u);
                    Log.i("USER CREATED ", "OK");
                    //Log.i("LE MD5",mdpSaved);
                    dao.close();
                    //Les 3 lignes qui suivent me permettent de recuperer le mail pour l'afficher a la page de connexion
                    Intent intentSaveMail=new Intent(this,MainActivity.class);
                    intentSaveMail.putExtra("Mail",mail);
                    startActivity(intentSaveMail);
                }else{
                    Toast.makeText(SubscribtionActivity.this, "This mail adress is already exist please change", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(SubscribtionActivity.this, messagePasswordFailled, Toast.LENGTH_SHORT).show();
                password1.setBackgroundColor(Color.RED);
            }
            } else {
                Toast.makeText(SubscribtionActivity.this, messageMailFail, Toast.LENGTH_SHORT).show();
            }



    }


}
