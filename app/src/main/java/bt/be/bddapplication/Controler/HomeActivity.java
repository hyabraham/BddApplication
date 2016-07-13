package bt.be.bddapplication.Controler;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
       }

    public void gotoProvider(View v) {

        Intent providerIntent = new Intent(HomeActivity.this, CreateProviderActivity.class);
        startActivity(providerIntent);
    }
    public  void gotoFridge(View v){

        Intent fridgeIntent = new Intent(HomeActivity.this, CreateFrigoActivity.class);
        startActivity(fridgeIntent);
    }
    public void gotoProduct(View v){
        Intent productIntent = new Intent(HomeActivity.this, CreateProductActivity.class);
        startActivity(productIntent);
    }
    public void gotoMouvementStock(View v){
        Intent mouvementStockIntent = new Intent(HomeActivity.this, MouvementStockActivity.class);
        startActivity(mouvementStockIntent);
    }
}
