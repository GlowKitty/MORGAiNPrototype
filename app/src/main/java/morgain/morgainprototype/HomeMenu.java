package morgain.morgainprototype;

import android.content.Context;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        UserData ud = UserData.instantiate(getApplicationContext());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_home_upper, HomeMorgain.newInstance()).commit();
        HomeLowerMenu hlm = HomeLowerMenu.newInstance();
        hlm.setHomeMenu(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_home_lower, hlm).commit();

        /*TextView  t = (TextView)  findViewById(R.id.home_morgain_text);
        ImageView v = (ImageView) findViewById(R.id.home_morgain_face);
        SpriteChat sc = new SpriteChat(getApplicationContext(), t, v); //unused, FOR NOW*/

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
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_home_upper, HomeMorgain.newInstance())
                    .replace(R.id.layout_home_lower, HomeLowerMenu.newInstance()).commit();
        }
    }

    public void setTotalWait(int totalWait) {
        //todo: implement this
    }
}