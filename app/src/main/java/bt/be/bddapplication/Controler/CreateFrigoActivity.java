package bt.be.bddapplication.Controler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bt.be.bddapplication.R;
import bt.be.bddapplication.Utilitaires.Main;
import bt.be.bddapplication.db.FrigoDAO;
import bt.be.bddapplication.db.GestionnaireDAO;
import bt.be.bddapplication.model.Frigo;

public class CreateFrigoActivity extends AppCompatActivity implements MyWeather.IWeather {

    Bundle extra;
    TextView tvTemp; //TextView devant contenir la température
    private MyWeather myWeather;
    private Main mainWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_frigo);
        tvTemp = (TextView) findViewById(R.id.tv_temperature_ambiante);
        testAPIWeather();

    }

    public void creerFrigo(Frigo f) {
        FrigoDAO dao = new FrigoDAO(this);
        dao.openWritable();
        dao.createFrigo(f);
        Log.i("CREATION Frigo ", "OK");
        dao.close();
    }

    public void createFridge(View v) {


        String messageDateVide = "Please enter a date";
        String messageTemperetureVide = "Please enter et Temperature";
        String messageLocalisationVide = "Please enter a localisation";
        String messageLibelleVide = "Please enter a wording";

        EditText libelleFrigo = (EditText) findViewById(R.id.txt_libelle_frigo);
        EditText localisationFrigo = (EditText) findViewById(R.id.txt_localisation_frigo);
        EditText temperatureFrigo = (EditText) findViewById(R.id.txt_temperature_frigo);
        EditText dateCreationFrigo = (EditText) findViewById(R.id.txt_date_creation_frigo);
        String libelle, localisation, dateCreation, temp;
        float temperature;

        libelle = libelleFrigo.getText().toString();
        localisation = localisationFrigo.getText().toString();
        dateCreation = dateCreationFrigo.getText().toString();
        temp = temperatureFrigo.getText().toString();
        temperature = Float.parseFloat(temp);
        GestionnaireDAO dao1 = new GestionnaireDAO(this);

        if (!libelle.equals("")) {
            if (!localisation.equals("")) {
                if (!temp.equals("")) {
                    if (dateCreation != "") {

                        SharedPreferences prefs_ID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        int idGest = prefs_ID.getInt("id", 0);
                        Log.e("ID_GEST", Integer.toString(idGest));
                        Frigo f = new Frigo(libelle, localisation, temperature, new Date(dateCreation), idGest);

                        creerFrigo(f);

                        libelleFrigo.setText("");
                        localisationFrigo.setText("");
                        temperatureFrigo.setText("");
                        dateCreationFrigo.setText("");
                    } else {
                        Toast.makeText(CreateFrigoActivity.this, messageDateVide, Toast.LENGTH_SHORT).show();
                        dateCreationFrigo.setBackgroundColor(Color.RED);
                    }
                } else {
                    Toast.makeText(CreateFrigoActivity.this, messageTemperetureVide, Toast.LENGTH_SHORT).show();
                    temperatureFrigo.setBackgroundColor(Color.RED);
                }
            } else {
                Toast.makeText(CreateFrigoActivity.this, messageLocalisationVide, Toast.LENGTH_SHORT).show();
                localisationFrigo.setBackgroundColor(Color.RED);
            }
        } else {
            Toast.makeText(CreateFrigoActivity.this, messageLibelleVide, Toast.LENGTH_SHORT).show();
            libelleFrigo.setBackgroundColor(Color.RED);
        }
    }

    public void cancelCreateFridge(View v) {
        Intent homeIntent = new Intent(CreateFrigoActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }

    @Override
    public String printValues(String s) {
        JSONObject reader = null;
        try {

            reader = new JSONObject(s);
            JSONObject main = reader.getJSONObject("main");
            mainWeather = Main.hydrate(main);
            double temperature = convertF(mainWeather.getTemp());
            tvTemp.setText(tvTemp.getText() + " " + temperature + "°C Tenez en compte pour la température du frigo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mainWeather.toString();
    }

    public void testAPIWeather() {
        myWeather = new MyWeather(this);
        myWeather.execute("brussels", "be");
    }

    private Double convertF(Double temp) {
        temp = (temp - 273.15);
        return temp;
    }
}
