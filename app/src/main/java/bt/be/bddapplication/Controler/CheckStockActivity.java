package bt.be.bddapplication.Controler;

import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.FrigoDAO;
import bt.be.bddapplication.db.ProduitDAO;

public class CheckStockActivity extends AppCompatActivity {
    ProgressBar myprogressBar;
    TextView progressingTextView;
    Handler progressHandler = new Handler();
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_stock);
        peuplageProduitSpinner();
        peuplageFrigoSpinner();
    }

    public String getSelectionSpinner(View v) {
        Spinner maSelection = (Spinner) v;
        return maSelection.getSelectedItem().toString();
    }

    public void peuplageProduitSpinner() {

        Spinner monSpinner = (Spinner) findViewById(R.id.spiner_produit_for_check);
        ProduitDAO daoProduit = new ProduitDAO(this);
        daoProduit.openWritable();
        Cursor c = daoProduit.getProduit();
        //cette boucle me petmet de recuperer les noms des Produits pour peupler mon spinner
        List<String> produit = new ArrayList<String>();
        if (c != null && c.getCount() > 0) {
            do {
                produit.add(c.getString(c.getColumnIndex(ProduitDAO.COLUMN_LIBELLE)));
            } while (c.moveToNext());
        }
        daoProduit.close();
        ArrayAdapter<String> dataProduitAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, produit);
        dataProduitAdapter .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(dataProduitAdapter );
    }

    public int recupIDProduit(String produit) {
        ProduitDAO daoProduit = new ProduitDAO(this);
        daoProduit.openWritable();
        Cursor c = daoProduit.getProduit();
        HashMap<String, Integer> produitHmap = new HashMap<>();
        if (c != null && c.getCount() > 0) {
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    produitHmap.put(c.getString(c.getColumnIndex(ProduitDAO.COLUMN_LIBELLE)),
                            c.getInt(c.getColumnIndex(ProduitDAO.COLUMN_ID)));
                } while (c.moveToNext());
            }
        }
        daoProduit.close();
        String monproduit = getSelectionSpinner(findViewById(R.id.spiner_produit_for_check));
        int idProduit = produitHmap.get(monproduit);
        Log.i("La clé est : ", "" + idProduit);
        return idProduit;
    }

    public void peuplageFrigoSpinner() {

        Spinner monSpinner = (Spinner) findViewById(R.id.spiner_frigo_for_check);
        FrigoDAO daoFrigo = new FrigoDAO(this);
        daoFrigo.openWritable();
        Cursor c = daoFrigo.getFrigo();
        //cette boucle me petmet de recuperer les noms des Frigos pour peupler mon spinner
        List<String> frigo = new ArrayList<String>();
        if (c != null && c.getCount() > 0) {
            do {
                frigo.add(c.getString(c.getColumnIndex(FrigoDAO.COLUMN_NOM_FRIGO)));
            } while (c.moveToNext());
        }
        daoFrigo.close();
        ArrayAdapter<String> dataProduitAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, frigo);
        dataProduitAdapter .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(dataProduitAdapter );
    }

    public int recupIDFrigo(String lefrigo) {
        FrigoDAO daoFrigo = new FrigoDAO(this);
        daoFrigo.openWritable();
        Cursor c = daoFrigo.getFrigo();
        HashMap<String, Integer> frigoHmap = new HashMap<>();
        if (c != null && c.getCount() > 0) {
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    frigoHmap.put(c.getString(c.getColumnIndex(FrigoDAO.COLUMN_NOM_FRIGO)),
                            c.getInt(c.getColumnIndex(FrigoDAO.COLUMN_ID)));
                } while (c.moveToNext());
            }
        }
        daoFrigo.close();
        String frigo = getSelectionSpinner(findViewById(R.id.spiner_frigo_for_check));
        int idFrigo = frigoHmap.get(frigo);
        Log.i("La clé est : ", "" + idFrigo);
        return idFrigo;
    }

    public void loadDashboard(final int qteStock){

        myprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressingTextView = (TextView) findViewById(R.id.progress_circle_text);

        new Thread(new Runnable() {
            public void run() {
                while (i < qteStock) {
                    i += 2;
                    progressHandler.post(new Runnable() {
                        public void run() {
                            myprogressBar.setProgress(i);
                            progressingTextView.setText("" + i + " ");
                        }
                    });
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void checkStock(View v){

        String monFrigo = getSelectionSpinner(findViewById(R.id.spiner_frigo_for_check));
        String monProduit = getSelectionSpinner(findViewById(R.id.spiner_produit_for_check));
        FrigoDAO daoFrigo = new FrigoDAO(this);
        daoFrigo.openWritable();
        int idFrigo = recupIDFrigo(monFrigo);
        int idProduit = recupIDProduit(monProduit);
        Log.i("ID FRIGO"," "+ idFrigo + "ID PRODUIT " + idProduit);
        if (daoFrigo.checkEstStocker(idFrigo, idProduit)){
            int qteStock = daoFrigo.getQteStock(idFrigo, idProduit);
            Log.e("QTE STOCK",""+qteStock);
            loadDashboard(qteStock);
        }else{
            Toast.makeText(CheckStockActivity.this, "This product does'nt exist in this frigde", Toast.LENGTH_SHORT).show();
        }


    }
}
