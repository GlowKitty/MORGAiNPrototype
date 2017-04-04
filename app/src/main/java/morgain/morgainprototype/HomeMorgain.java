package morgain.morgainprototype;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeMorgain extends Fragment {
    private SpriteChat sc;
    private ImageView v;
    private TextView t;
    private Resources res;

    public HomeMorgain() {
        // Required empty public constructor
    }

    public static HomeMorgain newInstance() {
        HomeMorgain fragment = new HomeMorgain();

        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        res = getResources();
        v = (ImageView) getView().findViewById(R.id.home_morgain_face);
        t = (TextView)  getView().findViewById(R.id.home_morgain_text);
        sc = new SpriteChat(getContext(), t, v); //!!! set resources !!!
        sc.setResources(res.obtainTypedArray(R.array.home_default_sprite),
                res.getStringArray(R.array.home_default_dialog), 100);
        sc.startChat();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_morgain, container, false);
    }
}