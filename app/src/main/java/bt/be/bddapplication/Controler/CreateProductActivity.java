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
import bt.be.bddapplication.db.FournisseurDAO;
import bt.be.bddapplication.db.GestionnaireDAO;
import bt.be.bddapplication.db.ProduitDAO;
import bt.be.bddapplication.model.Produit;

public class CreateProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        peuplageSpinner();

    }

    public void peuplageSpinner() {

        Spinner monSpinner = (Spinner) findViewById(R.id.spiner_fournisseur_produit);
        FournisseurDAO daoFournisseur = new FournisseurDAO(this);
        daoFournisseur.openWritable();
        Cursor c = daoFournisseur.getFournisseur();
        //cette boucle me petmet de recuperer les noms des founisseurs pour peupler mon spinner
        List<String> fournisseur = new ArrayList<String>();
        if (c != null && c.getCount() > 0) {
            do {
                fournisseur.add(c.getString(c.getColumnIndex(FournisseurDAO.COLUMN_NAME)));
            } while (c.moveToNext());
        }
        daoFournisseur.close();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fournisseur);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(dataAdapter);
    }

    public void creerProduit(Produit p) {
        ProduitDAO dao = new ProduitDAO(this);
        dao.openWritable();
        dao.createProduit(p);
        Log.i("Produit ", "OK");
        dao.close();
    }

    public String selectionList() {
        Spinner monSpinner = (Spinner) findViewById(R.id.spiner_fournisseur_produit);
        return monSpinner.getSelectedItem().toString();
    }

    public int recupIDFournisseur() {
        FournisseurDAO daoFournisseur = new FournisseurDAO(this);
        daoFournisseur.openWritable();
        Cursor c = daoFournisseur.getFournisseur();
        HashMap<String, Integer> fournisseurHmap = new HashMap<>();
        if (c != null && c.getCount() > 0) {
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    fournisseurHmap.put(c.getString(c.getColumnIndex(FournisseurDAO.COLUMN_NAME)),
                            c.getInt(c.getColumnIndex(FournisseurDAO.COLUMN_ID)));
                } while (c.moveToNext());
            }
        }
        daoFournisseur.close();
        String fss = selectionList();
        int idFss = fournisseurHmap.get(fss);
        Log.i("La clé est : ", "" + idFss);
        return idFss;
    }

    public void createProduct(View v) {

        String messageqte = "Quantity must be great tha 0";
        String messageDateExp = "Please fill the field Expiration Date ";
        String messageDateLiv = "Please fill the field Delivery Date ";
        String messageDescription = "Please fill the field Description ";
        String messageLibelle = "Please fill the field Product Label ";
        EditText produitLabel = (EditText) findViewById(R.id.txt_libelle_produit);
        EditText descriptionProduct = (EditText) findViewById(R.id.txt_description_product);
        EditText datelivraison = (EditText) findViewById(R.id.txt_date_livraison);
        EditText datePeremption = (EditText) findViewById(R.id.txt_date_expiration);
        EditText qty_seuil = (EditText) findViewById(R.id.txt_qte_seuil);
        String libelleProduit, descriptionProduit, dateDeLivraison, dateDePeremption;
        int qte_seuil;

        libelleProduit = produitLabel.getText().toString();
        descriptionProduit = descriptionProduct.getText().toString();
        dateDeLivraison = datelivraison.getText().toString();
        dateDePeremption = datePeremption.getText().toString();
        qte_seuil = Integer.parseInt(qty_seuil.getText().toString());

        if (!libelleProduit.equals("")) {
            if (!descriptionProduit.equals("")) {
                if (!dateDeLivraison.equals("")) {
                    if (dateDePeremption != "") {
                        if (qte_seuil > 0) {
                            int idFournisseur = recupIDFournisseur();
                            Produit p = new Produit(idFournisseur, libelleProduit, descriptionProduit, new Date(dateDeLivraison), new Date(dateDePeremption), qte_seuil);

                            creerProduit(p);

                            produitLabel.setText("");
                            descriptionProduct.setText("");
                            datelivraison.setText("");
                            datePeremption.setText("");
                            qty_seuil.setText("");
                        } else {
                            Toast.makeText(CreateProductActivity.this, messageqte, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CreateProductActivity.this, messageDateExp, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CreateProductActivity.this, messageDateLiv, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(CreateProductActivity.this, messageDescription, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(CreateProductActivity.this, messageLibelle, Toast.LENGTH_SHORT).show();
        }

    }

    public void cancle(View v) {
        Intent homeIntent = new Intent(CreateProductActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }

    public void cancel(View v) {
        Intent homeIntent = new Intent(CreateProductActivity.this, HomeActivity.class);
        startActivity(homeIntent);
    }
}
