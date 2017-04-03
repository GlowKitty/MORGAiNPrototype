package morgain.morgainprototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MoodColor extends FragmentQ {
    private UserData ud;
    private Mood mood;
    //private HomeMenu hm;

    public MoodColor() {
        // Required empty public constructor
    }

    public static MoodColor newInstance() {
        MoodColor fragment = new MoodColor();
        fragment.init();
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
        Button red    = (Button) getView().findViewById(R.id.red);
        Button orange = (Button) getView().findViewById(R.id.orange);
        Button yellow = (Button) getView().findViewById(R.id.yellow);
        Button green  = (Button) getView().findViewById(R.id.green);
        Button blue   = (Button) getView().findViewById(R.id.blue);
        Button purple = (Button) getView().findViewById(R.id.purple);
        Button pink   = (Button) getView().findViewById(R.id.pink);
        Button white  = (Button) getView().findViewById(R.id.white);
        Button black  = (Button) getView().findViewById(R.id.black);

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-1);
            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(0.5);
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(1);
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(0.5);
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-2);
            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(0);
            }
        });
        pink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(1);
            }
        });
        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(0);
            }
        });
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-2);
            }
        });
    }
    private void modifyMood(double mod) {
        mood.modifyMood(mod);
        ud.setToday(mood);
        ud.saveData();//ud, getContext());
        getHomeMenu().nextMood();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_color, container, false);
    }
}