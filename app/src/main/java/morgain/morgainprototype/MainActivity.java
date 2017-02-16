package morgain.morgainprototype;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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
        ud = new UserData(this.getApplicationContext());
    }

    public void setTotalWait(int totalWait) {
        this.totalWait = totalWait;
        changeUI();
    }

    public void changeUI() { //the entire changeUI block is so rediculously sloppy, please help it
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
        } else if (id == 20) {
            final Pets pt = Pets.newInstance(mf, m);
            r = new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_lower, pt).commit();
                }
            };
        } else if (id == 30) {
            final Hobbies hb = Hobbies.newInstance(mf, m);
            r = new Runnable() {
                @Override
                public void run() {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container_lower, hb).commit();
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
                .replace(R.id.fragment_container_lower, QuestionsMenu.newInstance(mf, m)).commit();
        mf.setSpriteChat(res.obtainTypedArray(R.array.questions_sprite_menu),
                res.getStringArray(R.array.questions_dialog_menu), 10);
        getSupportFragmentManager().executePendingTransactions();
    }
    public void goToUserQuestions() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_lower, Empty.newInstance()).commit();
        mf.setSpriteChat(res.obtainTypedArray(R.array.questions_sprite_start),
                res.getStringArray(R.array.questions_dialog_start), 20);
        getSupportFragmentManager().executePendingTransactions();
    }
    public void nextAfterPets() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_lower, Empty.newInstance()).commit();
        mf.setSpriteChat(res.obtainTypedArray(R.array.questions_sprite_after_pets),
                res.getStringArray(R.array.questions_dialog_after_pets), 30);
        getSupportFragmentManager().executePendingTransactions();
    }

    public void goHome() {
        startActivity(new Intent(this, HomeMenu.class));
    }

    public void onSpinnerSelect() {
        //TODO: everything
    }
    public void onTextTap(TextView t) {//this obviously doesnt work
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