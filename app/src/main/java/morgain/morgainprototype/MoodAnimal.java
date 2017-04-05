package morgain.morgainprototype;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MoodAnimal extends FragmentQ implements Dialogs {
    private UserData ud;
    private Mood mood;
    private final int ID = 1001;

    public MoodAnimal() {
        // Required empty public constructor
    }
    public static MoodAnimal newInstance() {
        MoodAnimal fragment = new MoodAnimal();
        return fragment;
    }
    private void init() {
        ud = UserData.instantiate(getContext());
        //ud = ud.loadData(this.getContext());
        mood = ud.getTodayMood();
    }

    public TypedArray getSprite() {
        return getResources().obtainTypedArray(R.array.mood_animal_sprite);
    }
    public String[] getDialog() {
        return getResources().getStringArray(R.array.mood_animal_dialog);
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
        Button cat   = (Button) getView().findViewById(R.id.cat);
        Button dog   = (Button) getView().findViewById(R.id.dog);
        Button tiger = (Button) getView().findViewById(R.id.tiger);
        Button sloth = (Button) getView().findViewById(R.id.sloth);
        Button frog  = (Button) getView().findViewById(R.id.frog);
        Button fish  = (Button) getView().findViewById(R.id.fish);
        Button bear  = (Button) getView().findViewById(R.id.bear);
        Button bird  = (Button) getView().findViewById(R.id.bird);

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(1);
            }
        });
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(2);
            }
        });
        tiger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-0.5);
            }
        });
        sloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-2);
            }
        });
        frog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(0.5);
            }
        });
        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(0);
            }
        });
        bear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(-1);
            }
        });
        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyMood(2);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mood_animal, container, false);
    }

}
