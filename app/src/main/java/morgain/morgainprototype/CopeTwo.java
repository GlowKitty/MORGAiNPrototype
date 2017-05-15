package morgain.morgainprototype;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CopeTwo extends Fragment {


    public CopeTwo() {
        // Required empty public constructor
    }
    public static CopeTwo newInstance() {
        CopeTwo fragment = new CopeTwo();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cope_two, container, false);
    }

}
