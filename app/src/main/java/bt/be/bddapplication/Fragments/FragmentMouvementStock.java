package bt.be.bddapplication.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bt.be.bddapplication.R;

/**
 * Created by Rome03 on 12/07/2016.
 */
public class FragmentMouvementStock extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mouvement_stock_fragment,container,false);
    }
}
