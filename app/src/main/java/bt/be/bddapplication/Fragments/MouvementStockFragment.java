package bt.be.bddapplication.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.FrigoDAO;
import bt.be.bddapplication.db.ProduitDAO;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MouvementStockFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MouvementStockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MouvementStockFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_mouvement_stock, container, false);
        return inflater.inflate(R.layout.fragment_mouvement_stock,container,false);

    }




}
