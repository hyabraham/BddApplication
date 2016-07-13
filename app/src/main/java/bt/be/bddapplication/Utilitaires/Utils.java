package bt.be.bddapplication.Utilitaires;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.FrigoDAO;
import bt.be.bddapplication.db.ProduitDAO;

/**
 * Created by Rome03 on 12/07/2016.
 */
public class Utils {

//    public void peuplageSpinnerProduit(View v){
//
//        Spinner monSpinner = (Spinner)v.findViewById(R.id.spiner_fournisseur_produit);
//        ProduitDAO daoProduit = new ProduitDAO(this);
//        daoProduit.openWritable();
//        Cursor c= daoProduit.getProduit();
//        //cette boucle me petmet de recuperer les noms des founisseurs pour peupler mon spinner
//        List<String> produit = new ArrayList<String>();
//        do {
//            produit.add(c.getString(c.getColumnIndex(ProduitDAO.COLUMN_LIBELLE)));
//        } while (c.moveToNext());
//        daoProduit.close();
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, produit);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        monSpinner.setAdapter(dataAdapter);
//    }


}
