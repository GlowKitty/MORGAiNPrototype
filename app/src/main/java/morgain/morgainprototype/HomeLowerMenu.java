package morgain.morgainprototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeLowerMenu extends Fragment {

    private HomeMenu hm;

    public HomeLowerMenu() {
        // Required empty public constructor
    }

    public static HomeLowerMenu newInstance() {
        HomeLowerMenu fragment = new HomeLowerMenu();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button trackMood = (Button) getView().findViewById(R.id.mood);
        trackMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hm.startMood();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_lower_menu, container, false);
    }

    public void setHomeMenu(HomeMenu hm) {
        this.hm = hm;
    }
}