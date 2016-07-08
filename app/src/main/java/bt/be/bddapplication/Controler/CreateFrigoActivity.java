package bt.be.bddapplication.Controler;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.GestionnaireDAO;

public class CreateFrigoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_frigo);

        Spinner monSpinner=(Spinner)findViewById(R.id.spiner_gestionnaire_frigo);

        //  Gestionnaire f = new Gestionnaire(nom, mailProvider,adressProvider);
        GestionnaireDAO dao =new GestionnaireDAO(this);
        dao.openWritable();
        Cursor c =dao.getGestionaire();
        List<String> gestionnaires = new ArrayList<String>();
        do {
            gestionnaires.add(c.getString(c.getColumnIndex(GestionnaireDAO.COLUMN_LAST_NAME))
                    + " "+ c.getString(c.getColumnIndex(GestionnaireDAO.COLUMN_FIRST_NAME)));
        }while (c.moveToNext());
        dao.close();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gestionnaires);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(dataAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        Spinner monSpinner=(Spinner)findViewById(R.id.spiner_gestionnaire_frigo);
        monSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                Log.i("la selection est : ", item);
            }
        });
        //String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
}
