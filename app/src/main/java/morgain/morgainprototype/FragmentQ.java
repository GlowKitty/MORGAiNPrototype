package morgain.morgainprototype;

import android.content.res.TypedArray;
import android.support.v4.app.Fragment;

public class FragmentQ extends Fragment implements MoodQ, Dialogs {
    private HomeMenu hm;

    public void setHomeMenu(HomeMenu hm) {
        this.hm = hm;
    }

    public HomeMenu getHomeMenu() {
        return hm;
    }
    public TypedArray getSprite() {
        return getResources().obtainTypedArray(R.array.error_sprite);
    }
    public String[] getDialog() {
        return getResources().getStringArray(R.array.error_dialog);
    }
    public int getID() {
        return -1; //error value
    }
}
