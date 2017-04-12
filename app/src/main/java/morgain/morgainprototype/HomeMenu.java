package morgain.morgainprototype;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeMenu extends AppCompatActivity {
    private int totalWait;
    private MoodQuestions mq;
    private MorgainFace mf;
    private UserData ud;
    private SpriteChat sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        ud = UserData.instantiate(getApplicationContext());

        TextView  t = (TextView)  findViewById(R.id.home_morgain_text);
        ImageView v = (ImageView) findViewById(R.id.home_morgain_face);
        sc = new SpriteChat(getApplicationContext(), t, v);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_home_upper, HomeMorgain.newInstance(getApplicationContext()))
                .commit();

        HomeLowerMenu hlm = HomeLowerMenu.newInstance();
        hlm.setHomeMenu(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_home_lower, hlm).commit();
    }

    public void runFirstTime() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void viewMoodGraph() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_home_lower, MoodGraph.newInstance()).commit();
    }

    public void startMood() {
        mq = new MoodQuestions(getApplicationContext());
        FragmentQ f = (FragmentQ) mq.getNextQuestion();
        f.setHomeMenu(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_home_lower, f).commitNow();

        mf = MorgainFace.newInstance(f.getSprite(), f.getDialog(), f.getID());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_home_upper, mf).commit();
    }

    public void nextMood() {
        //todo: create proper end of question management, using NullPointerException is sloppy
        try {
            FragmentQ f = (FragmentQ) mq.getNextQuestion();
            f.setHomeMenu(this);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_home_lower, f).commitNow();

            mf.setSpriteChat(f.getSprite(), f.getDialog(), f.getID());
        } catch (NullPointerException e) {
            e.printStackTrace();

            HomeLowerMenu hlm = HomeLowerMenu.newInstance();
            hlm.setHomeMenu(this);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_home_upper,
                            HomeMorgain.newInstance(getApplicationContext()))
                    .replace(R.id.layout_home_lower, hlm).commit();
            ud = ud.loadData(getApplicationContext());
            ud.flushMood();
        }
    }

    public void setTotalWait(int totalWait) {
        //todo: implement this if/when needed
    }
}