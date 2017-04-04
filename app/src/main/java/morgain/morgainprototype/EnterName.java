package morgain.morgainprototype;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EnterName extends Fragment {
    private OnFragmentInteractionListener mListener;
    private EditText e;
    private Button b;
    private MainActivity m;

    public EnterName() {
        // Required empty public constructor
    }

    public void setMainActivity(MainActivity m) {
        this.m = m;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        e = (EditText) getView().findViewById(R.id.enter_text_name);
        b = (Button)   getView().findViewById(R.id.next);
        UserData ut = new UserData(getContext());
        final UserData ud = ut.loadData(getContext());
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ud.setName(e.getText().toString());
                //ud.saveData(ud, getBaseContext());
                ud.saveData(ud, getContext());
                m.changemf2();
            }
        });
    }

    public static EnterName newInstance() {
        EnterName fragment = new EnterName();
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
        return inflater.inflate(R.layout.fragment_enter_name, container, false);
    }

    /*// TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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
        void onEnterName();
    }
}
