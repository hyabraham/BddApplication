package bt.be.bddapplication.Controler;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
        if (extra != null) {
            String leMail = extra.getString("Mail");
            TextView t = (TextView) findViewById(R.id.txt_userName);
            t.setText(leMail);
        }
    }

    public void userRegister(View v) {


        Intent registerIntent = new Intent(MainActivity.this, SubscribtionActivity.class);
        startActivity(registerIntent);

    }

    public String maLocalisation() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ArrayList<LocationProvider> providers = new ArrayList<LocationProvider>();
        ArrayList<String> names = (ArrayList<String>) locationManager.getProviders(true);

        for (String name : names) {
            providers.add(locationManager.getProvider(name));
        }
        Criteria critere = new Criteria();
        critere.setAccuracy(Criteria.ACCURACY_FINE);
        critere.setAltitudeRequired(true);
        critere.setBearingRequired(true);
        critere.setCostAllowed(false);
        critere.setPowerRequirement(Criteria.POWER_HIGH);
        critere.setSpeedRequired(true);
        Log.i("Provider", providers.toString());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return null;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.e("LOCATION_KNOW => ", "LATITUDE: " + location.getLatitude() + "; LONGITUDE: " + location.getLongitude());
        String localisation = "Latitude : " + location.getLatitude() + "," + " Longitude :" + location.getLongitude();

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.e("LOCATION_UPDATE => ", "LATITUDE: " + location.getLatitude() + "; LONGITUDE: " + location.getLongitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });
        return localisation;
    }

    public void logUser(View v) {
        // String maLocation=maLocalisation();
        String mdpToCompare = ""; //Mot de passe à comparer avec celui de la base de donnée.
        EditText email = (EditText) findViewById(R.id.txt_userName);
        EditText password = (EditText) findViewById(R.id.txt_userPassword);
        String leMail = email.getText().toString();
        String leMdp = password.getText().toString();
        int idgestionnaire = 0;
        try {
            Md5 mdpToSave = new Md5(leMdp);
            mdpToCompare = mdpToSave.getCode();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Gestionnaire u = new Gestionnaire(leMail, mdpToCompare);

        GestionnaireDAO dao = new GestionnaireDAO(this);
        dao = dao.openWritable();
        if (dao.checkUserByMail(leMail, mdpToCompare)) {
            Intent homeActivityIntent = new Intent(MainActivity.this, HomeActivity.class);
            Cursor c = dao.getGestionaire(leMail);
            Gestionnaire g = new Gestionnaire(c.getInt(c.getColumnIndex(GestionnaireDAO.COLUMN_ID)),
                    c.getString(c.getColumnIndex(GestionnaireDAO.COLUMN_FIRST_NAME)),
                    c.getString(c.getColumnIndex(GestionnaireDAO.COLUMN_LAST_NAME)),
                    c.getString(c.getColumnIndex(GestionnaireDAO.COLUMN_EMAIL)),
                    c.getString(c.getColumnIndex(GestionnaireDAO.COLUMN_PASSWORD)));
            SharedPreferences pref_Id_Gestionnaire = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = pref_Id_Gestionnaire.edit();
            editor.putInt("id", g.getId());
            editor.apply();
            String messagePosition = maLocalisation();
            Log.e("Position", "" + messagePosition);
            Toast.makeText(MainActivity.this, messagePosition, Toast.LENGTH_SHORT).show();
            startActivity(homeActivityIntent);
        } else {
            Toast.makeText(MainActivity.this, "E-mail and Password not matching", Toast.LENGTH_SHORT).show();

        }
        dao.close();
    }
}
