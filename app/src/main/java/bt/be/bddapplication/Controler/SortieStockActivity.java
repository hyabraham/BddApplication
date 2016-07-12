package bt.be.bddapplication.Controler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import bt.be.bddapplication.R;

public class SortieStockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortie_stock);
    }

    public String getSelectionFrigo() {
        Spinner monSpinner = (Spinner) findViewById(R.id.spiner_frigo_mouvement);
        return monSpinner.getSelectedItem().toString();
    }

    public String getSelectionProduit() {
        Spinner monSpinner = (Spinner) findViewById(R.id.spiner_mouvement_produit);
        return monSpinner.getSelectedItem().toString();
    }
}
