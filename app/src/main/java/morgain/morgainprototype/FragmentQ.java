package morgain.morgainprototype;

import android.support.v4.app.Fragment;

public class FragmentQ extends Fragment implements MoodQ {
    private HomeMenu hm;

    public void setHomeMenu(HomeMenu hm) {
        this.hm = hm;
    }

    public HomeMenu getHomeMenu() {
        return hm;
    }
}
