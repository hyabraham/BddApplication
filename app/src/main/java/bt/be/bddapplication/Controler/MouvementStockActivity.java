package bt.be.bddapplication.Controler;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.FrigoDAO;
import bt.be.bddapplication.db.Mouvement_Stock_DAO;
import bt.be.bddapplication.db.ProduitDAO;
import bt.be.bddapplication.model.Mouvement_Stock;
import bt.be.bddapplication.model.Produit;

public class MouvementStockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortie_stock);
        PeuplageSpinnerType();
        peuplageSpinnerFrigo();
        peuplageSpinnerProduit();
    }


//    public String getSelectionFrigo() {
//        Spinner monfrigoSpinner = (Spinner) findViewById(R.id.spiner_frigo_mouvement);
//        return monfrigoSpinner.getSelectedItem().toString();
//    }
//    public String getSelectionProduit() {
//        Spinner monproduitSpinner = (Spinner) findViewById(R.id.spiner_mouvement_produit);
//        return monproduitSpinner.getSelectedItem().toString();
//    }
//    public String getSelectionTypeMouvement() {
//        Spinner montypeSpinner = (Spinner) findViewById(R.id.spinner_type_mouvement);
//        return montypeSpinner.getSelectedItem().toString();
//    }

    public String getSelectionSpinner(View v) {
        Spinner maSelection = (Spinner) v;
        return maSelection.getSelectedItem().toString();
    }

    public void peuplageSpinnerProduit() {

        Spinner monSpinner = (Spinner) findViewById(R.id.spiner_mouvement_produit);
        ProduitDAO daoProduit = new ProduitDAO(this);
        daoProduit.openWritable();
        Cursor c = daoProduit.getProduit();
        //cette boucle me petmet de recuperer les noms des founisseurs pour peupler mon spinner
        List<String> produit = new ArrayList<String>();
        if (c != null && c.getCount() > 0) {
            do {
                produit.add(c.getString(c.getColumnIndex(ProduitDAO.COLUMN_LIBELLE)));
            } while (c.moveToNext());
        }
        daoProduit.close();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, produit);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(dataAdapter);
    }

    public void PeuplageSpinnerType() {
        Spinner spinnerType = (Spinner) findViewById(R.id.spinner_type_mouvement);
        List<String> typeMopuvement = new ArrayList<String>();
        typeMopuvement.add("Entry of stock");
        typeMopuvement.add("Out of stock");
        ArrayAdapter<String> typeMouvementAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeMopuvement);
        typeMouvementAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(typeMouvementAdapter);
    }

    public void peuplageSpinnerFrigo() {

        Spinner frigoSpinner = (Spinner) findViewById(R.id.spiner_frigo_mouvement);
        FrigoDAO daoFrigo = new FrigoDAO(this);
        daoFrigo.openWritable();
        Cursor c = daoFrigo.getFrigo();
        //cette boucle me petmet de recuperer les noms des founisseurs pour peupler mon spinner
        List<String> frigo = new ArrayList<String>();
        if (c != null && c.getCount() > 0) {
            do {
                frigo.add(c.getString(c.getColumnIndex(FrigoDAO.COLUMN_NOM_FRIGO)));
            } while (c.moveToNext());
        }
        daoFrigo.close();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, frigo);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frigoSpinner.setAdapter(dataAdapter);
    }

    public int recupIDProduit(String produit) {
        ProduitDAO daoProduit = new ProduitDAO(this);
        daoProduit.openWritable();
        Cursor c = daoProduit.getProduit();
        HashMap<String, Integer> produitHmap = new HashMap<>();
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                produitHmap.put(c.getString(c.getColumnIndex(ProduitDAO.COLUMN_LIBELLE)),
                        c.getInt(c.getColumnIndex(ProduitDAO.COLUMN_ID)));
            } while (c.moveToNext());
        }
        daoProduit.close();
        int idProduit = produitHmap.get(getSelectionSpinner(findViewById(R.id.spiner_mouvement_produit)));
        return idProduit;
    }

    public int recupIDFrigo(String frigo) {
        FrigoDAO daoFrigo = new FrigoDAO(this);
        daoFrigo.openWritable();
        Cursor c = daoFrigo.getFrigo();
        HashMap<String, Integer> frigoHmap = new HashMap<>();
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                frigoHmap.put(c.getString(c.getColumnIndex(FrigoDAO.COLUMN_NOM_FRIGO)),
                        c.getInt(c.getColumnIndex(FrigoDAO.COLUMN_ID)));
            } while (c.moveToNext());
        }
        daoFrigo.close();
        int idFrigo = frigoHmap.get(getSelectionSpinner(findViewById(R.id.spiner_frigo_mouvement)));
        return idFrigo;
    }

    public void onCancelMouvement(View v) {

        Intent homeIntent = new Intent(MouvementStockActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }

    public void onValidateMouvement(View v) {

        EditText dateMouvement = (EditText) findViewById(R.id.txt_date_mouvement);
        EditText justification = (EditText) findViewById(R.id.txt_justification);
        EditText qteMouvement = (EditText) findViewById(R.id.txt_qte_mouvement);

        String justificationMouvement, dateDuMouvement, typeMouvement, frigo, produit, qteDuMouvement;
        int qteDemande;

        justificationMouvement = justification.getText().toString();
        dateDuMouvement = dateMouvement.getText().toString();
        qteDuMouvement = qteMouvement.getText().toString();
        typeMouvement = getSelectionSpinner(findViewById(R.id.spinner_type_mouvement));
        frigo = getSelectionSpinner(findViewById(R.id.spiner_frigo_mouvement));
        produit = getSelectionSpinner(findViewById(R.id.spiner_mouvement_produit));
        qteDemande = Integer.parseInt(qteDuMouvement);

        if (!dateDuMouvement.equals("")) {
            if (!justificationMouvement.equals("")) {
                if (!qteDuMouvement.equals("")) {
                    if (qteDemande > 0) {
                        Mouvement_Stock_DAO daoMouvement = new Mouvement_Stock_DAO(this);
                        ProduitDAO daoProduit = new ProduitDAO(this);
                        daoMouvement.openWritable();
                        daoProduit.openWritable();
                        if (typeMouvement.equals("Entry of stock")) { //entrée stock

                            FrigoDAO daoFrigo = new FrigoDAO(this);
                            daoFrigo.openWritable();
                            if (daoFrigo.checkEstStocker(recupIDFrigo(frigo), recupIDProduit(produit))) {
                                Log.i("Result CHeck Estocker", "" + daoFrigo.checkEstStocker(recupIDFrigo(frigo), recupIDProduit(produit)));
                                int qteMAJ = daoFrigo.getQteStock(recupIDFrigo(frigo), recupIDProduit(produit)) + qteDemande;
                                Log.e("qteMAJ", "" + qteMAJ);
                                daoFrigo.misAjourStock(recupIDFrigo(frigo), recupIDProduit(produit), qteMAJ);
                                Cursor cursorProduit = daoProduit.getProduitCursorById(recupIDProduit(produit));
                                Produit monProduit = ProduitDAO.cursorToProduit(cursorProduit);
                                Mouvement_Stock monMouvement = new Mouvement_Stock(typeMouvement, justificationMouvement, new Date(dateDuMouvement));
                                daoMouvement.createMouvement(monMouvement);
                                // daoMouvement.createPorteSur(monMouvement,monProduit,qteDemande);
                                daoFrigo.close();
                                Toast.makeText(MouvementStockActivity.this, "Mouvement succefull", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.i("ligne stok", "OK");
                                //daoFrigo.misAjourStock(recupIDFrigo(frigo),recupIDProduit(produit),qteDemande);
                                Cursor cursorProduit = daoProduit.getProduitCursorById(recupIDProduit(produit));
                                Produit monProduit = ProduitDAO.cursorToProduit(cursorProduit);
                                Log.i("produit recup", "" + monProduit.getIdProduit());
                                //Mouvement_Stock monMouvement = new Mouvement_Stock(typeMouvement,justificationMouvement,new Date(dateDuMouvement));
                                Mouvement_Stock monMouvement = new Mouvement_Stock();
                                monMouvement.setDateMouvement(dateDuMouvement);
                                monMouvement.setJustificationMouvement(justificationMouvement);
                                monMouvement.setTypeMouvement(typeMouvement);

                                Log.i("Mouvement", "" + monMouvement.getTypeMouvement() + " idMouvement " + monMouvement.getIdMouvement() + " date :" + monMouvement.getDateMouvement());
                                //daoMouvement.createMouvementOnProduit(monMouvement,monProduit,qteDemande);
                                daoMouvement.createMouvement(monMouvement);
                                Log.e("Creation Mouvement", "OK");
                                daoFrigo.createEstStocker(recupIDProduit(produit), recupIDFrigo(frigo), qteDemande);
                                daoFrigo.close();
                                Toast.makeText(MouvementStockActivity.this, "Mouvement succefull", Toast.LENGTH_SHORT).show();
                            }
                        } else { //cas de sortie en stock
                            FrigoDAO daoFrigo = new FrigoDAO(this);
                            daoFrigo.openWritable();
                            if (daoFrigo.checkEstStocker(recupIDFrigo(frigo), recupIDProduit(produit))) {
                                if (daoFrigo.getQteStock(recupIDFrigo(frigo), recupIDProduit(produit)) >= qteDemande) {
                                    int qteMAJ = daoFrigo.getQteStock(recupIDFrigo(frigo), recupIDProduit(produit)) - qteDemande;
                                    ;//sortie de stock la qte doit etre décrementé
                                    daoFrigo.misAjourStock(recupIDFrigo(frigo), recupIDProduit(produit), qteMAJ);
                                    Cursor cursorProduit = daoProduit.getProduitCursorById(recupIDProduit(produit));
                                    Produit monProduit = ProduitDAO.cursorToProduit(cursorProduit);
                                    Mouvement_Stock monMouvement = new Mouvement_Stock(typeMouvement, justificationMouvement, new Date(dateDuMouvement));
                                    // daoMouvement.createMouvementOnProduit(monMouvement, monProduit, qteDemande);
                                    daoMouvement.createMouvement(monMouvement);
                                    daoFrigo.close();
                                    Toast.makeText(MouvementStockActivity.this, "Mouvement succefull", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MouvementStockActivity.this, "No more Product. The actual stock is : " + daoFrigo.getQteStock(recupIDFrigo(frigo), recupIDProduit(produit)), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MouvementStockActivity.this, "This product does'nt exist in this frigde", Toast.LENGTH_SHORT).show();
                            }
                        }
                        daoMouvement.close();
                        daoProduit.close();
                    } else {
                        Toast.makeText(MouvementStockActivity.this, "The quatity must be great than 0", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MouvementStockActivity.this, "the quantity cannot be empty !!!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MouvementStockActivity.this, "Please enter the justification", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(MouvementStockActivity.this, "Please enter the date", Toast.LENGTH_SHORT).show();
        }

    }

}
