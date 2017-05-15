package morgain.morgainprototype;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeLowerMenu extends Fragment {

    private HomeMenu hm;

    public HomeLowerMenu() {
        // Required empty public constructor
    }

    public static HomeLowerMenu newInstance() {
        HomeLowerMenu fragment = new HomeLowerMenu();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button trackMood = (Button) getView().findViewById(R.id.mood);
        Button firstRun  = (Button) getView().findViewById(R.id.first_run);
        Button viewMood  = (Button) getView().findViewById(R.id.view_mood);
        Button music     = (Button) getView().findViewById(R.id.music);
        Button cope      = (Button) getView().findViewById(R.id.cope);
        trackMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hm.startMood();
            }
        });
        firstRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hm.runFirstTime();
            }
        });
        viewMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hm.viewMoodGraph();
            }
        });
        music.setOnClickListener(new View.OnClickListener() { //TODO: add more moods and music
            @Override
            public void onClick(View v) {
                if (appInstalledOrNot("com.spotify.music")) {
                    UserData ud = UserData.instantiate(getContext());
                    Mood mood = ud.getMood();
                    if (mood.getMoodValue() > 0) {
                        final Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("spotify:user:morgainproject:playlist:4zQ89z0g2akAK2GND6PFOW"));
                        startActivity(intent);
                    } else if (mood.getMoodValue() <= 0) {
                        final Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("spotify:user:morgainproject:playlist:6gZ9BdbT4ViUeVi6TbYzIW"));
                        startActivity(intent);
                    }
                } else {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=com.spotify.music")));
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        cope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hm.goToCope();
            }
        });
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getContext().getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_lower_menu, container, false);
    }

    public void setHomeMenu(HomeMenu hm) {
        this.hm = hm;
    }
}