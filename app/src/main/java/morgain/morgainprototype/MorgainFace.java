package morgain.morgainprototype;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MorgainFace.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MorgainFace#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MorgainFace extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //my variables
    private SpriteChat sc;
    private TextView t;
    private ImageView v;
    private static int[] spriteSequence;
    private static String[] dialogSequence;
    private MainActivity m;

    public MorgainFace() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment morgain.
     */
    // TODO: Rename and change types and number of parameters
    public static MorgainFace newInstance(int[] param1, String[] param2) {
        MorgainFace fragment = new MorgainFace();
        Bundle args = new Bundle();

        spriteSequence = param1;
        dialogSequence = param2;

        fragment.setArguments(args);
        return fragment;
    }

    /** personal methods */
    public void skip() {
        sc.skip();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        t = (TextView)  getView().findViewById(R.id.textView);
        v = (ImageView) getView().findViewById(R.id.imageView);
        sc = new SpriteChat(t, v, spriteSequence, dialogSequence);
        sc.setMainActivity(m);
        sc.startChat();
    }
    public void setMainActivity(MainActivity m) {
        this.m = m;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_morgain, container, false);
        return view;//inflater.inflate(R.layout.fragment_morgain, container, false);
    }

    public void onButtonPressed(TextView t) {
        if (mListener != null) {
            mListener.onTextTap(t);
        }
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
        void onTextTap(TextView t);
    }
}
