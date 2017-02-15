package morgain.morgainprototype;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import morgain.morgainprototype.R;

public class QuestionsMenu extends Fragment {
    private MorgainFace mf;
    private Button purpose, helping, features, next;
    private Resources res;
    private Runnable r;
    private Handler h;
    private MainActivity m;

    public QuestionsMenu() {
        r = new Runnable() {
            @Override
            public void run() {
                mf.setSpriteChat(res.obtainTypedArray(R.array.questions_sprite_menu),
                        res.getStringArray(R.array.questions_dialog_menu), 10);
            }
        };
        h = new Handler();
    }
    public static QuestionsMenu newInstance(MorgainFace mf, MainActivity m) {
        QuestionsMenu fragment = new QuestionsMenu();
        fragment.setMorgainFace(mf);
        fragment.setMainActivity(m);
        return fragment;
    }
    public void setMorgainFace(MorgainFace mf) {
        this.mf = mf;
    }
    public void setMainActivity(MainActivity m) {
        this.m = m;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        res = getResources();
        purpose  = (Button) getView().findViewById(R.id.purpose);
        helping  = (Button) getView().findViewById(R.id.helping);
        features = (Button) getView().findViewById(R.id.features);
        next     = (Button) getView().findViewById(R.id.q_next);

        purpose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.questions_sprite_purpose),
                        res.getStringArray(R.array.questions_dialog_purpose), 11);
                h.postDelayed(r, mf.getTotalWait() + 1000);
            }
        });

        helping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.questions_sprite_helping),
                        res.getStringArray(R.array.questions_dialog_helping), 12);
                h.postDelayed(r, mf.getTotalWait() + 1000);
            }
        });

        features.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.questions_sprite_features),
                        res.getStringArray(R.array.questions_dialog_features), 13);
                h.postDelayed(r, mf.getTotalWait() + 1000);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.goToUserQuestions();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions_menu, container, false);
    }
}