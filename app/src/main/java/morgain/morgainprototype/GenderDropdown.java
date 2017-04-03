package morgain.morgainprototype;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GenderDropdown.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GenderDropdown#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GenderDropdown extends Fragment {
    private OnFragmentInteractionListener mListener;

    private Button b;
    private MainActivity m;
    private UserData ud;

    public GenderDropdown() {
        // Required empty public constructor
    }

    /** personal methods */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ud = new UserData(this.getContext());
        ud = ud.loadData();
        final Spinner spinner = (Spinner) getView().findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.genders, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        b = (Button)   getView().findViewById(R.id.g_next);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ud.setGender(spinner.getSelectedItemPosition());
                m.changemf3();
            }
        });
    }

    public void setMainActivity(MainActivity m) {
        this.m = m;
    }

    public static GenderDropdown newInstance() {
        GenderDropdown fragment = new GenderDropdown();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gender_dropdown, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSpinnerSelect();
    }
}
