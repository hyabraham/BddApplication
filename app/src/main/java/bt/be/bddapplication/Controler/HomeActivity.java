package bt.be.bddapplication.Controler;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.GestionnaireDAO;
import bt.be.bddapplication.model.Gestionnaire;

public class HomeActivity extends AppCompatActivity {

    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
            return ;
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
        Toast.makeText(HomeActivity.this, "Votre position : Latitude = " + location.getLatitude() + " Longitude = "+ location.getLongitude() , Toast.LENGTH_SHORT).show();
    }

    public void gotoProvider(View v) {

        Intent providerIntent = new Intent(HomeActivity.this, CreateProviderActivity.class);
        startActivity(providerIntent);
    }

    public void gotoFridge(View v) {

        Intent fridgeIntent = new Intent(HomeActivity.this, CreateFrigoActivity.class);
        startActivity(fridgeIntent);
    }

    public void gotoProduct(View v) {
        Intent productIntent = new Intent(HomeActivity.this, CreateProductActivity.class);
        startActivity(productIntent);
    }

    public void gotoMouvementStock(View v) {
        Intent mouvementStockIntent = new Intent(HomeActivity.this, MouvementStockActivity.class);
        startActivity(mouvementStockIntent);
    }
    public void gotoConfig(View v){
        Intent configIntent = new Intent(HomeActivity.this, ConfigActivity.class);
        startActivity(configIntent);
    }
}
