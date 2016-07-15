package bt.be.bddapplication.Controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bt.be.bddapplication.R;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
    }

    public void gotoCheckStock(View v){
        Intent checkStokIntent = new Intent(ConfigActivity.this, CheckStockActivity.class);
        startActivity(checkStokIntent);
    }
}
