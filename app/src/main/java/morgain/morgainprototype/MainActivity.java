package morgain.morgainprototype;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        MorgainFace.OnFragmentInteractionListener, GenderDropdown.OnFragmentInteractionListener,
        EnterName.OnFragmentInteractionListener {
    private int totalWait;
    private MorgainFace mf;
    private UserData ud;
    private Resources res;
    private MainActivity m = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = getResources();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            mf = MorgainFace.newInstance(res.obtainTypedArray(R.array.sprite_sequence_1),
                    res.getStringArray(R.array.dialog_sequence_1), 1);
            mf.setMainActivity(m);//feels sloppy

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mf)
                    .commit();
            getSupportFragmentManager().executePendingTransactions();
        }
        ud = new UserData();
    }

    public void setTotalWait(int totalWait) {
        this.totalWait = totalWait;
        changeUI();
    }
    public void changeUI() {//make this so it can change dynamically, how to though?
        Handler h = new Handler();
        final MainActivity m = this;
        final EnterName en = EnterName.newInstance();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_lower, en).commit();
                en.setMainActivity(m);
                getSupportFragmentManager().executePendingTransactions();
            }
        };
        h.postDelayed(r, totalWait);
    }
    public void changeUI(int totalWait, int id) {
        Handler h = new Handler();
        final MainActivity m = this;
        Runnable r;
        if (id == 1) {
            final EnterName en = EnterName.newInstance();
            r = new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment_container_lower, en).commit();
                    en.setMainActivity(m);
                    getSupportFragmentManager().executePendingTransactions();
                }
            };
        } else if (id == 2) {
            final GenderDropdown gd = GenderDropdown.newInstance();
            gd.setMainActivity(m);
            r = new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_lower, gd).commit();
                }
            };
        } else if (id == 3) {
            final SkipOrNext sn = SkipOrNext.newInstance(m);
            r = new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_lower, sn).commit();
                }
            };
        } else {
            final Empty em = Empty.newInstance();
            r = new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment_container_lower, em).commit();
                }
            };
        }
        h.postDelayed(r, totalWait);
    }

    public void changemf2() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_lower, Empty.newInstance()).commit();
        mf.setSpriteChat(res.obtainTypedArray(R.array.sprite_sequence_2),
                res.getStringArray(R.array.dialog_sequence_2), 2);
        getSupportFragmentManager().executePendingTransactions();
    }
    public void changemf3() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_lower, Empty.newInstance()).commit();
        mf.setSpriteChat(res.obtainTypedArray(R.array.sprite_sequence_3),
                res.getStringArray(R.array.dialog_sequence_3), 3);
        getSupportFragmentManager().executePendingTransactions();
    }

    public void goToQuestions() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_lower, QuestionsMenu.newInstance(mf)).commit();
        mf.setSpriteChat(res.obtainTypedArray(R.array.questions_sprite_menu),
                res.getStringArray(R.array.questions_dialog_menu), 10);
        getSupportFragmentManager().executePendingTransactions();
    }

    public void onSpinnerSelect() {
        //TODO: everything
    }

    public void onTextTap(TextView t) {
        MorgainFace mf = (MorgainFace) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container_lower);
        if (mf != null) {
            mf.skip();
        }
    }
    public void onEnterName() {
        //TODO: everything
    }
}