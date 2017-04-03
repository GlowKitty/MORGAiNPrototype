package morgain.morgainprototype;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_home_upper, HomeMorgain.newInstance()).commit();
        HomeLowerMenu hlm = HomeLowerMenu.newInstance();
        hlm.setHomeMenu(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_home_lower, hlm).commit();

        TextView  t = (TextView)  findViewById(R.id.home_morgain_text);
        ImageView v = (ImageView) findViewById(R.id.home_morgain_face);

        SpriteChat sc = new SpriteChat(t, v); //unused, FOR NOW
    }

    public void startMood() {
        MoodQuestions mq = new MoodQuestions();
        FragmentQ f = (FragmentQ) mq.getNextQuestion();
        f.setHomeMenu(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_home_lower, f).commit();
    }

    public void nextMood() {
        MoodQuestions mq = new MoodQuestions();
        FragmentQ f = (FragmentQ) mq.getNextQuestion();
        f.setHomeMenu(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout_home_lower, f).commit();
    }

}