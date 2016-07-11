package bt.be.bddapplication.Controler;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.FournisseurDAO;
import bt.be.bddapplication.db.ProduitDAO;
import bt.be.bddapplication.model.Fournisseur;

public class CreateProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        Spinner monSpinner=(Spinner)findViewById(R.id.spiner_fournisseur_produit);

        //ProduitDAO dao =new ProduitDAO(this);
        FournisseurDAO daoFss= new FournisseurDAO(this);
        //dao.openWritable();
        daoFss.openWritable();
        Cursor c=daoFss.getFournisseur();
        HashMap<Integer,String> fournisseurHmap=new HashMap<>();
        fournisseurHmap.put(1, "blop");
       //Log.i("CUSOR_ID", "" + c.getLong(c.getColumnIndex(FournisseurDAO.COLUMN_ID)));
        //fournisseurHmap.put(c.getInt(c.getColumnIndex(FournisseurDAO.COLUMN_ID)), c.getString(c.getColumnIndex(FournisseurDAO.COLUMN_NAME)));

        //cette boucle me permet de lier l'id aux nom ainsi je pourrais utiliser cet id pour la creation du produit


        //cette boucle me petmet de recuperer les noms des rounisseurs pour peupler mon spinner
        List<String> fournisseur = new ArrayList<String>();
        do {
            fournisseur.add(c.getString(c.getColumnIndex(FournisseurDAO.COLUMN_NAME)));
        }while (c.moveToNext());
        /*if(c.getCount() > 0){
            c.moveToFirst();
            do {
                fournisseurHmap.put(c.getInt(c.getColumnIndex(FournisseurDAO.COLUMN_ID)),
                        c.getString(c.getColumnIndex(FournisseurDAO.COLUMN_NAME)));
            }while(c.moveToNext());
        }*/

        daoFss.close();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fournisseur);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(dataAdapter);
    }
}
