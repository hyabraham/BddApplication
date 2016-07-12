package bt.be.bddapplication.Utilitaires;

import android.content.Context;
import android.database.Cursor;
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

    public void peuplageSpinnerProduit(){

        Spinner monSpinner = (Spinner)findViewById(R.id.spiner_fournisseur_produit);
        ProduitDAO daoProduit = new ProduitDAO(this);
        daoProduit.openWritable();
        Cursor c= daoProduit.getProduit();
        //cette boucle me petmet de recuperer les noms des founisseurs pour peupler mon spinner
        List<String> produit = new ArrayList<String>();
        do {
            produit.add(c.getString(c.getColumnIndex(ProduitDAO.COLUMN_LIBELLE)));
        } while (c.moveToNext());
        daoProduit.close();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, produit);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(dataAdapter);
    }

    public int recuperationIDFrigo(Context context){
        FrigoDAO daoFrigo = new FrigoDAO(context);
        daoFrigo.openWritable();
        Cursor c= daoFrigo.getFrigo();
        HashMap<String,Integer> fournisseurHmap=new HashMap<>();
        if(c.getCount() > 0) {
            c.moveToFirst();
            do {
                fournisseurHmap.put(c.getString(c.getColumnIndex(FrigoDAO.COLUMN_NOM_FRIGO)),
                        c.getInt(c.getColumnIndex(FrigoDAO.COLUMN_ID)));
            } while (c.moveToNext());
        }
        daoFrigo.close();
        String nomFrigo=selectionList();
        int idFrigo=fournisseurHmap.get(nomFrigo);
        return idFrigo;
    }
}
