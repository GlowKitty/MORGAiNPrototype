package morgain.morgainprototype;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MorgainFace extends Fragment {
    private OnFragmentInteractionListener mListener;

    //my variables
    private SpriteChat sc;
    private TextView t;
    private ImageView v;
    private static TypedArray spriteSequence;
    private static String[] dialogSequence;
    private MainActivity m;
    private int totalWaitTime;
    private static int id;
    private boolean isOnMain = false;

    public MorgainFace() {
        // Required empty public constructor
    }

    public static MorgainFace newInstance(TypedArray sprite, String[] dialog, int tmpId) {
        MorgainFace fragment = new MorgainFace();
        spriteSequence = sprite;
        dialogSequence = dialog;
        id = tmpId;
        return fragment;
    }

    /** personal methods */
    public void skip() { //todo: make this work
        sc.skip();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        t = (TextView)  getView().findViewById(R.id.textView);
        v = (ImageView) getView().findViewById(R.id.imageView);
        sc = new SpriteChat(getContext(), t, v, spriteSequence, dialogSequence);
        if (isOnMain){
            sc.setMainActivity(m);
        }
        sc.startChat();
    }

    public void setSpriteChat(TypedArray spriteSequence, String[] dialogSequence, int id) {
        sc.setResources(spriteSequence, dialogSequence, id);
        sc.startChat();
    }

    public void setMainActivity(MainActivity m) {
        isOnMain = true;
        this.m = m;
    }
    public int getTotalWait() {
        return sc.getTotalWait();
    }

    @Override
    public void onStart() {
        super.onStart();
        totalWaitTime = sc.getTotalWait();
        if (isOnMain) {
            m.setTotalWait(totalWaitTime);
            m.changeUI();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_morgain, container, false);
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
            //throw new RuntimeException(context.toString()
            //        + " must implement OnFragmentInteractionListener");
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
