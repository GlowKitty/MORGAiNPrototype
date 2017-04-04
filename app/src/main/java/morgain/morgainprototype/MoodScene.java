package morgain.morgainprototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MoodScene extends FragmentQ {
    private UserData ud;
    private Mood mood;

    public MoodScene() {
        // Required empty public constructor
    }
    public static MoodScene newInstance() {
        MoodScene fragment = new MoodScene();
        //fragment.init();
        return fragment;
    }
    private void init() {
        ud = new UserData(this.getContext());//MyApplication.getAppContext());//this.getContext());
        ud = ud.loadData(this.getContext());
        mood = ud.getTodayMood();
    }

    @Override
    public void setHomeMenu(HomeMenu hm) {
        super.setHomeMenu(hm);
    }
    @Override
    public HomeMenu getHomeMenu() {
        return super.getHomeMenu();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        Button nice   = (Button) getView().findViewById(R.id.nice);
        Button dark   = (Button) getView().findViewById(R.id.dark);
        Button magic  = (Button) getView().findViewById(R.id.magic);
        Button quiet  = (Button) getView().findViewById(R.id.quiet);
        Button normal = (Button) getView().findViewById(R.id.normal);

        nice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(2);
            }
        });
        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-2);
            }
        });
        magic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(1);
            }
        });
        quiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-1);
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(0);
            }
        });
    }
    private void modifyMood(double mod) {
        mood.modifyMood(mod);
        ud.setToday(mood);
        ud.saveData(ud, getContext());
        getHomeMenu().nextMood();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_scene, container, false);
    }

}
