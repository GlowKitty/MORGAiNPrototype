package morgain.morgainprototype;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        resizeFrameLayout(R.id.layout_home_upper, 1.0 / 3.0);

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

    private void resizeFrameLayout(int id, double percentage) {
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        Float px = displayMetrics.heightPixels * (float) percentage;
        Log.i("HomeMenu", "upper menu height: " + px.intValue() + "dp, ratio: " + percentage);
        FrameLayout layout = (FrameLayout) findViewById(id);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
        params.height = px.intValue();
        layout.setLayoutParams(params);
    }

    public void viewMoodGraph() {
        resizeFrameLayout(R.id.layout_home_upper, 1.0 / 3.0);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_home_lower, MoodGraph.newInstance()).commit();
    }

    public void startMood() {
        resizeFrameLayout(R.id.layout_home_upper, 0.5);
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
            //e.printStackTrace();
            Log.i("HomeMenu", "End of questions loop");
            HomeLowerMenu hlm = HomeLowerMenu.newInstance();
            hlm.setHomeMenu(this);
            resizeFrameLayout(R.id.layout_home_upper, 1.0 / 3.0);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_home_upper,
                            HomeMorgain.newInstance(getApplicationContext()))
                    .replace(R.id.layout_home_lower, hlm).commit();

            ud = ud.loadData(getApplicationContext());
            ud.flushMood();
            ud.saveData(ud, getApplicationContext());
        }
    }

    public void backToHome() {
        HomeLowerMenu hlm = HomeLowerMenu.newInstance();
        hlm.setHomeMenu(this);
        resizeFrameLayout(R.id.layout_home_upper, 1.0 / 3.0);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_home_lower, hlm)
                .commit();
    }

    public void setTotalWait(int totalWait) {
        //todo: implement this if/when needed
    }
}