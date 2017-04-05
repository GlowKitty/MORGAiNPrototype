package morgain.morgainprototype;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MoodTimeOfDay extends FragmentQ implements Dialogs {
    private UserData ud;
    private Mood mood;
    private final int ID = 1004;

    public MoodTimeOfDay() {
        // Required empty public constructor
    }
    public static MoodTimeOfDay newInstance() {
        MoodTimeOfDay fragment = new MoodTimeOfDay();
        return fragment;
    }
    private void init() {
        ud = UserData.instantiate(this.getContext());
        //ud = ud.loadData(this.getContext());
        mood = ud.getTodayMood();
    }

    public TypedArray getSprite() {
        return getResources().obtainTypedArray(R.array.mood_time_of_day_sprite);
    }
    public String[] getDialog() {
        return getResources().getStringArray(R.array.mood_time_of_day_dialog);
    }
    public int getID() {
        return ID;
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
        Button morning   = (Button) getView().findViewById(R.id.morning);
        Button afternoon = (Button) getView().findViewById(R.id.afternoon);
        Button evening   = (Button) getView().findViewById(R.id.evening);
        Button night     = (Button) getView().findViewById(R.id.night);

        morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(1);
            }
        });
        afternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(2);
            }
        });
        evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-1);
            }
        });
        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-2);
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
        return inflater.inflate(R.layout.fragment_mood_time_of_day, container, false);
    }

}
