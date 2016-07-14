package bt.be.bddapplication.Controler;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.FournisseurDAO;
import bt.be.bddapplication.model.Fournisseur;

public class CreateProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_provider);
    }

    // Cette méthode permet juste de vérivier si le mail contient un "@"
    public boolean verifMail(String sMail) {
        boolean result = false;
        for (int i = 1; i <= sMail.length() - 1; i++) {
            if (sMail.charAt(i) == '@') {
                result = true;
            }
        }
        return result;
    }

    public void onCancelProvider(View v) {
        Intent homeIntent = new Intent(CreateProviderActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }

    public void createProvider(View v) {
        String messageMailFail = "Please enter a valid adress mail";
        String messageEmptyField = "This field can\'t be empty";
        EditText name = (EditText) findViewById(R.id.txt_provider_name);
        EditText adress = (EditText) findViewById(R.id.txt_provider_adress);
        EditText email = (EditText) findViewById(R.id.txt_provider_mail);

        String adressProvider, nameProvider, mailProvider;
        nameProvider = name.getText().toString();
        adressProvider = adress.getText().toString();
        mailProvider = email.getText().toString();

        if (nameProvider != "") {
            if (adressProvider != "") {
                if (mailProvider != "") {
                    if (verifMail(mailProvider)) {
                        Fournisseur f = new Fournisseur(nameProvider, mailProvider, adressProvider);
                        FournisseurDAO dao = new FournisseurDAO(this);
                        dao.openWritable();
                        dao.createFournisser(f);
                        Log.i("CREATION PROVIDER ", "OK");
                        dao.close();
                        name.setText("");
                        adress.setText("");
                        email.setText("");
                    } else {
                        Toast.makeText(CreateProviderActivity.this, messageMailFail, Toast.LENGTH_SHORT).show();
                        adress.setBackgroundColor(Color.RED);
                        adress.setHint("Enter a correct email adress ");
                    }
                } else {
                    Toast.makeText(CreateProviderActivity.this, messageEmptyField, Toast.LENGTH_SHORT).show();
                    adress.setBackgroundColor(Color.RED);
                    adress.setHint(messageEmptyField);
                }
            } else {
                Toast.makeText(CreateProviderActivity.this, messageEmptyField, Toast.LENGTH_SHORT).show();
                adress.setBackgroundColor(Color.RED);
                adress.setHint(messageEmptyField);
            }
        } else {
            Toast.makeText(CreateProviderActivity.this, messageEmptyField, Toast.LENGTH_SHORT).show();
            adress.setBackgroundColor(Color.RED);
            adress.setHint(messageEmptyField);
        }

    }
}
