package morgain.morgainprototype;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MoodWeather extends FragmentQ implements Dialogs {
    private UserData ud;
    private Mood mood;
    private final int ID = 1005;

    public MoodWeather() {
        // Required empty public constructor
    }
    public static MoodWeather newInstance() {
        MoodWeather fragment = new MoodWeather();
        return fragment;
    }

    private void init() {
        ud = UserData.instantiate(this.getContext());
        //ud = ud.loadData(this.getContext());
        mood = ud.getTodayMood();
    }

    public TypedArray getSprite() {
        return getResources().obtainTypedArray(R.array.mood_weather_sprite);
    }
    public String[] getDialog() {
        return getResources().getStringArray(R.array.mood_weather_dialog);
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
        Button sunny   = (Button) getView().findViewById(R.id.sunny);
        Button rainy   = (Button) getView().findViewById(R.id.rainy);
        Button snowy   = (Button) getView().findViewById(R.id.snowy);
        Button thunder = (Button) getView().findViewById(R.id.thunder);
        Button extreme = (Button) getView().findViewById(R.id.extreme);

        sunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(2);
            }
        });
        rainy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-1);
            }
        });
        snowy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(0);
            }
        });
        thunder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-2);
            }
        });
        extreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-3);
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
        return inflater.inflate(R.layout.fragment_mood_weather, container, false);
    }

}
