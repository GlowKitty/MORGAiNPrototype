package morgain.morgainprototype;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

public class Hobbies extends Fragment {
    private CheckBox sports, art, music, cook, videoGame, tv, friends, other, none;
    private Button next;
    private MorgainFace mf;
    private MainActivity m;
    private Resources res;

    //TODO: save hobbies to UserData

    public Hobbies() {
        // Required empty public constructor
    }

    public static Hobbies newInstance(MorgainFace mf, MainActivity m) {
        Hobbies fragment = new Hobbies();
        fragment.setVar(mf, m);
        return fragment;
    }

    private void setVar(MorgainFace mf, MainActivity m) {
        this.mf = mf;
        this.m  = m;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        res = getResources();

        sports    = (CheckBox) getView().findViewById(R.id.sports);
        art       = (CheckBox) getView().findViewById(R.id.art);
        music     = (CheckBox) getView().findViewById(R.id.music);
        cook      = (CheckBox) getView().findViewById(R.id.cook);
        videoGame = (CheckBox) getView().findViewById(R.id.video_games);
        tv        = (CheckBox) getView().findViewById(R.id.tv);
        friends   = (CheckBox) getView().findViewById(R.id.friends);
        other     = (CheckBox) getView().findViewById(R.id.h_other);
        none      = (CheckBox) getView().findViewById(R.id.h_none);
        next      = (Button)   getView().findViewById(R.id.h_next);

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.sports_sprite),
                        res.getStringArray(R.array.sports_dialog), 31);
            }
        });
        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.art_sprite),
                        res.getStringArray(R.array.art_dialog), 32);
            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.music_sprite),
                        res.getStringArray(R.array.music_dialog), 33);
            }
        });
        cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.cook_sprite),
                        res.getStringArray(R.array.cook_dialog), 34);
            }
        });
        videoGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.video_games_sprite),
                        res.getStringArray(R.array.video_games_dialog), 35);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.tv_sprite),
                        res.getStringArray(R.array.tv_dialog), 36);
            }
        });
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.friends_sprite),
                        res.getStringArray(R.array.friends_dialog), 37);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.other_hobby_sprite),
                        res.getStringArray(R.array.other_hobby_dialog), 38);
            }
        });
        none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.none_hobby_sprite),
                        res.getStringArray(R.array.none_hobby_dialog), 39);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.goHome();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hobbies, container, false);
    }

}
