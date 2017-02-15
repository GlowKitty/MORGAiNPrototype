package morgain.morgainprototype;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class Pets extends Fragment {
    private CheckBox dog, cat, bird, rodent, reptile, spider, fish, other, none;
    private MorgainFace mf;
    private Resources res;

    public Pets() {
        //empty default constructor
    }
    public static Pets newInstance(MorgainFace mf) {
        Pets fragment = new Pets();
        fragment.setMF(mf);
        return fragment;
    }

    private void setMF(MorgainFace mf) {
        this.mf = mf;//somehow, this is null. make this be less null
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        res = getResources();

        dog     = (CheckBox) getView().findViewById(R.id.dog);
        cat     = (CheckBox) getView().findViewById(R.id.cat);
        bird    = (CheckBox) getView().findViewById(R.id.bird);
        rodent  = (CheckBox) getView().findViewById(R.id.rodent);
        reptile = (CheckBox) getView().findViewById(R.id.reptile);
        spider  = (CheckBox) getView().findViewById(R.id.spider);
        fish    = (CheckBox) getView().findViewById(R.id.fish);
        other   = (CheckBox) getView().findViewById(R.id.other_pet);
        none    = (CheckBox) getView().findViewById(R.id.none_pet);

        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.dogs_sprite),
                        res.getStringArray(R.array.dogs_dialog), 21);
            }
        });
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.cats_sprite),
                        res.getStringArray(R.array.cats_dialog), 22);
            }
        });
        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.bird_sprite),
                        res.getStringArray(R.array.bird_dialog), 23);
            }
        });
        rodent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.rodent_sprite),
                        res.getStringArray(R.array.rodent_dialog), 24);
            }
        });
        reptile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.reptile_sprite),
                        res.getStringArray(R.array.reptile_dialog), 25);
            }
        });
        spider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.spider_sprite),
                        res.getStringArray(R.array.spider_dialog), 26);
            }
        });
        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.fish_sprite),
                        res.getStringArray(R.array.fish_dialog), 26);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.other_pet_sprite),
                        res.getStringArray(R.array.other_pet_dialog), 26);
            }
        });
        none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.setSpriteChat(res.obtainTypedArray(R.array.none_pet_sprite),
                        res.getStringArray(R.array.none_pet_dialog), 26);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pets, container, false);
    }
}